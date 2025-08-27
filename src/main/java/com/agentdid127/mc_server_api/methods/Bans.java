package com.agentdid127.mc_server_api.methods;

import com.agentdid127.mc_server_api.JsonRPC;
import com.agentdid127.mc_server_api.components.UserBan;
import com.agentdid127.mc_server_api.methods.results.BanlistResult;

public class Bans extends BaseMethod {

    public Bans(JsonRPC jsonRPC) {
        super(jsonRPC, "minecraft:bans");
    }


    public UserBan[] get() {
        return MethodCommon.reqNoParams(METHOD, UserBan[].class, jsonRPC);
    }

    public UserBan[] set(UserBan[] players) {
        return MethodCommon.modifySameType(METHOD + "/set", "bans", players, UserBan[].class, UserBan[].class, jsonRPC);
    }

    public UserBan[] add(UserBan[] players) {
        return MethodCommon.modifySameType(METHOD + "/add", "add", players, UserBan[].class, UserBan[].class, jsonRPC);
    }

    public UserBan[] remove(UserBan[] players) {
        return MethodCommon.modifySameType(METHOD + "/remove", "remove", players, UserBan[].class, UserBan[].class, jsonRPC);
    }

    public boolean clear() {
        return MethodCommon.reqNoParams(METHOD + "/clear", Boolean.class, jsonRPC);
    }
}
