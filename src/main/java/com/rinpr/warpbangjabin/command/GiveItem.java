package com.rinpr.warpbangjabin.command;

import com.rinpr.warpbangjabin.util.Message;
import com.rinpr.warpbangjabin.util.SmartGive;
import com.rinpr.warpbangjabin.util.fromConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.rinpr.warpbangjabin.WarpBangJaBin.plugin;

public class GiveItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("wbjb") || label.equalsIgnoreCase("warpbangjabin")) {
            if (args.length == 0) {
                Message.send(commandSender, "Command Usage: /warpbangjabin givetp, givetpa <player>");
                return true;
            } else {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        plugin.reloadConfig();
                        Message.send(commandSender, "&aSuccessfully reloaded plugin");
                        return true;
                    }
                    Message.send(commandSender, "Command Usage: /warpbangjabin givetp, givetpa <player>");
                    return true;
                } else if (args.length == 2) {
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    if (args[0].equalsIgnoreCase("givetp")) {
                        new SmartGive(Objects.requireNonNull(target)).give(fromConfig.getTPItem());
                    } else if (args[0].equalsIgnoreCase("givetpa")) {
                        new SmartGive(Objects.requireNonNull(target)).give(fromConfig.getTPAItem());
                    }
                }
            }
        }
        return false;
    }
}
