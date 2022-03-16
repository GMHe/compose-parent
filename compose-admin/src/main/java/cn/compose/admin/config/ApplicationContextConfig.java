package cn.compose.admin.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @version v1.0
 * @Title: ApplicationContextConfig
 * @Description: 获取spring容器的bean, 部分bean使用Autowired获取为null，也会存在构造注入时异常，则直接从spring上下文中获取
 * @author: hgm
 * @date: 2021年7月27日 下午17:00:00
 * @warning: 本内容仅限于佳都新太科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Configuration
public class ApplicationContextConfig implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextConfig.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name spring配置文件中配置的bean名或注解的名称
     * @return 一个给定名字注册的bean的实例
     * @throws BeansException 抛出spring异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clazz 需要获取的bean的类型
     * @return 类类型的一个在ioc容器中的bean
     * @throws BeansException 抛出spring异常
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    /**
     * 如果ioc容器中包含一个与所给名称匹配的bean定义，则返回true否则返回false
     *
     * @param name ioc容器中注册的bean名称
     * @return 存在返回true否则返回false
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }
}
