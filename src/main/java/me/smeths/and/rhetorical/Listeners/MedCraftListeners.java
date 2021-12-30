package me.smeths.and.rhetorical.Listeners;

import me.smeths.and.rhetorical.Data.CustomItem;
import me.smeths.and.rhetorical.Handlers.MedicalHandler;
import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

import static me.smeths.and.rhetorical.Handlers.HexHandler.format;
import static me.smeths.and.rhetorical.Handlers.MedicalHandler.isBandaging;

@SuppressWarnings("InstantiationOfUtilityClass")
public class MedCraftListeners implements Listener {
    @EventHandler
    public void onMedicalItemUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();
        ItemStack oi = p.getInventory().getItemInOffHand();
        if (i.getItemMeta() != null) {
            for(CustomItem item : CustomItem.getCustomItems()) {
                if (item.getItem().getItemMeta() != null)
                    if (e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_BLOCK
                            && !p.hasPotionEffect(PotionEffectType.REGENERATION) && isBandaging(p) && oi != item.getItem()
                            && (i.getType().equals(item.getItem().getType())) && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                            && !e.getClickedBlock().getType().toString().contains("FURNACE") && !e.getClickedBlock().getType().toString().equals("SMOKER")
                            && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                            && !e.getClickedBlock().getType().toString().equals("BREWING_STAND") && !e.getClickedBlock().getType().toString().equals("LOOM")
                            && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData())
                            || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_BLOCK
                            && !p.hasPotionEffect(PotionEffectType.REGENERATION) && isBandaging(p) && oi != item.getItem()
                            && (i.getType().equals(item.getItem().getType())) && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                            && !e.getClickedBlock().getType().toString().contains("CHEST") && !e.getClickedBlock().getType().toString().contains("BED")
                            && !e.getClickedBlock().getType().toString().contains("FURNACE") && !e.getClickedBlock().getType().toString().equals("SMOKER")
                            && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                            && !e.getClickedBlock().getType().toString().equals("BREWING_STAND") && !e.getClickedBlock().getType().toString().equals("LOOM")
                            && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == Objects.requireNonNull(item.getItem().getItemMeta()).getCustomModelData())
                            || e.getAction() != Action.PHYSICAL && e.getAction() == Action.LEFT_CLICK_AIR
                            && isBandaging(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION) && oi != item.getItem()
                            && (i.getType().equals(item.getItem().getType())) && i.getItemMeta().hasCustomModelData()
                            && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == Objects.requireNonNull(item.getItem().getItemMeta()).getCustomModelData())
                            || e.getAction() != Action.PHYSICAL && e.getAction() == Action.RIGHT_CLICK_AIR
                            && isBandaging(p) && !p.hasPotionEffect(PotionEffectType.REGENERATION) && oi != item.getItem()
                            && (i.getType().equals(item.getItem().getType())) && i.getItemMeta().hasCustomModelData()
                            && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == Objects.requireNonNull(item.getItem().getItemMeta()).getCustomModelData())) {
                        if (p.getInventory().getItemInMainHand().getAmount() == 1 && p.hasPermission("med.craft.use")) {
                        p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                        p.updateInventory();
                        new MedicalHandler(p,item);
                        return;
                    }
                    if (i.getAmount() >= 2 && p.hasPermission("med.craft.use")) {
                        i.setAmount(i.getAmount() - 1);
                        p.updateInventory();
                        new MedicalHandler(p,item);
                        return;
                    }
                    if (!p.hasPermission("med.craft.use")) {
                        PacketHandler.getInstance().sendActionBarMessage(p,
                                format(Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Messages.NoPermUse")))
                                        .replace("[item]", Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName()));
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onOffhandNotRanged(PlayerInteractEntityEvent e) {
        for (CustomItem item : CustomItem.getCustomItems()) {
            Player p = e.getPlayer();
            Player recipient;
            ItemStack oi = p.getInventory().getItemInOffHand();
            ItemStack i = p.getInventory().getItemInMainHand();
            if (!(e.getRightClicked() instanceof Player))
                return;
            recipient = (Player) e.getRightClicked();
            if (item.getItem().getItemMeta() != null)
            if (oi.getType() == item.getItem().getType()
                    && oi.getItemMeta() != null && oi.getItemMeta().hasCustomModelData() && oi.getType() == item.getItem().getType()
                    && oi.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData()
                    && !recipient.hasPotionEffect(PotionEffectType.REGENERATION) && !item.HasRange()
                    && recipient.getHealth() < Objects.requireNonNull(recipient.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()
                    && !isBandaging(recipient) && i.getItemMeta() != null && i.getItemMeta().hasCustomModelData()
                    && i.getItemMeta().getCustomModelData() != item.getItem().getItemMeta().getCustomModelData()
                    || oi.getItemMeta() != null && oi.getItemMeta().hasCustomModelData() && oi.getType() == item.getItem().getType()
                    && oi.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData()
                    && !recipient.hasPotionEffect(PotionEffectType.REGENERATION) && !item.HasRange()
                    && recipient.getHealth() < Objects.requireNonNull(recipient.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()
                    && isBandaging(recipient) && i.getType() == Material.AIR
                    || oi.getItemMeta() != null && oi.getItemMeta().hasCustomModelData() && oi.getType() == item.getItem().getType()
                    && oi.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData()
                    && !recipient.hasPotionEffect(PotionEffectType.REGENERATION) && !item.HasRange()
                    && recipient.getHealth() < Objects.requireNonNull(recipient.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()
                    && isBandaging(recipient)) {
                if (oi.getAmount() == 1 && p.hasPermission("med.craft.use.offhand")) {
                    PacketHandler.getInstance().sendActionBarMessage(p,
                            format(Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Messages.OffhandUse")))
                                    .replace("[item]", Objects.requireNonNull(p.getInventory().getItemInOffHand().getItemMeta()).getDisplayName())
                                    .replace("[recipient]", recipient.getDisplayName()));
                    p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                    p.updateInventory();
                    new MedicalHandler(recipient, item);
                    return;
                }
                if (oi.getAmount() >= 2 && p.hasPermission("med.craft.use.offhand")) {
                    PacketHandler.getInstance().sendActionBarMessage(p,
                            format(Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Messages.OffhandUse")))
                                    .replace("[item]", Objects.requireNonNull(p.getInventory().getItemInOffHand().getItemMeta()).getDisplayName())
                                    .replace("[recipient]", recipient.getDisplayName()));
                    oi.setAmount(oi.getAmount() - 1);
                    p.updateInventory();
                    new MedicalHandler(recipient, item);
                    return;
                }
                if (!p.hasPermission("med.craft.use.offhand")) {
                    PacketHandler.getInstance().sendActionBarMessage(p,
                            format(Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Bandage.NoPermUseOffhand"))
                                    .replace("[item]", Objects.requireNonNull(p.getInventory().getItemInOffHand().getItemMeta()).getDisplayName())));
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void StopCraft(CraftItemEvent e) {
        if (e.getInventory().getResult() != null) {
            if (e.getWhoClicked().getType() == EntityType.PLAYER) {
                for (CustomItem customItem : CustomItem.getCustomItems()) {
                    if (customItem.getItem().getItemMeta() != null)
                    if (Objects.requireNonNull(e.getInventory().getResult()).getType() == customItem.getItem().getType()
                            && e.getInventory().getResult().getItemMeta() != null && e.getInventory().getResult().getItemMeta().hasCustomModelData()
                            && e.getInventory().getResult().getItemMeta().getCustomModelData() == customItem.getItem().getItemMeta().getCustomModelData()) {
                        Player crafter = (Player) e.getWhoClicked();
                        if (!crafter.hasPermission("med.craft")) {
                            e.setCancelled(true);
                            PacketHandler.getInstance().sendActionBarMessage(crafter,
                                    format(Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Messages.NoPermsCraft")))
                                            .replace("[item]", e.getInventory().getResult().getItemMeta().getDisplayName()));
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
    @EventHandler(priority = EventPriority.MONITOR)
    public void OnMedCraftItemPickup(PlayerMoveEvent e) {
        if (MedCraft.getPlugin().getConfig().getBoolean("Experimental.AlternatePickup")) {
            for (Entity E : e.getPlayer().getNearbyEntities(0.1,0.1,0.1)) {
                if (E.getType() == EntityType.DROPPED_ITEM) {
                    Item i = (Item) E;
                    ItemStack stackitem = i.getItemStack();
                    for (CustomItem citem : CustomItem.getCustomItems()) {
                        if (citem.getItem() == stackitem)
                        e.getPlayer().getInventory().addItem(citem.getItem());
                        E.remove();
                    }
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=true)
    public void OnVanillaItemPickup(EntityPickupItemEvent e) {
        if (MedCraft.getPlugin().getConfig().getBoolean("Experimental.AlternatePickup")) {
            if (e.getEntity().getType() == EntityType.PLAYER) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        for (CustomItem customitem : CustomItem.getCustomItems()) {
            Player player = e.getPlayer();
            player.discoverRecipe(new NamespacedKey(MedCraft.getPlugin(), customitem.getInternalName()));
        }
    }
    @EventHandler
    public void invclick(InventoryClickEvent ice){
        for (CustomItem customitem : CustomItem.getCustomItems()) {
            if (ice.getWhoClicked() instanceof Player
                    && ice.getClickedInventory() != null
                    && ice.getClickedInventory().getType() != null
                    && ice.getClickedInventory().getType() == InventoryType.GRINDSTONE
                    && ice.getCursor().getType() == customitem.getItem().getType()
                    && ice.getCursor().getItemMeta().hasCustomModelData()
                    && ice.getCursor().getItemMeta().getCustomModelData() == customitem.getItem().getItemMeta().getCustomModelData()) {
                Player p = (Player) ice.getWhoClicked();
                p.closeInventory();
            }
        }
    }
    @EventHandler
    public void invdrag(InventoryDragEvent ide){
        for (CustomItem customitem : CustomItem.getCustomItems()) {
            if (ide.getWhoClicked() instanceof Player
                    && ide.getInventory() != null
                    && ide.getInventory().getType() != null
                    && ide.getInventory().getType() == InventoryType.GRINDSTONE
                    && ide.getCursor() != null
                    && ide.getCursor().getType() == customitem.getItem().getType()
                    && ide.getCursor().getItemMeta().hasCustomModelData()
                    && ide.getCursor().getItemMeta().getCustomModelData() == customitem.getItem().getItemMeta().getCustomModelData()
                    || ide.getWhoClicked() instanceof Player
                    && ide.getInventory() != null
                    && ide.getInventory().getType() != null
                    && ide.getInventory().getType() == InventoryType.GRINDSTONE
                    && ide.getOldCursor() != null
                    && ide.getOldCursor().getType() == customitem.getItem().getType()
                    && ide.getOldCursor().getItemMeta().hasCustomModelData()
                    && ide.getOldCursor().getItemMeta().getCustomModelData() == customitem.getItem().getItemMeta().getCustomModelData()) {
                Player p = (Player) ide.getWhoClicked();
                p.closeInventory();
            }
        }
    }
}