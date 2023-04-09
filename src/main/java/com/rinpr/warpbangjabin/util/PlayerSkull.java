package com.rinpr.warpbangjabin.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerSkull {

    /**
     * This static method use to get all active/online player's head.
     * @return ItemStack list of online player's head.
     */
    public static List<ItemStack> getOnlinePlayerSkull() {
        List<ItemStack> active_playerskull = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) { active_playerskull.add(PlayerSkull.getPlayerSkull(p)); }
        return active_playerskull;
    }

    /**
     * This static method use to get player's head.
     * @param player A player whom you wanted to get their head.
     * @return A player's head.
     */
    public static ItemStack getPlayerSkull(Player player) {
        ItemStack target_head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) target_head.getItemMeta();
        assert meta != null;
        meta.setOwningPlayer(player);
        meta.setDisplayName(player.getDisplayName());
        target_head.setItemMeta(meta);
        return target_head;
    }
}
