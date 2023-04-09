package com.rinpr.warpbangjabin.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TpaRequestStore {
    private static Map<Player, Player> tpaRequest = new HashMap<>();

    public static void addTpaRequest(Player from, Player to) {
        tpaRequest.put(from, to);
    }

    public static void removeTpaRequest(Player from) {
        tpaRequest.remove(from);
    }

    public static Player getTpaRequest(Player from) {
        return tpaRequest.get(from);
    }
}
