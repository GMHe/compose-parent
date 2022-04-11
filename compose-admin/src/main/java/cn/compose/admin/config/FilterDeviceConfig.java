package cn.compose.admin.config;

import cn.compose.admin.entity.FilterDeviceBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "filter-device")
public class FilterDeviceConfig {

    @ApiModelProperty("对象")
    private List<FilterDeviceBean> deviceList;
}
