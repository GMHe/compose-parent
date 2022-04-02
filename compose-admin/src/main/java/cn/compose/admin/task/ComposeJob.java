package cn.compose.admin.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.*;

@Component
@Slf4j
public class ComposeJob {

    private ExecutorService dataExecutor = new ThreadPoolExecutor(3,10,300, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());


    @Scheduled(fixedDelay = 10*1000L, initialDelay = 10000L)
    public void syncBaseInfoDevice1() {
        //异步 调用下发指令
        CompletableFuture.runAsync(() -> {
            Random random=new Random();
            int d=random.nextInt(12);
            log.info("d:{}",d);
        }, dataExecutor);
        log.info("任务一========");
    }

    @Scheduled(fixedDelay = 13*1000L, initialDelay = 13000L)
    public void syncBaseInfoDevice2() {
        CompletableFuture.runAsync(() -> {
            Random random=new Random();
            int c=random.nextInt(20);
            log.info("c:{}",c);
        }, dataExecutor);
        log.info("任务二========");
    }

    @Scheduled(fixedDelay = 12*1000L, initialDelay = 12000L)
    public void syncBaseInfoDevice3() {
        CompletableFuture.runAsync(() -> {
            Random random=new Random();
            int e=random.nextInt(45);
            log.info("e:{}",e);
        }, dataExecutor);
        log.info("任务三========");
    }

    @Scheduled(fixedDelay = 18*1000L, initialDelay = 18000L)
    public void syncBaseInfoDevice4() {
        CompletableFuture.runAsync(() -> {
            Random random=new Random();
            int h=random.nextInt(77);
            log.info("h:{}",h);
        }, dataExecutor);
        log.info("任务四========");
    }

    @Scheduled(fixedDelay = 15*1000L, initialDelay = 15000L)
    public void syncBaseInfoDevic5() {
        CompletableFuture.runAsync(() -> {
            Random random=new Random();
            int k=random.nextInt(33);
            log.info("k:{}",k);
        }, dataExecutor);
        log.info("任务五========");
    }
}
