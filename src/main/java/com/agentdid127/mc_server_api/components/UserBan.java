package com.agentdid127.mc_server_api.components;

public class UserBan {
    public String reason;
    public String expires;
    public String source;
    public Player player;

    public UserBan(String reason, String expires, String source, Player player) {
        this.reason = reason;
        this.expires = expires;
        this.source = source;
        this.player = player;
    }
}
