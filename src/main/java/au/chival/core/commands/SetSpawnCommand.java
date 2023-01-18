package au.chival.core.commands;

import au.chival.core.CommandBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class SetSpawnCommand extends CommandBase {

    public SetSpawnCommand() {
        super("setspawn", false, "chival.setspawn");
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            sender.sendMessage(tl("setspawn-command.invalid-args"));
            return;
        }

        Player player = (Player) sender;

        player.getWorld().setSpawnLocation((int) player.getLocation().getX(), (int) player.getLocation().getY(), (int) player.getLocation().getZ());
        player.sendMessage(tl("setspawn-command.success", (int) player.getLocation().getX() + " " + (int) player.getLocation().getY() + " " + (int) player.getLocation().getZ()));
    }

    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {

        return Collections.EMPTY_LIST;
    }
}
