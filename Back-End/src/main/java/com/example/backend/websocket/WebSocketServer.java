package com.example.backend.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/websocket/order/{userId}")
public class WebSocketServer {
    private static AtomicInteger COUNT = new AtomicInteger();

    private static Map<Integer, Session> SESSIONS = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("userId") int userId, Session session) throws IOException {
        if (SESSIONS.get(userId) != null)
            return;
        COUNT.incrementAndGet();
        SESSIONS.put(userId, session);
        System.out.println(userId + " connects to ws!");
    }

    @OnClose
    public void onClose(@PathParam("userId") int userId, Session session) throws IOException {
        SESSIONS.remove(userId);
        COUNT.decrementAndGet();
        System.out.println(userId + " disconnects from ws!");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("WebSocketServer has received message: " + message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("WebSocketServer Error!");
        throwable.printStackTrace();
    }

    public void sendMessageToUser(int userId, String message) {
        Session toSession = SESSIONS.get(userId);
        if (toSession != null) {
            try {
                toSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User: " + userId + " is offline!");
        }
        System.out.println(message);
    }
}
