package au.chival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static au.chival.core.util.Formating.formatChat;

public class Chat implements Listener {

    @EventHandler
    public void playerJoin(AsyncPlayerChatEvent event) {
        formatChat(event);
    }
}
