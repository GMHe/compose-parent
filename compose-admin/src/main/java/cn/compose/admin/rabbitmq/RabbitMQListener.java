package cn.compose.admin.rabbitmq;

import cn.compose.admin.rabbitmq.consumer.ReceiverMessageConsumer;
import cn.compose.admin.rabbitmq.entry.MqDto;
import cn.hutool.json.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQListener {

    @Autowired
    private ReceiverMessageConsumer receiverMessageConsumer;

    /**
     * 监听confirm_test_queue队列
     *
     * @param channel
     * @param mqDto
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "confirm_test_queue")
    public void receiverMessage(Channel channel, MqDto<JSONObject> mqDto, Message message) throws Exception {
        receiverMessageConsumer.run(channel, message, mqDto);
    }

}