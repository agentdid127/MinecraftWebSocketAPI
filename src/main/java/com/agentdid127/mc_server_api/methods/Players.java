package com.agentdid127.mc_server_api.methods;

import com.agentdid127.mc_server_api.JsonRPC;
import com.agentdid127.mc_server_api.components.KickPlayer;
import com.agentdid127.mc_server_api.components.Player;
import com.agentdid127.mc_server_api.methods.results.GetPlayerResult;
import com.agentdid127.mc_server_api.methods.results.KickPlayerResult;

public class Players extends BaseMethod {
    public Players(JsonRPC jsonRPC) {
        super(jsonRPC, "minecraft:players");
    }

    public Player[] get() {
        return MethodCommon.reqNoParams(METHOD, Player[].class, jsonRPC);
    }

    public Player[] kick(KickPlayer[] kickPlayers) {
        return MethodCommon.modifySameType(METHOD + "/kick", "kick", kickPlayers, KickPlayer[].class, Player[].class, jsonRPC);
    }
}
