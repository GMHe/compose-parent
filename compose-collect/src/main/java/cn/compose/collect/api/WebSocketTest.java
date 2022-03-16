package cn.compose.collect.api;

import cn.compose.collect.base.ResponseBuilder;
import cn.compose.collect.websocket.TableWebSocketHandler;
import cn.compose.collect.base.Response;
import cn.compose.collect.constant.Constants;
import cn.compose.collect.dao.AlertMapper;
import cn.compose.collect.dto.MessageVO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import javax.websocket.ClientEndpoint;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

@RestController
@Slf4j
@RequestMapping("/webSocket")
@ClientEndpoint
public class WebSocketTest {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource
    private AlertMapper alertMapper;

    @Resource
    private TableWebSocketHandler tableWebSocketHandler;

    private Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();


    public void setWebSocketSessionMap(Map<String, WebSocketSession> webSocketSessionMap) {
        this.webSocketSessionMap = webSocketSessionMap;
    }

    @GetMapping("/orderNotify")
    public String orderNotify() {
        System.err.println("通知到了");
        return "success";
    }

    @GetMapping("/orderNotifyReturn")
    public String orderNotifyReturn(@RequestParam("orderId") String orderId) {
        System.err.println("订单支付成功！"+orderId);
        return "success";
    }

//    @GetMapping("/saveData")
//    public String saveData() {
//        alertMapper.insert()
//        return "success";
//    }

        @GetMapping("/saveKey")
    @ResponseBody
    public Response<Object> saveKey(@RequestParam(name = "redisKey", required = true) String redisKey,
                                    @RequestParam(name = "hashKey", required = true) String hashKey,
                                 @RequestParam(name = "hashValue", required = true) String hashValue) {

        Map<String,String> hashMap=new HashMap<>();
        hashMap.put(hashKey,hashValue);
        redisTemplate.opsForHash().putAll(redisKey,hashMap);
        Map<Object,Object> result=redisTemplate.opsForHash().entries(redisKey);
        result.entrySet().forEach(entry->{
            System.err.println(entry.getKey()+"====="+entry.getValue());
        });

        redisTemplate.opsForHash().putAll("testHash:123:",hashMap);

        List<String> keys=  assembleScanKeys("testHash*",1000L);
            System.err.println(keys);
        return ResponseBuilder.ok( "已保存！");
    }

    /**
     * 组装 scan 的结果集
     */
    public List<String> assembleScanKeys(String pattern, Long limit) {
        HashSet<String> set = new HashSet<>();
        Cursor<String> cursor = scan(redisTemplate, pattern, limit);
        while (cursor.hasNext()) {
            set.add(cursor.next());
        }
        try {
            cursor.close();
        } catch (Exception e) {
            log.error("关闭 redis connection 失败");
        }
        return set.stream().map(String::valueOf).collect(toList());
    }
    /**
     * 自定义 redis scan 操作
     */
    private Cursor<String> scan(RedisTemplate redisTemplate, String pattern, Long limit) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(limit).build();
        RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
        return (Cursor) redisTemplate.executeWithStickyConnection(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection)
                    throws org.springframework.dao.DataAccessException {
                return new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize);
            }
        });
    }

