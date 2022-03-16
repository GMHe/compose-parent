package cn.compose.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * JxCognitiveOpenApplication
 *
 * @author hgm
 * @date 2021/7/22
 */
@SpringBootApplication
@MapperScan("cn.compose.admin.dao")
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.compose.admin.apiFeign"})
@EnableAdminServer
@EnableZuulProxy
public class ComposeAdminApp {

    public static void main(String[] args) {
        SpringApplication.run(ComposeAdminApp.class, args);
    }


    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService cacheScheduledExecutor() {
        CustomizableThreadFactory customizableThreadFactory = new CustomizableThreadFactory("caffeine-schedule-pool-");
        customizableThreadFactory.setDaemon(true);
        return new ScheduledThreadPoolExecutor(3, customizableThreadFactory);
    }
}
