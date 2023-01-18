package au.chival.core.util;

import org.bukkit.ChatColor;

import java.util.ResourceBundle;
import java.util.logging.Logger;


public class I18n {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");
	public static final String INFO = ChatColor.translateAlternateColorCodes('&', get("info"));
	public static final String ERROR = ChatColor.translateAlternateColorCodes('&', get("error"));
	public static final String SUCCESS = ChatColor.translateAlternateColorCodes('&', get("success"));
	public static final String WARNING = ChatColor.translateAlternateColorCodes('&', get("warning"));


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
		msg = ChatColor.translateAlternateColorCodes('&', msg);

		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) continue;
			msg = msg.replace("{" + i + "}", args[i].toString());
		}

		return msg;
	}
}
