package cn.compose.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author: hgm
 * @CreateDate: 2020/12/4 18:36
 */
@Configuration
public class SeataConfiguration {

    //Hikari数据源配置
    /*@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource hikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        return hikariDataSource;
    }*/

    //Druid数据源配置
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

}

