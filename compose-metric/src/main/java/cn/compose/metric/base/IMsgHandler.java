package cn.compose.metric.base;

/**
 * @interfaceName IMsgHandler
 * @Description 接口描述
 * @Author hgm
 * @Date 2021/8/24 10:55
 * @Version 1.0
 **/
public interface IMsgHandler {
    public void processMsg(String message);
    public String getHandlerType();
}
