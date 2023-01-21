package au.chival.core.listener;

import au.chival.core.Core;
import au.chival.core.util.I18n;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class PlayerChatListener implements Listener {
    private static final String CHAT_FORMAT = I18n.colorize(Core.PLUGIN.getConfig().getString("chat-format", "&r{NAME}: {MESSAGE}"));
    private static final boolean CHAT_FORMAT_ENABLED = !CHAT_FORMAT.equals("off");


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().hasPermission("chival.chatcolor"))
            event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));

        if (CHAT_FORMAT_ENABLED)
            event.setFormat(CHAT_FORMAT
                .replace("{NAME}", event.getPlayer().getName())
                .replace("{DISPLAYNAME}", event.getPlayer().getDisplayName())
                .replace("{MESSAGE}", event.getMessage()));
    }
}
