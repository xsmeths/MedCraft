package me.smeths.and.rhetorical;

import me.smeths.and.rhetorical.BandageItemManagement.BandageItemListener;
import me.smeths.and.rhetorical.BandageItemManagement.BandageItemLoader;
import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.MedKitItemManagement.MedKitItemListener;
import me.smeths.and.rhetorical.MedKitItemManagement.MedKitItemLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MedCraft extends JavaPlugin
{
    private static MedCraft instance;

    public void onEnable()
    {
        if (instance != null) {
            return;
        }
        instance = this;

        new BandageItemLoader();
        new MedKitItemLoader();
        new PacketHandler();

        Bukkit.getPluginManager().registerEvents(new BandageItemListener(), getPlugin());
        Bukkit.getPluginManager().registerEvents(new MedKitItemListener(), getPlugin());

        loadConfiguration();
    }
    public void loadConfiguration(){
        try {
            if (!getDataFolder().exists()) {
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
    }
    public void onDisable()
    {
        Bukkit.removeRecipe(MedKitItemLoader.getInstance().key);
        Bukkit.removeRecipe(BandageItemLoader.getInstance().recipekey);
    }
    public static MedCraft getInstance() {
        return instance;
    }
    public static JavaPlugin getPlugin() {
        return instance;
    }
}