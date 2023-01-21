package au.chival.core.listener;

import au.chival.core.Core;
import au.chival.core.util.I18n;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerQuitListener implements Listener {
	private static final String LEAVE_MESSAGE = I18n.colorize(Core.PLUGIN.getConfig().getString("leave-message", "none"));
	private static final boolean LEAVE_MESSAGE_ENABLED = !LEAVE_MESSAGE.equals("off");


	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		if (LEAVE_MESSAGE_ENABLED)
			event.setQuitMessage(LEAVE_MESSAGE
					.replace("{NAME}", event.getPlayer().getName())
					.replace("{DISPLAYNAME}", event.getPlayer().getDisplayName()));
	}
}
