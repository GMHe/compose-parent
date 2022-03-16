package cn.compose.admin.biz;

import cn.compose.admin.base.ApiCodes;
import cn.compose.admin.base.Response;
import cn.compose.admin.base.ResponseBuilder;
import cn.compose.admin.biz.ibiz.IAdminSeataBiz;
import cn.compose.admin.dao.AccountMapper;
import cn.compose.admin.entity.Account;
import cn.compose.admin.entity.AccountExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AdminSeataBiz
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/15 14:59
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdminSeataBiz implements IAdminSeataBiz {
    @Resource
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response deduct(String userId, Long money) {
        try {
            AccountExample accountExample = new AccountExample();
            accountExample.createCriteria().andUserIdEqualTo(userId);
            List<Account> accountList = accountMapper.selectByExample(accountExample);

            if (!CollectionUtils.isEmpty(accountList)) {
                Account account = accountList.get(0);
                Long mon = account.getMoney() - money;
                if (mon < 0) {
                    return ResponseBuilder.error(ApiCodes.INTERNAL_ERROR, "余额不足！");
                }
                account.setMoney(account.getMoney() - money);
                accountMapper.updateByExample(account, accountExample);
            }
            return ResponseBuilder.ok(userId, "扣减余额成功！");

        } catch (Exception e) {
            log.error("操作账户数据异常", e);
        }
        return ResponseBuilder.error(ApiCodes.INTERNAL_ERROR, "操作账户数据异常！");
    }
}
