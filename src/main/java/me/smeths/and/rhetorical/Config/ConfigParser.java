package me.smeths.and.rhetorical.Config;

import me.smeths.and.rhetorical.Data.CustomItem;
import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
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
    public static final String KEY_USE_OFF_HAND = "UseOffHand";
    public static final String KEY_INTERNAL_NAME = "InternalName";

    public static final String KEY_CRAFTING_1 = "Crafting-Material-top-left";
    public static final String KEY_CRAFTING_2 = "Crafting-Material-top-middle";
    public static final String KEY_CRAFTING_3 = "Crafting-Material-top-right";
    public static final String KEY_CRAFTING_4 = "Crafting-Material-middle-left";
    public static final String KEY_CRAFTING_5 = "Crafting-Material-center";
    public static final String KEY_CRAFTING_6 = "Crafting-Material-middle-right";
    public static final String KEY_CRAFTING_7 = "Crafting-Material-bottom-left";
    public static final String KEY_CRAFTING_8 = "Crafting-Material-bottom-middle";
    public static final String KEY_CRAFTING_9 = "Crafting-Material-bottom-right";

    private FileConfiguration config;

    public ConfigParser(FileConfiguration config) {
        this.config = config;
    }

    public void loadItems() {
        for (String material : config.getKeys(false)) {
            if(material.equals("MedKit"))
                continue;
            for (String custommodeldata : config.getConfigurationSection(material).getKeys(false)) {
                int custommodeldataint = Integer.parseInt(custommodeldata);
                ItemStack item = new ItemStack(Material.matchMaterial(material));
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&',(String) getValueForObject(material, custommodeldataint, KEY_DISPLAYNAME)));
                im.setCustomModelData(custommodeldataint);
                List<String> loreconfig = getValueForStringList(material, custommodeldataint, KEY_LORE);
                List<String> lore = new ArrayList<>();
                for(String s : loreconfig){
                    lore.add(ChatColor.translateAlternateColorCodes('&',s));
                }
                im.setLore(lore);
                item.setItemMeta(im);
                CustomItem customitem = new CustomItem(getValueForString(material, custommodeldataint, KEY_INTERNAL_NAME), custommodeldataint, item);
                customitem.setConsoleCMD(getValueForBoolean(material, custommodeldataint, KEY_CONSOLECMD));
                customitem.setPerformCMD(getValueForBoolean(material, custommodeldataint, KEY_PERFORMCMD));
                customitem.setCommand(getValueForString(material, custommodeldataint, KEY_CMD));
                customitem.setDropifnotused(getValueForBoolean(material, custommodeldataint, KEY_DROPIFNOTUSED));
                ;
                customitem.setEnderchest(getValueForBoolean(material, custommodeldataint, KEY_USEENDERCHESTIFINVFULL));
                customitem.setCraftable(getValueForBoolean(material, custommodeldataint, KEY_CRAFTABLE));
                customitem.setWarmupspeed(getValueForInt(material, custommodeldataint, KEY_WARMUPSPEED));
                customitem.setRegen_amplifier(getValueForInt(material, custommodeldataint, KEY_REGEN_AMPLIFIER));
                customitem.setRegen_time(getValueForInt(material, custommodeldataint, KEY_REGEN_TIME));
                customitem.setGlows(getValueForBoolean(material, custommodeldataint, KEY_GLOWS));
                customitem.setPermissionOffhand(getValueForBoolean(material, custommodeldataint, KEY_USE_OFF_HAND));

                if (customitem.isCraftable()) {
                    ItemStack CraftedBandage = new ItemStack(item.getType(), 1);
                    ItemMeta craftedBandageMeta = item.getItemMeta();
                    CraftedBandage.setItemMeta(craftedBandageMeta);

                    ShapedRecipe Bandagerecipe = new ShapedRecipe(new NamespacedKey(MedCraft.getPlugin(),customitem.getInternalName()), customitem.getItem());
                   // Bandagerecipe.shape(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Shape-top-row"), MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Shape-middle-row"), MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Shape-bottom-row"));
                    Bandagerecipe.shape("123","456","789");
                    if (Bandagerecipe.getIngredientMap().containsKey('1')) {
                        Bandagerecipe.setIngredient('1', Material.getMaterial(getValueForString(material,custommodeldataint,KEY_CRAFTING_1).toUpperCase()));
                    }
                    if (Bandagerecipe.getIngredientMap().containsKey('2')) {
                        Bandagerecipe.setIngredient('2', Material.getMaterial(getValueForString(material,custommodeldataint,KEY_CRAFTING_2).toUpperCase()));
                    }
                    if (Bandagerecipe.getIngredientMap().containsKey('3')) {
                        Bandagerecipe.setIngredient('3', Material.getMaterial(getValueForString(material,custommodeldataint,KEY_CRAFTING_3).toUpperCase()));
                    }
                    if (Bandagerecipe.getIngredientMap().containsKey('4')) {
                        Bandagerecipe.setIngredient('4', Material.getMaterial(getValueForString(material,custommodeldataint,KEY_CRAFTING_4).toUpperCase()));
                    }
                    if (Bandagerecipe.getIngredientMap().containsKey('5')) {
                        Bandagerecipe.setIngredient('5', Material.getMaterial(getValueForString(material,custommodeldataint,KEY_CRAFTING_5).toUpperCase()));
                    }
                    if (Bandagerecipe.getIngredientMap().containsKey('6')) {
                        Bandagerecipe.setIngredient('6', Material.getMaterial(getValueForString(material,custommodeldataint,KEY_CRAFTING_6).toUpperCase()));
                    }
                    if (Bandagerecipe.getIngredientMap().containsKey('7')) {
                        Bandagerecipe.setIngredient('7', Material.getMaterial(getValueForString(material,custommodeldataint,KEY_CRAFTING_7).toUpperCase()));
                    }
                    if (Bandagerecipe.getIngredientMap().containsKey('8')) {
                        Bandagerecipe.setIngredient('8', Material.getMaterial(getValueForString(material,custommodeldataint,KEY_CRAFTING_8).toUpperCase()));
                    }
                    if (Bandagerecipe.getIngredientMap().containsKey('9')) {
                        Bandagerecipe.setIngredient('9', Material.getMaterial(getValueForString(material,custommodeldataint,KEY_CRAFTING_9).toUpperCase()));
                    }
                    Bukkit.addRecipe(Bandagerecipe);
                }
            }
        }
    }

    public String getValueForString(String material, int custommodeldata, String key) {
        return config.getString(material + "." + custommodeldata + "." + key);
    }

    public List<String> getValueForStringList(String material, int custommodeldata, String key) {
        return config.getStringList(material + "." + custommodeldata + "." + key);
    }

    public boolean getValueForBoolean(String material, int custommodeldata, String key) {
        return config.getBoolean(material + "." + custommodeldata + "." + key);
    }

    public Object getValueForObject(String material, int custommodeldata, String key) {
        return config.get(material + "." + custommodeldata + "." + key);
    }

    public int getValueForInt(String material, int custommodeldata, String key) {
        return config.getInt(material + "." + custommodeldata + "." + key);
    }


}
