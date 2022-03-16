package cn.compose.admin.biz;

import cn.compose.admin.base.Response;
import cn.compose.admin.dao.UserMapper;
import cn.compose.admin.dao.UserTokenMapper;
import cn.compose.admin.entity.User;
import cn.compose.admin.entity.UserToken;
import cn.compose.admin.entity.UserTokenExample;
import cn.compose.admin.exception.RateLimiterException;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AccessLimiterBiz {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisScript<Boolean> rateLimitLua;

    @Resource
    private UserTokenMapper userTokenMapper;

    public void limitAccess(String key, Integer limit) {
        boolean acquired = stringRedisTemplate.execute(
                rateLimitLua,//lua脚本的真身
                Lists.newArrayList(key),//lua脚本中的key列表
                limit.toString()//lua脚本的value列表
        );

        if (!acquired) {
            log.error("访问过于频繁，请稍后再试！,key={}", key);
            //向上抛出异常，在全局捕获异常做处理
            throw new RateLimiterException("访问过于频繁，请稍后再试！");
        }

    }

    public void limitAccessTest() {
        UserToken user=new UserToken();
        user.setUuid(UUID.randomUUID().toString());
        user.setCreateDate(new Date());
        user.setLoginTime(new Date());
        userTokenMapper.insert(user);
    }

    public List<UserToken> listUserToken() {
        UserTokenExample userTokenExample=new UserTokenExample();
        userTokenExample.createCriteria().andCreateDateIsNotNull();
        return userTokenMapper.selectByExample(userTokenExample);
    }

}