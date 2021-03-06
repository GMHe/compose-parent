package cn.compose.admin.websocket;

import cn.compose.admin.api.WebSocketRest;
import cn.compose.admin.biz.WebSocketTableMsgBiz;
import cn.compose.admin.config.ApplicationContextConfig;
import cn.compose.admin.constant.Constants;
import cn.compose.admin.dto.MessageDTO;
import cn.compose.admin.dto.MessageVO;
import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.*;
import lombok.Data;
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
@Data
public class TableWebSocketHandler extends AbstractWebSocketHandler {
    private Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap();

    public LoadingCache<String, String> sessionLocalCache = Caffeine.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .scheduler(Scheduler.forScheduledExecutorService(new ScheduledThreadPoolExecutor(2,
                    new BasicThreadFactory.Builder().namingPattern("caffeine-schedule-pool-%d").daemon(true).build())))
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(@Nullable String key, @Nullable String value, @NonNull RemovalCause removalCause) {
                    WebSocketSession socketSession = webSocketSessionMap.get(key);
                    MessageVO messageVO = new MessageVO();
                    messageVO.setMsg("??????????????????,??????????????????????????????");
                    messageVO.setTime(String.valueOf(System.currentTimeMillis()));
                    closeSession(socketSession, JSON.toJSONString(messageVO));
                }
            })
            .build(key -> {
                return "";
            });

    //????????????????????????????????????????????????????????????????????????????????????
    private static int onlineCount = 0;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        addOnlineCount();
        String sessionId = session.getId();
        webSocketSessionMap.put(sessionId, session);
        log.info("????????????,??????????????????{}", getOnlineCount());
        MessageVO respResult = new MessageVO();
        respResult.setMsgCode(Constants.RESPONSE_CODE_SUCCESS);
        respResult.setMsg("???????????????");
        respResult.setSessionId(sessionId);
        respResult.setTime(String.valueOf(System.currentTimeMillis()));
        sendMessage(session, JSON.toJSONString(respResult));

        //????????????????????????????????????????????????
        WebSocketRest tableService = ApplicationContextConfig.getBean(WebSocketRest.class);
        tableService.setWebSocketSessionMap(webSocketSessionMap);

        WebSocketTableMsgBiz webSocketTableMsgBiz = ApplicationContextConfig.getBean(WebSocketTableMsgBiz.class);
        webSocketTableMsgBiz.setWebSocketSessionMap(webSocketSessionMap);

        //????????????????????????????????????????????????sessionId????????????????????????????????????????????????IO??????
        sessionLocalCache.put(session.getId(), session.getId());
    }

    //TODO
    //?????????????????????????????????
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        MessageVO respResult = new MessageVO();
        respResult.setMsgCode(Constants.RESPONSE_CODE_SUCCESS);
        try {
            sessionLocalCache.put(session.getId(), session.getId());
            String msg = message.getPayload();
            log.info("??????????????????{}", msg);
            MessageDTO messageDTO = JSON.parseObject(msg, MessageDTO.class);
            if (Constants.WEBSOCKET_STATUS_MSG.equals(messageDTO.getMsg())) {
                respResult.setMsg("???????????????");
                respResult.setTime(String.valueOf(System.currentTimeMillis()));
                sendMessage(session, JSON.toJSONString(respResult));
                session.close();
            }

        } catch (Exception e) {
            log.error("error", e);
            respResult.setMsgCode(Constants.RESPONSE_CODE_FAILURE);
            respResult.setMsg("???????????????????????????????????????????????????JSON???");
            respResult.setTime(String.valueOf(System.currentTimeMillis()));
            sendMessage(session, JSON.toJSONString(respResult));
            //session.close();
        }
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        sessionLocalCache.put(session.getId(), session.getId());
        log.info("????????????,????????????BinaryMessage");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("???????????????", exception);
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
        log.info("???????????????,??????????????????{}", getOnlineCount());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * ???????????????
     *
     * @param message
     */
    public void sendMessage(WebSocketSession session, String message) {
        if (session == null || !session.isOpen()) {
            return;
        }
        try {
            sessionLocalCache.put(session.getId(), session.getId());
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            log.error("error", e);
        }
    }

    /**
     * ??????session????????????????????????
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