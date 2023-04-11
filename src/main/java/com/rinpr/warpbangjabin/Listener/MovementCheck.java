package com.rinpr.warpbangjabin.Listener;

import com.rinpr.warpbangjabin.util.DataStore;
import com.rinpr.warpbangjabin.util.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementCheck implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player target = event.getPlayer();
        if (!DataStore.getIsMove(target)) return;
        DataStore.removeIsMove(target);
        Message.send(target, "&cYou cannot move while teleporting!");
    }

    @EventHandler
    public void onPlayerDamaged(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player victim = (Player) event.getEntity();
        if (!DataStore.getIsMove(victim)) return;
        DataStore.removeIsMove(victim);
        Message.send(victim, "&cYou were damaged, Teleport cancelled!");
    }
}
