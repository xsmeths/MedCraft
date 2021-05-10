package me.smeths.and.rhetorical.ItemManager;

import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ItemLoader {
    private final Plugin medCraft = MedCraft.getPlugin();
    private static ItemLoader instance;
    private ItemStack MedKitItem;
    private ItemStack CraftedMedKit;
    private ItemStack BandageItem;
    private ItemStack CraftedBandage;
    public NamespacedKey Medrecipekey = new NamespacedKey(medCraft, "MedKit");
    public NamespacedKey Bandagerecipekey = new NamespacedKey(medCraft, "Bandage");
    public ItemLoader() {
        if (instance != null) {
            return;
        }
        instance = this;
        setupMedKitItem();
        setupCraftedMedKit();
        setupMedKitRecipe();
        setupBandageItem();
        setupCraftedBandage();
        setupBandageRecipe();
    }
    public static ItemLoader getInstance() {
        return instance;
    }
    public static ItemStack getMedKitItem() {
        return getInstance().MedKitItem;
    }
    public static ItemStack getBandageItem() {
        return getInstance().BandageItem;
    }
    private void setupBandageItem() {
        List<String> Bandagelore = new ArrayList<String>();
        String BandageName = MedCraft.getPlugin().getConfig().getString("Bandage.Name");
        BandageItem = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")), 1);
        ItemMeta BandageMeta = BandageItem.getItemMeta();
        BandageMeta.setCustomModelData(medCraft.getConfig().getInt("Bandage.ModelData"));
        BandageMeta.setDisplayName(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&',BandageName));
        BandageMeta.addEnchant(Enchantment.DURABILITY,1,true);
        BandageMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        for (String bandagelorelines : MedCraft.getPlugin().getConfig().getStringList("Bandage.Lore")) {
            Bandagelore.add(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', bandagelorelines));
        }
        BandageMeta.setLore(Bandagelore);
        BandageItem.setItemMeta(BandageMeta);
    }
    private void setupCraftedBandage() {
        CraftedBandage = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")), medCraft.getConfig().getInt("Bandage.Result-Amount"));
        ItemMeta craftedBandageMeta = BandageItem.getItemMeta();
        CraftedBandage.setItemMeta(craftedBandageMeta);
    }
    private void setupBandageRecipe() {
        ShapedRecipe Bandagerecipe = new ShapedRecipe(Bandagerecipekey, CraftedBandage);
        Bandagerecipe.shape("123", "456", "789");
        Bandagerecipe.setIngredient('1', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-top-left").toUpperCase()));
        Bandagerecipe.setIngredient('2', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-top-middle").toUpperCase()));
        Bandagerecipe.setIngredient('3', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-top-right").toUpperCase()));
        Bandagerecipe.setIngredient('4', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-middle-left").toUpperCase()));
        Bandagerecipe.setIngredient('5', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-center").toUpperCase()));
        Bandagerecipe.setIngredient('6', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-middle-right").toUpperCase()));
        Bandagerecipe.setIngredient('7', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-bottom-left").toUpperCase()));
        Bandagerecipe.setIngredient('8', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-bottom-middle").toUpperCase()));
        Bandagerecipe.setIngredient('9', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-bottom-right").toUpperCase()));
        Bukkit.addRecipe(Bandagerecipe);
    }
    private void setupMedKitItem() {
        List<String> Medkitlore = new ArrayList<String>();
        String MedKitName = MedCraft.getPlugin().getConfig().getString("MedKit.Name");
        MedKitItem = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),1 );
        ItemMeta MedKitMeta = MedKitItem.getItemMeta();
        MedKitMeta.setCustomModelData(medCraft.getConfig().getInt("MedKit.ModelData"));
        MedKitMeta.setDisplayName(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&',MedKitName));
        MedKitMeta.addEnchant(Enchantment.DURABILITY,1,true);
        MedKitMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        for (String medkitlorelines : MedCraft.getPlugin().getConfig().getStringList("MedKit.Lore")) {
            Medkitlore.add(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', medkitlorelines));
        }
        MedKitMeta.setLore(Medkitlore);
        MedKitItem.setItemMeta(MedKitMeta);
    }
    private void setupCraftedMedKit() {
        CraftedMedKit = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")), medCraft.getConfig().getInt("MedKit.Result-Amount"));
        ItemMeta CraftedMedKitMeta = MedKitItem.getItemMeta();
        CraftedMedKit.setItemMeta(CraftedMedKitMeta);
    }
    private void setupMedKitRecipe()
    {
        ShapedRecipe MedKitrecipe = new ShapedRecipe(Medrecipekey, CraftedMedKit);
        MedKitrecipe.shape("123", "456", "789");
        MedKitrecipe.setIngredient('1', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-top-left").toUpperCase()));
        MedKitrecipe.setIngredient('2', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-top-middle").toUpperCase()));
        MedKitrecipe.setIngredient('3', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-top-right").toUpperCase()));
        MedKitrecipe.setIngredient('4', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-middle-left").toUpperCase()));
        MedKitrecipe.setIngredient('5', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-center").toUpperCase()));
        MedKitrecipe.setIngredient('6', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-middle-right").toUpperCase()));
        MedKitrecipe.setIngredient('7', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-bottom-left").toUpperCase()));
        MedKitrecipe.setIngredient('8', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-bottom-middle").toUpperCase()));
        MedKitrecipe.setIngredient('9', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-bottom-right").toUpperCase()));
        Bukkit.addRecipe(MedKitrecipe);
    }
}