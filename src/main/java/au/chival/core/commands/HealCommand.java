package au.chival.core.commands;

import au.chival.core.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class HealCommand extends CommandBase {

    public HealCommand() {
        super("heal", true, "chival.heal");
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && args.length < 1) {
            Player player = (Player) sender;
            player.setHealth(20.0);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
            player.sendMessage(tl("heal-command.success.self"));
            return;
        } else if (args.length < 1) {
            sender.sendMessage(tl("heal-command.invalid-args"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(tl("player-not-found"));
            return;
        }

        target.setHealth(20.0);
        target.playSound(target.getLocation(), Sound.LEVEL_UP, 1, 1);
        target.sendMessage(tl("heal-command.success.self"));

        sender.sendMessage(tl("heal-command.success", target.getName()));
    }

    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {

        if (args.length == 1) {
            return null;
        }

        return Collections.EMPTY_LIST;
    }
}
