package com.agentdid127.mc_server_api.methods;

import com.agentdid127.mc_server_api.JsonRPC;
import com.agentdid127.mc_server_api.components.ServerState;
import com.agentdid127.mc_server_api.components.SystemMessage;

public class Server extends BaseMethod {
    public Server(JsonRPC jsonRPC) {
        super(jsonRPC, "minecraft:server");
    }

    public ServerState status() {
        return MethodCommon.reqNoParams(METHOD + "/status", ServerState.class, jsonRPC);
    }

    public boolean save(boolean flush) {
        return MethodCommon.modifySameType(METHOD + "/save", "flush", flush, Boolean.class, Boolean.class, jsonRPC);
    }

    public boolean stop() {
        return MethodCommon.reqNoParams(METHOD + "/stop", Boolean.class, jsonRPC);
    }

    public boolean system_message(SystemMessage msg) {
        return MethodCommon.modifySameType(METHOD + "/system_message", "message", msg, SystemMessage.class, Boolean.class, jsonRPC);
    }


}