//    /**
//     * 组装分布式缓存中的 value 值
//     * <p>
//     *    pattern：message:xxx:yyy:id:
//     *    limit：每次限制筛选的数量，不建议 Integer.MAX_VALUE
//     */
//    public List<String> assembleScanValues(String pattern, Long limit) {
//        Set<String> valueSet = scan(redisTemplate, pattern, limit);
//        return valueSet.stream().map(String::valueOf).collect(toList());
//    }
//
//    /**
//     * 组装 scan 的结果集
//     */
//    private Set<String> scan(RedisTemplate redisTemplate, String pattern, Long limit) {
//        return (Set<String>) redisTemplate.execute(new RedisCallback<Set<String>>() {
//            @Override
//            public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
//                Set<String> valueSet = new HashSet<>();
//                try (Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder()
//                        .match(pattern).count(limit).build())) {
//                    while (cursor.hasNext()) {
//                        byte[] bytes = connection.get(cursor.next());
//                        String value = String.valueOf(redisTemplate.getValueSerializer().deserialize(bytes));
//                        valueSet.add(value);
//                    }
//                } catch (Exception e) {
//                    log.error(String.format("get cursor close {%s}", e));
//                }
//                return valueSet;
//            }
//        });
//    }


    @GetMapping("/sendText")
    @ResponseBody
    public Response<Object> Test(@RequestParam(name = "sessionId", required = true) String sessionId,
                                 @RequestParam(name = "message", required = true) String message) {
        MessageVO messageVO = new MessageVO();
        messageVO.setMsg(message);
        messageVO.setSessionId(sessionId);
        messageVO.setTime(String.valueOf(System.currentTimeMillis()));
        redisTemplate.convertAndSend(Constants.WEBSOCKET_CHANNEL, messageVO);

        //String str = String.valueOf(redisTemplate.opsForValue().get("api"));
        //HashOperations<String, String, Object> hps = redisTemplate.opsForHash();
        //hps.putAll("redisMap", webSocketSessionMap);
        //redisTemplate.opsForValue().set("socketKey", sessionId);
        //redisTemplate.opsForValue().set("testObject",new SocketCommonDTO());
        //redisTemplate.opsForHash().putAll("webSocketSessionMap",webSocketSessionMap);
        //Map<String, Object> commonDTOMap = new HashMap<>();
        //commonDTOMap.put("234", new SocketCommonDTO());
        //redisTemplate.opsForHash().putAll("redisMap", commonDTOMap);
        //log.info("============={}", str);
        //Map<Object, Object> webMap = redisTemplate.opsForHash().entries("redisMap");
        //alertMapper.selectByExample(new AlertExample())
        return ResponseBuilder.ok(messageVO, "消息已转发！");
    }


    public void sendMessage(String message) {
        try {
            //解析message
            MessageVO messageVO = JSON.parseObject(message, MessageVO.class);
            //从单节点中获取sessionId
            WebSocketSession socketSession = webSocketSessionMap.get(messageVO.getSessionId());
            //session不在当前节点，直接return
            if (socketSession == null) {
                return;
            }
            socketSession.sendMessage(new TextMessage(JSON.toJSONString(messageVO)));
            log.info("channel:tableWebSocketHandler的消息{}  已发送！", JSON.toJSONString(messageVO));

            //服务端发送消息完毕主动关闭连接
            if (Constants.WEBSOCKET_STATUS_MSG.equals(messageVO.getMsg())) {
                socketSession.close();
            }
        } catch (Exception e) {
            log.error("订阅消息转发异常：", e);
        }
    }


//    public static void main(String[] args) {
//        TableSocketDTO tableSocketDTO=new TableSocketDTO();
//        tableSocketDTO.setFileName("OMOF_CAMERA_INFO.xlsx");
//        tableSocketDTO.setHeadLine(3);
//        tableSocketDTO.setPlatFlag("CFTRT");
//        tableSocketDTO.setTableCode("TBSE");
//
//        SocketCommonDTO socketCommonDTO=new SocketCommonDTO();
//        socketCommonDTO.setData(tableSocketDTO);
//        socketCommonDTO.setType("tableWebSocketService");
//
//        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//        String uri ="ws://127.0.0.1:8910/webSocket/connect/tableWebSocketService";
//        try {
//            WebSocketClient client = new WebSocketClient();
//            Session session = container.connectToServer(client, URI.create(uri));
//            socketCommonDTO.setSessionId(session.getId());
//            String str= JSON.toJSONString(socketCommonDTO);
//            System.err.println(str);
//            client.send(str);
//            ByteBuffer byteBuffer=ByteBuffer.wrap(JSON.toJSONBytes(socketCommonDTO));
//            client.send(byteBuffer);
//            client.close();
//        } catch (DeploymentException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
