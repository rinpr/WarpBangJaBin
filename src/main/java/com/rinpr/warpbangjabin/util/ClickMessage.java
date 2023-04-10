package com.rinpr.warpbangjabin.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ClickMessage {
    public static void sendTPA(Player target, Player sender) {
        TextComponent result = new TextComponent(ChatColor.GOLD + sender.getName() + ChatColor.GREEN + " send a teleport request to you.");
        result.setColor(ChatColor.GOLD);
        result.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("Click here to accept teleport").color(ChatColor.GRAY).create()));
        result.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/debug-command tpaccept"));
        target.spigot().sendMessage(result);
    }
}
