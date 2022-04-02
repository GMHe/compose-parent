package cn.compose.admin.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author web
 * @date 2021/3/3 10:50
 */
@Slf4j
public class FutureTaskUtil {
    private FutureTaskUtil() {
    }

    public static <R> Future<R> supplyAsyncFuture(Supplier<R> supplier) {
        return CompletableFuture.supplyAsync(supplier, FutureTaskThreadPool.INSTANCE);
    }

    public static <R> List<Future<R>> supplyAsyncFutures(List<Supplier<R>> suppliers) {
        return suppliers.stream()
                .map(CompletableFuture::supplyAsync)
                .collect(Collectors.toList());
    }

    public static <R> List<R> supplyAsyncFutureResult(List<Supplier<R>> suppliers) {
        List<R> result = new ArrayList<>(suppliers.size());
        List<Future<R>> futures = supplyAsyncFutures(suppliers);
        for (Future<R> future : futures) {
            try {
                result.add(future.get());
            } catch (ExecutionException e) {
                log.error("执行异常", e);
            } catch (InterruptedException e) {
                log.error("Interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }

    public static <K, R> Map<K, Future<R>> supplyAsyncFuture(Map<K, Supplier<R>> taskMap) {
        Map<K, Future<R>> futureMap = new HashMap<>(taskMap.size());
        taskMap.forEach((id, supplier) -> futureMap.put(id, CompletableFuture.supplyAsync(supplier, FutureTaskThreadPool.INSTANCE)));
        return futureMap;
    }

    public static <K, R> Map<K, R> supplyAsyncFutureResult(Map<K, Supplier<R>> taskMap) {
        Map<K, R> resultMap = new HashMap<>(taskMap.size());
        Map<K, Future<R>> futureMap = supplyAsyncFuture(taskMap);
        futureMap.forEach((id, future) -> {
            try {
                resultMap.put(id, future.get());
            } catch (ExecutionException e) {
                log.error("执行异常", e);
            } catch (InterruptedException e) {
                log.error("Interrupted", e);
                Thread.currentThread().interrupt();
            }
        });
        return resultMap;
    }

    @SneakyThrows
    public static <R> R supplyAsyncFutureResult(Supplier<R> supplier) {
        Future<R> future = supplyAsyncFuture(supplier);
        return future.get();
    }

    private static class FutureTaskThreadPool {
        private static final ExecutorService INSTANCE = new ThreadPoolExecutor(
                3,
                10,
                300, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

}
