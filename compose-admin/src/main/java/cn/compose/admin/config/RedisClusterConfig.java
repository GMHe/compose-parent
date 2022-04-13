package cn.compose.admin.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterConfig {

    @ApiModelProperty("redis节点信息")
    private String nodes;
}
