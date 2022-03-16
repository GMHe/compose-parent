package cn.compose.metric.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName OrderDTO
 * @Description 类描述
 * @Author hgm
 * @Date 2021/8/4 19:20
 * @Version 1.0
 **/
@Data
public class OrderDTO {
    private Long id;

    private String orderNumber;

    private Long buyerId;

    private Integer status;

    private Long payAmount;

    private Date payTime;

    private Long totalAmount;

    private Long resourceId;

    private String outerTradeNo;

    private String remark;

    private Date createTime;

    private Date updateTime;
}
