package cn.compose.alert.dto;

import lombok.Data;

@Data
public class SocketCommonDTO {
    private String sessionId;
    private String type;
    private int respCode;
    private String msg;
    private Object data;
    private String dataDsc;
}
