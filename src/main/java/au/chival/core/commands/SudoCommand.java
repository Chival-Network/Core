package au.chival.core.commands;

import au.chival.core.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SudoCommand extends CommandBase {
	public SudoCommand() {
		super("sudo", true, "chival.sudo");
	}

	@Override
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 2) {
			sender.sendMessage(tl("sudo.usage"));
			return;
		}

		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			sender.sendMessage(tl("player-not-found"));
			return;
		} else if (target == sender) {
			sender.sendMessage(tl("sudo.self"));
			return;
		} else if (target.hasPermission("chival.sudo.exempt") && sender instanceof Player) { // Console can sudo anyone
			sender.sendMessage(tl("sudo.exempt"));
			return;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < args.length; i++) sb.append(args[i]).append(" ");

		String cmd = sb.toString().trim();
		if (!cmd.startsWith("/") && !cmd.startsWith("c:")) cmd = "/" + cmd;
		if (cmd.startsWith("c:")) cmd = cmd.substring(2);

		target.chat(cmd);
		sender.sendMessage(tl("sudo.execute", target.getName(), cmd));
	}
}
