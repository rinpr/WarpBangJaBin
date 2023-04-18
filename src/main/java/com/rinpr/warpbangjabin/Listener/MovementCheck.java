package com.rinpr.warpbangjabin.Listener;

import com.rinpr.warpbangjabin.util.DataStore;
import com.rinpr.warpbangjabin.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementCheck implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getTo().getBlockX() == event.getFrom().getBlockX() && event.getTo().getBlockY() == event.getFrom().getBlockY() && event.getTo().getBlockZ() == event.getFrom().getBlockZ()) return; //The player hasn't moved
        if (DataStore.containsKey(player)) {
            // Cancel the task and send a cancellation message if the player moves
            int taskId = DataStore.getTpTask(player).getTaskId();
            if (taskId != -1) {
                Bukkit.getScheduler().cancelTask(taskId);
                Message.send(player, "Teleportation cancelled: You moved.");
            }
            DataStore.removeIsMove(player);
        }
    }
}
