package com.zanpo.it.model.terminal;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Windows下的终端操作
 *
 * @author cg
 * @date 2021/5/13 16:47
 */
@Slf4j
@Getter
public class WindowsTerminal {
    private Process cmd;
    private BufferedReader br;
    private PrintWriter pw;
    private Session session;
    private volatile boolean exit = false;

    public WindowsTerminal(Session session) {
        try {
            cmd = Runtime.getRuntime().exec("powershell");
        } catch (IOException e) {
            log.info("WindowsTerminal->执行powershell失败");
            e.printStackTrace();
        }

        InputStream inputStream = cmd.getInputStream();
        OutputStream outputStream = cmd.getOutputStream();

        try {
            this.br = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.pw = new PrintWriter(outputStream);
        this.session = session;
    }


    /**
     * 让终端执行命令
     *
     * @param command 命令
     */
    public void execCommand(String command) {
        pw.print(command);
        pw.flush();
    }


    /**
     * 处理消息，并响应给客户端
     *
     * @param message 客户端发送的消息内容
     * @param session 客户端连接对象
     */
    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);

            log.info("服务端响应消息成功，接收的Session ID：{}，响应内容：{}", session.getId(), message);
        } catch (IOException e) {
            log.error("服务端响应消息异常：{}", e.getMessage());
        }
    }

    public void beginRead() {
        new Thread(() -> {
            String line = null;
            while (!exit) {
                try {
                    line = br.readLine() + System.lineSeparator();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (line == null) {
                    break;
                }

                sendMessage(session, line);
            }
        }).start();
    }

    public void destroy() {
        exit = true;
        pw.close();
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cmd.destroy();
    }
}
