package au.chival.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static au.chival.core.Tab.formatTab;
import static au.chival.core.util.Formating.formatInGame;
import static au.chival.core.util.Formating.formatTabNames;
import static au.chival.core.util.I18n.format;

public class Join implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(format("join-message", event.getPlayer().getDisplayName()));

        formatTabNames(event.getPlayer());
        formatTab(event.getPlayer(), null, null);
        formatInGame(event.getPlayer());
    }
}
