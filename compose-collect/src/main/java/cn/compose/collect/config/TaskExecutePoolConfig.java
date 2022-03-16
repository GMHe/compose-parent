package cn.compose.collect.config;//package cn.compose.collect.config;
//
//import org.apache.commons.lang3.concurrent.BasicThreadFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//
///**
// * @author hgm
// * @date 2021/7/30
// */
//@Configuration
//public class TaskExecutePoolConfig {
//
//    @Bean(destroyMethod = "shutdown")
//    public ScheduledExecutorService cacheScheduledExecutor() {
//        return new ScheduledThreadPoolExecutor(3,
//                new BasicThreadFactory.Builder()
//                        .namingPattern("caffeine-schedule-pool-%d")
//                        .daemon(true).build());
//    }
//
//}
