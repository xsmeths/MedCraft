package me.smeths.and.rhetorical.ItemManager;

import me.smeths.and.rhetorical.Data.CustomItem;
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
    private ItemStack TwoMedKits;
    private ItemStack ThreeMedKits;
    private ItemStack FourMedKits;
    private ItemStack FiveMedKits;
    private ItemStack SixMedKits;
    private ItemStack SevenMedKits;
    private ItemStack EightMedKits;
    private ItemStack NineMedKits;
    private ItemStack TenMedKits;
    private ItemStack ElevenMedKits;
    private ItemStack TwelveMedKits;
    private ItemStack ThirteenMedKits;
    private ItemStack FourteenMedKits;
    private ItemStack FifteenMedKits;
    private ItemStack SixteenMedKits;
    private ItemStack SeventeenMedKits;
    private ItemStack EighteenMedKits;
    private ItemStack NineteenMedKits;
    private ItemStack TwentyMedKits;
    private ItemStack TwentyOneMedKits;
    private ItemStack TwentyTwoMedKits;
    private ItemStack TwentyThreeMedKits;
    private ItemStack TwentyFourMedKits;
    private ItemStack TwentyFiveMedKits;
    private ItemStack TwentySixMedKits;
    private ItemStack TwentySevenMedKits;
    private ItemStack TwentyEightMedKits;
    private ItemStack TwentyNineMedKits;
    private ItemStack ThirtyMedKits;
    private ItemStack ThirtyOneMedKits;
    private ItemStack ThirtyTwoMedKits;
    private ItemStack ThirtyThreeMedKits;
    private ItemStack ThirtyFourMedKits;
    private ItemStack ThirtyFiveMedKits;
    private ItemStack ThirtySixMedKits;
    private ItemStack ThirtySevenMedKits;
    private ItemStack ThirtyEightMedKits;
    private ItemStack ThirtyNineMedKits;
    private ItemStack FortyMedKits;
    private ItemStack FortyOneMedKits;
    private ItemStack FortyTwoMedKits;
    private ItemStack FortyThreeMedKits;
    private ItemStack FortyFourMedKits;
    private ItemStack FortyFiveMedKits;
    private ItemStack FortySixMedKits;
    private ItemStack FortySevenMedKits;
    private ItemStack FortyEightMedKits;
    private ItemStack FortyNineMedKits;
    private ItemStack FiftyMedKits;
    private ItemStack FiftyOneMedKits;
    private ItemStack FiftyTwoMedKits;
    private ItemStack FiftyThreeMedKits;
    private ItemStack FiftyFourMedKits;
    private ItemStack FiftyFiveMedKits;
    private ItemStack FiftySixMedKits;
    private ItemStack FiftySevenMedKits;
    private ItemStack FiftyEightMedKits;
    private ItemStack FiftyNineMedKits;
    private ItemStack SixtyMedKits;
    private ItemStack SixtyOneMedKits;
    private ItemStack SixtyTwoMedKits;
    private ItemStack SixtyThreeMedKits;
    private ItemStack SixtyFourMedKits;
   /* private ItemStack BandageItem;
    private ItemStack CraftedBandage;
    private ItemStack TwoBandages;
    private ItemStack ThreeBandages;
    private ItemStack FourBandages;
    private ItemStack FiveBandages;
    private ItemStack SixBandages;
    private ItemStack SevenBandages;
    private ItemStack EightBandages;
    private ItemStack NineBandages;
    private ItemStack TenBandages;
    private ItemStack ElevenBandages;
    private ItemStack TwelveBandages;
    private ItemStack ThirteenBandages;
    private ItemStack FourteenBandages;
    private ItemStack FifteenBandages;
    private ItemStack SixteenBandages;
    private ItemStack SeventeenBandages;
    private ItemStack EighteenBandages;
    private ItemStack NineteenBandages;
    private ItemStack TwentyBandages;
    private ItemStack TwentyOneBandages;
    private ItemStack TwentyTwoBandages;
    private ItemStack TwentyThreeBandages;
    private ItemStack TwentyFourBandages;
    private ItemStack TwentyFiveBandages;
    private ItemStack TwentySixBandages;
    private ItemStack TwentySevenBandages;
    private ItemStack TwentyEightBandages;
    private ItemStack TwentyNineBandages;
    private ItemStack ThirtyBandages;
    private ItemStack ThirtyOneBandages;
    private ItemStack ThirtyTwoBandages;
    private ItemStack ThirtyThreeBandages;
    private ItemStack ThirtyFourBandages;
    private ItemStack ThirtyFiveBandages;
    private ItemStack ThirtySixBandages;
    private ItemStack ThirtySevenBandages;
    private ItemStack ThirtyEightBandages;
    private ItemStack ThirtyNineBandages;
    private ItemStack FortyBandages;
    private ItemStack FortyOneBandages;
    private ItemStack FortyTwoBandages;
    private ItemStack FortyThreeBandages;
    private ItemStack FortyFourBandages;
    private ItemStack FortyFiveBandages;
    private ItemStack FortySixBandages;
    private ItemStack FortySevenBandages;
    private ItemStack FortyEightBandages;
    private ItemStack FortyNineBandages;
    private ItemStack FiftyBandages;
    private ItemStack FiftyOneBandages;
    private ItemStack FiftyTwoBandages;
    private ItemStack FiftyThreeBandages;
    private ItemStack FiftyFourBandages;
    private ItemStack FiftyFiveBandages;
    private ItemStack FiftySixBandages;
    private ItemStack FiftySevenBandages;
    private ItemStack FiftyEightBandages;
    private ItemStack FiftyNineBandages;
    private ItemStack SixtyBandages;
    private ItemStack SixtyOneBandages;
    private ItemStack SixtyTwoBandages;
    private ItemStack SixtyThreeBandages;
    private ItemStack SixtyFourBandages;*/

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
    }
    public static ItemLoader getInstance() { return instance; }
    public static ItemStack getMedKitItem() { return getInstance().MedKitItem; }
    public static ItemStack getTwoMedKits() { return getInstance().TwoMedKits; }
    public static ItemStack getThreeMedKits() { return getInstance().ThreeMedKits; }
    public static ItemStack getFourMedKits() { return getInstance().FourMedKits; }
    public static ItemStack getFiveMedKits() { return getInstance().FiveMedKits; }
    public static ItemStack getSixMedKits() { return getInstance().SixMedKits; }
    public static ItemStack getSevenMedKits() { return getInstance().SevenMedKits; }
    public static ItemStack getEightMedKits() { return getInstance().EightMedKits; }
    public static ItemStack getNineMedKits() { return getInstance().NineMedKits; }
    public static ItemStack getTenMedKits() { return getInstance().TenMedKits; }
    public static ItemStack getElevenMedKits() { return getInstance().ElevenMedKits; }
    public static ItemStack getTwelveMedKits() { return getInstance().TwelveMedKits; }
    public static ItemStack getThirteenMedKits() { return getInstance().ThirteenMedKits; }
    public static ItemStack getFourteenMedKits() { return getInstance().FourteenMedKits; }
    public static ItemStack getFifteenMedKits() { return getInstance().FifteenMedKits; }
    public static ItemStack getSixteenMedKits() { return getInstance().SixteenMedKits; }
    public static ItemStack getSeventeenMedKits() { return getInstance().SeventeenMedKits; }
    public static ItemStack getEighteenMedKits() { return getInstance().EighteenMedKits; }
    public static ItemStack getNineteenMedKits() { return getInstance().NineteenMedKits; }
    public static ItemStack getTwentyMedKits() { return getInstance().TwentyMedKits; }
    public static ItemStack getTwentyOneMedKits() { return getInstance().TwentyOneMedKits; }
    public static ItemStack getTwentyTwoMedKits() { return getInstance().TwentyTwoMedKits; }
    public static ItemStack getTwentyThreeMedKits() { return getInstance().TwentyThreeMedKits; }
    public static ItemStack getTwentyFourMedKits() { return getInstance().TwentyFourMedKits; }
    public static ItemStack getTwentyFiveMedKits() { return getInstance().TwentyFiveMedKits; }
    public static ItemStack getTwentySixMedKits() { return getInstance().TwentySixMedKits; }
    public static ItemStack getTwentySevenMedKits() { return getInstance().TwentySevenMedKits; }
    public static ItemStack getTwentyEightMedKits() { return getInstance().TwentyEightMedKits; }
    public static ItemStack getTwentyNineMedKits() { return getInstance().TwentyNineMedKits; }
    public static ItemStack getThirtyMedKits() { return getInstance().ThirtyMedKits; }
    public static ItemStack getThirtyOneMedKits() { return getInstance().ThirtyOneMedKits; }
    public static ItemStack getThirtyTwoMedKits() { return getInstance().ThirtyTwoMedKits; }
    public static ItemStack getThirtyThreeMedKits() { return getInstance().ThirtyThreeMedKits; }
    public static ItemStack getThirtyFourMedKits() { return getInstance().ThirtyFourMedKits; }
    public static ItemStack getThirtyFiveMedKits() { return getInstance().ThirtyFiveMedKits; }
    public static ItemStack getThirtySixMedKits() { return getInstance().ThirtySixMedKits; }
    public static ItemStack getThirtySevenMedKits() { return getInstance().ThirtySevenMedKits; }
    public static ItemStack getThirtyEightMedKits() { return getInstance().ThirtyEightMedKits; }
    public static ItemStack getThirtyNineMedKits() { return getInstance().ThirtyNineMedKits; }
    public static ItemStack getFortyMedKits() { return getInstance().FortyMedKits; }
    public static ItemStack getFortyOneMedKits() { return getInstance().FortyOneMedKits; }
    public static ItemStack getFortyTwoMedKits() { return getInstance().FortyTwoMedKits; }
    public static ItemStack getFortyThreeMedKits() { return getInstance().FortyThreeMedKits; }
    public static ItemStack getFortyFourMedKits() { return getInstance().FortyFourMedKits; }
    public static ItemStack getFortyFiveMedKits() { return getInstance().FortyFiveMedKits; }
    public static ItemStack getFortySixMedKits() { return getInstance().FortySixMedKits; }
    public static ItemStack getFortySevenMedKits() { return getInstance().FortySevenMedKits; }
    public static ItemStack getFortyEightMedKits() { return getInstance().FortyEightMedKits; }
    public static ItemStack getFortyNineMedKits() { return getInstance().FortyNineMedKits; }
    public static ItemStack getFiftyMedKits() { return getInstance().FiftyMedKits; }
    public static ItemStack getFiftyOneMedKits() { return getInstance().FiftyOneMedKits; }
    public static ItemStack getFiftyTwoMedKits() { return getInstance().FiftyTwoMedKits; }
    public static ItemStack getFiftyThreeMedKits() { return getInstance().FiftyThreeMedKits; }
    public static ItemStack getFiftyFourMedKits() { return getInstance().FiftyFourMedKits; }
    public static ItemStack getFiftyFiveMedKits() { return getInstance().FiftyFiveMedKits; }
    public static ItemStack getFiftySixMedKits() { return getInstance().FiftySixMedKits; }
    public static ItemStack getFiftySevenMedKits() { return getInstance().FiftySevenMedKits; }
    public static ItemStack getFiftyEightMedKits() { return getInstance().FiftyEightMedKits; }
    public static ItemStack getFiftyNineMedKits() { return getInstance().FiftyNineMedKits; }
    public static ItemStack getSixtyMedKits() { return getInstance().SixtyMedKits; }
    public static ItemStack getSixtyOneMedKits() { return getInstance().SixtyOneMedKits; }
    public static ItemStack getSixtyTwoMedKits() { return getInstance().SixtyTwoMedKits; }
    public static ItemStack getSixtyThreeMedKits() { return getInstance().SixtyThreeMedKits; }
    public static ItemStack getSixtyFourMedKits() { return getInstance().SixtyFourMedKits; }
    /*public static ItemStack getBandageItem() { return getInstance().BandageItem; }

    public static ItemStack getTwoBandages() { return getInstance().TwoBandages; }
    public static ItemStack getThreeBandages() { return getInstance().ThreeBandages; }
    public static ItemStack getFourBandages() { return getInstance().FourBandages; }
    public static ItemStack getFiveBandages() { return getInstance().FiveBandages; }
    public static ItemStack getSixBandages() { return getInstance().SixBandages; }
    public static ItemStack getSevenBandages() { return getInstance().SevenBandages; }
    public static ItemStack getEightBandages() { return getInstance().EightBandages; }
    public static ItemStack getNineBandages() { return getInstance().NineBandages; }
    public static ItemStack getTenBandages() { return getInstance().TenBandages; }
    public static ItemStack getElevenBandages() { return getInstance().ElevenBandages; }
    public static ItemStack getTwelveBandages() { return getInstance().TwelveBandages; }
    public static ItemStack getThirteenBandages() { return getInstance().ThirteenBandages; }
    public static ItemStack getFourteenBandages() { return getInstance().FourteenBandages; }
    public static ItemStack getFifteenBandages() { return getInstance().FifteenBandages; }
    public static ItemStack getSixteenBandages() { return getInstance().SixteenBandages; }
    public static ItemStack getSeventeenBandages() { return getInstance().SeventeenBandages; }
    public static ItemStack getEighteenBandages() { return getInstance().EighteenBandages; }
    public static ItemStack getNineteenBandages() { return getInstance().NineteenBandages; }
    public static ItemStack getTwentyBandages() { return getInstance().TwentyBandages; }
    public static ItemStack getTwentyOneBandages() { return getInstance().TwentyOneBandages; }
    public static ItemStack getTwentyTwoBandages() { return getInstance().TwentyTwoBandages; }
    public static ItemStack getTwentyThreeBandages() { return getInstance().TwentyThreeBandages; }
    public static ItemStack getTwentyFourBandages() { return getInstance().TwentyFourBandages; }
    public static ItemStack getTwentyFiveBandages() { return getInstance().TwentyFiveBandages; }
    public static ItemStack getTwentySixBandages() { return getInstance().TwentySixBandages; }
    public static ItemStack getTwentySevenBandages() { return getInstance().TwentySevenBandages; }
    public static ItemStack getTwentyEightBandages() { return getInstance().TwentyEightBandages; }
    public static ItemStack getTwentyNineBandages() { return getInstance().TwentyNineBandages; }
    public static ItemStack getThirtyBandages() { return getInstance().ThirtyBandages; }
    public static ItemStack getThirtyOneBandages() { return getInstance().ThirtyOneBandages; }
    public static ItemStack getThirtyTwoBandages() { return getInstance().ThirtyTwoBandages; }
    public static ItemStack getThirtyThreeBandages() { return getInstance().ThirtyThreeBandages; }
    public static ItemStack getThirtyFourBandages() { return getInstance().ThirtyFourBandages; }
    public static ItemStack getThirtyFiveBandages() { return getInstance().ThirtyFiveBandages; }
    public static ItemStack getThirtySixBandages() { return getInstance().ThirtySixBandages; }
    public static ItemStack getThirtySevenBandages() { return getInstance().ThirtySevenBandages; }
    public static ItemStack getThirtyEightBandages() { return getInstance().ThirtyEightBandages; }
    public static ItemStack getThirtyNineBandages() { return getInstance().ThirtyNineBandages; }
    public static ItemStack getFortyBandages() { return getInstance().FortyBandages; }
    public static ItemStack getFortyOneBandages() { return getInstance().FortyOneBandages; }
    public static ItemStack getFortyTwoBandages() { return getInstance().FortyTwoBandages; }
    public static ItemStack getFortyThreeBandages() { return getInstance().FortyThreeBandages; }
    public static ItemStack getFortyFourBandages() { return getInstance().FortyFourBandages; }
    public static ItemStack getFortyFiveBandages() { return getInstance().FortyFiveBandages; }
    public static ItemStack getFortySixBandages() { return getInstance().FortySixBandages; }
    public static ItemStack getFortySevenBandages() { return getInstance().FortySevenBandages; }
    public static ItemStack getFortyEightBandages() { return getInstance().FortyEightBandages; }
    public static ItemStack getFortyNineBandages() { return getInstance().FortyNineBandages; }
    public static ItemStack getFiftyBandages() { return getInstance().FiftyBandages; }
    public static ItemStack getFiftyOneBandages() { return getInstance().FiftyOneBandages; }
    public static ItemStack getFiftyTwoBandages() { return getInstance().FiftyTwoBandages; }
    public static ItemStack getFiftyThreeBandages() { return getInstance().FiftyThreeBandages; }
    public static ItemStack getFiftyFourBandages() { return getInstance().FiftyFourBandages; }
    public static ItemStack getFiftyFiveBandages() { return getInstance().FiftyFiveBandages; }
    public static ItemStack getFiftySixBandages() { return getInstance().FiftySixBandages; }
    public static ItemStack getFiftySevenBandages() { return getInstance().FiftySevenBandages; }
    public static ItemStack getFiftyEightBandages() { return getInstance().FiftyEightBandages; }
    public static ItemStack getFiftyNineBandages() { return getInstance().FiftyNineBandages; }
    public static ItemStack getSixtyBandages() { return getInstance().SixtyBandages; }
    public static ItemStack getSixtyOneBandages() { return getInstance().SixtyOneBandages; }
    public static ItemStack getSixtyTwoBandages() { return getInstance().SixtyTwoBandages; }
    public static ItemStack getSixtyThreeBandages() { return getInstance().SixtyThreeBandages; }
    public static ItemStack getSixtyFourBandages() { return getInstance().SixtyFourBandages; }*/
    /*private void setupBandageItem() {
        List<String> Bandagelore = new ArrayList<>();
        String BandageName = MedCraft.getPlugin().getConfig().getString("Bandage.Name");
        BandageItem = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")), 1);
        ItemMeta BandageMeta = BandageItem.getItemMeta();
        assert BandageMeta != null;
        BandageMeta.setCustomModelData(medCraft.getConfig().getInt("Bandage.ModelData"));
        assert BandageName != null;
        BandageMeta.setDisplayName(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&',BandageName));
        if (MedCraft.getPlugin().getConfig().getBoolean("Bandage.Glows")) {
            BandageMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            BandageMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        for (String bandagelorelines : MedCraft.getPlugin().getConfig().getStringList("Bandage.Lore")) {
            Bandagelore.add(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', bandagelorelines));
        }
        BandageMeta.setLore(Bandagelore);
        BandageItem.setItemMeta(BandageMeta);
        TwoBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),2);
        TwoBandages.setItemMeta(BandageMeta);
        ThreeBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),3);
        ThreeBandages.setItemMeta(BandageMeta);
        FourBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),4);
        FourBandages.setItemMeta(BandageMeta);
        FiveBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),5);
        FiveBandages.setItemMeta(BandageMeta);
        SixBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),6);
        SixBandages.setItemMeta(BandageMeta);
        SevenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),7);
        SevenBandages.setItemMeta(BandageMeta);
        EightBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),8);
        EightBandages.setItemMeta(BandageMeta);
        NineBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),9);
        NineBandages.setItemMeta(BandageMeta);
        TenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),10);
        TenBandages.setItemMeta(BandageMeta);
        ElevenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),11);
        ElevenBandages.setItemMeta(BandageMeta);
        TwelveBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),12);
        TwelveBandages.setItemMeta(BandageMeta);
        ThirteenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),13);
        ThirteenBandages.setItemMeta(BandageMeta);
        FourteenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),14);
        FourteenBandages.setItemMeta(BandageMeta);
        FifteenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),15);
        FifteenBandages.setItemMeta(BandageMeta);
        SixteenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),16);
        SixteenBandages.setItemMeta(BandageMeta);
        SeventeenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),17);
        SeventeenBandages.setItemMeta(BandageMeta);
        EighteenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),18);
        EighteenBandages.setItemMeta(BandageMeta);
        NineteenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),19);
        NineteenBandages.setItemMeta(BandageMeta);
        TwentyBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),20);
        TwentyBandages.setItemMeta(BandageMeta);
        TwentyOneBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),21);
        TwentyOneBandages.setItemMeta(BandageMeta);
        TwentyTwoBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),22);
        TwentyTwoBandages.setItemMeta(BandageMeta);
        TwentyThreeBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),23);
        TwentyThreeBandages.setItemMeta(BandageMeta);
        TwentyFourBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),24);
        TwentyFourBandages.setItemMeta(BandageMeta);
        TwentyFiveBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),25);
        TwentyFiveBandages.setItemMeta(BandageMeta);
        TwentySixBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),26);
        TwentySixBandages.setItemMeta(BandageMeta);
        TwentySevenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),27);
        TwentySevenBandages.setItemMeta(BandageMeta);
        TwentyEightBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),28);
        TwentyEightBandages.setItemMeta(BandageMeta);
        TwentyNineBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),29);
        TwentyNineBandages.setItemMeta(BandageMeta);
        ThirtyBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),30);
        ThirtyBandages.setItemMeta(BandageMeta);
        ThirtyOneBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),31);
        ThirtyOneBandages.setItemMeta(BandageMeta);
        ThirtyTwoBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),32);
        ThirtyTwoBandages.setItemMeta(BandageMeta);
        ThirtyThreeBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),33);
        ThirtyThreeBandages.setItemMeta(BandageMeta);
        ThirtyFourBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),34);
        ThirtyFourBandages.setItemMeta(BandageMeta);
        ThirtyFiveBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),35);
        ThirtyFiveBandages.setItemMeta(BandageMeta);
        ThirtySixBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),36);
        ThirtySixBandages.setItemMeta(BandageMeta);
        ThirtySevenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),37);
        ThirtySevenBandages.setItemMeta(BandageMeta);
        ThirtyEightBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),38);
        ThirtyEightBandages.setItemMeta(BandageMeta);
        ThirtyNineBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),39);
        ThirtyNineBandages.setItemMeta(BandageMeta);
        FortyBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),40);
        FortyBandages.setItemMeta(BandageMeta);
        FortyOneBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),41);
        FortyOneBandages.setItemMeta(BandageMeta);
        FortyTwoBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),42);
        FortyTwoBandages.setItemMeta(BandageMeta);
        FortyThreeBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),43);
        FortyThreeBandages.setItemMeta(BandageMeta);
        FortyFourBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),44);
        FortyFourBandages.setItemMeta(BandageMeta);
        FortyFiveBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),45);
        FortyFiveBandages.setItemMeta(BandageMeta);
        FortySixBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),46);
        FortySixBandages.setItemMeta(BandageMeta);
        FortySevenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),47);
        FortySevenBandages.setItemMeta(BandageMeta);
        FortyEightBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),48);
        FortyEightBandages.setItemMeta(BandageMeta);
        FortyNineBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),49);
        FortyNineBandages.setItemMeta(BandageMeta);
        FiftyBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),50);
        FiftyBandages.setItemMeta(BandageMeta);
        FiftyOneBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),51);
        FiftyOneBandages.setItemMeta(BandageMeta);
        FiftyTwoBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),52);
        FiftyTwoBandages.setItemMeta(BandageMeta);
        FiftyThreeBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),53);
        FiftyThreeBandages.setItemMeta(BandageMeta);
        FiftyFourBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),54);
        FiftyFourBandages.setItemMeta(BandageMeta);
        FiftyFiveBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),55);
        FiftyFiveBandages.setItemMeta(BandageMeta);
        FiftySixBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),56);
        FiftySixBandages.setItemMeta(BandageMeta);
        FiftySevenBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),57);
        FiftySevenBandages.setItemMeta(BandageMeta);
        FiftyEightBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),58);
        FiftyEightBandages.setItemMeta(BandageMeta);
        FiftyNineBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),59);
        FiftyNineBandages.setItemMeta(BandageMeta);
        SixtyBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),60);
        SixtyBandages.setItemMeta(BandageMeta);
        SixtyOneBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),61);
        SixtyOneBandages.setItemMeta(BandageMeta);
        SixtyTwoBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),62);
        SixtyTwoBandages.setItemMeta(BandageMeta);
        SixtyThreeBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),63);
        SixtyThreeBandages.setItemMeta(BandageMeta);
        SixtyFourBandages = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")),64);
        SixtyFourBandages.setItemMeta(BandageMeta);
    }
    private void setupCraftedBandage() {
        CraftedBandage = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("Bandage.Material")), medCraft.getConfig().getInt("Bandage.Result-Amount"));
        ItemMeta craftedBandageMeta = BandageItem.getItemMeta();
        CraftedBandage.setItemMeta(craftedBandageMeta);
    }*/
    private void setupMedKitItem() {
        List<String> Medkitlore = new ArrayList<String>();
        String MedKitName = MedCraft.getPlugin().getConfig().getString("MedKit.Name");
        MedKitItem = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),1 );
        ItemMeta MedKitMeta = MedKitItem.getItemMeta();
        MedKitMeta.setCustomModelData(medCraft.getConfig().getInt("MedKit.ModelData"));
        MedKitMeta.setDisplayName(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&',MedKitName));
        if (MedCraft.getPlugin().getConfig().getBoolean("MedKit.Glows")) {
            MedKitMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            MedKitMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        for (String medkitlorelines : MedCraft.getPlugin().getConfig().getStringList("MedKit.Lore")) {
            Medkitlore.add(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', medkitlorelines));
        }
        MedKitMeta.setLore(Medkitlore);
        MedKitItem.setItemMeta(MedKitMeta);
        TwoMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),2);
        TwoMedKits.setItemMeta(MedKitMeta);
        ThreeMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),3);
        ThreeMedKits.setItemMeta(MedKitMeta);
        FourMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),4);
        FourMedKits.setItemMeta(MedKitMeta);
        FiveMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),5);
        FiveMedKits.setItemMeta(MedKitMeta);
        SixMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),6);
        SixMedKits.setItemMeta(MedKitMeta);
        SevenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),7);
        SevenMedKits.setItemMeta(MedKitMeta);
        EightMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),8);
        EightMedKits.setItemMeta(MedKitMeta);
        NineMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),9);
        NineMedKits.setItemMeta(MedKitMeta);
        TenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),10);
        TenMedKits.setItemMeta(MedKitMeta);
        ElevenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),11);
        ElevenMedKits.setItemMeta(MedKitMeta);
        TwelveMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),12);
        TwelveMedKits.setItemMeta(MedKitMeta);
        ThirteenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),13);
        ThirteenMedKits.setItemMeta(MedKitMeta);
        FourteenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),14);
        FourteenMedKits.setItemMeta(MedKitMeta);
        FifteenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),15);
        FifteenMedKits.setItemMeta(MedKitMeta);
        SixteenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),16);
        SixteenMedKits.setItemMeta(MedKitMeta);
        SeventeenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),17);
        SeventeenMedKits.setItemMeta(MedKitMeta);
        EighteenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),18);
        EighteenMedKits.setItemMeta(MedKitMeta);
        NineteenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),19);
        NineteenMedKits.setItemMeta(MedKitMeta);
        TwentyMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),20);
        TwentyMedKits.setItemMeta(MedKitMeta);
        TwentyOneMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),21);
        TwentyOneMedKits.setItemMeta(MedKitMeta);
        TwentyTwoMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),22);
        TwentyTwoMedKits.setItemMeta(MedKitMeta);
        TwentyThreeMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),23);
        TwentyThreeMedKits.setItemMeta(MedKitMeta);
        TwentyFourMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),24);
        TwentyFourMedKits.setItemMeta(MedKitMeta);
        TwentyFiveMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),25);
        TwentyFiveMedKits.setItemMeta(MedKitMeta);
        TwentySixMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),26);
        TwentySixMedKits.setItemMeta(MedKitMeta);
        TwentySevenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),27);
        TwentySevenMedKits.setItemMeta(MedKitMeta);
        TwentyEightMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),28);
        TwentyEightMedKits.setItemMeta(MedKitMeta);
        TwentyNineMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),29);
        TwentyNineMedKits.setItemMeta(MedKitMeta);
        ThirtyMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),30);
        ThirtyMedKits.setItemMeta(MedKitMeta);
        ThirtyOneMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),31);
        ThirtyOneMedKits.setItemMeta(MedKitMeta);
        ThirtyTwoMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),32);
        ThirtyTwoMedKits.setItemMeta(MedKitMeta);
        ThirtyThreeMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),33);
        ThirtyThreeMedKits.setItemMeta(MedKitMeta);
        ThirtyFourMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),34);
        ThirtyFourMedKits.setItemMeta(MedKitMeta);
        ThirtyFiveMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),35);
        ThirtyFiveMedKits.setItemMeta(MedKitMeta);
        ThirtySixMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),36);
        ThirtySixMedKits.setItemMeta(MedKitMeta);
        ThirtySevenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),37);
        ThirtySevenMedKits.setItemMeta(MedKitMeta);
        ThirtyEightMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),38);
        ThirtyEightMedKits.setItemMeta(MedKitMeta);
        ThirtyNineMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),39);
        ThirtyNineMedKits.setItemMeta(MedKitMeta);
        FortyMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),40);
        FortyMedKits.setItemMeta(MedKitMeta);
        FortyOneMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),41);
        FortyOneMedKits.setItemMeta(MedKitMeta);
        FortyTwoMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),42);
        FortyTwoMedKits.setItemMeta(MedKitMeta);
        FortyThreeMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),43);
        FortyThreeMedKits.setItemMeta(MedKitMeta);
        FortyFourMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),44);
        FortyFourMedKits.setItemMeta(MedKitMeta);
        FortyFiveMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),45);
        FortyFiveMedKits.setItemMeta(MedKitMeta);
        FortySixMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),46);
        FortySixMedKits.setItemMeta(MedKitMeta);
        FortySevenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),47);
        FortySevenMedKits.setItemMeta(MedKitMeta);
        FortyEightMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),48);
        FortyEightMedKits.setItemMeta(MedKitMeta);
        FortyNineMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),49);
        FortyNineMedKits.setItemMeta(MedKitMeta);
        FiftyMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),50);
        FiftyMedKits.setItemMeta(MedKitMeta);
        FiftyOneMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),51);
        FiftyOneMedKits.setItemMeta(MedKitMeta);
        FiftyTwoMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),52);
        FiftyTwoMedKits.setItemMeta(MedKitMeta);
        FiftyThreeMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),53);
        FiftyThreeMedKits.setItemMeta(MedKitMeta);
        FiftyFourMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),54);
        FiftyFourMedKits.setItemMeta(MedKitMeta);
        FiftyFiveMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),55);
        FiftyFiveMedKits.setItemMeta(MedKitMeta);
        FiftySixMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),56);
        FiftySixMedKits.setItemMeta(MedKitMeta);
        FiftySevenMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),57);
        FiftySevenMedKits.setItemMeta(MedKitMeta);
        FiftyEightMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),58);
        FiftyEightMedKits.setItemMeta(MedKitMeta);
        FiftyNineMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),59);
        FiftyNineMedKits.setItemMeta(MedKitMeta);
        SixtyMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),60);
        SixtyMedKits.setItemMeta(MedKitMeta);
        SixtyOneMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),61);
        SixtyOneMedKits.setItemMeta(MedKitMeta);
        SixtyTwoMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),62);
        SixtyTwoMedKits.setItemMeta(MedKitMeta);
        SixtyThreeMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),63);
        SixtyThreeMedKits.setItemMeta(MedKitMeta);
        SixtyFourMedKits = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")),64);
        SixtyFourMedKits.setItemMeta(MedKitMeta);
    }
    private void setupCraftedMedKit() {
        CraftedMedKit = new ItemStack(Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Material")), medCraft.getConfig().getInt("MedKit.Result-Amount"));
        ItemMeta CraftedMedKitMeta = MedKitItem.getItemMeta();
        CraftedMedKit.setItemMeta(CraftedMedKitMeta);
    }
    private void setupMedKitRecipe() {
        if (MedCraft.getPlugin().getConfig().getBoolean("MedKit.Craftable")) {
            ShapedRecipe MedKitrecipe = new ShapedRecipe(Medrecipekey, CraftedMedKit);
            MedKitrecipe.shape(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Shape-top-row"), MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Shape-middle-row"), MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Shape-bottom-row"));
            if (MedKitrecipe.getIngredientMap().containsKey('1')) {
                MedKitrecipe.setIngredient('1', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-top-left").toUpperCase()));
            }
            if (MedKitrecipe.getIngredientMap().containsKey('2')) {
                MedKitrecipe.setIngredient('2', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-top-middle").toUpperCase()));
            }
            if (MedKitrecipe.getIngredientMap().containsKey('3')) {
                MedKitrecipe.setIngredient('3', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-top-right").toUpperCase()));
            }
            if (MedKitrecipe.getIngredientMap().containsKey('4')) {
                MedKitrecipe.setIngredient('4', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-middle-left").toUpperCase()));
            }
            if (MedKitrecipe.getIngredientMap().containsKey('5')) {
                MedKitrecipe.setIngredient('5', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-center").toUpperCase()));
            }
            if (MedKitrecipe.getIngredientMap().containsKey('6')) {
                MedKitrecipe.setIngredient('6', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-middle-right").toUpperCase()));
            }
            if (MedKitrecipe.getIngredientMap().containsKey('7')) {
                MedKitrecipe.setIngredient('7', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-bottom-left").toUpperCase()));
            }
            if (MedKitrecipe.getIngredientMap().containsKey('8')) {
                MedKitrecipe.setIngredient('8', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-bottom-middle").toUpperCase()));
            }
            if (MedKitrecipe.getIngredientMap().containsKey('9')) {
                MedKitrecipe.setIngredient('9', Material.getMaterial(MedCraft.getPlugin().getConfig().getString("MedKit.Crafting-Material-bottom-right").toUpperCase()));
            }
            Bukkit.addRecipe(MedKitrecipe);
        }
    }
}