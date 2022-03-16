package cn.compose.open.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hgm
 */
@Data
public class MessageDTO implements Serializable {
    private String msg;
    private String time;
    private String type;
}
