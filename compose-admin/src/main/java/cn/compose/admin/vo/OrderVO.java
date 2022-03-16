package cn.compose.admin.vo;

import lombok.Data;

/**
 * @ClassName OrderVO
 * @Description 类描述
 * @Author hgm
 * @Date 2021/8/4 19:20
 * @Version 1.0
 **/
@Data
public class OrderVO {
    /*
    买家ID
     */
    private Long buyerId;
    /*
    资源ID
     */
    private Long resourceId;
    /*
    订单ID
     */
    private String orderNumber;

    /*
    支付地址
     */
    private String payUrl;
}
