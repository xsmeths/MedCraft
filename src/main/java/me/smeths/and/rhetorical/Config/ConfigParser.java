package me.smeths.and.rhetorical.Config;

import me.smeths.and.rhetorical.Data.CustomItem;
import me.smeths.and.rhetorical.MedCraft;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigParser {
    public static final String KEY_DISPLAYNAME = "Name";
    public static final String KEY_LORE = "Lore";
    public static final String KEY_DROPIFNOTUSED = "DropIfNotUsed";
    public static final String KEY_WARMUPSPEED = "Warmup-Speed";
    public static final String KEY_CRAFTABLE = "Craftable";
    public static final String KEY_PERFORMSUCCESSCMD = "PerformSuccessCMD";
    public static final String KEY_CONSOLESUCCESSCMD = "ConsoleSuccessCMD";
    public static final String KEY_SUCCESSCMD = "SuccessCMD";
    public static final String KEY_PERFORMFAILURECMD = "PerformFailureCMD";
    public static final String KEY_CONSOLEFAILURECMD = "ConsoleFailureCMD";
    public static final String KEY_FAILURECMD = "FailureCMD";
    public static final String KEY_REGEN_TIME = "Regen-Time";
    public static final String KEY_REGEN_AMPLIFIER = "Regen-Amplifier";
    public static final String KEY_GLOWS = "Glows";
    public static final String KEY_USE_OFF_HAND = "UseOffHand";
    public static final String KEY_INTERNAL_NAME = "InternalName";
    public static final String KEY_HASRANGE = "HasRange";
    public static final String KEY_RADIUS = "Radius";
    public static final String KEY_RESULT_AMOUNT = "Result-Amount";
    public static final String KEY_CRAFTING_1 = "Crafting-Material-top-left";
    public static final String KEY_CRAFTING_2 = "Crafting-Material-top-middle";
    public static final String KEY_CRAFTING_3 = "Crafting-Material-top-right";
    public static final String KEY_CRAFTING_4 = "Crafting-Material-middle-left";
    public static final String KEY_CRAFTING_5 = "Crafting-Material-center";
    public static final String KEY_CRAFTING_6 = "Crafting-Material-middle-right";
    public static final String KEY_CRAFTING_7 = "Crafting-Material-bottom-left";
    public static final String KEY_CRAFTING_8 = "Crafting-Material-bottom-middle";
    public static final String KEY_CRAFTING_9 = "Crafting-Material-bottom-right";
    public static final String KEY_SHAPE_TOP = "Crafting-Shape-top-row";
    public static final String KEY_SHAPE_MIDDLE = "Crafting-Shape-middle-row";
    public static final String KEY_SHAPE_BOTTOM = "Crafting-Shape-bottom-row";
    private final FileConfiguration config;

    public ConfigParser(FileConfiguration config) {
        this.config = config;
    }
    public void loadItems() {
        for (String material : config.getKeys(false)) {
            if (material.equals("Experimental"))
                continue;
            if (material.equals("Messages"))
                continue;
            for (String custommodeldata : Objects.requireNonNull(config.getConfigurationSection(material)).getKeys(false)) {
                int custommodeldataint = Integer.parseInt(custommodeldata);
                ItemStack item = new ItemStack(Objects.requireNonNull(Material.matchMaterial(material)));
                CustomItem customitem = new CustomItem(getValueForString(material, custommodeldataint, KEY_INTERNAL_NAME), custommodeldataint, item);
                ItemMeta im = item.getItemMeta();
                assert im != null;
                im.setDisplayName(format((String) getValueForObject(material, custommodeldataint, KEY_DISPLAYNAME)));
                im.setCustomModelData(custommodeldataint);
                List<String> loreconfig = getValueForStringList(material, custommodeldataint, KEY_LORE);
                List<String> lore = new ArrayList<>();
                for (String s : loreconfig) {
                    lore.add(format(s));
                }
                if (getValueForBoolean(material, custommodeldataint, KEY_GLOWS)) {
                    im.addEnchant(Enchantment.DURABILITY, 1, true);
                    im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                im.setLore(lore);
                item.setItemMeta(im);
                customitem.setConsoleSuccessCMD(getValueForBoolean(material, custommodeldataint, KEY_CONSOLESUCCESSCMD));
                customitem.setPerformSuccessCMD(getValueForBoolean(material, custommodeldataint, KEY_PERFORMSUCCESSCMD));
                customitem.setSuccessCMD(getValueForString(material, custommodeldataint, KEY_SUCCESSCMD));
                customitem.setConsoleFailureCMD(getValueForBoolean(material, custommodeldataint, KEY_CONSOLEFAILURECMD));
                customitem.setPerformFailureCMD(getValueForBoolean(material, custommodeldataint, KEY_PERFORMFAILURECMD));
                customitem.setFailureCMD(getValueForString(material, custommodeldataint, KEY_FAILURECMD));
                customitem.setDropIfNotUsed(getValueForBoolean(material, custommodeldataint, KEY_DROPIFNOTUSED));
                customitem.setCraftable(getValueForBoolean(material, custommodeldataint, KEY_CRAFTABLE));
                customitem.setWarmupspeed(getValueForInt(material, custommodeldataint, KEY_WARMUPSPEED));
                customitem.setRegen_amplifier(getValueForInt(material, custommodeldataint, KEY_REGEN_AMPLIFIER));
                customitem.setRegen_time(getValueForInt(material, custommodeldataint, KEY_REGEN_TIME));
                customitem.setisGlows(getValueForBoolean(material, custommodeldataint, KEY_GLOWS));
                customitem.setOffhand(getValueForBoolean(material, custommodeldataint, KEY_USE_OFF_HAND));
                customitem.setHasRange(getValueForBoolean(material, custommodeldataint, KEY_HASRANGE));
                customitem.setRadius(getValueForDouble(material, custommodeldataint, KEY_RADIUS));
                if (customitem.isCraftable()) {
                    ItemStack CraftedBandage = new ItemStack(item.getType(), 1);
                    ItemMeta craftedBandageMeta = item.getItemMeta();
                    CraftedBandage.setItemMeta(craftedBandageMeta);
                    ItemStack ResultItemStack = customitem.getItem().clone();
                    ResultItemStack.setAmount(getValueForInt(material, custommodeldataint, KEY_RESULT_AMOUNT));
                    ShapedRecipe HealingItemRecipe = new ShapedRecipe(new NamespacedKey(MedCraft.getPlugin(), customitem.getInternalName()), ResultItemStack);
                    HealingItemRecipe.shape(getValueForString(material, custommodeldataint, KEY_SHAPE_TOP), getValueForString(material, custommodeldataint, KEY_SHAPE_MIDDLE), getValueForString(material, custommodeldataint, KEY_SHAPE_BOTTOM));
                    if (HealingItemRecipe.getIngredientMap().containsKey('1')) {
                        HealingItemRecipe.setIngredient('1', Objects.requireNonNull(Material.getMaterial(getValueForString(material, custommodeldataint, KEY_CRAFTING_1).toUpperCase())));
                    }
                    if (HealingItemRecipe.getIngredientMap().containsKey('2')) {
                        HealingItemRecipe.setIngredient('2', Objects.requireNonNull(Material.getMaterial(getValueForString(material, custommodeldataint, KEY_CRAFTING_2).toUpperCase())));
                    }
                    if (HealingItemRecipe.getIngredientMap().containsKey('3')) {
                        HealingItemRecipe.setIngredient('3', Objects.requireNonNull(Material.getMaterial(getValueForString(material, custommodeldataint, KEY_CRAFTING_3).toUpperCase())));
                    }
                    if (HealingItemRecipe.getIngredientMap().containsKey('4')) {
                        HealingItemRecipe.setIngredient('4', Objects.requireNonNull(Material.getMaterial(getValueForString(material, custommodeldataint, KEY_CRAFTING_4).toUpperCase())));
                    }
                    if (HealingItemRecipe.getIngredientMap().containsKey('5')) {
                        HealingItemRecipe.setIngredient('5', Objects.requireNonNull(Material.getMaterial(getValueForString(material, custommodeldataint, KEY_CRAFTING_5).toUpperCase())));
                    }
                    if (HealingItemRecipe.getIngredientMap().containsKey('6')) {
                        HealingItemRecipe.setIngredient('6', Objects.requireNonNull(Material.getMaterial(getValueForString(material, custommodeldataint, KEY_CRAFTING_6).toUpperCase())));
                    }
                    if (HealingItemRecipe.getIngredientMap().containsKey('7')) {
                        HealingItemRecipe.setIngredient('7', Objects.requireNonNull(Material.getMaterial(getValueForString(material, custommodeldataint, KEY_CRAFTING_7).toUpperCase())));
                    }
                    if (HealingItemRecipe.getIngredientMap().containsKey('8')) {
                        HealingItemRecipe.setIngredient('8', Objects.requireNonNull(Material.getMaterial(getValueForString(material, custommodeldataint, KEY_CRAFTING_8).toUpperCase())));
                    }
                    if (HealingItemRecipe.getIngredientMap().containsKey('9')) {
                        HealingItemRecipe.setIngredient('9', Objects.requireNonNull(Material.getMaterial(getValueForString(material, custommodeldataint, KEY_CRAFTING_9).toUpperCase())));
                    }
                    Bukkit.addRecipe(HealingItemRecipe);
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
    public double getValueForDouble(String material, int custommodeldata, String key) {
        return config.getDouble(material + "." + custommodeldata + "." + key);
    }
    Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    private String format(String msg) {
        if (!Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9")
                && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11")
                && !Bukkit.getVersion().contains("1.12") && !Bukkit.getVersion().contains("1.13")
                && !Bukkit.getVersion().contains("1.14") && !Bukkit.getVersion().contains("1.15")) {
            Matcher matcher = pattern.matcher(msg);
            while (matcher.find()) {
                String color = msg.substring(matcher.start(), matcher.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                matcher = pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}