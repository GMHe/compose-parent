package cn.compose.admin.api;

import cn.compose.admin.biz.CodeBiz;
import cn.hutool.extra.qrcode.QrCodeUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("/admin/img")
@Api(tags = "二维码相关接口",value = "二维码相关接口")
public class CodeRest {
    @Resource
    private CodeBiz codeBiz;

    //获取登录二维码、放入Token
    @RequestMapping(value = "/getLoginQr", method = RequestMethod.GET)
    public void createCodeImg(HttpServletRequest request, HttpServletResponse response) {
//        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        response.setHeader("Access-Control-Expose-Headers", "uuid");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");

        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        try {
            //这里没啥操作 就是生成一个UUID插入 数据库的表里
            String uuid = codeBiz.createUserToken();
            response.setHeader("uuid", uuid);
            // 这里是开源工具类 hutool里的QrCodeUtil
            // 网址：http://hutool.mydoc.io/
            QrCodeUtil.generate("http://127.0.0.1:8581/codeLogin?uuid=" + uuid, 300, 300, "jpg", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
