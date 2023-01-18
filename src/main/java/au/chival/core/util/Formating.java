package au.chival.core.util;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static au.chival.core.Core.PLUGIN;

public class Formating {

    public static String getDisplayName(Player player) {

        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        String name = Bukkit.getPlayer(user.getUniqueId()).getName();
        String prefix = user.getCachedData().getMetaData().getPrefix();
        String suffix = user.getCachedData().getMetaData().getSuffix();

        if (prefix == null) {
            prefix = "";
        }

        if (suffix == null) {
            suffix = "";
        }

        String displayName = (prefix + name + suffix);

        player.setDisplayName(displayName);

        return displayName;
    }

    public static void foramtDisplayName(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                while (player == null) return;

                LuckPerms luckPerms = LuckPermsProvider.get();
                User user = luckPerms.getUserManager().getUser(player.getUniqueId());

                String name = Bukkit.getPlayer(user.getUniqueId()).getName();
                String prefix = user.getCachedData().getMetaData().getPrefix();
                String suffix = user.getCachedData().getMetaData().getSuffix();

                if (prefix == null) {
                    prefix = "";
                }

                if (suffix == null) {
                    suffix = "";
                }

                String displayName = (prefix + name + suffix);

                player.setDisplayName(displayName);

            }
        }.runTaskAsynchronously(PLUGIN);

    }

    public static void formatChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        String displayName = getDisplayName(event.getPlayer());

        event.setFormat(displayName + ": " + message);
    }

    public static void formatTabNames(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                while (player == null) return;

                TabAPI tabAPI = TabAPI.getInstance();
                TabPlayer tabPlayer = tabAPI.getPlayer(player.getUniqueId());

                LuckPerms luckPerms = LuckPermsProvider.get();
                User user = luckPerms.getUserManager().getUser(player.getUniqueId());

                String name = Bukkit.getPlayer(user.getUniqueId()).getName();
                String prefix = user.getCachedData().getMetaData().getPrefix();
                String suffix = user.getCachedData().getMetaData().getSuffix();

                if (prefix == null) {
                    prefix = "";
                }

                if (suffix == null) {
                    suffix = "";
                }

                while (!tabPlayer.isLoaded()) return;

                tabAPI.getTablistFormatManager().setName(tabPlayer, name);
                tabAPI.getTablistFormatManager().setPrefix(tabPlayer, prefix);
                tabAPI.getTablistFormatManager().setSuffix(tabPlayer, suffix);

            }
        }.runTaskAsynchronously(PLUGIN);
    }

    public static void formatInGame(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                while (player == null) return;

                TabAPI tabAPI = TabAPI.getInstance();
                TabPlayer tabPlayer = tabAPI.getPlayer(player.getUniqueId());

                LuckPerms luckPerms = LuckPermsProvider.get();
                User user = luckPerms.getUserManager().getUser(player.getUniqueId());

                String prefix = user.getCachedData().getMetaData().getPrefix();
                String suffix = user.getCachedData().getMetaData().getSuffix();

                if (prefix == null) {
                    prefix = "";
                }

                if (suffix == null) {
                    suffix = "";
                }

                while (!tabPlayer.isLoaded()) return;

                tabAPI.getTeamManager().setPrefix(tabPlayer, prefix);
                tabAPI.getTeamManager().setSuffix(tabPlayer, suffix);

            }
        }.runTaskAsynchronously(PLUGIN);
    }
}
