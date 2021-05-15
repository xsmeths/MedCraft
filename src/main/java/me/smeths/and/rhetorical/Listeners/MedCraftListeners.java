package me.smeths.and.rhetorical.Listeners;

import me.smeths.and.rhetorical.Data.CustomItem;
import me.smeths.and.rhetorical.Handlers.BandageHandler;
import me.smeths.and.rhetorical.Handlers.MedKitHandler;
import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.ItemManager.ItemLoader;
import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
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
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();
        if (i.getItemMeta() != null){
            for(CustomItem item : CustomItem.getCustomItems()){
               if (e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_BLOCK
                        && !p.hasPotionEffect(PotionEffectType.REGENERATION) && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                        && (i.getType().equals(item.getItem().getType())) && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                        && !e.getClickedBlock().getType().toString().contains("FURNACE") && e.getClickedBlock().getType() != Material.SMOKER
                        && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                        && e.getClickedBlock().getType() != Material.BREWING_STAND && e.getClickedBlock().getType() != Material.LOOM
                        && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData())
                        || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_BLOCK
                        && !p.hasPotionEffect(PotionEffectType.REGENERATION) && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                        && (i.getType().equals(item.getItem().getType())) && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                        && !e.getClickedBlock().getType().toString().contains("CHEST") && !e.getClickedBlock().getType().toString().contains("BED")
                        && !e.getClickedBlock().getType().toString().contains("FURNACE") && e.getClickedBlock().getType() != Material.SMOKER
                        && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                        && e.getClickedBlock().getType() != Material.BREWING_STAND && e.getClickedBlock().getType() != Material.LOOM
                        && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData())
                        || e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_AIR
                        && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                        && (i.getType().equals(item.getItem().getType())) && i.getItemMeta().hasCustomModelData()
                        && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData())
                        || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_AIR
                        && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                        && (i.getType().equals(item.getItem().getType())) && i.getItemMeta().hasCustomModelData()
                        && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData())) {
                    if (p.getInventory().getItemInMainHand().getAmount() == 1 && p.hasPermission("bandage.use")) {
                        int heldslot = p.getInventory().getHeldItemSlot();
                        p.getInventory().setItem(heldslot, new ItemStack(Material.AIR));
                        p.updateInventory();
                        new BandageHandler(p,item);
                        return;
                    }
                    if (i.getAmount() >= 2 && p.hasPermission("bandage.use")) {
                        i.setAmount(i.getAmount() - 1);
                        p.updateInventory();
                        new BandageHandler(p,item);
                        return;
                    }
                    if (!p.hasPermission("bandage.use")) {
                        PacketHandler.getInstance().sendActionBarMessage(p, ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Bandage.NoPermUse"))));
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

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
        /*if (i.getItemMeta() != null)
            if (e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_BLOCK
                    && !p.hasPotionEffect(PotionEffectType.REGENERATION) && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                    && !e.getClickedBlock().getType().toString().contains("FURNACE") && e.getClickedBlock().getType() != Material.SMOKER
                    && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                    && e.getClickedBlock().getType() != Material.BREWING_STAND && e.getClickedBlock().getType() != Material.LOOM
                    && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))
                    || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_BLOCK
                    && !p.hasPotionEffect(PotionEffectType.REGENERATION) && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                    && !e.getClickedBlock().getType().toString().contains("CHEST") && !e.getClickedBlock().getType().toString().contains("BED")
                    && !e.getClickedBlock().getType().toString().contains("FURNACE") && e.getClickedBlock().getType() != Material.SMOKER
                    && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                    && e.getClickedBlock().getType() != Material.BREWING_STAND && e.getClickedBlock().getType() != Material.LOOM
                    && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))
                    || e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_AIR
                    && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData()
                    && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))
                    || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_AIR
                    && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData()
                    && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))) {
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
            }*/
    }

    @EventHandler
    public void offhandbandage(PlayerInteractEntityEvent e) {
        /*Player p = e.getPlayer();
        Player recipient;
        ItemStack oi = p.getInventory().getItemInOffHand();
        ItemStack i = p.getInventory().getItemInMainHand();
        if (!(e.getRightClicked() instanceof Player))
            return;
        recipient = (Player) e.getRightClicked();
        if (oi.getType() == ItemLoader.getBandageItem().getType()
                && oi.getItemMeta() != null && oi.getItemMeta().hasCustomModelData() && oi.getType() == ItemLoader.getBandageItem().getType()
                && oi.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData")
                && !recipient.hasPotionEffect(PotionEffectType.REGENERATION)
                && recipient.getHealth() < Objects.requireNonNull(recipient.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()
                && BandageHandler.isBandaging(recipient) && MedKitHandler.isMedding(recipient)
                && i.getItemMeta() != null && i.getItemMeta().hasCustomModelData() && !i.getType().equals(ItemLoader.getMedKitItem().getType())
                && i.getItemMeta().getCustomModelData() != MedCraft.getPlugin().getConfig().getInt("MedKit.ModelData")
                || oi.getItemMeta() != null && oi.getItemMeta().hasCustomModelData() && oi.getType() == ItemLoader.getBandageItem().getType()
                && oi.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData")
                && !recipient.hasPotionEffect(PotionEffectType.REGENERATION)
                && recipient.getHealth() < Objects.requireNonNull(recipient.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()
                && BandageHandler.isBandaging(recipient) && MedKitHandler.isMedding(recipient)
                && i.getType() == Material.AIR
                || oi.getItemMeta() != null && oi.getItemMeta().hasCustomModelData() && oi.getType() == ItemLoader.getBandageItem().getType()
                && oi.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData")
                && !recipient.hasPotionEffect(PotionEffectType.REGENERATION)
                && recipient.getHealth() < Objects.requireNonNull(recipient.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()
                && BandageHandler.isBandaging(recipient) && MedKitHandler.isMedding(recipient)
                && i.getItemMeta() != ItemLoader.getMedKitItem().getItemMeta()) {
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
        }*/
    }

    @EventHandler
    public void StopCraft(CraftItemEvent e) {
        if (e.getInventory().getResult() != null) {
            if (e.getWhoClicked().getType() == EntityType.PLAYER) {
                for (CustomItem customItem : CustomItem.getCustomItems()) {
                    if (Objects.requireNonNull(e.getInventory().getResult()).getType() == customItem.getItem().getType()
                            && e.getInventory().getResult().getItemMeta() != null && e.getInventory().getResult().getItemMeta().hasCustomModelData()
                            && e.getInventory().getResult().getItemMeta().getCustomModelData() == customItem.getItem().getItemMeta().getCustomModelData()) {
                        Player crafter = (Player) e.getWhoClicked();
                        if (!crafter.hasPermission(customItem.getInternalName() + ".craft")) {
                            e.setCancelled(true);
                            PacketHandler.getInstance().sendActionBarMessage(crafter, ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString(customItem.getInternalName() + ".NoPermCraft"))));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void VanillaOnly(PrepareItemCraftEvent e) {
        for (CustomItem customitem : CustomItem.getCustomItems()) {
            for (int i = 0; i <= 64; i++) {
                ItemStack is = customitem.getItem().clone();
                is.setAmount(i);
                if (Arrays.stream(e.getInventory().getMatrix()).anyMatch(Predicate.isEqual(is))) {
                    e.getInventory().setResult(new ItemStack(Material.AIR));
                }
            }
        }
    }
}