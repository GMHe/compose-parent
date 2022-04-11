package cn.compose.admin.biz;

import cn.compose.admin.annotations.lockConfig.RedisLock;
import cn.compose.admin.base.IMsgHandler;
import cn.compose.admin.constant.Constants;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName OrderMsgBiz
 * @Description 类描述
 * @Author hgm
 * @Date 2021/9/29 18:22
 * @Version 1.0
 **/
@Service
@Slf4j
public class OrderMsgBiz implements IMsgHandler {

    @Autowired
    private RedisLock redisLock;

    @Override
    public void processMsg(String message) {

        // 此处key暂时使用message定义
        //TODO 需要根据业务修改
        if(!(message.contains("{")&&message.contains("}"))){
            log.info("消息为非json数据,抛弃");
            return;
        }

        JSONObject jsonMsg = JSONUtil.parseObj(message);
        String key = jsonMsg.getStr("msg_id");

        String clientId = UUID.randomUUID().toString();
        // 获取锁 5 S
        boolean lock = redisLock.tryLock(key, clientId, 10);
        log.info("lock {} tryLock key = [{}], clientId = [{}]", lock, key, clientId);

        if (lock) {
            // 获取锁成功
            try {
                //处理监听到的Redis 消息
                log.info("处理监听到的Redis 消息:{}",message);
                Thread.sleep(5100);

                // 执行业务
                //TODO 需要根据业务修改
                log.info("tryLock success, key = [{}], clientId = [{}] , 执行业务开始。。。  ", key, clientId);

            }catch (Exception e){
                log.error("处理监听到的Redis 消息 异常",e);
            }
            finally {
                // 解锁
                Boolean isRel = redisLock.releaseLock(key, clientId);
                if (!isRel) {
                    throw new RuntimeException("解锁失败，锁key为:" + key);
                }
                log.info("releaseLock success, key = [{}], clientId = [{}] , 执行业务结束。。。", key, clientId);
            }
        } else {
            // 获取锁失败，认为是重复提交的请求
            log.info("通知重复，不做处理！, key = [{}]", key);
            //向上抛出异常，在全局捕获异常做处理
            //log.info("通知重复，不做处理!");
        }

    }

    @Override
    public String getHandlerType() {
        return Constants.ORDER_STATE_CHANNEL;
    }
}
