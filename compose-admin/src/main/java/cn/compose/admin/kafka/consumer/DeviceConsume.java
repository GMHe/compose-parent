package cn.compose.admin.kafka.consumer;

import cn.compose.admin.biz.SourceBiz;
import cn.compose.admin.kafka.EnvConfig;
import cn.compose.admin.kafka.SourceInfo;
import cn.compose.admin.utils.RateLimit;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.contive.plugin.mq.consumer.api.IBatchConsume;
import com.contive.plugin.mq.consumer.api.PartitionRebalanceListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 档案数据同步消费
 *
 * @author hgm
 * @date 2020/11/16
 */
@Slf4j
public class DeviceConsume extends BaseConsume implements IBatchConsume<String>, PartitionRebalanceListener {

    protected static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(8);
    private static final RateLimit RATE_LIMIT = RateLimit.create(3);
    private static final RateLimit ERROR_RATE_LIMIT = RateLimit.create(5);
    private final SourceInfo source;
    private final EnvConfig envConfig;
    private Class modelClass = null;
    private SourceBiz sourceBiz;
    private String measurement;

    public DeviceConsume(SourceInfo source, EnvConfig envConfig,
                         SourceBiz sourceBiz) {
        this.source = source;
        this.envConfig = envConfig;
        this.sourceBiz = sourceBiz;
    }

    @Override
    public List<String> consume(List<String> beans) {
        if (CollectionUtils.isEmpty(beans)) {
            return null;
        }

        try {
            // 转换数据
            List<Map<String, Object>> generalDevices = beans.stream()
                    .map(it -> {
                        Map<String, Object> devices = JSONObject.parseObject(it, Map.class);
                        return devices;
                    }).collect(Collectors.toList());

            log.info(JSON.toJSONString(generalDevices));
            // 批量上报设备数据
            try {
                saveDevice(source.getSign(), generalDevices);
            } catch (Exception e) {
                ERROR_RATE_LIMIT.tryApply(() -> log.error("saveDevice error", e));
            }

        } catch (Exception e) {
            RATE_LIMIT.tryApply(() -> log.error("consume error. first data：{}", beans.get(0), e));
        }
        return null;
    }

    private void saveDevice(String tableCode, List<Map<String, Object>> deviceData) {
        if (CollectionUtils.isEmpty(deviceData)) {
            return;
        }

        log.info("消费处理数据。。。。:{}", JSON.toJSONString(deviceData));
    }

}
