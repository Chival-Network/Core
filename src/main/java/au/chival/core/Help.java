package au.chival.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class Help implements CommandExecutor, TabExecutor {

    public void helpMessage(Player player) {

        player.sendMessage(" ");
        player.sendMessage(ChatColor.DARK_AQUA + "Welcome to Chival-Network®");
        player.sendMessage(ChatColor.DARK_AQUA + "Use the compass in lobby to select a server");
        player.sendMessage(ChatColor.DARK_AQUA + "Discord: " + ChatColor.DARK_GREEN + "https://discord.gg/kuz6EJpuZ9");
        player.sendMessage(ChatColor.DARK_AQUA + "Ask a staff member for more info");
        player.sendMessage(" ");

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return false;
        }

        helpMessage((Player) sender);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
