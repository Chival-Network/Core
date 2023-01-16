package au.chival.core.commands;

import au.chival.core.CommandBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;


public class Speed extends CommandBase {

    public Speed() {super("speed", false, "chival.speed");}

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {

        Player player = (Player) sender;

        if (args.length == 1) {

            if (args[0].contains("reset") && args[0].length() == 5) {

                player.sendMessage("Reset speed");

                player.setFlySpeed(0.1f);
                player.setWalkSpeed(0.2f);
                return;
            }

            try {

                Float i = Float.parseFloat(args[0]);

                if (i > 10 || i < 1) {

                    player.sendMessage("Number must be from 1 - 10");
                    return;
                }

                i = i / 10;
                player.setFlySpeed(i);
                if (i <= 0.2) {i = 0.2f;}
                player.setWalkSpeed(i);

            } catch (Exception e) {

               player.sendMessage("You may only put an number");

            }
        }
    }

    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {

        LinkedList tab = new LinkedList<>();

        if (args.length == 1) {

            tab.add("reset");

            return StringUtil.copyPartialMatches(args[0], tab, new ArrayList<>());
        }

        return Collections.EMPTY_LIST;
    }
}
