package au.chival.core.events;

import au.chival.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatEvent implements Listener {
    private static final String CHAT_FORMAT = ChatColor.translateAlternateColorCodes('&', Core.PLUGIN.getConfig().getString("chat-format", "&r{NAME}: {MESSAGE}"));

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().hasPermission("chival.chatcolor"))
            event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));

        event.setFormat(CHAT_FORMAT
            .replace("{NAME}", event.getPlayer().getName())
            .replace("{DISPLAYNAME}", event.getPlayer().getDisplayName())
            .replace("{MESSAGE}", event.getMessage()));
    }
}
