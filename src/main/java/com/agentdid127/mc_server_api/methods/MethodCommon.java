package com.agentdid127.mc_server_api.methods;

import com.agentdid127.mc_server_api.JsonRPC;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MethodCommon {

    public static <T,V> V modifySameType(String method, String argName, T value, Class<T> argType, Class<V> type, JsonRPC jsonRPC) {
        JsonArray params = new JsonArray();
        params.add(JsonRPC.GSON.toJsonTree(value, argType));
        try {
            return jsonRPC.sendRequest(method, params, type);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T,V> V arrayType(String method, String argName, T value, Class<V> type, JsonRPC jsonRPC) {
        try {
            return jsonRPC.sendRequest(method, JsonRPC.GSON.toJsonTree(value), type);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T reqNoParams(String method, Class<T> type, JsonRPC jsonRPC) {
        try {
            return jsonRPC.sendRequest(method, null, type);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
