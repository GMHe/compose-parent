package cn.compose.admin.biz;

import cn.compose.admin.dao.UserTokenMapper;
import cn.compose.admin.entity.UserToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class CodeBiz {
    @Resource
    private UserTokenMapper userTokenMapper;

    public String createUserToken() {
        UserToken userToken = new UserToken();
        userToken.setUuid(UUID.randomUUID().toString());
        userTokenMapper.insert(userToken);
        return userToken.getUuid();
    }
}
