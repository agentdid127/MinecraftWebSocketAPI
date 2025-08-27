package com.agentdid127.mc_server_api.methods;

import com.agentdid127.mc_server_api.JsonRPC;
import com.agentdid127.mc_server_api.components.Player;
import com.agentdid127.mc_server_api.methods.results.AllowlistResult;

public class Allowlist extends BaseMethod {

    public Allowlist(JsonRPC jsonRPC) {
        super(jsonRPC, "minecraft:allowlist");
    }


    public Player[] get() {
        return MethodCommon.reqNoParams(METHOD, Player[].class, jsonRPC);
    }

    public Player[] set(Player[] players) {
        return MethodCommon.modifySameType(METHOD + "/set", "players", players, Player[].class, Player[].class, jsonRPC);
    }

    public Player[] add(Player[] players) {
        return MethodCommon.modifySameType(METHOD + "/add", "add", players, Player[].class, Player[].class, jsonRPC);
    }

    public Player[] remove(Player[] players) {
        return MethodCommon.modifySameType(METHOD + "/remove", "remove", players, Player[].class, Player[].class, jsonRPC);
    }

    public boolean clear() {
        return MethodCommon.reqNoParams(METHOD + "/clear", Boolean.class, jsonRPC);
    }

}
