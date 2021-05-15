package me.smeths.and.rhetorical;

import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.ItemManager.ItemLoader;
import me.smeths.and.rhetorical.Listeners.MedCraftListeners;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MedCraft extends JavaPlugin {
    private static MedCraft instance;

    public void onEnable() {
        if (instance != null) {
            return;
        }
        instance = this;
        new ItemLoader();
        new PacketHandler();
        Bukkit.getPluginManager().registerEvents(new MedCraftListeners(), getPlugin());
        loadConfiguration();
    }
    public void loadConfiguration() {
        try { if (!getDataFolder().exists()) {
                //noinspection ResultOfMethodCallIgnored
            getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getConfig().options().copyDefaults(true);
                saveConfig();
                MedCraft.getPlugin().reloadConfig();
            } else {
                MedCraft.getPlugin().saveDefaultConfig();
                MedCraft.getPlugin().reloadConfig();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } public void onDisable() {
        Bukkit.removeRecipe(ItemLoader.getInstance().Medrecipekey);
        Bukkit.removeRecipe(ItemLoader.getInstance().Bandagerecipekey);
    }
    public void onLoad(){
    }
    public static JavaPlugin getPlugin() {
        return instance;
    }
}