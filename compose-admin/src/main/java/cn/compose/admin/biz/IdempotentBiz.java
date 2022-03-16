package cn.compose.admin.biz;

import cn.compose.admin.base.ApiCodes;
import cn.compose.admin.base.Response;
import cn.compose.admin.constant.Constants;
import cn.compose.admin.convert.AccountConvert;
import cn.compose.admin.dao.AccountDao;
import cn.compose.admin.dto.AccountDTO;
import cn.compose.admin.entity.Account;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class IdempotentBiz {

    @Resource
    private AccountDao accountDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Response takeUpdateVersion(Account account) {
        Response response = new Response();
        response.setCode(ApiCodes.SUCCESS);
        AccountDTO accountDTO = AccountConvert.INSTANCE.toDTO(account);
        try {
            int flag = accountDao.updateAccount(accountDTO);
            response.setMsg("数据已更新，请勿重复操作！");
            response.setCode(ApiCodes.ERROR);
            if (flag > 0) {
                response.setMsg("更新成功！");
            }
        } catch (Exception e) {
            response.setMsg("更新失败！");
            response.setCode(ApiCodes.ERROR);
            log.error("更新account数据失败", e);
        }
        return response;
    }


    @Transactional
    public Response takeAddVersion(Account account) {
        Response response = new Response();
        response.setCode(ApiCodes.SUCCESS);
        try {
            String userId = account.getUserId();
            if (StringUtils.isBlank(userId)) {
                response.setCode(ApiCodes.ERROR);
                response.setMsg("userId不能为空！");
                return response;
            }
            //插入数据之前先判断是否存在
            Object user = redisTemplate.opsForHash().get(Constants.redis_add_hashKey, account.getUserId());
            if (user != null) {
                response.setCode(ApiCodes.ERROR);
                response.setMsg("用户已新增，请勿重复操作！");
                return response;
            }
            accountDao.insert(account);
            //插入完成后将数据加入到redis，同时
            redisTemplate.opsForHash().put(Constants.redis_add_hashKey, account.getUserId(), account);

            response.setCode(ApiCodes.OK);
            response.setMsg("新增成功！");
        } catch (Exception e) {
            response.setMsg("新增失败！");
            response.setCode(ApiCodes.ERROR);
            log.error("新增account数据失败", e);
        }

        return response;
    }
}
