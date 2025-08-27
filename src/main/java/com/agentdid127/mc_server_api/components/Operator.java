package com.agentdid127.mc_server_api.components;

public class Operator {
    public int permissionLevel;
    public boolean bypassesPlayerLimit;
    public Player player;

    public Operator(Player player, int permissionLevel, boolean bypassesPlayerLimit) {
        this.player = player;
        this.permissionLevel = permissionLevel;
        this.bypassesPlayerLimit = bypassesPlayerLimit;
    }
}
