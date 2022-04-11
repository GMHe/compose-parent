package cn.compose.admin.config;

import cn.compose.admin.annotations.lockConfig.RedisLock;
import cn.compose.admin.exception.RateLimiterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Redis的过期监听-----redis单机时可用，若redis为集群，则可以使用管道监听方式
 */
@Slf4j
//@Component //若只需要单节点时，放开此注释，同时踢出 RedisListenerConfig  的  Configuration注入
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private RedisLock redisLock;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {

            // 此处key暂时使用message定义
            //TODO 需要根据业务修改
            String key = message.toString();


            //从失效key中筛选代表订单失效的key
            if (key != null && key.startsWith("order_")) {

                String clientId = UUID.randomUUID().toString();

                // 获取锁 5 S
                boolean lock = redisLock.tryLock(key, clientId, 10);
                log.info("lock {} tryLock key = [{}], clientId = [{}]", lock, key, clientId);

                if (lock) {
                    // 获取锁成功
                    try {
                        // 执行业务
                        //TODO 需要根据业务修改
                        //截取订单号，查询订单，如果是未支付状态则为-取消订单
                        log.info("监听到需要处理到期订单:{}", key);

                        Thread.sleep(5100);

                        log.info("tryLock success, key = [{}], clientId = [{}] , 执行业务开始。。。  ", key, clientId);

                    }catch (Exception e){
                        log.error("处理监听到需要处理到期订单 异常",e);
                    } finally {
                        // 解锁
                        Boolean isRel = redisLock.releaseLock(key, clientId);
                        if (!isRel) {
                            throw new RuntimeException("解锁失败，锁key = [{}], clientId = [{}]" + key);
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【修改支付订单过期状态异常】：" + e.getMessage());
        }
    }

}

