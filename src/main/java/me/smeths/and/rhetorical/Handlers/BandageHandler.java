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

public class BandageHandler
{
  private static Map<Player, BukkitRunnable> bandagingPlayers = new HashMap<>();

  public BandageHandler(final Player p) {
    if (bandagingPlayers.containsKey(p)) {
      bandagingPlayers.get(p).cancel();
      bandagingPlayers.remove(p);
      p.getInventory().addItem(ItemLoader.getBandageItem());
    }
    bandagingPlayers.put(p, new BukkitRunnable()
    {
      final Location position = p.getLocation();
      final int total = 60;
      int progress = 0;
      final int duration = 20 * MedCraft.getPlugin().getConfig().getInt("Bandage.Regen-Time");
      final int amplifier = MedCraft.getPlugin().getConfig().getInt("Bandage.Regen-Amplifier");
      final int multiplier = MedCraft.getPlugin().getConfig().getInt("Bandage.Warmup-Speed");

      public void cancel()
      {
        super.cancel();
      }
      public void run()
      {

        boolean cancelled = p.getLocation().distance(this.position) > 0.75D;
        if ((this.progress > total) || (cancelled))
        {
          if (cancelled) {
            p.getInventory().addItem(ItemLoader.getBandageItem());
          }
          BandageHandler.bandagingPlayers.remove(p);
          cancel();
        } else if (this.progress == total) {
          if (MedCraft.getPlugin().getConfig().getBoolean("Bandage.PerformCMD") == true && MedCraft.getPlugin().getConfig().getBoolean("Bandage.ConsoleCMD") == true && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.valueOf(MedCraft.getPlugin().getConfig().get("Bandage.CMD")).replace("[playername]", p.getName()));
          } else if (MedCraft.getPlugin().getConfig().getBoolean("Bandage.PerformCMD") == true && MedCraft.getPlugin().getConfig().getBoolean("Bandage.ConsoleCMD") == false && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
            Bukkit.dispatchCommand(p.getPlayer(),String.valueOf(MedCraft.getPlugin().getConfig().get("Bandage.CMD")).replace("[playername]", p.getName()));
          } else if (MedCraft.getPlugin().getConfig().getBoolean("Bandage.PerformCMD") == false && p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
          } else {
            p.getInventory().addItem(ItemLoader.getBandageItem());
            BandageHandler.bandagingPlayers.remove(p);
            cancel();
          }
        }
        int a = Math.round(this.progress / 60.0F * 10.0F);
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

        this.progress += multiplier;
      }
    });
    bandagingPlayers.get(p).runTaskTimer(MedCraft.getPlugin(), 0L, 1L);
  }
  public static boolean isBandaging(Player p)
  {
    return bandagingPlayers.containsKey(p);
  }
}