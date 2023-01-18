package au.chival.core.commands;

import au.chival.core.CommandBase;
import au.chival.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import static au.chival.core.util.I18n.INFO;
import static au.chival.core.util.I18n.SUCCESS;


public class HelpCommand extends CommandBase {
    public HelpCommand() {
        super("help", true, "chival.help");
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        sender.sendMessage(" ");
        sender.sendMessage(INFO + ChatColor.BOLD + "Welcome to Chival-Network®");
        sender.sendMessage(INFO + "Use the compass in lobby to select a server");
        sender.sendMessage(INFO + "Discord: " + SUCCESS + Core.PLUGIN.getConfig().getString("discord-invite"));
        sender.sendMessage(INFO + "Ask a staff member for more info");
        sender.sendMessage(" ");
        sender.sendMessage(INFO + "Core Version: " + SUCCESS + Core.PLUGIN.getDescription().getVersion());
        sender.sendMessage(INFO + "Online players: " + SUCCESS + Bukkit.getOnlinePlayers().size());
        sender.sendMessage(" ");
    }
}
