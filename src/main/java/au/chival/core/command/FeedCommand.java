package au.chival.core.command;

import au.chival.core.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class FeedCommand extends CommandBase {
	public FeedCommand() {
		super("feed", true, "chival.feed");
	}

	@Override
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		Player target = Utils.getPlayerFromArgs(sender, args, "chival.feed.others");
		if (target == null) return;

		target.setFoodLevel(20);
		target.setSaturation(5.0F);

		target.sendMessage(tl("feed.success"));
		if (target != sender) sender.sendMessage(tl("feed.success.other", target.getName()));
	}
}
