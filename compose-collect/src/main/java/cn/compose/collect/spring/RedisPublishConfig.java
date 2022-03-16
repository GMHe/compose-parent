package cn.compose.collect.spring;

import cn.compose.collect.constant.Constants;
import cn.compose.collect.websocket.RedisMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class RedisPublishConfig {
    /**
     * redis消息监听器容器 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 订阅了的通道
        Set<PatternTopic> topics=new HashSet<>();
        topics.add(new PatternTopic(Constants.WEBSOCKET_CHANNEL));
        topics.add(new PatternTopic(Constants.SYNC_MGR_UPDATE_LIST_CACHE));
        container.addMessageListener(listenerAdapter, topics);
        //多PatternTopic处理消息，处理类需要实现IMsgHandler
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(RedisMsg receiver) {
        return new MessageListenerAdapter(receiver);
    }
}