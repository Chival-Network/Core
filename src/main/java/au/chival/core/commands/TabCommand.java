package au.chival.core.commands;

import au.chival.core.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class TabCommand extends CommandBase {
    public TabCommand() {
        super("tab", true, "chival.tab");
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 3) {
            sender.sendMessage(tl("tab-command.invalid-args"));
            return;
        }

        if (!Bukkit.getOnlinePlayers().contains(args[0]) && (args[0] != "all" || args[0] != "@a"));
    }

    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {

        if (args.length == 1) {
            return null;
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
