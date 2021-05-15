package me.smeths.and.rhetorical.Data;

import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;

public class CustomItem {

    private static HashMap<Integer, CustomItem> customitems = new HashMap<>();

    private ItemStack item;
    private boolean isglows;
    private boolean craftable;
    private boolean DropIfNotUsed;
    private boolean performCMD;
    private boolean consoleCMD;
    private String command;
    private int regen_time;
    private int regen_amplifier;
    private int warmupspeed;
    private int Radius;
    private boolean Offhand;
    private boolean HasRange;
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

    public void setDropIfNotUsed(boolean DropIfNotUsed) {
        this.DropIfNotUsed = DropIfNotUsed;
    }

    public void setOffhand(boolean Offhand) {
        this.Offhand = Offhand;
    }

    public void setHasRange(boolean HasRange) {
        this.HasRange = HasRange;
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

    public void setisGlows(boolean isglows) {
        this.isglows = isglows;
    }

    public void setPerformCMD(boolean performCMD) {
        this.performCMD = performCMD;
    }

    public void setRadius(int Radius) {
        this.Radius = Radius;
    }

    public boolean isConsoleCMD() {
        return consoleCMD;
    }

    public boolean isCraftable() {
        return craftable;
    }

    public boolean isDropifnotused() {
        return DropIfNotUsed;
    }

    public boolean isGlows() {
        return isglows;
    }

    public int getRadius() {
        return Radius;
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

    public boolean Offhand() {
        return Offhand;
    }

    public boolean HasRange() {
        return HasRange;
    }

    public String getInternalName() {
        return internalName;
    }
}
