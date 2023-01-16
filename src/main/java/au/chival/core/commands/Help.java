package au.chival.core.commands;

import au.chival.core.CommandBase;
import au.chival.core.Core;
import au.chival.core.util.Theme;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;


public class Help extends CommandBase {
    public Help() {
        super("help");
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        Player player = (Player) sender;

        player.sendMessage(" ");
        Theme.sendMessage(player, "&b&lWelcome to Chival-Network®");
        Theme.sendMessage(player, "%info%Use the compass in lobby to select a server");
        Theme.sendMessage(player, "%info%Discord: %success%" + ChatColor.DARK_GREEN + Core.PLUGIN.getConfig().getString("discord-invite"));
        Theme.sendMessage(player, "%info%Ask a staff member for more info");
        player.sendMessage(" ");

        if (args.length < 1) return;

        if ("info".equalsIgnoreCase(args[0])) {
            Theme.sendMessage(player, "%info%&lInfo:");

            Theme.sendMessage(player, "%info%Loc: %success%" + player.getLocation());
            Theme.sendMessage(player, "%info%Rank: %success%" + LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup());
            Theme.sendMessage(player, "%info%Display-name: %success%" + player.getDisplayName());
            Theme.sendMessage(player, "%info%Ping: %success%" + ((CraftPlayer)player).getHandle().ping);
            Theme.sendMessage(player, "%info%TPS: %success%" + Arrays.toString(MinecraftServer.getServer().recentTps));
            Theme.sendMessage(player, "%info%Online players: %success%" + Bukkit.getOnlinePlayers().size());
            Theme.sendMessage(player, "%info%Core Version: %success%" + Core.PLUGIN.getDescription().getVersion());
            player.sendMessage(" ");
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {
        LinkedList<String> tab = new LinkedList<>();

        if (args.length == 1) {
            tab.add("info");
            return StringUtil.copyPartialMatches(args[0], tab, new ArrayList<>());
        }

        return Collections.EMPTY_LIST;
    }
}
