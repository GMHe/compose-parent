package cn.compose.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class FilterDeviceBean {

    @ApiModelProperty("model编号")
    private String deviceModel;

    @ApiModelProperty("车编号")
    private List<String> orderSerial;
}
