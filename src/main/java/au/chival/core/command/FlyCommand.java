package au.chival.core.command;

import au.chival.core.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;


public class FlyCommand extends CommandBase {
	public FlyCommand() {
		super("fly", true, "chival.fly");
	}

	@Override
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		Player target = Utils.getPlayerFromArgs(sender, args, "chival.fly.others");
		if (target == null) return;

		boolean fly = !target.getAllowFlight();
		String key = fly ? "fly.enabled" : "fly.disabled";

		target.setAllowFlight(fly);
		target.setFlying(fly);
		if (fly && ((Entity) target).isOnGround()) // If they are on the ground, bump them up a bit and make them fly
			target.setVelocity(target.getVelocity().setY(0.5));

		target.sendMessage(tl(key));
		if (target != sender) sender.sendMessage(tl(key + ".other", target.getName()));
	}
}
