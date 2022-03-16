package cn.compose.admin.biz;

import cn.compose.admin.entity.Source;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Handler;

@Slf4j
@Service
public class ThreadPoolBiz {

    private ExecutorService executor = new ScheduledThreadPoolExecutor(3,
            new BasicThreadFactory.Builder().namingPattern("example-updateDeviceOnLine-schedule-pool-%d").daemon(true).build());

    private ExecutorService pool = new ThreadPoolExecutor(3,20,300, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());

    private void ThreadSync() {
        Map<String, List<String>> sources = new HashMap<>();
        sources.entrySet().forEach(entry -> {
            List<List<String>> lists = ListUtils.partition(entry.getValue(), 100);
            //多线程分批更新，单次更新上限3000条
            lists.forEach(ids -> {
                CompletableFuture.runAsync(() -> {
                    log.info("更新设备状态,数据长度：{}", ids.size());

                }, executor);
            });
        });
    }
}
