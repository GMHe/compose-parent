package cn.compose.collect.websocket;

import cn.compose.collect.api.WebSocketTest;
import cn.compose.collect.config.ApplicationContextConfig;
import cn.compose.collect.constant.Constants;
import cn.compose.collect.dto.MessageDTO;
import cn.compose.collect.dto.MessageVO;
import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.github.benmanes.caffeine.cache.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class TableWebSocketHandler extends AbstractWebSocketHandler {
    private Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap();
    private LoadingCache<String, String> sessionLocalCache = Caffeine.newBuilder()
            .expireAfterWrite(15,TimeUnit.MINUTES)
            .scheduler(Scheduler.forScheduledExecutorService(new ScheduledThreadPoolExecutor(3,
                    new BasicThreadFactory.Builder().namingPattern("caffeine-schedule-pool-%d").daemon(true).build())))
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(@Nullable String key, @Nullable String value, @NonNull RemovalCause removalCause) {
                    WebSocketSession socketSession=webSocketSessionMap.get(key);
                    MessageVO messageVO=new MessageVO();
                    messageVO.setMsg("连接闲置到期,服务端主动关闭连接！");
                    messageVO.setTime(String.valueOf(System.currentTimeMillis()));
                    closeSession(socketSession, JSON.toJSONString(messageVO));
                }
            })
            .build(key -> {
                return "";
            });
    //静态变量，用来记录当前连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private WebSocketTest tableService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        addOnlineCount();
        String sessionId = session.getId();
        webSocketSessionMap.put(sessionId, session);
        log.info("新增连接,当前连接数：{}", getOnlineCount());
        MessageVO respResult = new MessageVO();
        respResult.setMsgCode(Constants.RESPONSE_CODE_SUCCESS);
        respResult.setMsg("连接成功！");
        respResult.setSessionId(sessionId);
        respResult.setTime(String.valueOf(System.currentTimeMillis()));
        sendMessage(session, JSON.toJSONString(respResult));

        //将连接信息存储到使用消息的业务中
        tableService = ApplicationContextConfig.getBean(WebSocketTest.class);
        tableService.setWebSocketSessionMap(webSocketSessionMap);

        //每次连接时都去检测一下当前节点的sessionId缓存，防止连接未主动关闭一直消耗IO连接
        sessionLocalCache.put(sessionId,sessionId);
    }


    //TODO
    //若需接收消息处理时使用
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        MessageVO respResult = new MessageVO();
        respResult.setMsgCode(Constants.RESPONSE_CODE_SUCCESS);
        try {
            String msg = message.getPayload();
            log.info("接收到消息：{}", msg);
            MessageDTO messageDTO = JSON.parseObject(msg, MessageDTO.class);
            if (Constants.WEBSOCKET_STATUS_MSG.equals(messageDTO.getMsg())) {
                respResult.setMsg("关闭连接！");
                respResult.setTime(String.valueOf(System.currentTimeMillis()));
                sendMessage(session, JSON.toJSONString(respResult));
                session.close();
            }

        } catch (Exception e) {
            log.error("error", e);
            respResult.setMsgCode(Constants.RESPONSE_CODE_FAILURE);
            respResult.setMsg("接收消息异常！检查数据接结构是否为JSON，连接关闭！");
            respResult.setTime(String.valueOf(System.currentTimeMillis()));
            sendMessage(session, JSON.toJSONString(respResult));
            session.close();
        }
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        log.info("收到消息,类型为：BinaryMessage");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("连接错误：", exception);
        if (session.isOpen()) {
            webSocketSessionMap.remove(session.getId());
            session.close();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        webSocketSessionMap.remove(session.getId());
        session.close();
        subOnlineCount();
        log.info("连接已关闭,当前连接数：{}", getOnlineCount());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 指定发消息
     *
     * @param message
     */
    public void sendMessage(WebSocketSession session, String message) {
        if (session == null || !session.isOpen()) {
            return;
        }
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            log.error("error", e);
        }
    }

    /**
     * 指定session关闭并告知客户端
     *
     * @param session
     */
    public void closeSession(WebSocketSession session, String message) {
        if (session == null || !session.isOpen()) {
            return;
        }
        try {
            session.sendMessage(new TextMessage(message));
            if (session.isOpen()) {
                webSocketSessionMap.remove(session.getId());
                session.close();
            }
        } catch (IOException e) {
            log.error("error", e);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        TableWebSocketHandler.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        TableWebSocketHandler.onlineCount--;
    }
}