package com.rinpr.warpbangjabin;

import com.rinpr.warpbangjabin.Listener.MovementCheck;
import com.rinpr.warpbangjabin.Listener.TeleportToggle;
import com.rinpr.warpbangjabin.Listener.guiInteraction;
import com.rinpr.warpbangjabin.command.GiveItem;
import com.rinpr.warpbangjabin.command.debug;
import com.rinpr.warpbangjabin.tabcomplete.WarpTabComplete;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class WarpBangJaBin extends JavaPlugin {
    public static WarpBangJaBin plugin;
    public WarpBangJaBin() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        RegisterCommand();
        RegisterListener();
        loadConfig();
    }
    private void loadConfig() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
    private void RegisterCommand() {
        Objects.requireNonNull(this.getCommand("debug-command")).setExecutor(new debug());
        Objects.requireNonNull(this.getCommand("WarpBangJaBin")).setExecutor(new GiveItem());
        Objects.requireNonNull(this.getCommand("WarpBangJaBin")).setTabCompleter(new WarpTabComplete());
    }

    private void RegisterListener() {
        Bukkit.getPluginManager().registerEvents(new guiInteraction(), this);
        Bukkit.getPluginManager().registerEvents(new TeleportToggle(), this);
        Bukkit.getPluginManager().registerEvents(new MovementCheck(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
