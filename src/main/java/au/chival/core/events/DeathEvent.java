package au.chival.core.events;

import au.chival.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class DeathEvent implements Listener {
	private final boolean DEATH_MESSAGES_ENABLED = Core.PLUGIN.getConfig().getBoolean("death-messages-enabled", false);


	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (!DEATH_MESSAGES_ENABLED) event.setDeathMessage(null);
	}
}
