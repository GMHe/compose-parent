package cn.compose.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RedisClusterBean {

    @ApiModelProperty("nodes")
    private String nodes;
}
