package cn.compose.admin.api;

import cn.compose.admin.base.Response;
import cn.compose.admin.base.ResponseBuilder;
import cn.compose.admin.convert.UserConvert;
import cn.compose.admin.dto.UserDTO;
import cn.compose.admin.entity.User;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController()
@Slf4j
@RequestMapping("/admin/convert")
public class ConvertRest {

    @Resource
    private UserConvert userConvert;

    @GetMapping("/testConvert")
    public Response testConvert() {
        User user = new User();
        user.setName("测试12345");
        UserDTO userDTO = userConvert.toDTO(user);
        JSONObject result = JSONUtil.createObj();
        result.set("userDTO", JSON.toJSONString(userDTO));
        return ResponseBuilder.ok(result, "转换成功！");
    }

    @GetMapping("/testConverts")
    public Response testConverts() {
        List<User> userList = new ArrayList<>();
        User u = new User();
        u.setName("测试");
        userList.add(u);
        UserDTO userDTO = userConvert.toDTO(u);
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("测试" + i);
            userList.add(user);
        }
        List<UserDTO> userDTOList = userConvert.collectionCvt(userList);
        JSONObject result = JSONUtil.createObj();
        result.set("userDTO", JSON.toJSONString(userDTO));
        result.set("userDTOList", JSON.toJSONString(userDTOList));
        return ResponseBuilder.ok(result, "转换成功！");
    }

}
