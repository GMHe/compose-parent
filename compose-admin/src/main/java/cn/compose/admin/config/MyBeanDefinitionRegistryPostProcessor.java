//package cn.compose.admin.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.boot.context.properties.bind.Binder;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.core.env.Environment;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//@Slf4j
//// TODO 未生效
//public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {
//
//    private List<String> list = new ArrayList<>();
//    private JedisPoolConfig jedisPoolConfig;
//
//    public MyBeanDefinitionRegistryPostProcessor(){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(100);
//        jedisPoolConfig.setMaxWaitMillis(1000);
//        this.jedisPoolConfig  = jedisPoolConfig;
//    }
//
//    private String nodes;
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        Binder binder = Binder.get(environment);
//        this.nodes = binder.bind("spring.redis.cluster.nodes", String.class).get();
//    }
//
//    public void inits() {
//        try {
//            String[] cluster = nodes.split(",");
//            for (int i = 0; i < cluster.length; i++) {
//                list.add(cluster[i]);
//            }
//        }catch (Exception e){
//            log.info("读取配置信息失败",e);
//        }
//    }
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        inits();
//
//        AtomicInteger sum = new AtomicInteger(1);
//        list.forEach(str->{
//            String[] node = str.split(":");
//            RedisMessageListenerContainer listenerContainer = getContainer(node[0],Integer.valueOf(node[1]));
//            BeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(RedisKeyExpirationListener.class)
//                    .addConstructorArgValue(listenerContainer).getBeanDefinition();
//            beanDefinitionRegistry.registerBeanDefinition("redisKeyExpirationListener"+ sum.get(), beanDefinition);
//            sum.getAndIncrement();
//        });
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
////        configurableListableBeanFactory.getBean(KJ.class);
//        //在bean finishBeanFactoryInitialization(beanFactory)之前就会调用这个方法
//        System.err.println("sssssssssssssss");
//    }
//
//    public RedisMessageListenerContainer getContainer(String host,Integer port){
//        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(host);
//        jedisConnectionFactory.setPort(Integer.valueOf(port));
//        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
//        jedisConnectionFactory.afterPropertiesSet();
//        container.setConnectionFactory(jedisConnectionFactory);
//        return container;
//    }
//}