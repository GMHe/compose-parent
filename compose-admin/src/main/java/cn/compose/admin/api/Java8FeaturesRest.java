package cn.compose.admin.api;

import cn.compose.admin.base.Response;
import cn.compose.admin.base.ResponseBuilder;
import cn.compose.admin.biz.Java8FeatureBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/admin/java8")
@Api(tags = "java8特性接口",value = "java8特性接口")
public class Java8FeaturesRest {

    @Resource
    private Java8FeatureBiz java8FeatureBiz;

    @ApiOperation(value = "stream写法测试", nickname = "stream写法测试")
    @GetMapping("/stream")
    public Response stream(){
        java8FeatureBiz.stream();
        return ResponseBuilder.ok(new ArrayList<>());
    }
}
