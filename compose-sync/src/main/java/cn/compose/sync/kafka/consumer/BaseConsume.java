package cn.compose.sync.kafka.consumer;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.Map;

/**
 * @author hgm
 * @date 2021/10/15
 */
public abstract class BaseConsume {

    /**
     * 处理数据体每个数据类型映射（由于上报数据可能数据类型都是字符串、这里可以强转成对应类型）
     *
     * @param data
     * @param keyMappingMap
     */
    public void processKeyType(Map<String, Object> data, Map<String, String> keyMappingMap) {
        data.entrySet().forEach(entry -> {
            if(entry.getValue() == null) {
                return;
            }
            String fieldType = null;
            if(JSONObject.class.equals(entry.getValue().getClass()) || JSONArray.class.equals(entry.getValue().getClass())) {
                fieldType = "String";
            } else if (keyMappingMap != null) {
                fieldType = keyMappingMap.get(entry.getKey());
            }
            if (fieldType == null) {
                return;
            }
            switch (fieldType) {
                case "Long":
                    entry.setValue(Double.valueOf(entry.getValue().toString()).longValue());
                    break;
                case "Double":
                    entry.setValue(Double.valueOf(entry.getValue().toString()));
                    break;
                case "String":
                    entry.setValue(String.valueOf(entry.getValue()));
                    break;
                case "Integer":
                    entry.setValue(Integer.valueOf(entry.getValue().toString()));
                case "Unicode":
                    entry.setValue(StringEscapeUtils.unescapeJava(String.valueOf(entry.getValue())));
                default:
                    break;
            }
        });
    }
}
