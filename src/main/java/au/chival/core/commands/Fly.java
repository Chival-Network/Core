package au.chival.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static au.chival.core.util.Theme.*;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) || args.length < 1) {
            sender.sendMessage(ERROR + "The console can't fly");
        }

        Player player = (Player) sender;

        if (args.length < 0) {

            if (player.getAllowFlight() == false) {

                player.setAllowFlight(true);
                player.sendMessage(SUCCESS + "Flight enabled");
            } else {

                player.setAllowFlight(false);
                player.sendMessage(SUCCESS + "Flight " + ERROR + "disabled");

            }

            return true;
        }

        if (args.length != 1) {

        }

        return true;
    }
}
