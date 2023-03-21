package me.smeths.and.rhetorical.Handlers;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexHandler {
    public static Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    public static String format(String msg) {
        if (!Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9")
                && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11")
                && !Bukkit.getVersion().contains("1.12") && !Bukkit.getVersion().contains("1.13")
                && !Bukkit.getVersion().contains("1.14") && !Bukkit.getVersion().contains("1.15")) {
            Matcher matcher = pattern.matcher(msg);
            while (matcher.find()) {
                String color = msg.substring(matcher.start(), matcher.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                matcher = pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}