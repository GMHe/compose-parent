package cn.compose.sync;

import cn.compose.sync.canal.app.redis.RedisSyncApp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * JxCognitiveOpenApplication
 *
 * @author hgm
 * @date 2021/7/22
 */
@SpringBootApplication
@MapperScan("cn.compose.sync.dao")
@EnableScheduling
@EnableFeignClients(basePackages = {"cn.compose.sync.apiFeign"})
public class ComposeSyncApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ComposeSyncApp.class, args);
        RedisSyncApp redisSyncApp = context.getBean(RedisSyncApp.class);
        redisSyncApp.work();
    }

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService cacheScheduledExecutor() {
        CustomizableThreadFactory customizableThreadFactory = new CustomizableThreadFactory("caffeine-schedule-pool-");
        customizableThreadFactory.setDaemon(true);
        return new ScheduledThreadPoolExecutor(3, customizableThreadFactory);
    }
}
