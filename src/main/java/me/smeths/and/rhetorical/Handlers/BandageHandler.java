package me.smeths.and.rhetorical.Handlers;

import me.smeths.and.rhetorical.Data.CustomItem;
import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BandageHandler {
  private static final Map<Player, BukkitRunnable> bandagingPlayers = new HashMap<>();
    private static Map<Player, ItemMeta> ItemUsed = new HashMap<>();
  public BandageHandler(final Player p, CustomItem item) {
    if (bandagingPlayers.containsKey(p)) {
      bandagingPlayers.get(p).cancel();
      bandagingPlayers.remove(p);
        if (item.isDropifnotused()) {
            p.getWorld().dropItem(p.getLocation(), item.getItem());
        }
        if (!item.isDropifnotused()) {
            if (p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() > -1
                    && !p.getInventory().contains(item.getItem()) && !p.getEnderChest().contains(item.getItem())
                    && item.UseEnderchestIfInvFull()
                    || p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() > -1
                    && !p.getInventory().contains(item.getItem()) && p.getEnderChest().contains(item.getItem())
                    && item.UseEnderchestIfInvFull()
                    || p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() == -1
                    && !p.getInventory().contains(item.getItem()) && p.getEnderChest().contains(item.getItem())
                    && item.UseEnderchestIfInvFull()) {
                p.getEnderChest().addItem(item.getItem());
            } else if (p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() == -1
                    && !p.getInventory().contains(item.getItem()) && !p.getEnderChest().contains(item.getItem())
                    || p.getInventory().firstEmpty() == -1 && !p.getInventory().contains(item.getItem())
                    && !item.UseEnderchestIfInvFull()) {
                p.getWorld().dropItem(p.getLocation(), item.getItem());
            }else {
                p.getInventory().addItem(item.getItem());
            }
        }
    }
    bandagingPlayers.put(p, new BukkitRunnable() {
              final Location position = p.getLocation();
              final int total = 60;
              int progress = 0;
              final int duration = 20 * item.getRegen_time();//MedCraft.getPlugin().getConfig().getInt("Bandage.Regen-Time");
              final int amplifier = item.getRegen_amplifier();//MedCraft.getPlugin().getConfig().getInt("Bandage.Regen-Amplifier");
              final int multiplier = item.getWarmupspeed();//MedCraft.getPlugin().getConfig().getInt("Bandage.Warmup-Speed");

              public void cancel() {
                super.cancel();
              }

              public void run() {
                boolean cancelled = p.getLocation().distance(position) > 0.75D;
                if ((progress > total) || (cancelled)) {
                  if (cancelled) {
                      if (item.isDropifnotused()) {
                          p.getWorld().dropItem(p.getLocation(), item.getItem());
                      }
                      if (!item.isDropifnotused()) {
                          if (p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() > -1
                                  && !p.getInventory().contains(item.getItem()) && !p.getEnderChest().contains(item.getItem())
                                  && item.UseEnderchestIfInvFull()
                                  || p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() > -1
                                  && !p.getInventory().contains(item.getItem()) && p.getEnderChest().contains(item.getItem())
                                  && item.UseEnderchestIfInvFull()
                                  || p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() == -1
                                  && !p.getInventory().contains(item.getItem()) && p.getEnderChest().contains(item.getItem())
                                  && item.UseEnderchestIfInvFull()) {
                              p.getEnderChest().addItem(item.getItem());
                          } else if (p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() == -1
                                  && !p.getInventory().contains(item.getItem()) && !p.getEnderChest().contains(item.getItem())
                                  || p.getInventory().firstEmpty() == -1 && !p.getInventory().contains(item.getItem())
                                  && !item.UseEnderchestIfInvFull()) {
                              p.getWorld().dropItem(p.getLocation(), item.getItem());
                          }else {
                              p.getInventory().addItem(item.getItem());
                          }
                      }
                  }
          BandageHandler.bandagingPlayers.remove(p);
          cancel();
        } else if (progress == total) {
          if (item.isPerformCMD() && item.isConsoleCMD()
                  && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), item.getCommand().replace("[playername]", p.getName()));
          } else if (p.getPlayer() != null && item.isPerformCMD() && !item.isConsoleCMD()
                  && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(p.getPlayer(), item.getCommand().replace("[playername]", p.getName()));
          } else if (!item.isPerformCMD()
                  && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
          } else {
              if (item.isDropifnotused()) {
                  p.getWorld().dropItem(p.getLocation(), item.getItem());
              }
              if (!item.isDropifnotused()) {
                  if (p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() > -1
                          && !p.getInventory().contains(item.getItem()) && !p.getEnderChest().contains(item.getItem())
                          && item.UseEnderchestIfInvFull()
                          || p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() > -1
                          && !p.getInventory().contains(item.getItem()) && p.getEnderChest().contains(item.getItem())
                          && item.UseEnderchestIfInvFull()
                          || p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() == -1
                          && !p.getInventory().contains(item.getItem()) && p.getEnderChest().contains(item.getItem())
                          && item.UseEnderchestIfInvFull()) {
                      p.getEnderChest().addItem(item.getItem());
                  } else if (p.getInventory().firstEmpty() == -1 && p.getEnderChest().firstEmpty() == -1
                          && !p.getInventory().contains(item.getItem()) && !p.getEnderChest().contains(item.getItem())
                          || p.getInventory().firstEmpty() == -1 && !p.getInventory().contains(item.getItem())
                          && !item.UseEnderchestIfInvFull()) {
                      p.getWorld().dropItem(p.getLocation(), item.getItem());
                  }else {
                      p.getInventory().addItem(item.getItem());
                  }
              }
            BandageHandler.bandagingPlayers.remove(p);
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