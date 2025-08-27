package com.agentdid127.mc_server_api;

import com.agentdid127.mc_server_api.components.Operator;
import com.agentdid127.mc_server_api.components.Player;
import com.agentdid127.mc_server_api.components.UserBan;

/**
 * A Test program. Do not use publicly.
 */
public class TestProgram {

    public static void main(String[] args) {
        MinecraftServerAPI api = new MinecraftServerAPI("ws://localhost:25585");

        api.init();
        Player[] allowlist = api.allowlist().get();

        Player p;
        if (allowlist.length > 0) {
            for (Player player : allowlist) {
                System.out.println("Player: " + player.name + ", " + player.id);
            }

            Player player = allowlist[0];
            p = allowlist[0];
            api.allowlist().clear();
            System.out.println(api.allowlist().get().length);
            api.allowlist().add(new Player[]{player});
            System.out.println(api.allowlist().get().length);
            api.allowlist().remove(new Player[]{player});
            System.out.println(api.allowlist().get().length);

        } else {
            p = new Player("agentdid127", "d772947a-bd89-4f2f-b67a-904817860c8c");
        }

        UserBan[] bans = api.bans().get();
        System.out.println("Bans: " + bans.length);
        if (bans.length > 0) {
            for (UserBan ban : bans) {
                System.out.println("Ban:" + ban.reason + ", " + ban.player.name);
            }
            UserBan item = bans[0];

            api.bans().clear();
            api.bans().add(new UserBan[]{item});
            System.out.println(api.bans().get().length);
            api.bans().remove(new UserBan[]{item});
            api.bans().clear();
            System.out.println(api.bans().get().length);
            api.bans().set(new UserBan[]{item});
            System.out.println(api.bans().get().length);
            api.bans().clear();
            System.out.println(api.bans().get().length);

        }

        api.operators().clear();
        api.operators().add(new Operator[]{new Operator(p, 3,true)});
        Operator operator = api.operators().get()[0];

        api.operators().remove(new Player[]{operator.player});
        api.operators().set(new Operator[]{operator});

        System.out.println(api.operators().get().length);

        api.operators().remove(new Player[]{operator.player});
        System.out.println(api.operators().get().length);

        System.out.println(api.server().status().version.name);

        api.server().save(true);

        api.server().stop();

        api.close();
    }
}
