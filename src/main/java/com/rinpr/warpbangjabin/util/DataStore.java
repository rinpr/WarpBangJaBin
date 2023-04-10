package com.rinpr.warpbangjabin.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class DataStore {

    /**
     * Store tpaRequest from requester to target.
     */
    private static Map<Player, Player> tpaRequest = new HashMap<>();

    /**
     * Store player's current page.
     */
    private static Map<Player, Integer> currentPage = new HashMap<>();

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

    /**
     * This static method use to add player's current page to global variables.
     * @param player requester.
     * @param page target.
     */
    public static void addCurrentPage(Player player, Integer page) { currentPage.put(player, page); }

    /**
     * This static method use to remove player's current page.
     * @param player player you wanted to remove their current page.
     */
    public static void removeCurrentPage(Player player) { currentPage.remove(player); }

    /**
     * This static method use to get player's current page.
     * @param player player you wanted to get their current page.
     * @return The player's current page or -1 If there's no value
     */
    public static int getCurrentPage(Player player) { return currentPage.getOrDefault(player, -1); }

    /**
     * This static method use to update player's current page.
     * @param player player you wanted to update their new current page.
     * @param page new page you wanted to update to.
     */
    public static void updateCurrentPage(Player player, Integer page) { currentPage.replace(player, page); }
}
