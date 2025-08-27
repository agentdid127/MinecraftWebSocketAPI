package com.agentdid127.mc_server_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * JSONRPC 2.0 Client
 */
public class JsonRPC {

    // GSON
    public static Gson GSON = null;

    // id of request
    private int id;

    private WebSocket webSocket;


    // responses
    private final Map<Integer, JsonObject> responses;

    /**
     * Constructor. Creates a connection for JSONRPC/WS.
     * @param url url of websocket server.
     */
    public JsonRPC(String url) throws URISyntaxException {
        if (GSON == null) {
            GSON = new GsonBuilder().disableHtmlEscaping().create();
        }
        this.id = 0;
        this.responses = new LinkedHashMap<>();
        this.webSocket = new WebSocket(new URI(url), (response -> {
            JsonObject responseJson = GSON.fromJson(response, JsonObject.class);
            if (responseJson.has("id")) {
                responses.put(responseJson.get("id").getAsInt(), responseJson);
            }
        }));
    }

    /**
     * Sends a request and awaits a response.
     * @param method method of request.
     * @param params Request Parameters.
     * @return A JsonElement of the request.
     */
    public <T> T sendRequest(String method, JsonElement params, Class<T> clazz) throws InterruptedException {
        int id = ++this.id;
        sendRequestInternal(method, params, Optional.of(id));

        int timeout = 10;
        while (!responses.containsKey(id) && timeout > 0) {
            Thread.sleep(1000);
            timeout--;
        }

        if (timeout == 0) {
            throw new IllegalStateException("Request timed out");
        }

        JsonObject response = responses.remove(id);

        if (response.has("error")) {
            JsonObject error = response.get("error").getAsJsonObject();
            int code = error.get("code").getAsInt();
            String message = error.get("message").getAsString();
            JsonElement data = error.get("data");

            throw new IllegalStateException(code + ": " + message + "; data: " + data + ", for request: " + method);
        }
        if (!response.has("result")) {
            throw new IllegalStateException("Response has no result");
        }
        String result = GSON.toJson(response.get("result"));
        return GSON.fromJson(result, clazz);
    }

    public JsonElement sendRequest(String method, JsonElement params) throws InterruptedException {
        return sendRequest(method, params, JsonElement.class);
    }

    /**
     * Sends a notification to the API.
     * @param method Request method
     * @param params Request parameters.
     */
    public void sendNotification(String method, JsonElement params) {
        sendRequestInternal(method, params, Optional.empty());

    }

    /**
     * Internal Request sender
     * @param method Request method
     * @param params Request parameters
     * @param id Optional request id. if this is present, then it is a request, otherwise notification.
     */
    private void sendRequestInternal(String method, JsonElement params, Optional<Integer> id) {
        JsonObject request = new JsonObject();
        id.ifPresent(integer -> request.addProperty("id", integer));
        request.addProperty("method", method);
        if (params != null) {
            request.add("params", params);
        }
        request.addProperty("jsonrpc", "2.0");

        String requestJson = GSON.toJson(request);
        webSocket.send(requestJson);
    }

    public void close() {
        webSocket.close();
    }

    public void reconnect() {
        webSocket.reconnect();
    }

    public void connect() throws InterruptedException {
        webSocket.connect();
        int wait = 10;
        while (!webSocket.isOpen()) {
            Thread.sleep(1000);
        }
    }

    public boolean isConnected() {
        return webSocket.isOpen();
    }


}
