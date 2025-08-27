package com.agentdid127.mc_server_api.components;

public class SystemMessage {
    public Player[] receivingPlayers;
    public boolean overlay;
    public Message message;

    public SystemMessage(Player[] receivingPlayers, boolean overlay, Message message) {
        this.receivingPlayers = receivingPlayers;
        this.overlay = overlay;
        this.message = message;
    }
}
