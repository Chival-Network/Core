package au.chival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static au.chival.core.util.Formating.formatChat;
import static au.chival.core.util.I18n.format;

public class Chat implements Listener {

    @EventHandler
    public void playerJoin(AsyncPlayerChatEvent event) {
        formatChat(event);
    }
}
