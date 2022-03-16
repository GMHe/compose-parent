package cn.compose.sync.kafka.publisher;

import cn.compose.sync.biz.SourceBiz;
import cn.compose.sync.entity.Source;
import cn.compose.sync.kafka.EnvConfig;
import cn.compose.sync.kafka.SourceInfo;
import com.contive.plugin.mq.producer.Publisher;
import com.contive.plugin.mq.producer.config.KafkaPublishConfig;
import com.contive.plugin.mq.serialization.StringSerializer;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hgm
 * @date 2021/10/16
 */
@Slf4j
@Component
public class DevicePublisher {
    private final EnvConfig envConfig;
    private final LoadingCache<String, Optional<Publisher>> cachePublisher;
    private final SourceBiz sourceBiz;

    private final Map<String, Source> sourceMap=new ConcurrentHashMap<>();

    private ExecutorService executor = new ScheduledThreadPoolExecutor(100,
            new BasicThreadFactory.Builder().namingPattern("example-device-schedule-pool-%d").daemon(true).build());

    //private ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(10);

    public DevicePublisher(EnvConfig envConfig, ScheduledExecutorService cacheScheduledExecutor,
                           SourceBiz sourceBiz) {
        this.envConfig = envConfig;
        this.sourceBiz = sourceBiz;
        this.cachePublisher = Caffeine.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .scheduler(Scheduler.forScheduledExecutorService(cacheScheduledExecutor))
                .removalListener((key, value, cause) -> {
                    log.info("长时间不发布数据，关闭Publish：" + key);
                    ((Optional<Publisher>) value).get().shutdown();
                }).build(key -> {
                    Source source = sourceMap.get(key);
                    if (source == null) {
                        return Optional.ofNullable(null);
                    }
                    return Optional.ofNullable(initPublisher(source));

                });
    }

    public Publisher getPublisher(String sign) {
        return cachePublisher.get(sign).orElse(null);
    }

    public Publisher initPublisher(Source source) {
        KafkaPublishConfig config = new KafkaPublishConfig();
        config.setAppName(envConfig.getApplicationName());
        config.setServerAddress(envConfig.getKafkaAddress());
        config.setUserName(envConfig.getKafkaUserName());
        config.setPass(envConfig.getKafkaPass());
        config.setTopic(source.getSign());
        config.setReplicationFactor(source.getReplicationFactor().shortValue());
        config.setNumPartitions(source.getPartitionNumber());
        config.setSerializer(new StringSerializer());
        config.setSendAsync(true);
        config.setCompressionType("lz4");
        config.setLingerMillis(500);
        return new Publisher(config);
    }

    @PreDestroy
    public void destroy() {
        cachePublisher.asMap().values().forEach(value -> value.get().shutdown());
    }

    private List<SourceInfo> getSources(){
        List<Source> sourceList=sourceBiz.getSources();
        // 获取所有监控配置
        List<SourceInfo> sources = new ArrayList<>();
        for (int i = 0; i <sourceList.size() ; i++) {
            Source source=sourceList.get(i);
            SourceInfo sourceInfo=new SourceInfo();
            sourceInfo.setId(source.getId());
            sourceInfo.setSign(source.getSign());
            sourceInfo.setPartitionNumber(source.getPartitionNumber());
            sourceInfo.setReplicationFactor(source.getReplicationFactor());
            sources.add(sourceInfo);
            sourceMap.put(source.getSign(),sourceInfo);
        }
        return sources;
    }

    @PostConstruct
    public void init() {
        getSources();
    }

}
