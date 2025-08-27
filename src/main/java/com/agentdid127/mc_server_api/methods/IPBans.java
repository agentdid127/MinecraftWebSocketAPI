package com.agentdid127.mc_server_api.methods;

import com.agentdid127.mc_server_api.JsonRPC;
import com.agentdid127.mc_server_api.components.IPBan;
import com.agentdid127.mc_server_api.components.IncomingIPBan;
import com.agentdid127.mc_server_api.components.Player;
import com.agentdid127.mc_server_api.components.UserBan;
import com.agentdid127.mc_server_api.methods.results.BanlistResult;
import com.agentdid127.mc_server_api.methods.results.IPBanResult;
import com.agentdid127.mc_server_api.methods.results.IPBansResult;

public class IPBans extends BaseMethod {


    public IPBans(JsonRPC jsonRPC) {
        super(jsonRPC, "minecraft:ip_bans");
    }


    public Player[] get() {
        return MethodCommon.reqNoParams(METHOD, Player[].class, jsonRPC);
    }

    public IPBan[] set(IPBan[] banlist) {
        return MethodCommon.modifySameType(METHOD + "/set", "banlist", banlist, IPBan[].class, IPBan[].class, jsonRPC);
    }

    public IPBan[] add(IncomingIPBan[] banlist) {
        return MethodCommon.modifySameType(METHOD + "/add", "add", banlist, IncomingIPBan[].class, IPBan[].class, jsonRPC);
    }

    public IPBan[] remove(String[] ip) {
        return MethodCommon.modifySameType(METHOD + "/remove", "remove", ip, String[].class, IPBan[].class, jsonRPC);
    }

    public IPBan[] clear() {
        return MethodCommon.reqNoParams(METHOD + "/clear", IPBan[].class, jsonRPC);
    }
}
