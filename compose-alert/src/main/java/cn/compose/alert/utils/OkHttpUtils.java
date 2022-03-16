package cn.compose.alert.utils;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author hgm
 * @date 2021/8/6
 */
public class OkHttpUtils {

    private static final OkHttpClient DEFAULT_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            // 用于限制一次完整HTTP调用耗时
            //.callTimeout(10, TimeUnit.SECONDS)
            .build();

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json;charset=UTF-8");


    public static String postJsonResponseAsString(String url, Map<String, Object> params) throws IOException {
        Response response = postJson(url, params);
        return response.body().string();
    }

    /**
     * 使用ResponseBody需确保最后会close
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static Response postJson(String url, Map<String, Object> params) throws IOException {
        return postJson(HttpUrl.get(url), params);
    }

    public static Response postJson(HttpUrl url, Map<String, Object> params) throws IOException {
        return postJson(url, null, params);
    }

    public static Response postJson(String url, Map<String, String> headers, Map<String, Object> params) throws IOException {
        return postJson(HttpUrl.get(url), headers, params);
    }

    public static Response postJson(HttpUrl url, Map<String, String> headers, Map<String, Object> params) throws IOException {
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, JSON.toJSONString(params));
        Request.Builder builder = new Request.Builder().url(url);
        addHeaders(builder, headers);
        return DEFAULT_CLIENT.newCall(builder.post(body).build()).execute();
    }

    public static Response get(String url, Map<String, Object> params) throws IOException {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            params.forEach((k, v) -> httpBuilder.addQueryParameter(k, v.toString()));
        }
        Request request = new Request.Builder().url(httpBuilder.build()).build();
        return DEFAULT_CLIENT.newCall(request).execute();
    }

    private static void addHeaders(Request.Builder requestBuilder, Map<String, String> headers) {
        if (headers == null) {
            return;
        }
        headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
    }
}
