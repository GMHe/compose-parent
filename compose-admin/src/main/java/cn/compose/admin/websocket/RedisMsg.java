package cn.compose.admin.websocket;

import cn.compose.admin.base.IMsgHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RedisMsg implements MessageDelegate {

    private Map<String, IMsgHandler> handlerMap = new HashMap<>();

    @Resource
    private List<IMsgHandler> msgHandlers;

    @PostConstruct
    public void init() {
        if (CollectionUtils.isEmpty(msgHandlers)) {
            return;
        }
        msgHandlers.forEach(handler -> {
            handlerMap.put(handler.getHandlerType(), handler);
        });
    }

    /**
     * 接收订阅消息--区分不同管道
     * <p>
     * 此处只做业务消息的转发，不做具体业务处理
     *
     * @param message
     */
    @Override
    public void handleMessage(String message, String channel) {
        IMsgHandler msgHandler = handlerMap.get(channel);
        if (msgHandler != null) {
            msgHandler.processMsg(message);
        }
    }
}