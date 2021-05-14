package me.smeths.and.rhetorical.Listeners;

import me.smeths.and.rhetorical.handlers.BandageHandler;
import me.smeths.and.rhetorical.handlers.MedKitHandler;
import me.smeths.and.rhetorical.handlers.PacketHandler;
import me.smeths.and.rhetorical.itemmanager.ItemLoader;
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
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

@SuppressWarnings("InstantiationOfUtilityClass")
public class MedCraftListeners implements Listener {
   /*& @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();
        if (i.getItemMeta() != null)
            if (e.getAction() != Action.PHYSICAL && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                    && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData()
                    && e.getClickedBlock() != null
                    && !e.getClickedBlock().getType().toString().contains("FURNACE") && e.getClickedBlock().getType() != Material.SMOKER
                    && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                    && e.getClickedBlock().getType() != Material.BREWING_STAND && e.getClickedBlock().getType() != Material.LOOM
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
            if (e.getAction() != Action.PHYSICAL && !p.hasPotionEffect(PotionEffectType.REGENERATION) && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                    && (i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                    && !e.getClickedBlock().getType().toString().contains("FURNACE") && e.getClickedBlock().getType() != Material.SMOKER
                    && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                    && e.getClickedBlock().getType() != Material.BREWING_STAND && e.getClickedBlock().getType() != Material.LOOM
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
            }

    }

    @EventHandler
    public void offhandbandage(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
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
        }
    }*/

    @EventHandler
    public void StopCraft(CraftItemEvent e) {
        if (e.getInventory().getResult() != null)
            if (e.getWhoClicked().getType() == EntityType.PLAYER)
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
            if (e.getWhoClicked().getType() == EntityType.PLAYER)
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
}