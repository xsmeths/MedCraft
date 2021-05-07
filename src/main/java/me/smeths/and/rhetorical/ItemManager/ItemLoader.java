package me.smeths.and.rhetorical.ItemManager;

import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ItemLoader {
    private Plugin medCraft = MedCraft.getPlugin();
    private static ItemLoader instance;
    private ItemStack MedKitItem;
    private ItemStack MedKitItemcraftd;
    private ItemStack bandageItem;
    private ItemStack craftedbandageItem;

    public NamespacedKey Medrecipekey = new NamespacedKey(medCraft, "MedKit");

    public ItemLoader() {
        if (instance != null) {
            return;
        }
        instance = this;

        setupMedKitItem();
        setupCraftedMedKitItem();
        setupMedKitRecipe();
        setupBandageItem();
        setupCraftedBandageItem();
        setupBandageRecipe();
    }

    public static ItemLoader getInstance() {
        return instance;
    }

    public static ItemStack getMedKitItem() {
        return getInstance().MedKitItem;
    }

    public static ItemStack getBandageItem() {
        return getInstance().bandageItem;
    }

    private void setupBandageItem() {
        String BandageName = MedCraft.getPlugin().getConfig().getString("Bandage.Name");
        bandageItem = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")), 1);
        ItemMeta BandageMeta = bandageItem.getItemMeta();
        BandageMeta.setCustomModelData(medCraft.getConfig().getInt("Bandage.ModelData"));
        BandageMeta.setDisplayName(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&',BandageName));
        this.bandageItem.setItemMeta(BandageMeta);
    }
    private void setupCraftedBandageItem() {
        craftedbandageItem = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")), medCraft.getConfig().getInt("Bandage.Result-Amount"));
        ItemMeta craftedBandageMeta = bandageItem.getItemMeta();
        craftedbandageItem.setItemMeta(craftedBandageMeta);
    }
    private void setupBandageRecipe() {
        NamespacedKey recipekey = new NamespacedKey(medCraft, "Bandage");
        ShapedRecipe Bandagerecipe = new ShapedRecipe(recipekey, craftedbandageItem);
        Bandagerecipe.shape("123", "456", "789");
        Bandagerecipe.setIngredient('1', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-top-left")));
        Bandagerecipe.setIngredient('2', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-top-middle")));
        Bandagerecipe.setIngredient('3', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-top-right")));
        Bandagerecipe.setIngredient('4', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-middle-left")));
        Bandagerecipe.setIngredient('5', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-center")));
        Bandagerecipe.setIngredient('6', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-middle-right")));
        Bandagerecipe.setIngredient('7', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-bottom-left")));
        Bandagerecipe.setIngredient('8', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-bottom-middle")));
        Bandagerecipe.setIngredient('9', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Crafting-Material-bottom-right")));
        Bukkit.addRecipe(Bandagerecipe);
    }
    private void setupMedKitItem() {
        String MedKitName = MedCraft.getPlugin().getConfig().getString("MedKit.Name");
        MedKitItem = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),1 );
        ItemMeta MedKitMeta = MedKitItem.getItemMeta();
        MedKitMeta.setCustomModelData(medCraft.getConfig().getInt("MedKit.ModelData"));
        MedKitMeta.setDisplayName(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&',MedKitName));
        MedKitItem.setItemMeta(MedKitMeta);
    }
    private void setupCraftedMedKitItem() {
        MedKitItemcraftd = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")), medCraft.getConfig().getInt("MedKit.Result-Amount"));
        ItemMeta CraftedMedKitMeta = MedKitItem.getItemMeta();
        MedKitItemcraftd.setItemMeta(CraftedMedKitMeta);
    }

    private void setupMedKitRecipe()
    {
        ShapedRecipe MedKitrecipe = new ShapedRecipe(Medrecipekey, MedKitItemcraftd);
        MedKitrecipe.shape("123", "456", "789");
        MedKitrecipe.setIngredient('1', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-top-left")));
        MedKitrecipe.setIngredient('2', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-top-middle")));
        MedKitrecipe.setIngredient('3', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-top-right")));
        MedKitrecipe.setIngredient('4', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-middle-left")));
        MedKitrecipe.setIngredient('5', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-center")));
        MedKitrecipe.setIngredient('6', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-middle-right")));
        MedKitrecipe.setIngredient('7', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-bottom-left")));
        MedKitrecipe.setIngredient('8', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-bottom-middle")));
        MedKitrecipe.setIngredient('9', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-bottom-right")));
        Bukkit.addRecipe(MedKitrecipe);
    }
}