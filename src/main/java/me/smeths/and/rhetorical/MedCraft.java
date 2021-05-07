package me.smeths.and.rhetorical;

import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.ItemManager.ItemLoader;
import me.smeths.and.rhetorical.Listeners.BandageItemListener;
import me.smeths.and.rhetorical.Listeners.MedKitItemListener;
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

        new ItemLoader();
        new PacketHandler();

        Bukkit.getPluginManager().registerEvents(new BandageItemListener(), getPlugin());
        Bukkit.getPluginManager().registerEvents(new MedKitItemListener(), getPlugin());

        loadConfiguration();
    }
    public void loadConfiguration(){
        try {
            if (!getDataFolder().exists()) {
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
    }
    public void onDisable()
    {
        Bukkit.removeRecipe(ItemLoader.getInstance().Medrecipekey);
    }
    public static JavaPlugin getPlugin() {
        return instance;
    }
}