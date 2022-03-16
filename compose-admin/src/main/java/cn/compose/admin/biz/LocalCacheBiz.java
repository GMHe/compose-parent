package cn.compose.admin.biz;

import cn.compose.admin.entity.Source;
import com.beust.jcommander.internal.Nullable;
import com.github.benmanes.caffeine.cache.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LocalCacheBiz {
    private final LoadingCache<String, List<Source>> sourceCache = Caffeine.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build(key -> {
                return getSourceList(key);
            });

    private LoadingCache<String, String> sessionLocalCache = Caffeine.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .scheduler(Scheduler.forScheduledExecutorService(new ScheduledThreadPoolExecutor(2,
                    new BasicThreadFactory.Builder().namingPattern("caffeine-schedule-pool-cache-%d").daemon(true).build())))
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(@Nullable String key, @Nullable String value, @NonNull RemovalCause removalCause) {
                    System.err.println("过期的key删除" + key);
                }
            })
            .build(key -> {
                return "";
            });

    private List<Source> getSourceList(String sign) {

        return new ArrayList<>();
    }
}
