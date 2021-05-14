package com.zanpo.it.app.terminal;

import com.zanpo.it.model.terminal.WindowsTerminal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket
 *
 * @author cg
 * @date 2021/5/13 11:40
 */
@ServerEndpoint("/ws")
@Component
@Slf4j
public class WsServerEndpoint {

    private Map<Object, Session> sessionMap = new ConcurrentHashMap();
    private WindowsTerminal windowsTerminal;

    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        log.info("{}连接成功", session);
        sessionMap.put(1, session);
        windowsTerminal = new WindowsTerminal(session);
        // 开启一个读取线程
        windowsTerminal.beginRead();
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        windowsTerminal.destroy();
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text) {
        windowsTerminal.execCommand(text);
        return null;
    }

}
