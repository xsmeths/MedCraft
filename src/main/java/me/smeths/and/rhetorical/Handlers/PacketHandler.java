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

		try {
			ServerVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
			craftPlayer = Class.forName("org.bukkit.craftbukkit." + ServerVersion + ".entity.CraftPlayer");
		} catch (Exception ignored) {
		}
	}

	public static PacketHandler getInstance() {
		return instance;
	}

	public Class getCraftPlayer() {
		return craftPlayer;
	}

	/*
	 * returns class for name whether relocated or not
	 * new_name is used if the class was renamed
	 * not sure how useful this would be, but I added it anyway
	 */
	public static Class<?> getNmsClass(String name, String new_name) {
		try {
			return Class.forName("net.minecraft.server." + getInstance().ServerVersion + "." + name);
		} catch (Exception e) {
			try {
				return Class.forName("net.minecraft.server." + getInstance().ServerVersion + "." + new_name);
			} catch (Exception e2) {
				try {
					return Class.forName("net.minecraft.server." + name);
				} catch (Exception ex) {
					try {
						return Class.forName("net.minecraft.server." + new_name);
					} catch (Exception exception) {
						return null;
					}
				}
			}
		}
	}

	public void sendActionBarMessage(Player p, String message) {
		try {
			Object chatComponent = getNmsClass("ChatComponentText", "")
					.getConstructor(String.class).newInstance(message);
			Object packetPlayOutChat = getNmsClass("PacketPlayOutChat", "")
					.getConstructor(getNmsClass("IChatBaseComponent", ""), byte.class).newInstance(chatComponent, (byte) 2);
			Object handle = p.getClass().getMethod("getHandle").invoke(p);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getNmsClass("Packet", ""))
					.invoke(playerConnection, packetPlayOutChat);
		} catch (Exception e) {
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
		}
	}
}