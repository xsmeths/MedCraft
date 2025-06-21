package me.smeths.and.rhetorical.Utils;

import com.google.common.collect.Lists;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class abstractAttribute {
    public static @NotNull Attribute valueOf(@NotNull String name) {
//        try {
//            Attribute attribute = (Attribute) Bukkit.getUnsafe().get(RegistryKey.ATTRIBUTE, NamespacedKey.fromString(name.toLowerCase(Locale.ROOT)));
//            Preconditions.checkArgument(attribute != null, "No attribute found with the name %s", name);
//            return attribute;
//        } catch (Exception ex) {
//        }
        try {
            NamespacedKey key = NamespacedKey.fromString(name.toLowerCase());
            Attribute attribute = Registry.ATTRIBUTE.get(key);
            if (attribute == null) {
                return switch (name) {
                    case "GENERIC_MAX_HEALTH" ->
                            Registry.ATTRIBUTE.get(NamespacedKey.fromString("MAX_HEALTH".toLowerCase()));
                    default -> throw new IllegalStateException("Unexpected value: " + name);
                };
            } else {
                return attribute;
            }
        } catch (Exception handled) {
            Object GENERIC_MAX_HEALTH;
            try {
                Field field = Attribute.class.getField("GENERIC_MAX_HEALTH");
                GENERIC_MAX_HEALTH = field.get(null);
                return (Attribute) GENERIC_MAX_HEALTH;
            } catch (Throwable ignored) {
                return null;
            }
        }
    }
    public static @NotNull Attribute[] values() {
        return (Attribute[]) Lists.newArrayList(Registry.ATTRIBUTE).toArray(new Attribute[0]);
    }
}
