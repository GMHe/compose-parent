package cn.compose.admin.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String username) {
        // 执行注册逻辑
        log.info("[register][执行用户({}) 的注册逻辑]", username);

        // push事件
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, username));
    }


    public void takeAccount(String account) {
        // 执行注册逻辑
        log.info("[takeAccount][修改({}) 逻辑]", account);

        // push事件
        applicationEventPublisher.publishEvent(new AccountEvent(this, account));
    }
}