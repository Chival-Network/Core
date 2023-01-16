package au.chival.core.command;

import au.chival.core.util.Theme;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;


public class Fly extends CommandBase {
	public Fly() {
		super("fly", true, "chival.fly");
	}


	@Override
	public void execute(CommandSender sender, Command command, String[] args) {
		Player target;
		boolean self = true;

		if (args.length == 1) {
			target = getPlayer(args[0]);
			if (target != null && !target.getName().equals(sender.getName())) self = false;
		} else if (sender instanceof Player) {
			target = (Player) sender;
		} else {
			sender.sendMessage("Invalid arguments for console, please specify a player");
			return;
		}

		if (!self && !sender.hasPermission("chival.fly.others")) {
			this.sendNoPermission(sender);
			return;
		}

		if (target == null) {
			Theme.sendMessage(sender, "%error%Player not found");
			return;
		}

		if (target.getGameMode() == GameMode.CREATIVE) {
			Theme.sendMessage(sender, "%error%Player is in creative mode");
			return;
		}

		boolean fly = !target.getAllowFlight();
		target.setAllowFlight(fly);
		target.setFlying(fly);

		Theme.sendMessage(target, fly ? "%success%Flight enabled" : "%error%Flight disabled");
		if (!self) Theme.sendMessage(sender, fly ? "%success%Flight enabled for " + target.getDisplayName() : "%error%Flight disabled for " + target.getDisplayName());
	}
}
