package au.chival.core.util;

import au.chival.core.Core;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.apache.commons.io.IOUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.nio.file.Files;
import java.util.logging.Logger;

import static au.chival.core.Core.PLUGIN;


public class Utils {
    private static final String TAB_HEADER = I18n.colorize(PLUGIN.getConfig().getString("tab-header"));
    private static final String TAB_FOOTER = I18n.colorize(PLUGIN.getConfig().getString("tab-footer"));


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

    /**
     * Update the tab header and footer for a player
     * Also updates the prefix and suffix of the player in the tab list cell
     * Also updates the prefix and suffix above the player's head in-game
     * Call minimally for performance reasons.
     *
     * @param player The player to update the tab for
     */
    public static void updateTab(Player player) {
        if (player == null) return;
        TabAPI tabAPI = TabAPI.getInstance();

        long startTime = System.currentTimeMillis();
        final int maxTime = 30; // 30 second timeout
        new BukkitRunnable() {
            @Override
            public void run() {
                while (true) {
                    if (System.currentTimeMillis() - startTime > maxTime * 1000L) {
                        PLUGIN.getLogger().warning("Tab update took too long for player " + player.getName());
                        return;
                    }

                    TabPlayer tabPlayer = tabAPI.getPlayer(player.getUniqueId());
                    if (tabPlayer == null || !tabPlayer.isOnline()) return;
                    if (!tabPlayer.isLoaded()) continue;

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

                    tabAPI.getHeaderFooterManager().setHeaderAndFooter(tabPlayer, TAB_HEADER, TAB_FOOTER);
                    break;
                }
            }
        }.runTaskAsynchronously(PLUGIN);
    }

    /**
     * This method makes sure a resource exists, if so
     * it will return the file contents, if not it will
     * create the file and return the default contents from the jar.
     * @param path The path to the resource
     * @return The contents of the resource or null if it doesn't exist in the jar file
     */
    public static String getOrCreateResource(String path) {
        try { // Try to read the file contents from disk in plugin data folder
            return IOUtils.toString(Files.newInputStream(new File(Core.PLUGIN.getDataFolder(), path).toPath()));
        } catch (Exception e) {
            Core.PLUGIN.saveResource(path, false); // Will write default file to disk if it doesn't exist

            try {
                return IOUtils.toString(Core.PLUGIN.getResource(path)); // Get internal version from jar or newly written file
            } catch (Exception e1) {
                Logger.getLogger("Core").warning("Failed to load default " + path + "file: " + e1.getMessage());
                return null;
            }
        }
    }
}
