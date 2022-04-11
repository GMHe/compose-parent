package cn.compose.admin.config;

import cn.compose.admin.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author hgm
 * @Create 2022-04-06 14:46
 * @Desc 监听redis中Key过期事件
 */

@Configuration  //若只需要集群时，放开此注释，同时踢除 RedisKeyExpirationListener  的 Component 注入
@Slf4j
public class RedisListenerConfig implements EnvironmentAware {

    private List<String> list = new ArrayList<>();
    @Resource
    private RedisClusterConfig redisClusterConfig;

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;

    @Override
    public void setEnvironment(Environment environment) {
        Binder binder = Binder.get(environment);
        String bindNode = binder.bind("spring.redis.cluster.nodes", String.class).get();

        log.info("@Value 方式获取到Redis的节点配置信息为：[{}]",nodes);

        log.info("Environment 方式获取到Redis的节点配置信息为：[{}]",bindNode);

        log.info("ConfigurationProperties 获取yml配置方式 获取到 redis 的nodes 为：[{}]",redisClusterConfig.getNodes());
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        String[] cluster = nodes.split(",");
        for (int i = 0; i < cluster.length; i++) {
            list.add(cluster[i]);
        }

        if(list.size() != 6){
            throw new BizException("redis过期监听只支持三主三从，其他连接方式配置需修改 RedisListenerConfig  文件配置");
        }

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(1000);
        return jedisPoolConfig;
    }

    // redis-cluster不支持key过期监听，建立多个连接，对每个redis节点进行监听
    @Bean
    RedisMessageListenerContainer redisContainer1() {
        String[] node = list.get(0).split(":");
        return getContainer(node[0],Integer.valueOf(node[1]));
    }

    @Bean
    RedisMessageListenerContainer redisContainer2() {
        String[] node = list.get(1).split(":");
        return getContainer(node[0],Integer.valueOf(node[1]));
    }

    @Bean
    RedisMessageListenerContainer redisContainer3() {
        String[] node = list.get(2).split(":");
        return getContainer(node[0],Integer.valueOf(node[1]));
    }

    @Bean
    RedisMessageListenerContainer redisContainer4() {
        String[] node = list.get(3).split(":");
        return getContainer(node[0],Integer.valueOf(node[1]));
    }

    @Bean
    RedisMessageListenerContainer redisContainer5() {
        String[] node = list.get(4).split(":");
        return getContainer(node[0],Integer.valueOf(node[1]));
    }

    @Bean
    RedisMessageListenerContainer redisContainer6() {
        String[] node = list.get(5).split(":");
        return getContainer(node[0],Integer.valueOf(node[1]));
    }

    public RedisMessageListenerContainer getContainer(String host,Integer port){
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(Integer.valueOf(port));
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
        jedisConnectionFactory.afterPropertiesSet();
        container.setConnectionFactory(jedisConnectionFactory);
        return container;
    }

    @Bean
    RedisKeyExpirationListener redisKeyExpirationListener1() {
        return new RedisKeyExpirationListener(redisContainer1());
    }

    @Bean
    RedisKeyExpirationListener redisKeyExpirationListener2() {
        return new RedisKeyExpirationListener(redisContainer2());
    }

    @Bean
    RedisKeyExpirationListener redisKeyExpirationListener3() {
        return new RedisKeyExpirationListener(redisContainer3());
    }

    @Bean
    RedisKeyExpirationListener redisKeyExpirationListener4() {
        return new RedisKeyExpirationListener(redisContainer4());
    }

    @Bean
    RedisKeyExpirationListener redisKeyExpirationListener5() {
        return new RedisKeyExpirationListener(redisContainer5());
    }

    @Bean
    RedisKeyExpirationListener redisKeyExpirationListener6() {
        return new RedisKeyExpirationListener(redisContainer6());
    }

}
