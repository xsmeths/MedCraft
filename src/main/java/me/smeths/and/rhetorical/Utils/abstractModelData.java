package me.smeths.and.rhetorical.Utils;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class abstractModelData {
    public static boolean hasLegacyCustomModelData(ItemMeta meta) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Check if the method exists
        Method method = meta.getClass().getMethod("hasCustomModelData");
        // Make sure it's accessible
        method.setAccessible(true);
        // Invoke the method reflectively
        return (boolean) method.invoke(meta);
    }
    public static Integer getLegacyCustomModelData(ItemMeta meta) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = meta.getClass().getMethod("getCustomModelData");
        method.setAccessible(true);
        return (Integer) method.invoke(meta);
    }
    public static void setLegacyCustomModelData(ItemMeta meta, int value) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = meta.getClass().getMethod("setCustomModelData", Integer.class);
        method.setAccessible(true);
        method.invoke(meta, value);
    }

    public static int getCustomModelData(ItemMeta meta) {
        int data;
        try {
            data = getLegacyCustomModelData(meta);
        } catch (Exception handled) {
            CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
            data = customModelDataComponent.getFloats().getFirst().intValue();
        }
        return data;
    }

    public static boolean hasCustomModelData(ItemMeta meta) {
        boolean returnType;
        try {
            returnType = hasLegacyCustomModelData(meta);
        } catch (Exception handled) {
            returnType = meta.hasCustomModelDataComponent();
        }
        return returnType;
    }
}