package com.agentdid127.mc_server_api;

import com.agentdid127.mc_server_api.components.Player;
import com.agentdid127.mc_server_api.components.UserBan;
import com.agentdid127.mc_server_api.methods.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;

import java.net.URISyntaxException;

public class MinecraftServerAPI {

    private JsonRPC jsonRPC;
    private String url;

    private Allowlist allowlist;
    private Bans bans;
    private IPBans ipbans;
    private Operators operators;
    private Players players;
    private Server server;


    public MinecraftServerAPI() {
        this(null, null);
    }

    public MinecraftServerAPI(String url) {
        this(url, null);
    }

    public MinecraftServerAPI(String url, JsonRPC jsonRPC) {
        this.url = url;
        this.jsonRPC = jsonRPC;


    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
        if (jsonRPC != null) {
            jsonRPC = null;
            init();
        }
    }

    public void init() {
        if (this.url == null) {
            throw new IllegalStateException("There is no server url.");
        }

        if (this.jsonRPC == null) {
            try {
                this.jsonRPC = new JsonRPC(url);
                this.jsonRPC.connect();
            } catch (URISyntaxException | InterruptedException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        }

        this.allowlist = new Allowlist(jsonRPC);
        this.bans = new Bans(jsonRPC);
        this.ipbans = new IPBans(jsonRPC);
        this.operators = new Operators(jsonRPC);
        this.players = new Players(jsonRPC);
        this.server = new Server(jsonRPC);
    }

    public void printSchema() {
        JsonObject schema = getSchema().getAsJsonObject();

        String out = "info: \n"
                + "  title: " + schema.get("info").getAsJsonObject().get("title").getAsString() + "\n"
                + "  version: " + schema.get("info").getAsJsonObject().get("version").getAsString() + "\n"
                + "methods: [";

        JsonArray methods = schema.get("methods").getAsJsonArray();

        for (JsonElement method : methods) {
            JsonObject methodObject = method.getAsJsonObject();
            out += "  {\n"
                    + "    name: " + methodObject.get("name").getAsString() + "\n"
                    + "    description: " + methodObject.get("description").getAsString() + "\n"
                    + "    params: [\n";

            JsonArray params = methodObject.get("params").getAsJsonArray();
            for (JsonElement param : params) {
                JsonObject paramObject = param.getAsJsonObject();
                out += "      " + JsonRPC.GSON.toJson(paramObject, JsonElement.class) + "\n";
            }

            out += "    ]\n"
                    + "    result: " + JsonRPC.GSON.toJson(methodObject.get("result"), JsonElement.class) + "\n";
        }
        out += "]\n";

        out += "components: " + new GsonBuilder().setPrettyPrinting().create().toJson(schema.get("components")) + "\n";

        System.out.println("schema: " + out);
    }

    public JsonElement getSchema() {
        JsonElement schema = null;
        try {
            schema = sendRawRequest("rpc.discover", null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return schema;
    }

    public Allowlist allowlist() {
        return allowlist;
    }

    public Bans bans() {
        return bans;
    }

    public IPBans ip_bans() {
        return ipbans;
    }

    public Operators operators() {
        return operators;
    }

    public Players players() {
        return players;
    }

    public Server server() {
        return server;
    }

    public JsonElement sendRawRequest(String method, JsonElement params) throws InterruptedException {
        return this.jsonRPC.sendRequest(method, params);
    }

    public void close() {
        this.jsonRPC.close();
        this.server = null;
        this.allowlist = null;
        this.bans = null;
        this.ipbans = null;
        this.operators = null;
        this.players = null;
    }





}
