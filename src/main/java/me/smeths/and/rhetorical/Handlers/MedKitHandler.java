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

public class MedKitHandler
{
  private static final Map<Player, BukkitRunnable> MedKitPlayers = new HashMap<>();

  public MedKitHandler(final Player p) {
    if (MedKitPlayers.containsKey(p)) {
      MedKitPlayers.get(p).cancel();
      MedKitPlayers.remove(p);
      p.getInventory().addItem(ItemLoader.getMedKitItem());
    }

    MedKitPlayers.put(p, new BukkitRunnable()
    {
      final Location position = p.getLocation();
      final double total = 60;
      double progress = 0.0;
      final int duration = 20 * MedCraft.getPlugin().getConfig().getInt("MedKit.Regen-Time");
      final int amplifier = MedCraft.getPlugin().getConfig().getInt("MedKit.Regen-Amplifier");
      final double multiplier = MedCraft.getPlugin().getConfig().getDouble("MedKit.Warmup-Speed");

      public void cancel()
      {
        super.cancel();
      }

      public void run()
      {
        boolean cancelled = p.getLocation().distance(this.position) > 0.75D;

        if ((cancelled))
        {
          if (cancelled) {
            p.getInventory().addItem(ItemLoader.getMedKitItem());
          }

          MedKitHandler.MedKitPlayers.remove(p);
          cancel();
        } else if (this.progress >= total) {
          if (MedCraft.getPlugin().getConfig().getBoolean("MedKit.PerformCMD") == true && MedCraft.getPlugin().getConfig().getBoolean("MedKit.ConsoleCMD") == true && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.valueOf(MedCraft.getPlugin().getConfig().get("MedKit.CMD")).replace("[playername]", p.getName()));
          } else if (MedCraft.getPlugin().getConfig().getBoolean("MedKit.PerformCMD") == true && MedCraft.getPlugin().getConfig().getBoolean("MedKit.ConsoleCMD") == false && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(p.getPlayer(),String.valueOf(MedCraft.getPlugin().getConfig().get("MedKit.CMD")).replace("[playername]", p.getName()));
          } else if (MedCraft.getPlugin().getConfig().getBoolean("MedKit.PerformCMD") == false && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
          } else {
            p.getInventory().addItem(ItemLoader.getMedKitItem());
            MedKitHandler.MedKitPlayers.remove(p);
            cancel();
          }
        }

        double a = Math.round(this.progress / 60.00F * 10.00F);
        double b = 10.00 - a;

        StringBuilder sb = new StringBuilder();

        sb.append(ChatColor.GREEN);
        for (double i = 0.0; i < a; i++) {
          sb.append("■");
        }
        sb.append(ChatColor.RED);
        for (double i = 0.0; i < b; i++) {
          sb.append("■");
        }

        PacketHandler.getInstance().sendActionBarMessage(p,sb.toString());

        this.progress += multiplier;
      }
    });
    MedKitPlayers.get(p).runTaskTimer(MedCraft.getPlugin(), 0L, 1L);
  }

  public static boolean isMedding(Player p) { return MedKitPlayers.containsKey(p);
  }
}