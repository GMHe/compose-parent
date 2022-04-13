package cn.compose.admin.rabbitmq.config;

import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq.listener.simple.retry")
@Data
public class RabbitMQConfig {
    //item交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";
    //item队列名称
    public static final String ITEM_QUEUE = "item_queue";
    //item  routingKey
    public static final String ITEM_ROUTING_KEY = "item.#";

    //confirm交换机名称
    public static final String CONFIRM_EXCHANGE = "confirmTestExchange";
    //confirm队列名称
    public static final String CONFIRM_QUEUE = "confirm_test_queue";

    //confirm  routingKey
    public static final String CONFIRM_ROUTING_KEY = "confirm.#";

    private Integer maxAttempts;

    //声明交换机
    @Bean("itemTopicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }

    //声明队列
    @Bean("itemQueue")
    public Queue itemQueue(){
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    //绑定队列和交换机
    @Bean
    public Binding itemQueueExchange(@Qualifier("itemQueue") Queue queue,
                                     @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ITEM_ROUTING_KEY).noargs();
    }

    //通知队列
    @Bean(name = "confirmTestQueue")
    public Queue confirmTestQueue() {
        return new Queue(CONFIRM_QUEUE, true, false, false);
    }

    //通知交换机
    @Bean(name = "confirmTestExchange")
    public FanoutExchange confirmTestExchange() {
        return new FanoutExchange(CONFIRM_EXCHANGE);
    }

    //通知队列与交换机绑定
    @Bean
    public Binding confirmTestFanoutExchangeAndQueue(
            @Qualifier("confirmTestExchange") Exchange confirmTestExchange,
            @Qualifier("confirmTestQueue") Queue confirmTestQueue) {
        return BindingBuilder.bind(confirmTestQueue).to(confirmTestExchange).with(CONFIRM_ROUTING_KEY).noargs();
    }

    //设置开启 Mandatory
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启 Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

}