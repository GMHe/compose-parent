package cn.compose.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @EnableWebSocket
 * @EnableScheduling 两个注解使用存在冲突需要每个都单独定义配置bean
 */
@Configuration
public class ScheduledConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduling = new ThreadPoolTaskScheduler();
        // 已配置类为准  也可去掉配置类在 yml中新增配置
        scheduling.setPoolSize(10);
        scheduling.initialize();
        return scheduling;
    }
}
