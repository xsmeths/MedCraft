package me.smeths.and.rhetorical.customitems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CustomItemUtil {

    private static CustomItemListener listener = new CustomItemListener();
    private static JavaPlugin plugin;

    private static List<CustomItem> customitems = new ArrayList<>();

    public static CustomItem getCustomItem(String internalname) {
        for (CustomItem c : customitems) {
            if (c.getInternalName().equals(internalname))
                return c;
        }
        return null;
    }

    public static CustomItem getCustomItem(ItemStack itemstack) {
        for (CustomItem customItem : customitems) {
            if (customItem.isSimilar(itemstack))
                return customItem;
        }
        return null;
    }

    public static void registerCustomItem(CustomItem customItem) {
        customitems.add(customItem);
    }

    public static void init(JavaPlugin mainplugin) {
        Bukkit.getPluginManager().registerEvents(listener, mainplugin);
        plugin = mainplugin;
    }


    public static class CustomItem {

        private String internalName;
        private Material material;
        private String displayname;
        private int custommodeldata;
        private List<String> lore;
        private ItemEvents itemeventtype;

        public CustomItem(String internalName, Material material, String displayname, int custommodeldata) {
            this(internalName, material, displayname, custommodeldata, null, ItemEvents.DEFAULT);
        }

        public CustomItem(String internalName, Material material, String displayname, int custommodeldata, List<String> lore) {
            this(internalName, material, displayname, custommodeldata, lore, ItemEvents.DEFAULT);
        }

        public CustomItem(String internalName, Material material, String displayname, int custommodeldata, ItemEvents type) {
            this(internalName, material, displayname, custommodeldata, null, type);
        }

        public CustomItem(String internalName, Material material, String displayname, int custommodeldata, List<String> lore, ItemEvents type) {
            this.material = material;
            this.displayname = displayname;
            this.internalName = internalName;
            this.custommodeldata = custommodeldata;
            this.lore = lore;
            this.itemeventtype = type;
        }

        public ItemStack asItemStack() {
            ItemStack is = new ItemStack(material);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(displayname);
            if (lore != null) {
                im.setLore(lore);
            }
            im.setCustomModelData(custommodeldata);
            return is;
        }

        public String getInternalName() {
            return internalName;
        }

        public boolean isSimilar(ItemStack base) {
            if (base.getType() == material) {
                if ((!base.getItemMeta().hasDisplayName() && displayname == null) || base.getItemMeta().getDisplayName().equals(displayname)) {
                    if (base.getItemMeta().getCustomModelData() == custommodeldata) {
                        //TODO: You may want to also check fore lore if you want items to be lore specific, but I don't think you need to.
                        return true;
                    }
                }
            }
            return false;
        }

        public ItemEvents getItemEvents() {
            return itemeventtype;
        }
    }

    public static abstract class ItemEvents {

        public static ItemEvents DEFAULT = new ItemEvents() {
            @Override
            public void onLeftCLick(PlayerInteractEvent event) {
            }

            @Override
            public void onRightClick(PlayerInteractEvent event) {
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
        };

        public abstract void onLeftCLick(PlayerInteractEvent event);

        public abstract void onRightClick(PlayerInteractEvent event);

        public abstract void onRightClickEntity(PlayerInteractEntityEvent event);

        public abstract void onConsume(PlayerItemConsumeEvent event);

        public abstract void onDrop(PlayerDropItemEvent event);
    }

}

class CustomItemListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        CustomItemUtil.CustomItem customItem = CustomItemUtil.getCustomItem(event.getItem());
        if (customItem != null) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                customItem.getItemEvents().onLeftCLick(event);
            } else {
                customItem.getItemEvents().onRightClick(event);
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        CustomItemUtil.CustomItem customItem = CustomItemUtil.getCustomItem(event.getItemDrop().getItemStack());
        if (customItem != null) {
            customItem.getItemEvents().onDrop(event);
        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        CustomItemUtil.CustomItem customItem = CustomItemUtil.getCustomItem(event.getItem());
        if (customItem != null) {
            customItem.getItemEvents().onConsume(event);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event) {
        CustomItemUtil.CustomItem customItem = CustomItemUtil.getCustomItem(event.getPlayer().getInventory().getItemInMainHand());
        if (customItem != null) {
            customItem.getItemEvents().onRightClickEntity(event);
        }
    }


    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        for (int i = 0; i < 9; i++) {
            if (CustomItemUtil.getCustomItem(e.getInventory().getMatrix()[i]) != null) {
                //TODO: Add variant where you can only craft an item with a custom item
                //This checks if any of the items in the crafting table are similar to any of the custom items, and if so, stops the crafting.
                e.getInventory().setResult(new ItemStack(Material.AIR));
                break;
            }
        }
    }
}
