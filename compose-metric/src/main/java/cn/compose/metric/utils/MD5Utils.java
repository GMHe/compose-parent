package cn.compose.metric.utils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5Utils {

    private static String merchants="16333712497442816";
    private static String sign="562ab46088af41fbce567445bad20a1f";
    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String sign(String orderNo, BigDecimal amount,String notifyUrl){
        String sing=merchants+orderNo+amount+notifyUrl+sign;
        return md5(sing);
    }
//    public static void main(String[] args) {
//         String merchantNum="16333712497442816";
//         String orderNo="20210629023134393661194";
//         Number amount = 1.01;
//         String sign="562ab46088af41fbce567445bad20a1f";
//         String notifyUrl="http://gwiki.natappvip.cc/webSocket/orderNotify";
//
//        //商户号+商户订单号+支付金额+异步通知地址+接入密钥
//        //商户号+商户订单号+支付金额+异步通知地址+接入密钥
//        String sing=merchantNum+orderNo+amount+notifyUrl+sign;
//        System.out.println(md5(sing));
//
//        System.err.println(md5(""));
//    }
}