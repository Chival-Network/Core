package au.chival.core.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static au.chival.core.Core.LOBBYWORLDS;

public class VoidSaveEvent implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent event) {
        if (LOBBYWORLDS.contains(event.getPlayer().getWorld())) {
            if (event.getPlayer().getLocation().getY() <= 0) {
                event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
            }
        }
    }
}
