package cn.compose.sync.kafka;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 存放一些公共配置（如：spring.application.name）
 *
 * @author hgm
 * @date 2021/10/16
 */
@Data
@Configuration
public class EnvConfig {

    /**
     * 项目名
     */
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 公用kafka地址
     */
    @Value("${kafka.address}")
    private String kafkaAddress;
    /**
     * 公用kafka用户名
     */
    @Value("${kafka.userName:#{null}}")
    private String kafkaUserName;
    /**
     * 公用kafka密码
     */
    @Value("${kafka.pass:#{null}}")
    private String kafkaPass;

    /**
     * 批量消费个数
     */
    @Value("${kafka.batchSize:1000}")
    private int batchSize;

    /**
     * 消费线程
     */
    @Value("${kafka.consumerNum:3}")
    private int consumerNum;
}
