package me.smeths.and.rhetorical.Handlers;

import me.smeths.and.rhetorical.MedCraft;
import me.smeths.and.rhetorical.MedKitItemManagement.MedKitItemLoader;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class MedKitHandler
{
  private static Map<Player, BukkitRunnable> MedKitPlayers = new HashMap();

  public MedKitHandler(final Player p) {
    if (MedKitPlayers.containsKey(p)) {
      ((BukkitRunnable)MedKitPlayers.get(p)).cancel();
      MedKitPlayers.remove(p);
      p.getInventory().addItem(new ItemStack[] { MedKitItemLoader.getMedKitItem() });
    }

    MedKitPlayers.put(p, new BukkitRunnable()
    {
      Location position = p.getLocation();
      final int total = 60;
      int progress = 0;
      int duration = 20 * MedCraft.getPlugin().getConfig().getInt("MedKit.Regen-Time");
      int amplifier = MedCraft.getPlugin().getConfig().getInt("MedKit.Regen-Amplifier");

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
            p.getInventory().addItem(new ItemStack[] { MedKitItemLoader.getMedKitItem() });
          }

          MedKitHandler.MedKitPlayers.remove(p);
          cancel();
        } else if (this.progress == 60) {
          if (p.getHealth() < p.getMaxHealth())
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
          else {
            p.getInventory().addItem(new ItemStack[]{MedKitItemLoader.getMedKitItem()});
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

        this.progress += 1;
      }
    });
    ((BukkitRunnable)MedKitPlayers.get(p)).runTaskTimer(MedCraft.getPlugin(), 0L, 1L);
  }

  public static boolean isMedding(Player p) { return MedKitPlayers.containsKey(p);
  }
}