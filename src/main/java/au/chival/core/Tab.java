package au.chival.core;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static au.chival.core.Core.PLUGIN;

public class Tab {

    public static void formatTab(Player player, String header, String footer) {

        new BukkitRunnable() {

            @Override
            public void run() {

                while (player == null) return;

                TabAPI tabAPI = TabAPI.getInstance();
                TabPlayer tabPlayer = tabAPI.getPlayer(player.getUniqueId());

                if (header == null && footer == null) {
                    tabAPI.getHeaderFooterManager().setHeaderAndFooter(tabPlayer, PLUGIN.getConfig().getString("tab-header"), PLUGIN.getConfig().getString("tab-footer"));
                } else {
                    tabAPI.getHeaderFooterManager().setHeaderAndFooter(tabPlayer, header, footer);
                }

            }
        }.runTaskAsynchronously(PLUGIN);
    }
}
