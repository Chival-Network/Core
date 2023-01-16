package au.chival.core.util;

import org.bukkit.ChatColor;


public class Theme {
	public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.AQUA + "Chival" + ChatColor.GRAY + "] " + ChatColor.RESET;
	public static final String INFO = ChatColor.AQUA + ""; // &b
	public static final String ERROR = ChatColor.RED + ""; // &c
	public static final String SUCCESS = ChatColor.GREEN + ""; // &a
	public static final String WARNING = ChatColor.GOLD + ""; // &6


	public static String color(String message) {
		message = message.replace("%prefix%", PREFIX);
		message = message.replace("%info%", INFO);
		message = message.replace("%error%", ERROR);
		message = message.replace("%success%", SUCCESS);
		message = message.replace("%warning%", WARNING);

		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
