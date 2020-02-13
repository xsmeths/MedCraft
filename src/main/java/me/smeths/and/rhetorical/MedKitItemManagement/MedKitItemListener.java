package me.smeths.and.rhetorical.MedKitItemManagement;

import me.smeths.and.rhetorical.Handlers.MedKitHandler;
import me.smeths.and.rhetorical.Handlers.PacketHandler;
import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;

public class MedKitItemListener
  implements Listener {

  @EventHandler
  public void onPlayerUseMedKit(PlayerInteractEvent e) {
    if ((e.getAction() == Action.LEFT_CLICK_BLOCK) && (e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getPlayer().getGameMode().equals(GameMode.ADVENTURE) && (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)))) {
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
      if ((i.getType().equals(MedKitItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData() == true && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("MedKit.ModelData"))) {
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
  }
  @EventHandler
  public void StopCraftMedKit(CraftItemEvent e) {
    if (e.getInventory().getResult().getType() == MedKitItemLoader.getMedKitItem().getType()) {
      Player crafter = (Player) e.getWhoClicked();
      if (!crafter.hasPermission("medkit.craft")) {
        e.setCancelled(true);
        PacketHandler.getInstance().sendActionBarMessage(crafter,ChatColor.RED + "No permissions: you need medkit.craft");
      }
    }
  }
}