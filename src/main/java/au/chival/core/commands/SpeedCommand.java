package au.chival.core.commands;

import au.chival.core.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SpeedCommand extends CommandBase {
    public SpeedCommand() {
        super("speed", true, "chival.speed");
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(tl("speed.usage"));
            return;
        }

        if (args.length < 2 && !(sender instanceof Player)) {
            sender.sendMessage(tl("speed.usage"));
            return;
        }

        Player target = args.length == 1 ? (Player) sender : Bukkit.getPlayer(args[1]);
        if (target == null) {
            if (sender != null) sender.sendMessage(tl("player-not-found"));
            return;
        }

        if (target != sender && !sender.hasPermission("chival.speed.others")) {
            sender.sendMessage(tl("no-permission"));
            return;
        }

        if ("reset".equals(args[0])) {
            sender.sendMessage(tl("speed.reset"));

            target.setFlySpeed(0.1F);
            target.setWalkSpeed(0.2F);
            return;
        }

        try {
            float speed = Float.parseFloat(args[0]);
            if (speed > 10 || speed < 1) {
                sender.sendMessage(tl("speed.invalid"));
                return;
            }

            float i = speed / 10;
            if (target.isFlying()) target.setFlySpeed(i);
            else {
                if (i <= 0.2) i = 0.2F;
                target.setWalkSpeed(i);
            }

            target.sendMessage(tl("speed.set", speed));
            if (target != sender) sender.sendMessage(tl("speed.set.other", speed, target.getName()));
        } catch (Exception e) {
            sender.sendMessage(tl("speed.invalid"));
        }
    }

    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {
        LinkedList<String> tab = new LinkedList<>();

        if (args.length == 1) {
            tab.add("reset");
            return StringUtil.copyPartialMatches(args[0], tab, new ArrayList<>());
        }

        return null;
    }
}
