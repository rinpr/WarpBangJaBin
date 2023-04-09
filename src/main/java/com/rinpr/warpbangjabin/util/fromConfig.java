package com.rinpr.warpbangjabin.util;

import com.rinpr.warpbangjabin.WarpBangJaBin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class fromConfig {
    private static final Plugin plugin = WarpBangJaBin.getPlugin(WarpBangJaBin.class);

    /**
     * This static method use to get Title name that show player list from config.
     * @return Title name of player's list gui.
     */
    public static int getRequestKeepalive() {
        return plugin.getConfig().getInt("keep-alive");
    }

    /**
     * This static method use to get space slot.
     * @return Space slot's ItemStack.
     */
    public static ItemStack getSpaceSlot() {
        Material m = Material.matchMaterial(Objects.requireNonNull(plugin.getConfig().getString("space_slot.material")));
        ItemStack result = new ItemStack(Objects.requireNonNull(m));
        ItemMeta meta = result.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("space_slot.name"))));
        meta.setCustomModelData(plugin.getConfig().getInt("space_slot.customdata"));
        result.setItemMeta(meta);
        return result;
    }

    /**
     * This static method use to get instant tp item.
     * @return An item use to trigger instant teleport gui.
     */
    public static ItemStack getTPItem() {
        Material m = Material.matchMaterial(Objects.requireNonNull(plugin.getConfig().getString("teleport_item.material")));
        ItemStack result = new ItemStack(Objects.requireNonNull(m));
        ItemMeta meta = result.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("teleport_item.name"))));
        meta.setCustomModelData(plugin.getConfig().getInt("teleport_item.customdata"));
        result.setItemMeta(meta);
        return result;
    }

    /**
     * This static method use to get teleport request item.
     * @return An item use to trigger teleport request gui.
     */
    public static ItemStack getTPAItem() {
        Material m = Material.matchMaterial(Objects.requireNonNull(plugin.getConfig().getString("teleport_ask_item.material")));
        ItemStack result = new ItemStack(Objects.requireNonNull(m));
        ItemMeta meta = result.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("teleport_ask_item.name"))));
        meta.setCustomModelData(plugin.getConfig().getInt("teleport_ask_item.customdata"));
        result.setItemMeta(meta);
        return result;
    }

    /**
     * This static method use to get Title name that show player list from config.
     * @return Title name of player's list gui.
     */
    public static String getPlayerTitle() {
        return plugin.getConfig().getString("player_list.title_name");
    }

    /**
     * This static method use to get next button in player list gui.
     * @return Next button's ItemStack.
     */
    public static ItemStack getNextButton() {
        Material m = Material.matchMaterial(Objects.requireNonNull(plugin.getConfig().getString("player_list.next.material")));
        ItemStack result = new ItemStack(Objects.requireNonNull(m));
        ItemMeta meta = result.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("player_list.next.name"))));
        meta.setCustomModelData(plugin.getConfig().getInt("player_list.next.customdata"));
        result.setItemMeta(meta);
        return result;
    }

    /**
     * This static method use to get previous button in player list gui.
     * @return Previous button's ItemStack.
     */
    public static ItemStack getPreviousButton() {
        Material m = Material.matchMaterial(Objects.requireNonNull(plugin.getConfig().getString("player_list.previous.material")));
        ItemStack result = new ItemStack(Objects.requireNonNull(m));
        ItemMeta meta = result.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("player_list.previous.name"))));
        meta.setCustomModelData(plugin.getConfig().getInt("player_list.previous.customdata"));
        result.setItemMeta(meta);
        return result;
    }

    /**
     * This static method use to get request's title name from config.
     * @return Title name of request gui.
     */
    public static String getRequestTitle() {
        return plugin.getConfig().getString("request_menu.title_name");
    }

    /**
     * This static method use to get accept button in request gui.
     * @return Accept button's ItemStack.
     */
    public static ItemStack getAcceptButton() {
        Material m = Material.matchMaterial(Objects.requireNonNull(plugin.getConfig().getString("request_menu.accept.material")));
        ItemStack result = new ItemStack(Objects.requireNonNull(m));
        ItemMeta meta = result.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("request_menu.accept.name"))));
        meta.setCustomModelData(plugin.getConfig().getInt("request_menu.accept.customdata"));
        result.setItemMeta(meta);
        return result;
    }

    /**
     * This static method use to get deny button in request gui.
     * @return Deny button's ItemStack.
     */
    public static ItemStack getDenyButton() {
        Material m = Material.matchMaterial(Objects.requireNonNull(plugin.getConfig().getString("request_menu.deny.material")));
        ItemStack result = new ItemStack(Objects.requireNonNull(m));
        ItemMeta meta = result.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("request_menu.deny.name"))));
        meta.setCustomModelData(plugin.getConfig().getInt("request_menu.deny.customdata"));
        result.setItemMeta(meta);
        return result;
    }
}
