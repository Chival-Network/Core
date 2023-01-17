package au.chival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static au.chival.core.util.I18n.format;

public class Leave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(format("quit-message", event.getPlayer().getDisplayName()));

    }
}
