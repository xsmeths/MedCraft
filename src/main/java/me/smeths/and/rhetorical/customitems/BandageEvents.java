package me.smeths.and.rhetorical.customitems;

import me.smeths.and.rhetorical.MedCraft;
import me.smeths.and.rhetorical.handlers.BandageHandler;
import me.smeths.and.rhetorical.handlers.MedKitHandler;
import me.smeths.and.rhetorical.handlers.PacketHandler;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class BandageEvents extends CustomItemUtil.ItemEvents {

    @Override
    public void onLeftCLick(PlayerInteractEvent e) {
        onInteract(e);
        //TODO: See if you want this to be both left click and right click or only right click exclusive
    }

    @Override
    public void onRightClick(PlayerInteractEvent e) {
        onInteract(e);
    }

    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();
        if (i.getItemMeta() != null)
            if (e.getAction() != Action.PHYSICAL && !p.hasPotionEffect(PotionEffectType.REGENERATION) && BandageHandler.isBandaging(p) && MedKitHandler.isMedding(p)
                    && (i.getType().equals(me.smeths.and.rhetorical.itemmanager.ItemLoader.getMedKitItem().getType())) && i.getItemMeta().hasCustomModelData() && e.getClickedBlock() != null
                    && !e.getClickedBlock().getType().toString().contains("FURNACE") && e.getClickedBlock().getType() != Material.SMOKER
                    && !e.getClickedBlock().getType().toString().contains("TABLE") && !e.getClickedBlock().getType().toString().contains("HOPPER")
                    && e.getClickedBlock().getType() != Material.BREWING_STAND && e.getClickedBlock().getType() != Material.LOOM
                    && p.getGameMode() != GameMode.CREATIVE && (i.getItemMeta().getCustomModelData() == MedCraft.getPlugin().getConfig().getInt("Bandage.ModelData"))) {
                if (p.getInventory().getItemInMainHand().getAmount() == 1 && p.hasPermission("bandage.use")) {
                    int heldslot = p.getInventory().getHeldItemSlot();
                    p.getInventory().setItem(heldslot, new ItemStack(Material.AIR));
                    p.updateInventory();
                    new BandageHandler(p);
                    return;
                }
                if (i.getAmount() >= 2 && p.hasPermission("bandage.use")) {
                    i.setAmount(i.getAmount() - 1);
                    p.updateInventory();
                    new BandageHandler(p);
                    return;
                }
                if (!p.hasPermission("bandage.use")) {
                    PacketHandler.getInstance().sendActionBarMessage(p, ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(MedCraft.getPlugin().getConfig().getString("Bandage.NoPermUse"))));
                } else {
                    e.setCancelled(true);
                }
            }
    }

    @Override
    public void onRightClickEntity(PlayerInteractEntityEvent event) {

    }

    @Override
    public void onConsume(PlayerItemConsumeEvent event) {

    }

    @Override
    public void onDrop(PlayerDropItemEvent event) {

    }
}
