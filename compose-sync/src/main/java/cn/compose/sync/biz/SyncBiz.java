package cn.compose.sync.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

/**
 * @ClassName SyncBiz
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/15 09:37
 * @Version 1.0
 **/
@Slf4j
@Service
public class SyncBiz {

    public Integer execute01() {
        log.info("[execute01]");
        sleep(10);
        return 1;
    }

    public Integer execute02() {
        log.info("[execute02]");
        sleep(5);
        return 2;
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //异步调用
    @Async
    public Integer execute01Async() {
        return this.execute01();
    }

    @Async
    public Integer execute02Async() {
        return this.execute02();
    }


    @Async
    public Future<Integer> execute01AsyncWithFuture() {
        return AsyncResult.forValue(this.execute01());
    }

    @Async
    public Future<Integer> execute02AsyncWithFuture() {
        return AsyncResult.forValue(this.execute02());
    }

    @Async
    public ListenableFuture<Integer> execute01AsyncWithListenableFuture() {
        try {
            return AsyncResult.forValue(this.execute02());
        } catch (Throwable ex) {
            return AsyncResult.forExecutionException(ex);
        }
    }


    /**
     * 捕获异步处理异常
     * @param a
     * @param b
     * @return
     */
    @Async
    public Integer syncException(Integer a, Integer b) {
        throw new RuntimeException("异步异常：程序员不需要女朋友");
    }
}
