package me.smeths.and.rhetorical.Config;

import me.smeths.and.rhetorical.Data.CustomItem;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ConfigParser {

    public static final String KEY_DISPLAYNAME = "Name";
    public static final String KEY_LORE = "Lore";
    public static final String KEY_DROPIFNOTUSED = "DropIfNotUsed";
    public static final String KEY_WARMUPSPEED = "Warmup-Speed";
    public static final String KEY_CRAFTABLE = "Craftable";
    public static final String KEY_PERFORMCMD = "PerformCMD";
    public static final String KEY_CONSOLECMD = "ConsoleCMD";
    public static final String KEY_CMD = "CMD";
    public static final String KEY_REGEN_TIME = "Regen-Time";
    public static final String KEY_REGEN_AMPLIFIER = "Regen-Amplifier";
    public static final String KEY_USEENDERCHESTIFINVFULL = "UseEnderchestIfInvFull";
    public static final String KEY_GLOWS = "Glows";
    public static final String KEY_RANGE = "UseRange";
    public static final String KEY_RANGE_INT = "Range-Radius";
    public static final String KEY_USE_OFF_HAND= "UseOffHand";
    public static final String KEY_PERMISSIONNAME= "PermissionName";

    private FileConfiguration config;

    public ConfigParser(FileConfiguration config){
        this.config = config;
    }

    public void loadItems(){
        for(String material : config.getKeys(false)){
            for(String custommodeldata : config.getConfigurationSection(material).getKeys(false)){
                int custommodeldataint = Integer.parseInt(custommodeldata);
                ItemStack item = new ItemStack(Material.matchMaterial(material));
                ItemMeta im = item.getItemMeta();
                im.setDisplayName((String) getValueForObject(material,custommodeldataint,KEY_DISPLAYNAME));
                im.setCustomModelData(custommodeldataint);
                im.setLore(getValueForStringList(material,custommodeldataint,KEY_LORE));
                item.setItemMeta(im);
                CustomItem customitem = new CustomItem(getValueForString(material,custommodeldataint,KEY_PERMISSIONNAME), custommodeldataint,item);
                customitem.setConsoleCMD(getValueForBoolean(material,custommodeldataint,KEY_CONSOLECMD));
                customitem.setPerformCMD(getValueForBoolean(material,custommodeldataint,KEY_PERFORMCMD));
                customitem.setCommand(getValueForString(material,custommodeldataint,KEY_CMD));
                customitem.setDropifnotused(getValueForBoolean(material,custommodeldataint,KEY_DROPIFNOTUSED));;
                customitem.setEnderchest(getValueForBoolean(material,custommodeldataint,KEY_USEENDERCHESTIFINVFULL));
                customitem.setCraftable(getValueForBoolean(material,custommodeldataint,KEY_CRAFTABLE));
                customitem.setWarmupspeed(getValueForInt(material,custommodeldataint,KEY_WARMUPSPEED));
                customitem.setRegen_amplifier(getValueForInt(material,custommodeldataint,KEY_REGEN_AMPLIFIER));
                customitem.setRegen_time(getValueForInt(material,custommodeldataint,KEY_REGEN_TIME));
                customitem.setGlows(getValueForBoolean(material,custommodeldataint,KEY_GLOWS));
                customitem.setRadius(getValueForInt(material,custommodeldataint,KEY_RANGE_INT));
                customitem.setUseRadius(getValueForBoolean(material,custommodeldataint,KEY_RANGE));
                customitem.setPermissionOffhand(getValueForBoolean(material,custommodeldataint,KEY_USE_OFF_HAND));
            }
        }
    }

    public String getValueForString(String material, int custommodeldata, String key){
        return config.getString(material+"."+custommodeldata+"."+key);
    }
    public List<String> getValueForStringList(String material, int custommodeldata, String key){
        return config.getStringList(material+"."+custommodeldata+"."+key);
    }
    public boolean getValueForBoolean(String material, int custommodeldata, String key){
        return config.getBoolean(material+"."+custommodeldata+"."+key);
    }
    public Object getValueForObject(String material, int custommodeldata, String key){
        return config.get(material+"."+custommodeldata+"."+key);
    }

    public int getValueForInt(String material, int custommodeldata, String key){
        return config.getInt(material+"."+custommodeldata+"."+key);
    }




}
