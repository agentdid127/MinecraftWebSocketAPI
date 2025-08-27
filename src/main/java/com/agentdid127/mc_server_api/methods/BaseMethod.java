package com.agentdid127.mc_server_api.methods;

import com.agentdid127.mc_server_api.JsonRPC;

public abstract class BaseMethod {

    protected final JsonRPC jsonRPC;

    protected final String METHOD;


    public BaseMethod(JsonRPC jsonRPC, String method) {
        this.jsonRPC = jsonRPC;
        this.METHOD = method;
    }
}
