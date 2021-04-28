package me.smeths.and.rhetorical.BandageItemManagement;

import me.smeths.and.rhetorical.Handlers.BandageHandler;
import me.smeths.and.rhetorical.Handlers.PacketHandler;
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
import java.util.Objects;

public class BandageItemListener
  implements Listener {

  @EventHandler
  public void onPlayerUseBandage(PlayerInteractEvent e) {
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
      if (i.getType().equals(BandageItemLoader.getBandageItem().getType()) && Objects.requireNonNull(i.getItemMeta()).hasCustomModelData() && i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData")) {
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
  public void StopCraftBandage(CraftItemEvent e) {
    if (e.getInventory().getResult().getType() == BandageItemLoader.getBandageItem().getType() && e.getInventory().getResult().getItemMeta().hasCustomModelData() == true && e.getInventory().getResult().getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData")) {
      Player crafter = (Player) e.getWhoClicked();
      if (!crafter.hasPermission("bandage.craft")) {
        e.setCancelled(true);
        PacketHandler.getInstance().sendActionBarMessage(crafter,ChatColor.RED + "No permissions: you need bandage.craft");
      }
    }
  }
}