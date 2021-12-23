package me.smeths.and.rhetorical.Handlers;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@SuppressWarnings({"rawtypes","unused"})
public class PacketHandler {
	private static PacketHandler instance;
	private String ServerVersion;
	private Class craftPlayer;
	public PacketHandler() {
		if (instance != null)
			return;
		instance = this;
		ServerVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			craftPlayer = Class.forName("org.bukkit.craftbukkit." + ServerVersion + ".entity.CraftPlayer");
		}
		catch(Exception e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not get CraftPlayer class! Is there a typo?");
			e.printStackTrace();
		}
	}
	public static PacketHandler getInstance() {
		return instance;
	}
	public Class getCraftPlayer() {
		return craftPlayer;
	}
	public static Class<?> getNmsClass(String name)
	{ try {
		return Class.forName("net.minecraft.server." + getInstance().ServerVersion + "." + name);
	}
	catch(Exception e) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not get class for name! Is there a typo?");
		e.printStackTrace();
		return null;
	}
	} public void sendActionBarMessage(Player p, String message) {
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
	}
}