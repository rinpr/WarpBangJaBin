package com.rinpr.warpbangjabin.Listener;

import com.rinpr.warpbangjabin.gui.GUIhandler;
import com.rinpr.warpbangjabin.util.DataStore;
import com.rinpr.warpbangjabin.util.Message;
import com.rinpr.warpbangjabin.util.fromConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TeleportToggle implements Listener {

    @EventHandler
    public void useWarpItem(PlayerInteractEvent event) {
        Player user = event.getPlayer();
        ItemStack in_hand = event.getItem();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && in_hand != null) {
            if (user.getInventory().getItemInMainHand().isSimilar(fromConfig.getTPItem())) {
                new GUIhandler(user).openTPgui(1);
                DataStore.addCurrentPage(user, 1);
                if (in_hand.getAmount() > 1) {
                    in_hand.setAmount(in_hand.getAmount() - 1); }
                else { user.setItemInHand(null); }
            } else if (user.getInventory().getItemInMainHand().isSimilar(fromConfig.getTPAItem())) {
                new GUIhandler(user).openTPAgui(1);
                DataStore.addCurrentPage(user, 1);
                if (in_hand.getAmount() > 1) {
                    in_hand.setAmount(in_hand.getAmount() - 1); }
                else { user.setItemInHand(null); }
            }
        }
    }
}
