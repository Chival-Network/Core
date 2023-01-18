package au.chival.core.commands;

import au.chival.core.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GamemodeCommand extends CommandBase {
	public GamemodeCommand() {
		super("gmc", true, "chival.gamemode");
	}

	@Override
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		Player target;
		boolean self = true;
		if (args.length > 0) {
			if (!sender.hasPermission("chival.gamemode.others")) {
				sender.sendMessage(tl("no-permission"));
				return;
			}

			target = Bukkit.getPlayer(args[0]);
			if (target != sender) self = false;
		} else {
			if (!(sender instanceof Player)) { // Console must specify player
				sender.sendMessage(tl("player-not-found"));
				return;
			}

			target = (Player) sender;
		}

		if (target == null) {
			sender.sendMessage(tl("player-not-found"));
			return;
		}

		GameMode targetGamemode = GameMode.SURVIVAL;
		switch (label) {
			case "gmc":
				targetGamemode = GameMode.CREATIVE;
				break;
			case "gma":
				targetGamemode = GameMode.ADVENTURE;
				break;
			case "gmsp":
				targetGamemode = GameMode.SPECTATOR;
				break;
		}

		target.setGameMode(targetGamemode);
		target.sendMessage(tl("gamemode.success", targetGamemode.name().toLowerCase()));
		if (!self) sender.sendMessage(tl("gamemode.success.other", target.getName(), targetGamemode.name().toLowerCase()));
	}
}
