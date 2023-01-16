package au.chival.core;

import net.luckperms.api.LuckPermsProvider;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public class Help implements CommandExecutor, TabExecutor {

    public void helpMessage(Player player, String[] args) {

        player.sendMessage(" ");
        player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Welcome to Chival-Network®");
        player.sendMessage(ChatColor.DARK_AQUA + "Use the compass in lobby to select a server");
        player.sendMessage(ChatColor.DARK_AQUA + "Discord: " + ChatColor.DARK_GREEN + "https://discord.gg/kuz6EJpuZ9");
        player.sendMessage(ChatColor.DARK_AQUA + "Ask a staff member for more info");
        player.sendMessage(" ");

        if (args.length < 1) {
            return;
        }

        if (args[0] == "info") {
            player.sendMessage(ChatColor.DARK_AQUA + "Info:");
            player.sendMessage(" Loc: " + ChatColor.DARK_GREEN + player.getLocation());
            player.sendMessage(" Rank: " + ChatColor.DARK_GREEN + LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup());
            player.sendMessage(" Display-name: " + ChatColor.DARK_GREEN + player.getDisplayName());
            player.sendMessage(" Ping: " + ChatColor.DARK_GREEN + ((CraftPlayer)player).getHandle().ping);
            player.sendMessage(" TPS: " + ChatColor.DARK_GREEN + MinecraftServer.getServer().recentTps);
            player.sendMessage(" ");
        }

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return false;
        }

        helpMessage((Player) sender, args);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        LinkedList<String> tab = new LinkedList();

        if (args.length == 0) {
            tab.add("info");
        }

        return tab;
    }
}
