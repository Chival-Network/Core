package au.chival.core.command;

import au.chival.core.util.Utils;
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
		Player target = Utils.getPlayerFromArgs(sender, args, "chival.gamemode.others");
		if (target == null) return;

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
		target.sendMessage(tl("gamemode.set", targetGamemode.name().toLowerCase()));
		if (target != sender) sender.sendMessage(tl("gamemode.set.other", targetGamemode.name().toLowerCase(), target.getName()));
	}
}
