package cn.compose.admin.rabbitmq.consumer;

import cn.compose.admin.rabbitmq.config.RabbitMQConfig;
import cn.compose.admin.rabbitmq.entry.MqDto;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public abstract class DefaultConsumer<T> {


    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取rabbitmq配置
     *
     * @return
     */
    @Resource
    private RabbitMQConfig rabbitMQConfig;

    /**
     * 业务执行
     *
     * @param t
     */
    public abstract void execute(T t) throws Exception;


    /**
     * 业务执行失败，处理策略
     */
    public void executeFail(Channel channel, Message message,T t,Exception e) throws Exception {
        // 是否重复消息
        if (message.getMessageProperties().getRedelivered()) {
            //获取当前重试的参数
            MqDto retry = getMsgRetry(t);

            int retrySetting = rabbitMQConfig.getMaxAttempts() ;
            //如果需要自定义重试次数
            if(retry.getRetrySetting() > 0){
                retrySetting = retry.getRetrySetting();
            }

            //如果有异常，且没有达到最大投递次数,失败执行策略
            if (retrySetting > retry.getRetry()) {
                setMegRetry(retry);

                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                log.error("消息重复处理失败,当前重复消费次数为:[{}]",retry.getRetry());
                return;
            }

            log.error("消息已重复处理失败,拒绝再次接收...");
            //消费处理异常时，重新将消息打回队列的消息拒绝再次接收
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
        } else {
            //处理异常时，重新将消息打回队列
            log.error("消息处理异常，即将再次返回队列处理...",e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    public void run(Channel channel, Message message, T t) throws Exception {
        try {
            execute(t);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("consumer fail:{}", message.toString());
            executeFail(channel, message,t,e);
        }
    }


    public abstract MqDto getMsgRetry(T t);

    public abstract void setMegRetry(MqDto mqDto);

}