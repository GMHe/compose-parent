package cn.compose.admin.api;

import cn.compose.admin.biz.PulshBiz;
import cn.compose.admin.kafka.publisher.DevicePublisher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/admin/kafka")
@Api(tags = "Kafka操作相关接口",value = "Kafka操作相关接口")
public class KafkaPushRest {
    @Resource
    private PulshBiz pulshBiz;

    @ApiOperation(value = "测试kafkaPush", nickname = "测试kafkaPush")
    @GetMapping("/kafkaPush")
    public String kafkaPush() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "测试kafkaPush");
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(map);
        pulshBiz.report(mapList, "compose-admin");
        return "ok";
    }
}
