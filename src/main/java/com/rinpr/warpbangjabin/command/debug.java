package com.rinpr.warpbangjabin.command;

import com.rinpr.warpbangjabin.util.DataStore;
import com.rinpr.warpbangjabin.WarpBangJaBin;
import com.rinpr.warpbangjabin.gui.GUIhandler;
import com.rinpr.warpbangjabin.util.Message;
import com.rinpr.warpbangjabin.util.PlayerSkull;
import com.rinpr.warpbangjabin.util.SmartGive;
import com.rinpr.warpbangjabin.util.fromConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class debug implements CommandExecutor {
    private static final Plugin plugin = WarpBangJaBin.getPlugin(WarpBangJaBin.class);
    Map<Player, Player> tpaRequest = new HashMap<>();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            Message.send("&cOnly for player right now!");
            return true;
        }
        Player player = (Player) commandSender;
        if (args.length == 0) {
            Message.send(commandSender, "&cYou have no permission to use this command!");
            return true;
        } else if (args.length == 1) {
            Player requestor = DataStore.getTpaRequest(player);
            switch (args[0]) {
                case "getskull":
                    new SmartGive(player).give(PlayerSkull.getPlayerSkull(player));
                    break;
                case "opengui":
                    new GUIhandler(player).openTPgui(1);
                    break;
                case "tpaccept":
                    if (requestor != null) {
                        // Add a delay of 3 seconds before teleporting the player
                        int delay = 3;
                        Message.send(requestor, "Teleporting in " + delay + " seconds. Don't move!");
                        DataStore.setIsMove(requestor);

                        // Schedule a task to teleport the player after the delay
                        BukkitTask task = Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            // Check if the player has moved during the delay period
                            if (!DataStore.getIsMove(requestor)) {
                                requestor.teleport(player);
                                DataStore.removeTpaRequest(player);
                                Message.send(requestor, "Teleported to " + player.getName());
                            } else {
                                Message.send(requestor, "Teleportation cancelled: You moved.");
                            }
                            // Remove the move check flag and teleportation task for the player
                            DataStore.removeIsMove(requestor);
                            DataStore.removeTpTask(requestor);
                        }, delay * 20L); // Convert seconds to ticks

                        // Associate the task with the player in the DataStore
                        DataStore.setTpTask(requestor, task);
                    } else {
                        Message.send(player, "&cThere's no pending request.");
                    }
                    break;
                case "tpagui":
                    if (requestor != null) {
                        new GUIhandler(player).openRequestGUI();
                    } else {
                        Message.send(player, "&cThere's no pending request.");
                    }
                    break;
            }
        } else if (args.length == 2) {
            if (args[0].equals("tpa")) {
                Player target = Bukkit.getPlayer(args[1]);
                DataStore.addTpaRequest(target, player);
                Message.send(target, player.getName() + " wants to tp to you");
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    DataStore.removeTpaRequest(target);
                    Message.send(target, "&cYour teleport request timed out!");
                }, 20L * fromConfig.getRequestKeepalive());
            }
        }
        return false;
    }
}
