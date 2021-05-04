package me.smeths.and.rhetorical.MedKitItemManagement;

import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class MedKitItemLoader {
    private Plugin medCraft = MedCraft.getPlugin();
    private static MedKitItemLoader instance;
    private ItemStack MedKitItem;
    private ItemStack MedKitItemcraftd;
    public NamespacedKey Medrecipekey = new NamespacedKey(medCraft, "MedKit");

    public MedKitItemLoader() {
        if (instance != null) {
            return;
        }
        instance = this;

        setupMedKitItem();
        setupCraftedMedKitItem();
        setupMedKitRecipe();
    }

    public static MedKitItemLoader getInstance() {
        return instance;
    }

    public static ItemStack getMedKitItem() {
        return getInstance().MedKitItem;
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