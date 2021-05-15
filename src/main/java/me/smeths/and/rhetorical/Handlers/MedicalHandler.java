package me.smeths.and.rhetorical.Handlers;

import me.smeths.and.rhetorical.Data.CustomItem;
import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MedicalHandler {
  private static final Map<Player, BukkitRunnable> bandagingPlayers = new HashMap<>();
    private static Map<Player, ItemMeta> ItemUsed = new HashMap<>();
  public MedicalHandler(final Player p, CustomItem item) {
    if (bandagingPlayers.containsKey(p)) {
        bandagingPlayers.get(p).cancel();
        bandagingPlayers.remove(p);
        if (item.isDropifnotused()) {
            p.getWorld().dropItem(p.getLocation(), item.getItem());
        }
        if (!item.isDropifnotused()) {
            p.getInventory().addItem(item.getItem());
        }
    }
    bandagingPlayers.put(p, new BukkitRunnable() {
              final Location position = p.getLocation();
              final int total = 60;
              int progress = 0;
              final int duration = 20 * item.getRegen_time();
              final int amplifier = item.getRegen_amplifier();
              final int multiplier = item.getWarmupspeed();

              public void cancel() {
                super.cancel();
              }

              public void run() {
                boolean cancelled = p.getLocation().distance(position) > 0.75D;
                if ((progress > total) || (cancelled)) {
                  if (cancelled) {
                      if (item.isPerformFailureCMD() && item.isConsoleFailureCMD()) {
                          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), item.getSuccessCMD().replace("[playername]", p.getName()));
                          if (item.isDropifnotused()) {
                              p.getWorld().dropItem(p.getLocation(), item.getItem());
                          }
                          if (!item.isDropifnotused()) {
                              p.getInventory().addItem(item.getItem());
                          }
                      }
                      if (item.isPerformFailureCMD() && !item.isConsoleFailureCMD() && p.getPlayer() != null) {
                          Bukkit.dispatchCommand(p.getPlayer(), item.getSuccessCMD().replace("[playername]", p.getName()));
                          if (item.isDropifnotused()) {
                              p.getWorld().dropItem(p.getLocation(), item.getItem());
                          }
                          if (!item.isDropifnotused()) {
                              p.getInventory().addItem(item.getItem());
                          }
                      }
                      if (!item.isPerformFailureCMD() && !item.isConsoleFailureCMD()) {
                          if (item.isDropifnotused()) {
                              p.getWorld().dropItem(p.getLocation(), item.getItem());
                          }
                          if (!item.isDropifnotused()) {
                              p.getInventory().addItem(item.getItem());
                          }
                      }
                  }
          MedicalHandler.bandagingPlayers.remove(p);
          cancel();
        } else if (progress == total) {
          if (item.isPerformSuccessCMD() && item.isConsoleSuccessCMD()
                  && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
              if (item.HasRange()) {
                  for (Entity e : p.getNearbyEntities(item.getRadius(), item.getRadius(), item.getRadius())) {
                      if (e instanceof Player)
                          if (!((Player) e).hasPotionEffect(PotionEffectType.REGENERATION) && ((Player) e).getHealth() < Objects.requireNonNull(((Player) e).getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue())
                              ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
                  }
              }
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), item.getSuccessCMD().replace("[playername]", p.getName()));
          } else if (p.getPlayer() != null && item.isPerformSuccessCMD() && !item.isConsoleSuccessCMD()
                  && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
              if (item.HasRange()) {
                  for (Entity e : p.getNearbyEntities(item.getRadius(), item.getRadius(), item.getRadius())) {
                      if (e instanceof Player)
                          if (!((Player) e).hasPotionEffect(PotionEffectType.REGENERATION) && ((Player) e).getHealth() < Objects.requireNonNull(((Player) e).getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue())
                              ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
                  }
              }
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(p.getPlayer(), item.getSuccessCMD().replace("[playername]", p.getName()));
          } else if (!item.isPerformSuccessCMD()
                  && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
              if (item.HasRange()) {
                  for (Entity e : p.getNearbyEntities(item.getRadius(), item.getRadius(), item.getRadius())) {
                      if (e instanceof Player)
                          if (!((Player) e).hasPotionEffect(PotionEffectType.REGENERATION) && ((Player) e).getHealth() < Objects.requireNonNull(((Player) e).getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue())
                              ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
                  }
              }
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
          } else {
              if (item.isDropifnotused()) {
                  p.getWorld().dropItem(p.getLocation(), item.getItem());
              }
              if (!item.isDropifnotused()) {
                  p.getInventory().addItem(item.getItem());
              }
            MedicalHandler.bandagingPlayers.remove(p);
            cancel();
          }
        }
        int a = Math.round(progress / 60.0F * 10.0F);
        int b = 10 - a;
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.GREEN);
        for (int i = 0; i < a; i++) {
          sb.append("■");
        }
        sb.append(ChatColor.RED);
        for (int i = 0; i < b; i++) {
          sb.append("■");
        }
        PacketHandler.getInstance().sendActionBarMessage(p,sb.toString());
        progress += multiplier;
      }
    });
    bandagingPlayers.get(p).runTaskTimer(MedCraft.getPlugin(), 0L, 1L);
  }
  public static boolean isBandaging(Player p){return !bandagingPlayers.containsKey(p);}
}