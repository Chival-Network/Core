package au.chival.core.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static au.chival.core.Core.LOBBYWORLDS;

public class LobbyEvents implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageEvent event) {
        if (LOBBYWORLDS.contains(event.getEntity().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onHunger(FoodLevelChangeEvent event) {
        if (LOBBYWORLDS.contains(event.getEntity().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(BlockPlaceEvent event) {

        if (event.getPlayer().hasPermission("chival.build")) {
            return;
        }

        if (LOBBYWORLDS.contains(event.getPlayer().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent event) {

        if (event.getPlayer().hasPermission("chival.build")) {
            return;
        }

        if (LOBBYWORLDS.contains(event.getPlayer().getWorld())) {
            event.setCancelled(true);
        }
    }
}
