package com.rinpr.warpbangjabin.Listener;

import com.rinpr.warpbangjabin.gui.GUIhandler;
import com.rinpr.warpbangjabin.util.DataStore;
import com.rinpr.warpbangjabin.util.Message;
import com.rinpr.warpbangjabin.util.fromConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class guiInteraction implements Listener {
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void playerListHandler(InventoryClickEvent event) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43);
        Player player = (Player) event.getWhoClicked();
        int currentPage = DataStore.getCurrentPage(player);
        if (event.getClickedInventory() == null) return;
        // check if the inventory is not an ordinary one
        if (event.getView().getTitle().equals(fromConfig.getPlayerTitle()) || event.getView().getTitle().equals(fromConfig.getRequestTitle()) || event.getView().getTitle().equals(fromConfig.getRequestTitle() + " ")) { event.setCancelled(true); }
        // to check if the player is clicking on player list slot or not if they do they will teleport to the owner of the skull
        if (list.contains(event.getSlot()) && event.getCurrentItem() != null) {
            String target_player = Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName();
            event.getWhoClicked().teleport(Objects.requireNonNull(Bukkit.getServer().getPlayer(target_player)));
            Message.send(event.getWhoClicked(), "You teleported to " + target_player);
            event.getWhoClicked().closeInventory();
        // next and previous button
        } else if (event.getSlot() == 48 || event.getSlot() == 50 && event.getCurrentItem() != null) {
            switch (event.getSlot()) {
                case 48:
                    if (currentPage >= 1 && currentPage != 1 && isTheresNextPage(currentPage)) {
                        new GUIhandler(player).openTPgui(currentPage - 1);
                        DataStore.updateCurrentPage(player, currentPage - 1);
                    }
                    break;
                case 50:
                    if (currentPage >= 1 && isTheresNextPage(currentPage)) {
                        new GUIhandler(player).openTPgui(currentPage + 1);
                        DataStore.updateCurrentPage(player, currentPage + 1);
                    }
                    break;
            }
        }
    }

    @EventHandler
    public void playerCloseGUI(InventoryCloseEvent event) {
        // check if the inventory is not an ordinary one
        if (event.getView().getTitle().equals(fromConfig.getPlayerTitle()) || event.getView().getTitle().equals(fromConfig.getRequestTitle()) || event.getView().getTitle().equals(fromConfig.getRequestTitle() + " ")) {
            DataStore.removeCurrentPage((Player) event.getPlayer());
        }
    }

    @EventHandler
    public void playerImmediatelyExit(PlayerQuitEvent event) {
        if (DataStore.getCurrentPage(event.getPlayer()) != -1) {
            DataStore.removeCurrentPage(event.getPlayer());
        }
    }

    /** This private method use to check if there's next page for specific player.
     * @param currentPage player's current page.
     * @return true if there's next page available false if there's no next page.
     */
    private boolean isTheresNextPage(int currentPage) {
        return currentPage < (int) Math.ceil( (double) Bukkit.getOnlinePlayers().size() / (double) 28 );
    }
}