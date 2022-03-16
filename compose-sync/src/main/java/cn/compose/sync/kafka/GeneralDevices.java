package cn.compose.sync.kafka;

import java.util.HashMap;

/**
 * @author hgm
 * @date 2021/10/16
 */
public class GeneralDevices extends HashMap<String, Object> {

    public long getTimestamp() {
        return Double.valueOf(get("timestamp").toString()).longValue();
    }
}
