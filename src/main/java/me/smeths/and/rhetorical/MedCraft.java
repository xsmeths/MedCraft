package me.smeths.and.rhetorical;

import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.Listeners.MedCraftListeners;
import me.smeths.and.rhetorical.Config.ConfigParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MedCraft extends JavaPlugin {
    private static MedCraft instance;
    public void onEnable() {
        if (instance != null) {
            return;
        }
        instance = this;
        new PacketHandler();
        Bukkit.getPluginManager().registerEvents(new MedCraftListeners(), getPlugin());
        loadConfiguration();
    }
    public void loadConfiguration() {
        try {
            if (!getDataFolder().exists()) {
                //noinspection ResultOfMethodCallIgnored
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getConfig().options().copyDefaults(true);
                saveConfig();
            } else {
                MedCraft.getPlugin().saveDefaultConfig();
            }
            MedCraft.getPlugin().reloadConfig();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ConfigParser parser = new ConfigParser(getConfig());
        parser.loadItems();
    }
    public void onDisable() {
    }
    public void onLoad() {
    }
    public static JavaPlugin getPlugin() {
        return instance;
    }
}