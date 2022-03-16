package cn.compose.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ComposeGatewayApp
 *
 * @author hgm
 * @date 2021/7/22
 */
@SpringBootApplication
@MapperScan("cn.compose.gateway.dao")
@EnableScheduling
public class ComposeGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(ComposeGatewayApp.class, args);
    }

}
