package cn.compose.admin.biz;

import cn.compose.admin.base.IMsgHandler;
import cn.compose.admin.constant.Constants;
import cn.compose.admin.dto.MessageVO;
import cn.compose.admin.websocket.TableWebSocketHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName WebSocketTableMsgBiz
 * @Description 类描述
 * @Author hgm
 * @Date 2021/9/29 18:22
 * @Version 1.0
 **/
@Service
@Slf4j
public class WebSocketTableMsgBiz implements IMsgHandler {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void processMsg(String message) {
        sendMessage(message);
    }

    private Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();

    @Resource
    private TableWebSocketHandler tableWebSocketHandler;


    public void setWebSocketSessionMap(Map<String, WebSocketSession> webSocketSessionMap) {
        this.webSocketSessionMap = webSocketSessionMap;
    }

    public void sendMessage(String message) {
        try {
            log.info("接收到需要给客户端发送的消息,{}",message);
            //解析message
            MessageVO messageVO = JSON.parseObject(message, MessageVO.class);
            //从单节点中获取sessionId
            WebSocketSession socketSession = webSocketSessionMap.get(messageVO.getSessionId());
            //session不在当前节点，直接return
            if (socketSession == null) {
                log.info("websocket连接不在此节点，不做处理。。。。");
                return;
            }
            redisTemplate.opsForHash().put(Constants.WEBSOCKET_TIME,socketSession.getId(),System.currentTimeMillis());

            tableWebSocketHandler.sendMessage(socketSession,message);

            //socketSession.sendMessage(new TextMessage(JSON.toJSONString(messageVO)));
            log.info("channel:tableWebSocketHandler的消息{}  已发送！", JSON.toJSONString(messageVO));

            //服务端发送消息完毕主动关闭连接
            if (Constants.WEBSOCKET_STATUS_MSG.equals(messageVO.getMsg())) {
                socketSession.close();
            }
        } catch (Exception e) {
            log.error("订阅消息转发异常：", e);
        }
    }

    @Override
    public String getHandlerType() {
        return Constants.WEBSOCKET_CHANNEL;
    }
}
