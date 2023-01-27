package au.chival.core.command;

import au.chival.core.Core;
import au.chival.core.util.I18n;
import au.chival.core.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public abstract class CommandBase {
	private final String name;
	private final boolean allowedInConsole;
	private final String permission; // null if no permission required


	public CommandBase(String name) {
		this(name, false, null);
	}

	public CommandBase(String name, boolean allowedInConsole, String permission) {
		this.name = name;
		this.allowedInConsole = allowedInConsole;
		this.permission = permission;

		Core.PLUGIN.getCommand(name).setExecutor((sender, command, label, args) -> {
			if (!allowedInConsole && !(sender instanceof Player)) {
				sender.sendMessage("You must be a player to use this command");
				return true;
			}

			if (permission != null && !sender.hasPermission(permission)) {
				sender.sendMessage(tl("no-permission") + ".");
				return true;
			}

			execute(sender, command, label, args);
			return true;
		});

		// Require permission to tab complete
		Core.PLUGIN.getCommand(name).setTabCompleter((sender, command, alias, args) -> {
			if (permission != null && !sender.hasPermission(permission)) return Utils.EMPTY_LIST;
			return tabComplete(sender, command, args);
		});
	}


	public abstract void execute(CommandSender sender, Command command, String label, String[] args);

	public List<String> tabComplete(CommandSender sender, Command command, String[] args) {
		if (args.length == 1) return null; // By default, tab complete first arg as username unless overridden
		return Utils.EMPTY_LIST;
	}

	protected String tl(String key, Object... args) {
		return I18n.format(key, args);
	}

	public String getName() {
		return name;
	}

	public boolean isAllowedInConsole() {
		return allowedInConsole;
	}

	public String getPermission() {
		return permission;
	}
}
