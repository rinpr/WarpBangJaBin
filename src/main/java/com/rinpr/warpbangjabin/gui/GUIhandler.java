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

    /** This class use to handle gui creation.
     * @param player you wanted to open gui.
     */
    public GUIhandler(Player player) { this.player = player; }

    /**
     * This public method is used to open the teleport GUI for the given page.
     * @param pageNum The page number to open the GUI for.
     */
    public void openTPgui(int pageNum) { openGUI(pageNum, fromConfig.getPlayerTitle()); }

    /**
     * This public method is used to open the teleport request GUI for the given page.
     * @param pageNum The page number to open the GUI for.
     */
    public void openTPAgui(int pageNum) { openGUI(pageNum, fromConfig.getRequestTitle()); }

    /**
     * This private method is used to create the GUI with the given page number and title.
     * @param pageNum The page number to create the GUI for.
     * @param title The title of the GUI.
     */
    private void openGUI(int pageNum, String title) {
        Inventory inv = Bukkit.createInventory(player, 54, title);
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