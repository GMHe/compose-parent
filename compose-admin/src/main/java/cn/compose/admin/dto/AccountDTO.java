package cn.compose.admin.dto;

import cn.compose.admin.entity.Account;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccountDTO extends Account {
    private String name;
}