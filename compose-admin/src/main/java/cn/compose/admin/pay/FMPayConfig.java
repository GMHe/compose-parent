package cn.compose.admin.pay;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName FMPayConfig
 * @Description 类描述
 * @Author hgm
 * @Date 2021/8/8 13:14
 * @Version 1.0
 **/
@Data
public class FMPayConfig {
    private String restUrl = "http://api-4gttfzlfpj4.zhifu.fm.it88168.com/api/startOrder?";
    //商户号
    private String merchantNum = "16333712497442816";
    //商户订单号
    private String orderNo;
    //订单金额，请求支付的金额
    private BigDecimal amount;
    //接口返回类型
    private String returnType;
    //支付结果通知网址又称异步回调网址
    private String notifyUrl = "http://gwiki.natappvip.cc/open/order/orderNotify";
    // 成功后展示网址
    private String returnUrl = "http://gwiki.natappvip.cc/open/order/orderPayResult";
    // 支付方式
    private String payType;
    // 附加信息
    private String attch;
    // 商品标题
    private String subject;
    // 商平描述
    private String body;
    // 订单有效期
    private String payDuration;
    // 签名
    private String sign;
}
