package com.agentdid127.mc_server_api.components;

public class Message {
    public String translatable;
    public String[] translatableParams;
    public String literal;

    public Message(String translatable, String[] translatableParams, String literal) {
        this.translatable = translatable;
        this.translatableParams = translatableParams;
        this.literal = literal;
    }
}
