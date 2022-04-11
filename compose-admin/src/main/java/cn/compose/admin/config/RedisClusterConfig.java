package cn.compose.admin.config;

import cn.compose.admin.entity.FilterDeviceBean;
import cn.compose.admin.entity.RedisClusterBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterConfig {

    @ApiModelProperty("redis节点信息")
//    private RedisClusterBean redisClusterBean;
    private String nodes;
}
