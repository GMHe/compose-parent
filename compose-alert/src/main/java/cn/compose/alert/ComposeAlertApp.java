package cn.compose.alert;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * JxCognitiveOpenApplication
 *
 * @author hgm
 * @date 2021/7/22
 */
@SpringBootApplication
@MapperScan("cn.compose.alert.dao")
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.compose.alert.apiFeign"})
public class ComposeAlertApp {

    public static void main(String[] args) {
        SpringApplication.run(ComposeAlertApp.class, args);
    }

}
