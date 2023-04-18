package com.rinpr.warpbangjabin.util;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

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
     * Store player's isMove boolean.
     */
    private static Map<Player, Boolean> isMove = new HashMap<>();
    private static Map<Player, BukkitTask> tpTask = new HashMap<>();

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
     * @param player whom you wanted to remove their current page.
     */
    public static void removeCurrentPage(Player player) { currentPage.remove(player); }

    /**
     * This static method use to get player's current page.
     * @param player whom you wanted to get their current page.
     * @return The player's current page or -1 If there's no value
     */
    public static int getCurrentPage(Player player) { return currentPage.getOrDefault(player, -1); }

    /**
     * This static method use to update player's current page.
     * @param player whom you wanted to update their new current page.
     * @param page new page you wanted to update to.
     */
    public static void updateCurrentPage(Player player, Integer page) { currentPage.replace(player, page); }

    /**
     * This static method use to add player's isMove boolean to global variable.
     * @param player whom you want to store a isMove boolean data.
     */
    public static void setIsMove(Player player) { isMove.put(player, false); }

    /**
     * This static method use to remove player's isMove boolean to global variable.
     * @param player whom you want to remove a isMove boolean data.
     */
    public static void removeIsMove(Player player) { isMove.remove(player); }

    /**
     * This static method use to get player's isMove boolean to global variable.
     * @param player whom you want to get a isMove boolean data.
     * @return true if player have moved, false if not.
     */
    public static boolean getIsMove(Player player) { return isMove.get(player); }

    /**
     * This static method use to check if given player has a key in isMove map or not.
     * @param player You wanted to check if they have a key or not.
     * @return true if there's a key , else false.
     */
    public static boolean containsKey(Player player) { return isMove.containsKey(player); }

    /**
     * This static method use to update player's isMove boolean to global variable.
     * @param player whom you want to update a isMove boolean data.
     */
    public static void updateIsMove(Player player) { isMove.replace(player, true); }

    public static void setTpTask(Player player, BukkitTask task) { tpTask.put(player, task); }

    public static BukkitTask getTpTask(Player player) { return tpTask.get(player); }

    public static void removeTpTask(Player player) { tpTask.remove(player); }
}
