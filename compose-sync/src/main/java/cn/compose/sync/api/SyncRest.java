package cn.compose.sync.api;

import cn.compose.sync.biz.SyncBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName SyncRest
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/15 09:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class SyncRest {
    @Autowired
    private SyncBiz syncBiz;

    @GetMapping("/taskSyncNo")
    public String task01() {
        long now = System.currentTimeMillis();
        log.info("[task01][开始执行]");

        syncBiz.execute01();
        syncBiz.execute02();

        log.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
        return "执行完毕，同步耗时:"+String.valueOf(System.currentTimeMillis() - now);
    }


    @GetMapping("/taskSyncYes")
    public String task02() {
        long now = System.currentTimeMillis();
        log.info("[task02][开始执行]");

        syncBiz.execute01Async();
        syncBiz.execute02Async();

        log.info("[task02][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
        return "执行完毕，异步耗时:"+String.valueOf(System.currentTimeMillis() - now);
    }


    @GetMapping("/taskSyncReturn")
    public String task03() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        log.info("[task03][开始执行]");

        // <1> 执行任务
        Future<Integer> execute01Result = syncBiz.execute01AsyncWithFuture();
        Future<Integer> execute02Result = syncBiz.execute02AsyncWithFuture();
        // <2> 阻塞等待结果
        execute01Result.get();
        execute02Result.get();

        log.info("[task03][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
        return "执行完毕，异步耗时:"+String.valueOf(System.currentTimeMillis() - now);
    }


    @GetMapping("/taskSyncReturnExp")
    public String task04() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        log.info("[task04][开始执行]");

        // <1> 执行任务
        ListenableFuture<Integer> execute01Result = syncBiz.execute01AsyncWithListenableFuture();
        log.info("[task04][execute01Result 的类型是：({})]",execute01Result.getClass().getSimpleName());
//        execute01Result.addCallback(new SuccessCallback<Integer>() { // <2.1> 增加成功的回调
//
//            @Override
//            public void onSuccess(Integer result) {
//                log.info("[onSuccess][result: {}]", result);
//            }
//
//        }, new FailureCallback() { // <2.1> 增加失败的回调
//
//            @Override
//            public void onFailure(Throwable ex) {
//                log.info("[onFailure][发生异常]", ex);
//            }
//
//        });
        execute01Result.addCallback(new ListenableFutureCallback<Integer>() { // <2.2> 增加成功和失败的统一回调

            @Override
            public void onSuccess(Integer result) {
                log.info("[onSuccess][result: {}]", result);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("[onFailure][发生异常]", ex);
            }

        });
        // <3> 阻塞等待结果
        execute01Result.get();

        log.info("[task04][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
        return "执行完毕，异步耗时:"+String.valueOf(System.currentTimeMillis() - now);
    }


    @GetMapping("/taskSyncReturnExpHandler")
    public String testZhaoDaoNvPengYou() throws InterruptedException {
        long now = System.currentTimeMillis();
        syncBiz.syncException(1, 2);

        // sleep 1 秒，保证异步调用的执行
        Thread.sleep(1000);

        return "执行完毕，异步耗时:"+String.valueOf(System.currentTimeMillis() - now);
    }
}
