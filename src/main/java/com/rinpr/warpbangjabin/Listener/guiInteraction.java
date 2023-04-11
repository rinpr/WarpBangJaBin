package com.rinpr.warpbangjabin.Listener;

import com.rinpr.warpbangjabin.WarpBangJaBin;
import com.rinpr.warpbangjabin.gui.GUIhandler;
import com.rinpr.warpbangjabin.util.ClickMessage;
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
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class guiInteraction implements Listener {
    private static final Plugin plugin = WarpBangJaBin.getPlugin(WarpBangJaBin.class);
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void playerListHandler(InventoryClickEvent event) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43);
        Player player = (Player) event.getWhoClicked();

        int currentPage = DataStore.getCurrentPage(player);

        // if player click at their inventory or outside inventory will cancel the event
        if (event.getClickedInventory() == null || event.getClickedInventory() == event.getWhoClicked().getInventory()) {
            event.setCancelled(true);
            return;
        }

        // if it's not this plugin gui it will do nothing, else will cancel every click event. (for item movable in gui safety)
        if (event.getView().getTitle().equals(fromConfig.getTeleportTitle()) ||  event.getView().getTitle().equals(fromConfig.getTeleportRequestTitle()) || event.getView().getTitle().equals(fromConfig.getRequestTitle())) { event.setCancelled(true); }

        // for teleport gui
        if (event.getView().getTitle().equals(fromConfig.getTeleportTitle())) {
            // to check if the player is clicking on player list slot or not if they do they will teleport to the owner of the skull
            if (list.contains(event.getSlot()) && event.getCurrentItem() != null) {
                Player target = Bukkit.getPlayer(Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName());
                // check if the target is not yourself.
                if (target == player) {
                    event.setCancelled(true);
                    return;
                }
                event.getWhoClicked().teleport(Objects.requireNonNull(Bukkit.getServer().getPlayer(target.getName())));
                Message.send(event.getWhoClicked(), "You teleported to " + target.getName());
                event.getWhoClicked().closeInventory();
                // next and previous button.
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
            // for teleport ask gui.
        } else if (event.getView().getTitle().equals(fromConfig.getTeleportRequestTitle())) {
            // to check if the player is clicking on player list slot or not if they do they will teleport to the owner of the skull
            if (list.contains(event.getSlot()) && event.getCurrentItem() != null) {
                Player target = Bukkit.getPlayer(Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName());
                // check if the target is not yourself.
                if (target == player) {
                    event.setCancelled(true);
                    return;
                }
                // tpa request logic here
                DataStore.addTpaRequest(target, player);
                ClickMessage.sendTPA(target, player);
                // set tpa request timeout cool down.
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    if (DataStore.getTpaRequest(target) != null) {
                        Message.send(target, "&cYour teleport request timed out!");
                    }
                    DataStore.removeTpaRequest(target);
                }, 20L * fromConfig.getRequestKeepalive());

                Message.send(event.getWhoClicked(), "You send a teleport request to " + Objects.requireNonNull(target).getDisplayName());
                event.getWhoClicked().closeInventory();
                // next and previous button.
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
            // for teleport request gui
        } else if (event.getView().getTitle().equals(fromConfig.getRequestTitle())) {
            switch (event.getSlot()) {
                // accept
                case 12:
                    player.performCommand("debug-command tpaccept");
                    DataStore.removeTpaRequest(player);
                    event.getWhoClicked().closeInventory();
                    break;
                    // decline
                case 14:
                    event.getWhoClicked().closeInventory();
                    break;
                    // other button
                default:
                    break;
            }
        }
    }

    @EventHandler
    public void playerCloseGUI(InventoryCloseEvent event) {
        // check if the gui is teleport and teleport ask.
        if (event.getView().getTitle().equals(fromConfig.getTeleportTitle()) || event.getView().getTitle().equals(fromConfig.getTeleportRequestTitle())) {
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