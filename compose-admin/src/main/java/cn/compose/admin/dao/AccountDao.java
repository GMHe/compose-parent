package cn.compose.admin.dao;

import cn.compose.admin.dto.AccountDTO;
import cn.compose.admin.entity.Account;
import cn.compose.admin.entity.AccountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountDao extends AccountMapper {
    int updateAccount(@Param("account") AccountDTO account);
}