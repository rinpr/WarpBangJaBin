package com.rinpr.warpbangjabin.tabcomplete;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WarpTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender,@NotNull Command command,@NotNull String s, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> argument = new ArrayList<>();
        if (args.length == 1) {
            if (commandSender.hasPermission("warpbangjabin.admin")) {
                argument.add("givetp");
                argument.add("givetpa");
                argument.add("reload");
            }
            StringUtil.copyPartialMatches(args[0], argument, completions);
            Collections.sort(completions);
        } else if (args.length == 2 & !Objects.equals(args[0], "reload")) {
            if (commandSender.hasPermission("warpbangjabin.admin")) {
                for (Player p : Bukkit.getOnlinePlayers()) { argument.add(p.getName()); }
            }
            StringUtil.copyPartialMatches(args[1], argument, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
