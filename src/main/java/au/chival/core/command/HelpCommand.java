package au.chival.core.command;

import au.chival.core.Core;
import au.chival.core.util.I18n;
import au.chival.core.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;


public class HelpCommand extends CommandBase {
    private final String HELP_MESSAGE;


    public HelpCommand() {
        super("help", true, "chival.help");

        String help = Utils.getOrCreateResource("help.txt");
        if (help == null) help = "An error occurred while loading help.txt";

        HELP_MESSAGE = I18n.colorize(help.replace("{VERSION}", Core.PLUGIN.getDescription().getVersion()));
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(HELP_MESSAGE.replace("{ONLINE}", String.valueOf(Bukkit.getOnlinePlayers().size())).split("\r?\n"));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {
        return Utils.EMPTY_LIST;
    }
}
