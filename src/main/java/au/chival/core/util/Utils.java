package au.chival.core.util;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
    /**
     * Should be called in the join event, and luck perms user update event
     * to always keep player.getDisplayName() in sync with their
     * luck perms prefix and suffix.
     * Call minimally for performance reasons.
     *
     * @param player The player to get the display name for
     * @return The display name of the player with luck perms prefix and suffix
     */
    public static String getDisplayName(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        if (user == null) return player.getName();
        String prefix = user.getCachedData().getMetaData().getPrefix();
        String suffix = user.getCachedData().getMetaData().getSuffix();

        if (prefix == null) prefix = "";
        if (suffix == null) suffix = "";

        return ChatColor.translateAlternateColorCodes('&', prefix + player.getName() + suffix);
    }

    public static void formatTabAndNametag(Player player) {
        if (player == null) return;

        TabAPI tabAPI = TabAPI.getInstance();
        TabPlayer tabPlayer = tabAPI.getPlayer(player.getUniqueId());
        if (tabPlayer == null || !tabPlayer.isLoaded()) return;

        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) return;

        String prefix = user.getCachedData().getMetaData().getPrefix();
        String suffix = user.getCachedData().getMetaData().getSuffix();

        if (prefix == null) prefix = "";
        if (suffix == null) suffix = "";

        tabAPI.getTablistFormatManager().setName(tabPlayer, player.getName());
        tabAPI.getTablistFormatManager().setPrefix(tabPlayer, prefix);
        tabAPI.getTablistFormatManager().setSuffix(tabPlayer, suffix);
        tabAPI.getTeamManager().setPrefix(tabPlayer, prefix);
        tabAPI.getTeamManager().setSuffix(tabPlayer, suffix);
    }
}
