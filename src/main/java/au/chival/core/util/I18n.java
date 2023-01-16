package au.chival.core.util;

import org.bukkit.ChatColor;

import java.util.ResourceBundle;
import java.util.logging.Logger;


public class I18n {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");
	private static final String INFO = ChatColor.translateAlternateColorCodes('&', get("info"));
	private static final String ERROR = ChatColor.translateAlternateColorCodes('&', get("error"));
	private static final String SUCCESS = ChatColor.translateAlternateColorCodes('&', get("success"));
	private static final String WARNING = ChatColor.translateAlternateColorCodes('&', get("warning"));


	public static String get(String key) {
		try {
			return BUNDLE.getString(key);
		} catch (Exception e) {
			Logger.getLogger("Chival").warning("Missing translation for key: " + key);
			return key;
		}
	}

	public static String format(String key, Object... args) {
		String msg = get(key);

		msg = msg.replace("%info%", INFO);
		msg = msg.replace("%error%", ERROR);
		msg = msg.replace("%success%", SUCCESS);
		msg = msg.replace("%warning%", WARNING);

		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) continue;
			msg = msg.replace("{" + i + "}", args[i].toString());
		}

		return ChatColor.translateAlternateColorCodes('&', msg);
	}
}
