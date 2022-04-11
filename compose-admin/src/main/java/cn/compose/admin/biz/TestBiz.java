package cn.compose.admin.biz;

import cn.compose.admin.config.FilterDeviceConfig;
import cn.compose.admin.config.RedisClusterConfig;
import cn.compose.admin.entity.FilterDeviceBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TestBiz {

    @Resource
    private FilterDeviceConfig filterDeviceConfig;

    @Resource
    private RedisClusterConfig redisClusterConfig;


    public  boolean existedFilteredDevice(String deviceModelId,String orderSerial) {
        //轮询遍历过滤设备
        List<FilterDeviceBean> filterDeviceBeanList = filterDeviceConfig.getDeviceList();
        if(!CollectionUtils.isEmpty(filterDeviceBeanList)) {
            Optional<FilterDeviceBean> option =filterDeviceBeanList.stream().filter(item ->
                    item.getDeviceModel().equals(deviceModelId)
            ).findFirst();
            if(option.isPresent()) {
                FilterDeviceBean  filterDeviceBean = option.get();
                if(!CollectionUtils.isEmpty(filterDeviceBean.getOrderSerial())) {
                    return filterDeviceBean.getOrderSerial().stream()
                            .filter(item -> item.equals(orderSerial))
                            .findFirst().isPresent();
                }
            }
        }
        return false;
    }


    public void configText(){
        log.info("ConfigurationProperties 获取yml配置方式 获取到 redis 的nodes 为：[{}]",redisClusterConfig.getNodes());

    }
}
