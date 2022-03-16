package cn.compose.admin.kafka;

import cn.compose.admin.biz.SourceBiz;
import cn.compose.admin.entity.Source;
import cn.compose.admin.kafka.publisher.DevicePublisher;
import com.contive.plugin.mq.producer.Publisher;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author hgm
 * @date 2021/10/16
 */
@Slf4j
@Service
public class KafkaDeviceServiceImpl implements IKafkaDeviceService {

    private final SourceBiz sourceBiz;
    private final DevicePublisher devicePublisher;

    public KafkaDeviceServiceImpl(SourceBiz sourceBiz, DevicePublisher devicePublisher) {
        this.sourceBiz = sourceBiz;
        this.devicePublisher = devicePublisher;
    }

    private final LoadingCache<String, List<Source>> SOURCE_CACHE = Caffeine.newBuilder()
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .build(key -> getSources());
    private Map<String, Source> sourceMap = new ConcurrentHashMap<>();

    @Override
    public void report(List<Map<String, Object>> datas, String sign) {
        if (CollectionUtils.isEmpty(datas)) {
            return;
        }
        Source source = sourceMap.get(sign);
        if (source == null) {
            log.error("上报失败，找不到唯一标识sign:{}", sign);
        }

        // 写入kafka
        Publisher publisher = devicePublisher.getPublisher(sign);
        if (publisher == null) {
            return;
        }

        datas.forEach(data -> {
            String hashKey = (String) data.get("TABLE_CODE");
            publisher.publish(hashKey, GsonUtil.toJson(data));
        });
    }


    private List<Source> getSources() {
        List<Source> sourceList = sourceBiz.getSources();
        if (!CollectionUtils.isEmpty(sourceList)) {
            for (int i = 0; i < sourceList.size(); i++) {
                Source source = sourceList.get(i);
                sourceMap.put(source.getSign(), source);
            }
        }
        return sourceList;
    }
}
