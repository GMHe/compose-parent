package cn.compose.collect;

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
@MapperScan("cn.compose.collect.dao")
@EnableScheduling
public class ComposeCollectApp {

    public static void main(String[] args) {
        SpringApplication.run(ComposeCollectApp.class, args);
    }

}
