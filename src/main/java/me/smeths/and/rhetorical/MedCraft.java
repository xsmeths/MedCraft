package me.smeths.and.rhetorical;

import me.smeths.and.rhetorical.customitems.BandageEvents;
import me.smeths.and.rhetorical.customitems.CustomItemUtil;
import me.smeths.and.rhetorical.handlers.PacketHandler;
import me.smeths.and.rhetorical.itemmanager.ItemLoader;
import me.smeths.and.rhetorical.Listeners.MedCraftListeners;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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



        String bandageName = getConfig().getString("Bandage.Name");
        Material bandageMaterial = Material.getMaterial(getConfig().getString("Bandage.Material"));
        int bandageData = getConfig().getInt("Bandage.ModelData");

        String medkitName = getConfig().getString("MedKit.Name");
        Material medkitMaterial = Material.getMaterial(getConfig().getString("MedKit.Material"));
        int medkitData = getConfig().getInt("MedKit.ModelData");

        CustomItemUtil.init(this);
        CustomItemUtil.registerCustomItem(new CustomItemUtil.CustomItem("BANDAGE", bandageMaterial,bandageName,bandageData,new BandageEvents()));
        CustomItemUtil.registerCustomItem(new CustomItemUtil.CustomItem("MEDKIT", medkitMaterial,medkitName,medkitData,new BandageEvents()));
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
                MedCraft.getPlugin().reloadConfig();
            } else {
                MedCraft.getPlugin().saveDefaultConfig();
                MedCraft.getPlugin().reloadConfig();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDisable() {
        Bukkit.removeRecipe(ItemLoader.getInstance().Medrecipekey);
        Bukkit.removeRecipe(ItemLoader.getInstance().Bandagerecipekey);
    }
    public void onLoad(){
    }
    public static JavaPlugin getPlugin() {
        return instance;
    }
}