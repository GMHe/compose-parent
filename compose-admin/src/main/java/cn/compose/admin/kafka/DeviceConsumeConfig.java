package cn.compose.admin.kafka;

import cn.compose.admin.biz.SourceBiz;
import cn.compose.admin.entity.Source;
import cn.compose.admin.kafka.consumer.DeviceConsume;
import com.contive.plugin.mq.consumer.Consumer;
import com.contive.plugin.mq.consumer.config.KafkaConsumeConfig;
import com.contive.plugin.mq.consumer.config.ServerInfo;
import com.contive.plugin.mq.serialization.StringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hgm
 * @date 2021/10/16
 */
@Slf4j
@Component
public class DeviceConsumeConfig {

    private static final String CONSUME_GROUP = "GROUP-COMPOSE_";

    private Map<Long, Consumer> consumers = new ConcurrentHashMap<>();
    private EnvConfig envConfig;
    private final SourceBiz sourceBiz;

    public DeviceConsumeConfig(EnvConfig envConfig, SourceBiz sourceBiz) {
        this.envConfig = envConfig;
        this.sourceBiz = sourceBiz;
    }

    @PostConstruct
    public void init() {
        checkInterval();
    }

    /**
     * 定时检测所有配置监控的数据源
     */
    @Scheduled(initialDelay = 5 * 60 * 1000, fixedDelay = 30 * 1000)
    public void checkInterval() {

        List<Source> sourceList = sourceBiz.getSources();
        if (CollectionUtils.isEmpty(sourceList)) {
            log.error("定时初始化监控配置有误,档案定义缺失！");
            return;
        }
        // 获取所有监控配置
        List<SourceInfo> sources = new ArrayList<>();
        for (int i = 0; i < sourceList.size(); i++) {
            Source source = sourceList.get(i);
            SourceInfo sourceInfo = new SourceInfo();
            sourceInfo.setId(source.getId());
            sourceInfo.setSign(source.getSign());
            sourceInfo.setPartitionNumber(source.getPartitionNumber());
            sourceInfo.setReplicationFactor(source.getReplicationFactor());
            sources.add(sourceInfo);
        }
        // 遍历并开启消费者
        sources.stream()
                .filter(source -> !consumers.containsKey(source.getId()))
                .forEach(source -> startConsumer(source));

        // 关闭禁用的配置
        for (Iterator<Map.Entry<Long, Consumer>> it = consumers.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Long, Consumer> item = it.next();

            if (sources.stream()
                    .noneMatch(source -> source.getId().equals(item.getKey()))) {
                stopConsumer(item.getKey(), item.getValue());
                it.remove();
            }
        }
    }

    private void stopConsumer(Long sourceId, Consumer consumer) {
        consumer.shutdown();
        log.info("关闭消费，sourceId={}", sourceId);
    }

    private void startConsumer(SourceInfo source) {
        KafkaConsumeConfig<String> consumeConfig = buildKafkaConsumeConfig(source);
        try {
            Consumer consumer = new Consumer(consumeConfig, 200, new DeviceConsume(source, envConfig, sourceBiz));
            consumer.start();
            log.info("开启消费：{}", source.getSign());
            consumers.put(source.getId(), consumer);
        } catch (Exception e) {
            log.warn("开启消费异常【跳过初始化】：{}", source.getSign(), e);
        }
    }

    private KafkaConsumeConfig buildKafkaConsumeConfig(Source source) {
        KafkaConsumeConfig consumeConfig = new KafkaConsumeConfig<>();
        consumeConfig.setAppName(envConfig.getApplicationName());
        consumeConfig.setConsumeGroup(CONSUME_GROUP + source.getSign());
        consumeConfig.setReplicationFactor(source.getReplicationFactor().shortValue());
        consumeConfig.setNumPartitions(source.getPartitionNumber());
        consumeConfig.setRetryTimes(3);
        consumeConfig.setSerializer(new StringSerializer());
        consumeConfig.setServerInfo(Collections.singletonList(new ServerInfo(envConfig.getKafkaAddress(), StringUtils.isEmpty(envConfig.getKafkaUserName())
                ? null : envConfig.getKafkaUserName(), StringUtils.isEmpty(envConfig.getKafkaPass()) ? null : envConfig.getKafkaPass(), Collections.singletonList(source.getSign()))));
        consumeConfig.setSkipNull(true);
        consumeConfig.setAutoAck(true);
        consumeConfig.setConsumerNum((byte) envConfig.getConsumerNum());
        consumeConfig.setFirstConsumePosition("latest");
        consumeConfig.setMaxWaitMillis(200);
        consumeConfig.setPrefetchCount(10000);
        consumeConfig.setSessionTimeoutMillis(60000);
        consumeConfig.setHeartbeatMillis(1000);
        return consumeConfig;
    }

    @PreDestroy
    public void destroy() {
        consumers.values().forEach(Consumer::shutdown);
    }
}
