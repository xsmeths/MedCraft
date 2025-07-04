package me.smeths.and.rhetorical.Listeners;

import me.smeths.and.rhetorical.Data.CustomItem;
import me.smeths.and.rhetorical.Handlers.MedicalHandler;
import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.MedCraft;
import me.smeths.and.rhetorical.Utils.abstractAttribute;
import me.smeths.and.rhetorical.Utils.abstractModelData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
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
            for (CustomItem item : CustomItem.getCustomItems()) {
                if (item.getItem().getItemMeta() != null)
                    if (shouldStartHealing(p, oi, item.getItem(), i, e.getAction(), e.getClickedBlock())) {
                        if (!p.hasPermission("med.craft.use")) {
                            if (p.getInventory().getItemInMainHand().getItemMeta() != null) {
                                PacketHandler.getInstance().sendActionBarMessage(p,
                                        format(MedCraft.getPlugin().getConfig().getString("Messages.NoPermUse"))
                                                .replace("[item]", p.getInventory().getItemInMainHand().getItemMeta().getDisplayName()));
                            }
                        }
                        if (p.getInventory().getItemInMainHand().getAmount() == 1 && p.hasPermission("med.craft.use")) {
                            p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                            p.updateInventory();
                            new MedicalHandler(p, item);

                            return;
                        }
                        if (i.getAmount() >= 2 && p.hasPermission("med.craft.use")) {
                            i.setAmount(i.getAmount() - 1);
                            p.updateInventory();
                            new MedicalHandler(p, item);
                            return;
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
            if (!(e.getRightClicked() instanceof Player)) return;
            recipient = (Player) e.getRightClicked();
            if (item.getItem().getItemMeta() != null) {
                if (!item.HasRange() && shouldStartHealingOthers(recipient, p, oi, item.getItem(), i)) {
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
    }

    @EventHandler
    public void StopCraft(CraftItemEvent e) {
        if (e.getInventory().getResult() != null) {
            if (e.getWhoClicked().getType() == EntityType.PLAYER) {
                for (CustomItem customItem : CustomItem.getCustomItems()) {
                    if (customItem.getItem().getItemMeta() != null) {
                        if (e.getInventory().getResult().getType() == customItem.getItem().getType()
                                && e.getInventory().getResult().getItemMeta() != null && abstractModelData.hasCustomModelData(e.getInventory().getResult().getItemMeta())
                                && abstractModelData.getCustomModelData(e.getInventory().getResult().getItemMeta()) == abstractModelData.getCustomModelData(customItem.getItem().getItemMeta())) {
                            Player crafter = (Player) e.getWhoClicked();
                            if (!crafter.hasPermission("med.craft")) {
                                e.setCancelled(true);
                                PacketHandler.getInstance().sendActionBarMessage(crafter,
                                        format(MedCraft.getPlugin().getConfig().getString("Messages.NoPermsCraft"))
                                                .replace("[item]", e.getInventory().getResult().getItemMeta().getDisplayName()));
                            }
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
        String Etype = EntityType.valueOf("DROPPED_ITEM") == null ? "ITEM" : "DROPPED_ITEM";
        if (MedCraft.getPlugin().getConfig().getBoolean("Experimental.AlternatePickup")) {
            for (Entity E : e.getPlayer().getNearbyEntities(0.1, 0.1, 0.1)) {
                if (E.getType() == EntityType.valueOf(Etype)) {
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

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnVanillaItemPickup(EntityPickupItemEvent e) {
        if (MedCraft.getPlugin().getConfig().getBoolean("Experimental.AlternatePickup")) {
            if (e.getEntity().getType() == EntityType.PLAYER) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        for (CustomItem customitem : CustomItem.getCustomItems()) {
            Player player = e.getPlayer();
            player.discoverRecipe(new NamespacedKey(MedCraft.getPlugin(), customitem.getInternalName()));
        }
    }

    private boolean isMatchingCustomItem(ItemStack i, ItemStack item) {
        if (i == null || item == null || i.getItemMeta() == null || item.getItemMeta() == null) return false;
        return i.getType() == item.getType()
                && abstractModelData.hasCustomModelData(i.getItemMeta())
                && abstractModelData.getCustomModelData(i.getItemMeta()) == abstractModelData.getCustomModelData(item.getItemMeta());
    }

    private boolean isDisallowedBlock(Block block) {
        if (block == null) return false;
        String type = block.getType().toString();
        return type.contains("CHEST") || type.contains("BED") || type.contains("FURNACE")
                || type.equals("SMOKER") || type.contains("TABLE") || type.contains("HOPPER")
                || type.equals("BREWING_STAND") || type.equals("LOOM");
    }

    private boolean shouldStartHealing(Player p, ItemStack offhand, ItemStack item, ItemStack mainhand, Action action, Block clickedBlock) {
        return !isBandaging(p)
                && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                && p.getGameMode() != GameMode.CREATIVE
                && offhand != item
                && isMatchingCustomItem(mainhand, item)
                && !isDisallowedBlock(clickedBlock)
                && (action == Action.LEFT_CLICK_BLOCK
                || action == Action.RIGHT_CLICK_BLOCK
                || action == Action.LEFT_CLICK_AIR
                || action == Action.RIGHT_CLICK_AIR);
    }
    private boolean shouldStartHealingOthers(Player recipient, Player p, ItemStack offhand, ItemStack item, ItemStack mainhand) {
        return !isBandaging(p) && !isBandaging(recipient)
                && !p.hasPotionEffect(PotionEffectType.REGENERATION)
                && !recipient.hasPotionEffect(PotionEffectType.REGENERATION)
                && recipient.getHealth() < recipient.getAttribute(abstractAttribute.valueOf("GENERIC_MAX_HEALTH")).getValue()
                && p.getGameMode() != GameMode.CREATIVE
                && recipient.getGameMode() != GameMode.CREATIVE
                && mainhand != item
                && !isMatchingCustomItem(mainhand, item)
                && isMatchingCustomItem(offhand, item);
    }
}