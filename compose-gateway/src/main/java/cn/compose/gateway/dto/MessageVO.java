package cn.compose.gateway.dto;

import lombok.Data;

@Data
public class MessageVO {
    private String sessionId;
    private String msg;
    private String msgDesc;
    private int msgCode = 0;
    private String time;
    private String type;
}
