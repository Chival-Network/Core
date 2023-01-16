package au.chival.core.commands;

import au.chival.core.CommandBase;
import au.chival.core.Core;
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
        player.sendMessage("&b&lWelcome to Chival-Network®");
        player.sendMessage("%info%Use the compass in lobby to select a server");
        player.sendMessage("%info%Discord: %success%" + ChatColor.DARK_GREEN + Core.PLUGIN.getConfig().getString("discord-invite"));
        player.sendMessage("%info%Ask a staff member for more info");
        player.sendMessage(" ");

        if (args.length < 1) return;

        if ("info".equalsIgnoreCase(args[0])) {
            player.sendMessage("%info%&lInfo:");

            player.sendMessage("%info%Loc: %success%" + player.getLocation());
            player.sendMessage("%info%Rank: %success%" + LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup());
            player.sendMessage("%info%Display-name: %success%" + player.getDisplayName());
            player.sendMessage("%info%Ping: %success%" + ((CraftPlayer)player).getHandle().ping);
            player.sendMessage("%info%TPS: %success%" + Arrays.toString(MinecraftServer.getServer().recentTps));
            player.sendMessage("%info%Online players: %success%" + Bukkit.getOnlinePlayers().size());
            player.sendMessage("%info%Core Version: %success%" + Core.PLUGIN.getDescription().getVersion());
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
