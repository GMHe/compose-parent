//package cn.compose.admin.config;
//
//import cn.compose.admin.exception.BizException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisPoolConfig;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
////TODO 未生效
//@Component
//public class RedisListenerConfigDynamic {
//    private List<String> list = new ArrayList<>();
//    private Map<String,RedisKeyExpirationListener> containerMap = new ConcurrentHashMap<>();
//    private JedisPoolConfig jedisPoolConfig;
//    public RedisListenerConfigDynamic(){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(100);
//        jedisPoolConfig.setMaxWaitMillis(1000);
//        this.jedisPoolConfig  = jedisPoolConfig;
//    }
//
//    @Value("${spring.redis.cluster.nodes}")
//    private String nodes;
//
//    @PostConstruct
//    public void init() {
//        String[] cluster = nodes.split(",");
//        for (int i = 0; i < cluster.length; i++) {
//            list.add(cluster[i]);
//        }
//
//        list.forEach(str->{
//            String[] node = str.split(":");
//            RedisMessageListenerContainer listenerContainer = getContainer(node[0],Integer.valueOf(node[1]));
//            containerMap.put(node[1],redisKeyExpirationListener(listenerContainer));
//        });
//    }
//
//    private RedisMessageListenerContainer getContainer(String host,Integer port){
//        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(host);
//        jedisConnectionFactory.setPort(Integer.valueOf(port));
//        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
//        jedisConnectionFactory.afterPropertiesSet();
//        container.setConnectionFactory(jedisConnectionFactory);
//        container.start();
//        return container;
//    }
//
//    private RedisKeyExpirationListener redisKeyExpirationListener(RedisMessageListenerContainer redisContainer) {
//        return new RedisKeyExpirationListener(redisContainer);
//    }
//}
