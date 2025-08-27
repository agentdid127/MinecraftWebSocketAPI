package com.agentdid127.mc_server_api.components;

public class TypedGameRule {

    public String type;
    public String value;
    public String key;

    public enum Type{
        INTEGER("integer"),
        BOOLEAN("boolean");

        private String value;
        Type(String value) {
            this.value = value;
        }

        public Type fromString(String val) {
            for (Type type : Type.values()) {
                if (type.value.equals(val)) {
                    return type;
                }
            }
            return null;
        }

    }
}
