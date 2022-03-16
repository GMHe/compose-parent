package cn.compose.gateway.websocket;

public interface MessageDelegate {
  // pass the channel/pattern as well
  void handleMessage(String message, String channel);
 }