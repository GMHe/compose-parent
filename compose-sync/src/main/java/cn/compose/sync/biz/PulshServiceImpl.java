package cn.compose.sync.biz;

import cn.compose.sync.entity.Source;
import cn.compose.sync.kafka.GsonUtil;
import cn.compose.sync.kafka.publisher.DevicePublisher;
import com.contive.plugin.mq.producer.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author hgm
 * @date 2020/11/16
 */
@Slf4j
@Service
public class PulshServiceImpl {

    private final SourceBiz sourceBiz;
    private final DevicePublisher devicePublisher;

    public PulshServiceImpl(SourceBiz sourceBiz, DevicePublisher devicePublisher) {
        this.sourceBiz = sourceBiz;
        this.devicePublisher = devicePublisher;
    }

    public void report(List<Map<String, Object>> datas, String sign) {
        if (CollectionUtils.isEmpty(datas)) {
            return;
        }
        List<Source> sourceList = sourceBiz.getSourcesBySign(sign);
        if (CollectionUtils.isEmpty(sourceList)) {
            log.error("sign:{} 未找到",sign);
        }

        // 写入kafka
        Publisher publisher = devicePublisher.getPublisher(sign);
        if (publisher == null) {
            return;
        }

        Source source=sourceList.get(0);
        datas.forEach(data -> {
            String hashKey = (String) data.get(source.getHashKey());
            publisher.publish(hashKey, GsonUtil.toJson(data));
        });
    }
}
