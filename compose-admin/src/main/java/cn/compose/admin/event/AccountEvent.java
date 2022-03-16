package cn.compose.admin.event;

import org.springframework.context.ApplicationEvent;

public class AccountEvent extends ApplicationEvent {

    /**
     * 账户
     */
    private String account;

    public AccountEvent(Object source) {
        super(source);
    }

    public AccountEvent(Object source, String account) {
        super(source);
        this.account = account;
    }

    public String getUsername() {
        return account;
    }

}