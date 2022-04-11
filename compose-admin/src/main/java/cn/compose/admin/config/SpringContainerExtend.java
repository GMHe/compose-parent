//package cn.compose.admin.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.stereotype.Component;
//
///**
// * @author hgm
// * @since 2022/3/25 9:54
// */
//@Slf4j
//@Component
//public class SpringContainerExtend implements ApplicationContextAware {
//
//    private DefaultListableBeanFactory defaultListableBeanFactory;
//
//    private ApplicationContext applicationContext;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        log.info("get applicationContext");
//        this.applicationContext = applicationContext;
//        //将applicationContext转换为ConfigurableApplicationContext
//        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
//        // 获取bean工厂并转换为DefaultListableBeanFactory
//        this.defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
//        log.info("get BeanFactory Success.");
//    }
//
//    /**
//     * 注册bean到spring容器中
//     *
//     * @param beanName 名称
//     * @param clazz    class
//     */
//    public void registerBean(String beanName, Class<?> clazz) {
//        // 通过BeanDefinitionBuilder创建bean定义
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
//        // 尝试移除之前相同的bean
//        if (defaultListableBeanFactory.containsBean(beanName)) {
//            defaultListableBeanFactory.removeBeanDefinition(beanName);
//        }
//        // 注册bean
//        defaultListableBeanFactory
//                .registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());
//        log.info("register bean [{}],Class [{}] success.", beanName, clazz);
//    }
//
//    public <T> T getBean(Class<T> tClass) {
//        return applicationContext.getBean(tClass);
//    }
//
//    public Object getBean(String name) {
//        return applicationContext.getBean(name);
//    }
//}