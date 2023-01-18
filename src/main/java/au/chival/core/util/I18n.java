package au.chival.core.util;

import au.chival.core.Core;
import org.bukkit.ChatColor;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class I18n {
	private static ResourceBundle BUNDLE;
	static {
		try { // Load the resource bundle from plugin data dir
			URL[] urls = new URL[] { Core.PLUGIN.getDataFolder().toURI().toURL() };
			ClassLoader loader = new java.net.URLClassLoader(urls);
			BUNDLE = ResourceBundle.getBundle("messages", Locale.ENGLISH, loader);
		} catch (Exception e) { // Fall back and use default messages.properties from inside the jar
			Core.PLUGIN.saveResource("messages.properties", false);
			BUNDLE = ResourceBundle.getBundle("messages");
		}
	}

	public static final String INFO = ChatColor.translateAlternateColorCodes('&', get("info"));
	public static final String ERROR = ChatColor.translateAlternateColorCodes('&', get("error"));
	public static final String SUCCESS = ChatColor.translateAlternateColorCodes('&', get("success"));
	public static final String WARNING = ChatColor.translateAlternateColorCodes('&', get("warning"));


	public static String get(String key) {
		try {
			return BUNDLE.getString(key);
		} catch (Exception e) {
			Logger.getLogger("Core").warning("Missing translation for key: " + key);
			return key;
		}
	}

	public static String colorize(String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);

		return msg.replace("{INFO}", INFO)
			.replace("{ERROR}", ERROR)
			.replace("{SUCCESS}", SUCCESS)
			.replace("{WARNING}", WARNING);
	}

	public static String format(String key, Object... args) {
		String msg = colorize(get(key));

		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) continue;
			msg = msg.replace("{" + i + "}", args[i].toString());
		}

		return msg;
	}
}
