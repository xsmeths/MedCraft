package me.smeths.and.rhetorical.Listeners;

import me.smeths.and.rhetorical.Handlers.BandageHandler;
import me.smeths.and.rhetorical.Handlers.MedKitHandler;
import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.ItemManager.ItemLoader;
import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

@SuppressWarnings("InstantiationOfUtilityClass")
public class MedCraftListeners implements Listener {
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();
        if (i.getItemMeta() != null)
            if (e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_BLOCK && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                    && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData()
                    && e.getClickedBlock() != null
                    && !e.getClickedBlock().getType().toString().contains("FURNACE") && e.getClickedBlock().getType() != Material.SMOKER
                    && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                    && e.getClickedBlock().getType() != Material.BREWING_STAND && e.getClickedBlock().getType() != Material.LOOM
                    && p.getGameMode() != GameMode.CREATIVE
                    && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("MedKit.ModelData"))

                    || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_BLOCK
                    && !p.hasPotionEffect(PotionEffectType.REGENERATION) && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData()
                    && e.getClickedBlock() != null
                    && !e.getClickedBlock().getType().toString().contains("CHEST") && !e.getClickedBlock().getType().toString().contains("BED")
                    && !e.getClickedBlock().getType().toString().contains("FURNACE") && e.getClickedBlock().getType() != Material.SMOKER
                    && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                    && e.getClickedBlock().getType() != Material.BREWING_STAND && e.getClickedBlock().getType() != Material.LOOM
                    && p.getGameMode() != GameMode.CREATIVE
                    && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("MedKit.ModelData"))

                    || e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_AIR
                    && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData()
                    && p.getGameMode() != GameMode.CREATIVE
                    && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("MedKit.ModelData"))

                    || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_AIR
                    && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData()
                    && p.getGameMode() != GameMode.CREATIVE
                    && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("MedKit.ModelData"))) {
                if (p.getInventory().getItemInMainHand().getAmount() == 1 && p.hasPermission("medkit.use")) {
                    int heldslot = p.getInventory().getHeldItemSlot();
                    p.getInventory().setItem(heldslot, new ItemStack(Material.AIR));
                    p.updateInventory();
                    new MedKitHandler(p);
                    return;
                }
                if (i.getAmount() >= 2 && p.hasPermission("medkit.use")) {
                    i.setAmount(i.getAmount() - 1);
                    p.updateInventory();
                    new MedKitHandler(p);
                    return;
                }
                if (!p.hasPermission("medkit.use")) {
                    PacketHandler.getInstance().sendActionBarMessage(p, ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("MedKit.NoPermUse"))));
                } else {
                    e.setCancelled(true);
                }
            }
        if (i.getItemMeta() != null)
            if (e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_BLOCK
                    && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                    && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                    && (i.getType().equals(ItemLoader.getBandageItem().getType()))
                    && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                    && !e.getClickedBlock().getType().toString().contains("FURNACE")
                    && e.getClickedBlock().getType() != Material.SMOKER
                    && !e.getClickedBlock().getType().toString().contains("TABLE")
                    && !e.getClickedBlock().getType().toString().contains("HOPPER")
                    && e.getClickedBlock().getType() != Material.BREWING_STAND
                    && e.getClickedBlock().getType() != Material.LOOM
                    && p.getGameMode() != GameMode.CREATIVE
                    && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))

                    || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_BLOCK
                    && !p.hasPotionEffect(PotionEffectType.REGENERATION) && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                    && (i.getType().equals(ItemLoader.getBandageItem().getType())) && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                    && !e.getClickedBlock().getType().toString().contains("CHEST")
                    && !e.getClickedBlock().getType().toString().contains("BED")
                    && !e.getClickedBlock().getType().toString().contains("FURNACE")
                    && e.getClickedBlock().getType() != Material.SMOKER
                    && !e.getClickedBlock().getType().toString().contains("TABLE")
                    && !e.getClickedBlock().getType().toString().contains("HOPPER")
                    && e.getClickedBlock().getType() != Material.BREWING_STAND
                    && e.getClickedBlock().getType() != Material.LOOM
                    && p.getGameMode() != GameMode.CREATIVE
                    && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))

                    || e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_AIR
                    && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData()
                    && p.getGameMode() != GameMode.CREATIVE
                    && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))

                    || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_AIR
                    && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                    && (i.getType().equals(ItemLoader.getBandageItem().getType())) && i.getItemMeta().hasCustomModelData()
                    && p.getGameMode() != GameMode.CREATIVE
                    && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))) {

                if (p.getInventory().getItemInMainHand().getAmount() == 1 && p.hasPermission("bandage.use")) {
                    int heldslot = p.getInventory().getHeldItemSlot();
                    p.getInventory().setItem(heldslot, new ItemStack(Material.AIR));
                    p.updateInventory();
                    new BandageHandler(p);
                    return;
                }
                if (i.getAmount() >= 2 && p.hasPermission("bandage.use")) {
                    i.setAmount(i.getAmount() - 1);
                    p.updateInventory();
                    new BandageHandler(p);
                    return;
                }
                if (!p.hasPermission("bandage.use")) {
                    PacketHandler.getInstance().sendActionBarMessage(p, ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Bandage.NoPermUse"))));
                } else {
                    e.setCancelled(true);
                }
            }
    }
    @EventHandler
    public void offhandbandage(PlayerInteractEntityEvent e){
    Player p = e.getPlayer();
    Player recipient;
    ItemStack oi = p.getInventory().getItemInOffHand();
        if (!(e.getRightClicked() instanceof Player))
            return;
        recipient = (Player) e.getRightClicked();
        if (oi.getType() == ItemLoader.getBandageItem().getType()
                && oi.getItemMeta() != null && oi.getItemMeta().hasCustomModelData()
                && oi.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData")
                && !recipient.hasPotionEffect(PotionEffectType.REGENERATION)
                && recipient.getHealth() < Objects.requireNonNull(recipient.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()
                && BandageHandler.isBandaging(recipient) && MedKitHandler.isMedding(recipient)){
            if (oi.getAmount() == 1 && p.hasPermission("bandage.use.offhand")) {
                p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                p.updateInventory();
                new BandageHandler(recipient);
                return;
            }
            if (oi.getAmount() >= 2 && p.hasPermission("bandage.use.offhand")) {
                oi.setAmount(oi.getAmount() - 1);
                p.updateInventory();
                new BandageHandler(recipient);
                return;
            }
            if (!p.hasPermission("bandage.use.offhand")) {
                PacketHandler.getInstance().sendActionBarMessage(p, ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Bandage.NoPermUseOffhand"))));
            } else {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void StopCraft(CraftItemEvent e) {
        if (e.getInventory().getResult() != null)
        if (Objects.requireNonNull(e.getInventory().getResult()).getType() == ItemLoader.getMedKitItem().getType()
                && e.getInventory().getResult().getItemMeta() != null && e.getInventory().getResult().getItemMeta().hasCustomModelData()
                && e.getInventory().getResult().getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("MedKit.ModelData")) {
            Player crafter = (Player) e.getWhoClicked();
            if (!crafter.hasPermission("medkit.craft")) {
                e.setCancelled(true);
                PacketHandler.getInstance().sendActionBarMessage(crafter, ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("MedKit.NoPermCraft"))));
            }
        }
        if (e.getInventory().getResult() != null)
        if (Objects.requireNonNull(e.getInventory().getResult()).getType() == ItemLoader.getBandageItem().getType()
                && e.getInventory().getResult().getItemMeta() != null && e.getInventory().getResult().getItemMeta().hasCustomModelData()
                && e.getInventory().getResult().getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData")) {
            Player crafter = (Player) e.getWhoClicked();
            if (!crafter.hasPermission("bandage.craft")) {
                e.setCancelled(true);
                PacketHandler.getInstance().sendActionBarMessage(crafter, ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Bandage.NoPermCraft"))));
            }
        }
    }
    @EventHandler
    public void VanillaOnly(PrepareItemCraftEvent e){
        if (Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getMedKitItem()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getElevenMedKits()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwelveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFourteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFifteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSeventeenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getEighteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getNineteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentySixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentySevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtySixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtySevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortySixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortySevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftySixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftySevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyFourMedKits()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getBandageItem()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getElevenMedKits()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwelveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFourteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFifteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSeventeenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getEighteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getNineteenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentySixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentySevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getTwentyNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtySixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtySevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getThirtyNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortySixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortySevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFortyNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyFourBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyFiveBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftySixBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftySevenBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyEightBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getFiftyNineBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyOneBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyTwoBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyThreeBandages()))
                || Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(ItemLoader.getSixtyFourBandages()))){
            e.getInventory().setResult(new ItemStack(Material.AIR));
        }
    }
}