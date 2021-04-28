package me.smeths.and.rhetorical.Handlers;

import me.smeths.and.rhetorical.MedCraft;
import me.smeths.and.rhetorical.MedKitItemManagement.MedKitItemLoader;
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
      p.getInventory().addItem(MedKitItemLoader.getMedKitItem());
    }

    MedKitPlayers.put(p, new BukkitRunnable()
    {
      final Location position = p.getLocation();
      final int total = 60;
      int progress = 0;
      final int duration = 20 * MedCraft.getPlugin().getConfig().getInt("MedKit.Regen-Time");
      final int amplifier = MedCraft.getPlugin().getConfig().getInt("MedKit.Regen-Amplifier");
      final int multiplier = MedCraft.getPlugin().getConfig().getInt("MedKit.Warmup-Speed");

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
            p.getInventory().addItem(MedKitItemLoader.getMedKitItem());
          }

          MedKitHandler.MedKitPlayers.remove(p);
          cancel();
        } else if (this.progress == total) {
          if (p.getHealth() < Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue())
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
          else {
            p.getInventory().addItem(MedKitItemLoader.getMedKitItem());
            MedKitHandler.MedKitPlayers.remove(p);
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
    MedKitPlayers.get(p).runTaskTimer(MedCraft.getPlugin(), 0L, 1L);
  }

  public static boolean isMedding(Player p)
  {
    return MedKitPlayers.containsKey(p);
  }
}