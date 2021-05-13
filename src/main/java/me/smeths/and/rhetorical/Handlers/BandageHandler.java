package me.smeths.and.rhetorical.Handlers;

import me.smeths.and.rhetorical.ItemManager.ItemLoader;
import me.smeths.and.rhetorical.MedCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BandageHandler {
  private static final Map<Player, BukkitRunnable> bandagingPlayers = new HashMap<>();
  public BandageHandler(final Player p) {
    if (bandagingPlayers.containsKey(p)) {
      bandagingPlayers.get(p).cancel();
      bandagingPlayers.remove(p);
        if (MedCraft.getPlugin().getConfig().getBoolean("Bandage.DropIfNotUsed")) {
            p.getWorld().dropItem(p.getLocation(), ItemLoader.getBandageItem());
        }
        if (!MedCraft.getPlugin().getConfig().getBoolean("Bandage.DropIfNotUsed")) {
            if (p.getInventory().firstEmpty() == -1
                    && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && !p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() > -1
                    && MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")
                    || p.getInventory().firstEmpty() == -1
                    && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() > -1
                    && MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")
                    || p.getInventory().firstEmpty() == -1
                    && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() == -1
                    && MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")) {
                p.getEnderChest().addItem(ItemLoader.getBandageItem());
            } else if (p.getInventory().firstEmpty() == -1
                    && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && !p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() == -1
                    || p.getInventory().firstEmpty() == -1
                    && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                    && !MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")) {
                p.getWorld().dropItem(p.getLocation(), ItemLoader.getBandageItem());
            }else {
                p.getInventory().addItem(ItemLoader.getBandageItem());
            }
        }
    }
    bandagingPlayers.put(p, new BukkitRunnable() {
              final Location position = p.getLocation();
              final int total = 60;
              int progress = 0;
              final int duration = 20 * MedCraft.getPlugin().getConfig().getInt("Bandage.Regen-Time");
              final int amplifier = MedCraft.getPlugin().getConfig().getInt("Bandage.Regen-Amplifier");
              final int multiplier = MedCraft.getPlugin().getConfig().getInt("Bandage.Warmup-Speed");

              public void cancel() {
                super.cancel();
              }

              public void run() {
                boolean cancelled = p.getLocation().distance(position) > 0.75D;
                if ((progress > total) || (cancelled)) {
                  if (cancelled) {
                    if (MedCraft.getPlugin().getConfig().getBoolean("Bandage.DropIfNotUsed")) {
                      p.getWorld().dropItem(p.getLocation(), ItemLoader.getBandageItem());
                    }
                    if (!MedCraft.getPlugin().getConfig().getBoolean("Bandage.DropIfNotUsed")) {
                      if (p.getInventory().firstEmpty() == -1
                              && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && !p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() > -1
                              && MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")
                              || p.getInventory().firstEmpty() == -1
                              && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() > -1
                              && MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")
                              || p.getInventory().firstEmpty() == -1
                              && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() == -1
                              && MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")) {
                        p.getEnderChest().addItem(ItemLoader.getBandageItem());
                      } else if (p.getInventory().firstEmpty() == -1
                              && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && !p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() == -1
                              || p.getInventory().firstEmpty() == -1
                              && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                              && !MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")) {
                        p.getWorld().dropItem(p.getLocation(), ItemLoader.getBandageItem());
                      }else {
                        p.getInventory().addItem(ItemLoader.getBandageItem());
                      }
                    }
                  }
          BandageHandler.bandagingPlayers.remove(p);
          cancel();
        } else if (progress == total) {
          if (MedCraft.getPlugin().getConfig().getBoolean("Bandage.PerformCMD") && MedCraft.getPlugin().getConfig().getBoolean("Bandage.ConsoleCMD")
                  && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.valueOf(MedCraft.getPlugin().getConfig().get("Bandage.CMD")).replace("[playername]", p.getName()));
          } else if (p.getPlayer() != null && MedCraft.getPlugin().getConfig().getBoolean("Bandage.PerformCMD") && !MedCraft.getPlugin().getConfig().getBoolean("Bandage.ConsoleCMD")
                  && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(p.getPlayer(), String.valueOf(MedCraft.getPlugin().getConfig().get("Bandage.CMD")).replace("[playername]", p.getName()));
          } else if (!MedCraft.getPlugin().getConfig().getBoolean("Bandage.PerformCMD")
                  && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
          } else {
            if (MedCraft.getPlugin().getConfig().getBoolean("Bandage.DropIfNotUsed")) {
              p.getWorld().dropItem(p.getLocation(), ItemLoader.getBandageItem());
            }
            if (!MedCraft.getPlugin().getConfig().getBoolean("Bandage.DropIfNotUsed")) {
                if (p.getInventory().firstEmpty() == -1
                        && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && !p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() > -1
                        && MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")
                        || p.getInventory().firstEmpty() == -1
                        && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() > -1
                        && MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")
                        || p.getInventory().firstEmpty() == -1
                        && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() == -1
                        && MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")) {
                    p.getEnderChest().addItem(ItemLoader.getBandageItem());
                } else if (p.getInventory().firstEmpty() == -1
                        && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && !p.getEnderChest().contains(ItemLoader.getBandageItem()) && p.getEnderChest().firstEmpty() == -1
                        || p.getInventory().firstEmpty() == -1
                        && p.getInventory().getItemInMainHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getType() != ItemLoader.getBandageItem().getType()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && p.getInventory().getItemInOffHand().getItemMeta() != ItemLoader.getBandageItem().getItemMeta()
                        && !MedCraft.getPlugin().getConfig().getBoolean("Bandage.UseEnderchestIfInvFull")) {
                    p.getWorld().dropItem(p.getLocation(), ItemLoader.getBandageItem());
                } else {
                    p.getInventory().addItem(ItemLoader.getBandageItem());
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