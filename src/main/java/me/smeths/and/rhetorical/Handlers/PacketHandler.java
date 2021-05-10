package me.smeths.and.rhetorical.Handlers;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

@SuppressWarnings("rawtypes")
public class PacketHandler {
	private static PacketHandler instance;
	private String nmsVersion;
	private Class craftPlayer;
	public PacketHandler() {
		if (instance != null)
			return;
		instance = this;
		nmsVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			craftPlayer = Class.forName("org.bukkit.craftbukkit." + nmsVersion + ".entity.CraftPlayer");
		} catch(Exception e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not get CraftPlayer class! Is there a typo?");
			e.printStackTrace();
		}
	}
	public static PacketHandler getInstance() {
		return instance;
	}
	public Class getCraftPlayer() { return craftPlayer; }
	public static Class<?> getNmsClass(String name){
		try {
			return Class.forName("net.minecraft.server." + getInstance().nmsVersion + "." + name);
		} catch(Exception e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not get class for name! Is there a typo?");
			e.printStackTrace();
			return null;
		}
	}
	public void sendActionBarMessage(Player p, String message) {
		try {
			Object chatComponent = Objects.requireNonNull(getNmsClass("ChatComponentText")).getConstructor(String.class).newInstance(message);
			Object packetPlayOutChat = Objects.requireNonNull(getNmsClass("PacketPlayOutChat")).getConstructor(getNmsClass("IChatBaseComponent"), byte.class).newInstance(chatComponent, (byte) 2);
			Object handle = p.getClass().getMethod("getHandle").invoke(p);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(playerConnection, packetPlayOutChat);
		} catch(Exception e) {
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
		}
	}
}