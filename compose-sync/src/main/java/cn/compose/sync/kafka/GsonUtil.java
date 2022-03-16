package cn.compose.sync.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class GsonUtil {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(new TypeToken<Map<String, Object>>() {
            }.getType(), new MapDeserializerDoubleAsIntFix())
            .create();

    /**
     * 将Object对象转换成json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * 将json字符串转换成对象
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T toObject(String jsonString, Class<T> cls) {
        return gson.fromJson(jsonString, cls);
    }

    /**
     * 将json字符串转换成带泛型的对象
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T toObject(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    /**
     * json字符串装换成带泛型的list
     *
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = (List) gson.fromJson(gsonString, (new TypeToken<List<T>>() {
            }).getType());
        }
        return list;
    }

    /**
     * json字符串装换成带泛型的list且list中是map集合
     *
     * @param gsonString
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> toListMap(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = (List) gson.fromJson(gsonString, (new TypeToken<List<Map<String, T>>>() {
            }).getType());
        }

        return list;
    }

    /**
     * Json字符串转map
     *
     * @param gsonString
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> toMap(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = (Map) gson.fromJson(gsonString, (new TypeToken<Map<String, T>>() {
            }).getType());
        }
        return map;
    }


    static class MapDeserializerDoubleAsIntFix implements JsonDeserializer<Map<String, Object>> {

        @Override
        @SuppressWarnings("unchecked")
        public Map<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return (Map<String, Object>) read(json);
        }

        public Object read(JsonElement in) {

            if (in.isJsonArray()) {
                List<Object> list = new ArrayList<>();
                JsonArray arr = in.getAsJsonArray();
                for (JsonElement anArr : arr) {
                    list.add(read(anArr));
                }
                return list;
            } else if (in.isJsonObject()) {
                Map<String, Object> map = new LinkedTreeMap<>();
                JsonObject obj = in.getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entitySet = obj.entrySet();
                for (Map.Entry<String, JsonElement> entry : entitySet) {
                    map.put(entry.getKey(), read(entry.getValue()));
                }
                return map;
            } else if (in.isJsonPrimitive()) {
                JsonPrimitive prim = in.getAsJsonPrimitive();
                if (prim.isBoolean()) {
                    return prim.getAsBoolean();
                } else if (prim.isString()) {
                    return prim.getAsString();
                } else if (prim.isNumber()) {

                    Number num = prim.getAsNumber();
                    // here you can handle double int/long values
                    // and return any type you want
                    // this solution will transform 3.0 float to long values
                    if (Math.ceil(num.doubleValue()) == num.longValue()) {
                        return num.longValue();
                    } else {
                        return num.doubleValue();
                    }
                }
            }
            return null;
        }
    }
}
