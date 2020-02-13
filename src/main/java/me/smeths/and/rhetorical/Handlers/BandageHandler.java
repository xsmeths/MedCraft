package me.smeths.and.rhetorical.Handlers;

import me.smeths.and.rhetorical.BandageItemManagement.BandageItemLoader;
import me.smeths.and.rhetorical.MedCraft;
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
      p.getInventory().addItem(BandageItemLoader.getBandageItem());
    }
    bandagingPlayers.put(p, new BukkitRunnable()
    {
      Location position = p.getLocation();
      final int total = 60;
      int progress = 0;
      int duration = 20 * MedCraft.getPlugin().getConfig().getInt("Bandage.Regen-Time");
      int amplifier = MedCraft.getPlugin().getConfig().getInt("Bandage.Regen-Amplifier");

      public void cancel()
      {
        super.cancel();
      }
      public void run()
      {
        boolean cancelled = p.getLocation().distance(this.position) > 0.75D;
        if ((this.progress > 60) || (cancelled))
        {
          if (cancelled) {
            p.getInventory().addItem(BandageItemLoader.getBandageItem());
          }
          BandageHandler.bandagingPlayers.remove(p);
          cancel();
        } else if (this.progress == 60) {
          if (p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue())
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
          else {
            p.getInventory().addItem(BandageItemLoader.getBandageItem());
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

        this.progress += 1;
      }
    });
    bandagingPlayers.get(p).runTaskTimer(MedCraft.getPlugin(), 0L, 1L);
  }
  public static boolean isBandaging(Player p)
  {
    return bandagingPlayers.containsKey(p);
  }
}