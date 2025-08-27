package com.agentdid127.mc_server_api;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.function.Consumer;

public class WebSocket extends WebSocketClient {
    private Consumer<String> responseHandler;

    public WebSocket(URI serverUri, Consumer<String> responseHandler) {
        super(serverUri);
        this.responseHandler = responseHandler;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("onOpen");
    }

    @Override
    public void onMessage(String s) {
        this.responseHandler.accept(s);
    }

    public void setResponseHandler(Consumer<String> responseHandler) {
        this.responseHandler = responseHandler;
    }

    public Consumer<String> getResponseHandler() {
        return responseHandler;
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}
