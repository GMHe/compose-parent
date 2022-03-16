package cn.compose.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RateLimiterException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    //异常信息
    private String message;
    //状态码
    private Integer code;

    public RateLimiterException(String message) {
        this.message = message;
    }
}  