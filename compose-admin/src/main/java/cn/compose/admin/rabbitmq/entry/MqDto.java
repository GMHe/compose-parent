package cn.compose.admin.rabbitmq.entry;

import groovy.transform.ToString;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.UUID;

@Data
@ToString
public class MqDto<T> implements Serializable {

    /**
     * 消息ID，用于去重，全局唯一,为空则系统自动创建
     */
    private String messageId;

    /**
     * 消息体
     */
    private T body;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由KEY，广播模式可以不传路由KEY
     */
    private String routingKey="";

    /**
     * 消息业务
     */
    private String busChannel;

    /**
     * 过期，单位毫秒
     */
    private Long expire;

    /**
     * 业务 重试次数
     */
    private int retry;


    /**
     * 单独定义业务 重试次数不使用全局重试次数
     */
    private int retrySetting = 0;


    public void setMessageId(String messageId) {
        if (StringUtils.isBlank(messageId)) {
            this.messageId = UUID.randomUUID().toString();
        } else {
            this.messageId = messageId;
        }
    }
}