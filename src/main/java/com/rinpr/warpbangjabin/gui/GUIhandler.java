package com.rinpr.warpbangjabin.gui;

import com.rinpr.warpbangjabin.util.PlayerSkull;
import com.rinpr.warpbangjabin.util.fromConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * This class handling gui creation for this plugin. This contains open teleport gui and
 * open teleport request method.
 */
public class GUIhandler {
    private final Player player;
    private final int[] space_slot = new int[]{0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,49,51,52,53};
    private final int[] player_slot = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43};
    private final int playersPerPage = player_slot.length;
    public GUIhandler(Player player) {
        this.player = player;
    }

    /** This public method use to open teleport gui for player with given page.
     * @param pageNum page number you want to open for this gui
     */
    public void openTPgui(int pageNum) {
        Inventory inv = Bukkit.createInventory(player, 54, fromConfig.getPlayerTitle());
        inv.setItem(48, fromConfig.getPreviousButton());
        inv.setItem(50, fromConfig.getNextButton());
        for (int slot : space_slot) { inv.setItem(slot,fromConfig.getSpaceSlot()); }
        List<ItemStack> active_player = PlayerSkull.getOnlinePlayerSkull();
        int startIndex = (pageNum - 1) * playersPerPage;
        int endIndex = Math.min(startIndex + playersPerPage, active_player.size());
        for (int i = startIndex; i < endIndex; i++) {
            inv.setItem(player_slot[i - startIndex], active_player.get(i));
        }
        player.openInventory(inv);
    }

    /** This public method use to open teleport request gui for player with given page.
     * @param pageNum page number you want to open for this gui
     */
    public void openTPAgui(int pageNum) {
        Inventory inv = Bukkit.createInventory(player, 54, fromConfig.getPlayerTitle() + " ");
        inv.setItem(48, fromConfig.getPreviousButton());
        inv.setItem(50, fromConfig.getNextButton());
        for (int slot : space_slot) { inv.setItem(slot,fromConfig.getSpaceSlot()); }
        List<ItemStack> active_player = PlayerSkull.getOnlinePlayerSkull();
        int startIndex = (pageNum - 1) * playersPerPage;
        int endIndex = Math.min(startIndex + playersPerPage, active_player.size());
        for (int i = startIndex; i < endIndex; i++) {
            inv.setItem(player_slot[i - startIndex], active_player.get(i));
        }
        player.openInventory(inv);
    }
}
