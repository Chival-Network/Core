package au.chival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static au.chival.core.util.Formating.*;
import static au.chival.core.util.I18n.format;

public class Join implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(format("join-message", event.getPlayer().getDisplayName()));

        formatTab(event.getPlayer());
        formatInGame(event.getPlayer());
    }
}
