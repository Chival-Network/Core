package au.chival.core.events;

import au.chival.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {
	private static final String LEAVE_MESSAGE = ChatColor.translateAlternateColorCodes('&', Core.PLUGIN.getConfig().getString("leave-message", "none"));
	private static final boolean LEAVE_MESSAGE_ENABLED = !LEAVE_MESSAGE.equals("none");

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		if (LEAVE_MESSAGE_ENABLED)
			event.setQuitMessage(LEAVE_MESSAGE
					.replace("{NAME}", event.getPlayer().getName())
					.replace("{DISPLAYNAME}", event.getPlayer().getDisplayName()));
	}
}
