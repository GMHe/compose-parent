package cn.compose.admin.kafka;

import java.util.List;
import java.util.Map;

/**
 * @author hgm
 * @date 2021/10/16
 */
public interface IKafkaDeviceService {

    /**
     * 统计数据上报
     *
     * @param datas 数据体
     * @param sign  数据源唯一标识（目前对应kafka队列topic）
     */
    void report(List<Map<String, Object>> datas, String sign);

}
