package cn.compose.admin.dto;

import lombok.Data;

@Data
public class MessageVO {
    private String sessionId;
    private String msg;
    private String msgDesc;
    private int code;
    private int msgCode;
    private String userId;
    private String time;
    private String type;
}
