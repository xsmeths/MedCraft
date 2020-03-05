package me.smeths.and.rhetorical.BandageItemManagement;

import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class BandageItemLoader {
    private Plugin medCraft = MedCraft.getPlugin();
    private static BandageItemLoader instance;
    private ItemStack bandageItem;
    private ItemStack craftedbandageItem;
    public NamespacedKey recipekey = new NamespacedKey(medCraft, "Bandage");
    public ShapedRecipe Bandagerecipe = new ShapedRecipe(recipekey, craftedbandageItem);

    public BandageItemLoader() {
        if (instance != null) {
            return;
        }
        instance = this;

        setupBandageItem();
        setupCraftedBandageItem();
        setupBandageRecipe();
    }

    public static BandageItemLoader getInstance() {
        return instance;
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
}