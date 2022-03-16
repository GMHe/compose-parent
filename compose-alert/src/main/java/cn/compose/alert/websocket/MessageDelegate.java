package cn.compose.alert.websocket;

public interface MessageDelegate {
  // pass the channel/pattern as well
  void handleMessage(String message, String channel);
 }