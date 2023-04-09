package com.rinpr.warpbangjabin.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TpaRequestStore {
    /**
     * Store tpaRequest from requester to target.
     */
    private static Map<Player, Player> tpaRequest = new HashMap<>();

    /**
     * This static method use to add tpa request to global variables.
     * @param from requester.
     * @param to target.
     */
    public static void addTpaRequest(Player from, Player to) { tpaRequest.put(from, to); }

    /**
     * This static method use to remove tpa request by requester.
     * @param from requester.
     */
    public static void removeTpaRequest(Player from) { tpaRequest.remove(from); }

    /**
     * This static method use to get tpa request by requester.
     * @param from requester.
     */
    public static Player getTpaRequest(Player from) { return tpaRequest.get(from); }
}
