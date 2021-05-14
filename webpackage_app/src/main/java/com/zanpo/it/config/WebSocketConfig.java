package com.zanpo.it.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket配置文件
 *
 * @author cg
 * @date 2021/5/13 11:37
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {

    /**
     * 注入ServerEndpointExporter
     * <p>
     * 该Bean会自动注册添加@ServerEndpoint注解的WebSocket端点
     * </p>
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }
}
