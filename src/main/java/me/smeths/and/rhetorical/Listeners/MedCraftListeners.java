package me.smeths.and.rhetorical.Listeners;

import me.smeths.and.rhetorical.Handlers.BandageHandler;
import me.smeths.and.rhetorical.Handlers.MedKitHandler;
import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.ItemManager.ItemLoader;
import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;

public class MedCraftListeners
        implements Listener {

    @EventHandler
    public void onPlayerUseMedKit(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Player p = e.getPlayer();
        ItemStack[] toCheck;
        try {
            toCheck = new ItemStack[2];
            toCheck[0] = ((ItemStack) p.getInventory().getClass().getMethod("getItemInMainHand", new Class[0]).invoke(p, new Object[0]));
            toCheck[1] = ((ItemStack) p.getInventory().getClass().getMethod("getItemInOffHand", new Class[0]).invoke(p, new Object[0]));
        } catch (NoSuchMethodException ex) {
            toCheck = new ItemStack[1];
            toCheck[0] = p.getInventory().getItemInHand();
        } catch (InvocationTargetException ex) {
            toCheck = new ItemStack[1];
            toCheck[0] = p.getInventory().getItemInHand();
        } catch (IllegalAccessException ex) {
            toCheck = new ItemStack[1];
            toCheck[0] = p.getInventory().getItemInHand();
        } catch (IllegalArgumentException ex) {
            toCheck = new ItemStack[1];
            toCheck[0] = p.getInventory().getItemInHand();
        }
        for (ItemStack i : toCheck)
            if ((i.getType().equals(ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData() == true && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("MedKit.ModelData"))) {
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
                    PacketHandler.getInstance().sendActionBarMessage(p, ChatColor.RED + "No Permissions: you need medkit.use");
                } else {
                    e.setCancelled(true);
                }
            }
        for (ItemStack i : toCheck)
            if ((i.getType().equals(ItemLoader.getBandageItem().getType())) && i.getItemMeta().hasCustomModelData() == true && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))) {
                if (p.getInventory().getItemInMainHand().getAmount() == 1 && p.hasPermission("bandage.use")) {
                    int heldslot = p.getInventory().getHeldItemSlot();
                    p.getInventory().setItem(heldslot, new ItemStack(Material.AIR));
                    p.updateInventory();
                    new BandageHandler(p);
                    return;
                } if (i.getAmount() >= 2 && p.hasPermission("bandage.use")) {
                    i.setAmount(i.getAmount() - 1);
                    p.updateInventory();
                    new BandageHandler(p);
                    return;
                }if (!p.hasPermission("bandage.use")){
                    PacketHandler.getInstance().sendActionBarMessage(p, ChatColor.RED + "No Permissions: you need bandage.use");
                } else {
                    e.setCancelled(true);
                }
            }
    }
    @EventHandler
    public void StopCraftMedKit(CraftItemEvent e) {
        if (e.getInventory().getResult().getType() == ItemLoader.getMedKitItem().getType() && e.getInventory().getResult().getItemMeta().hasCustomModelData() == true && e.getInventory().getResult().getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("MedKit.ModelData")) {
            Player crafter = (Player) e.getWhoClicked();
            if (!crafter.hasPermission("medkit.craft")) {
                e.setCancelled(true);
                PacketHandler.getInstance().sendActionBarMessage(crafter,ChatColor.RED + "No permissions: you need medkit.craft");
            }
        }
        if (e.getInventory().getResult().getType() == ItemLoader.getBandageItem().getType() && e.getInventory().getResult().getItemMeta().hasCustomModelData() == true && e.getInventory().getResult().getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData")) {
            Player crafter = (Player) e.getWhoClicked();
            if (!crafter.hasPermission("bandage.craft")) {
                e.setCancelled(true);
                PacketHandler.getInstance().sendActionBarMessage(crafter, ChatColor.RED + "No permissions: you need bandage.craft");
            }
        }
    }
}