package cn.compose.metric.config;

import cn.compose.metric.websocket.TableWebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Configuration
@EnableWebSocket
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new TableWebSocketHandler(), "/webSocket/connect/tableWebSocketHandler")
                .setAllowedOrigins("*")
                .addInterceptors(new WebSocketInterceptor());
    }

    /**
     * 自定义拦截器拦截WebSocket请求
     */
    class WebSocketInterceptor implements HandshakeInterceptor {
        //前置拦截一般用来注册用户信息，绑定 WebSocketSession
        @Override
        public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                       WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
            if (!(request instanceof ServletServerHttpRequest)) {
                return true;
            }
            if (request instanceof ServletServerHttpRequest) {
                ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
                HttpSession session = servletRequest.getServletRequest().getSession(false);
                if (session != null) {
                    String userName = (String) session.getAttribute("userName");
                    if(userName == null){
                        userName = "WEBSOCKET_USERNAME_IS_NULL";
                    }
                    attributes.put("userName",userName);
                }
            }
            return true;
        }

        @Override
        public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler
                wsHandler, Exception exception) {
        }
    }
}