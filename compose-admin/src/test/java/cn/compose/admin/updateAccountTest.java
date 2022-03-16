package cn.compose.admin;

import cn.compose.admin.biz.IdempotentBiz;
import cn.compose.admin.entity.Account;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.springframework.stereotype.Component;

public class updateAccountTest {


    @Test
    public void TestUpdate(){
        IdempotentBiz idempotentBiz=new IdempotentBiz();
        Account account=new Account();
        account.setId(1l);
        account.setMoney(23l);
        account.setVersion(1);
        idempotentBiz.takeUpdateVersion(account);
    }
}
