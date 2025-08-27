package com.agentdid127.mc_server_api.methods;

import com.agentdid127.mc_server_api.JsonRPC;
import com.agentdid127.mc_server_api.components.Operator;
import com.agentdid127.mc_server_api.components.Player;
import com.agentdid127.mc_server_api.methods.results.OperatorResult;

public class Operators extends BaseMethod {
    public Operators(JsonRPC jsonRPC) {
        super(jsonRPC, "minecraft:operators");
    }

    public Operator[] get() {
        return MethodCommon.reqNoParams(METHOD, Operator[].class, jsonRPC);
    }

    public Operator[] set(Operator[] operators) {
        return MethodCommon.modifySameType(METHOD + "/set", "operators", operators, Operator[].class, Operator[].class, jsonRPC);
    }


    public Operator[] add(Operator[] operators) {
        return MethodCommon.modifySameType(METHOD + "/add", "add", operators, Operator[].class, Operator[].class, jsonRPC);
    }


    public Operator[] remove(Player[] operators) {
        return MethodCommon.modifySameType(METHOD + "/remove", "remove", operators, Player[].class, Operator[].class, jsonRPC);
    }

    public boolean clear() {
        return MethodCommon.reqNoParams(METHOD + "/clear", Boolean.class, jsonRPC);
    }
}
