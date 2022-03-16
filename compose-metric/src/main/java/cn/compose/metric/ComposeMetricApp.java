package cn.compose.metric;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * JxCognitiveOpenApplication
 *
 * @author hgm
 * @date 2021/7/22
 */
@SpringBootApplication
@MapperScan("cn.compose.metric.dao")
@EnableScheduling
public class ComposeMetricApp {

    public static void main(String[] args) {
        SpringApplication.run(ComposeMetricApp.class, args);
    }

}
