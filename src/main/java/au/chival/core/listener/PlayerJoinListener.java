package au.chival.core.listener;

import au.chival.core.Core;
import au.chival.core.util.I18n;
import au.chival.core.util.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;


public class PlayerJoinListener implements Listener {
    private static final String JOIN_MESSAGE = I18n.colorize(Core.PLUGIN.getConfig().getString("join-message", "none"));
    private static final boolean JOIN_MESSAGE_ENABLED = !JOIN_MESSAGE.equals("off");


    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        event.getPlayer().setDisplayName(Utils.getDisplayName(event.getPlayer()));

        if (JOIN_MESSAGE_ENABLED)
            event.setJoinMessage(JOIN_MESSAGE
                .replace("{NAME}", event.getPlayer().getName())
                .replace("{DISPLAYNAME}", event.getPlayer().getDisplayName()));

        Utils.updateTab(event.getPlayer());
    }
}
