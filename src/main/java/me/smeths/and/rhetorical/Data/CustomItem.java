package me.smeths.and.rhetorical.Data;

import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;

public class CustomItem {

    private static HashMap<Integer, CustomItem> customitems = new HashMap<>();

    private ItemStack item;
    private boolean glows;
    private boolean craftable;
    private boolean dropifnotused;
    private boolean enderchest;
    private boolean performCMD;
    private boolean consoleCMD;
    private String command;
    private int regen_time;
    private int regen_amplifier;
    private int warmupspeed;
    private int radius;
    private boolean permissionOffhand;
    private String internalName;

    public CustomItem(String name, int customModelID, ItemStack item) {
        this.item = item;
        customitems.put(customModelID, this);
        this.internalName = name;
    }

    public static Collection<CustomItem> getCustomItems() {
        return customitems.values();
    }


    public void setCommand(String command) {
        this.command = command;
    }

    public void setConsoleCMD(boolean consoleCMD) {
        this.consoleCMD = consoleCMD;
    }

    public void setCraftable(boolean craftable) {
        this.craftable = craftable;
    }

    public void setDropifnotused(boolean dropifnotused) {
        this.dropifnotused = dropifnotused;
    }

    public void setEnderchest(boolean enderchest) {
        this.enderchest = enderchest;
    }

    public void setPermissionOffhand(boolean permissionOffhand) {
        this.permissionOffhand = permissionOffhand;
    }

    public void setRegen_amplifier(int regen_amplifier) {
        this.regen_amplifier = regen_amplifier;
    }

    public void setRegen_time(int regen_time) {
        this.regen_time = regen_time;
    }

    public void setWarmupspeed(int warmupspeed) {
        this.warmupspeed = warmupspeed;
    }

    public void setGlows(boolean glows) {
        this.glows = glows;
    }

    public void setPerformCMD(boolean performCMD) {
        this.performCMD = performCMD;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isConsoleCMD() {
        return consoleCMD;
    }

    public boolean isCraftable() {
        return craftable;
    }

    public boolean isDropifnotused() {
        return dropifnotused;
    }

    public boolean UseEnderchestIfInvFull() {
        return enderchest;
    }

    public boolean isGlows() {
        return glows;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isPerformCMD() {
        return performCMD;
    }

    public int getRegen_amplifier() {
        return regen_amplifier;
    }

    public int getRegen_time() {
        return regen_time;
    }

    public int getWarmupspeed() {
        return warmupspeed;
    }

    public String getCommand() {
        return command;
    }

    public ItemStack getItem() {
        return item;
    }

    public boolean isPermissionOffhand() {
        return permissionOffhand;
    }

    public String getInternalName() {
        return internalName;
    }
}
