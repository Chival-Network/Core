package au.chival.core.commands;

import au.chival.core.CommandBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
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
			sender.sendMessage(tl("fly.player-not-found"));
			return;
		}

		boolean fly = !target.getAllowFlight();
		String key = fly ? "fly.enabled" : "fly.disabled";

		target.setAllowFlight(fly);

		// if they are on the ground, bump them up a bit and make them fly
		if (fly && ((Entity) target).isOnGround()) target.setVelocity(target.getVelocity().setY(0.5));
		target.setFlying(fly);

		target.sendMessage(tl(key));
		if (!self) sender.sendMessage(tl(key + ".other", target.getName()));
	}
}
