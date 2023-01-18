package au.chival.core.events;

import au.chival.core.util.Utils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.user.UserDataRecalculateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class LuckpermsEvents {
    public LuckpermsEvents(JavaPlugin plugin, LuckPerms luckPerms) {
        EventBus eventBus = luckPerms.getEventBus();

        eventBus.subscribe(plugin, UserDataRecalculateEvent.class, this::onUserUpdated);
    }

    private void onUserUpdated(UserDataRecalculateEvent event) {
        Player player = Bukkit.getPlayer(event.getUser().getUniqueId());
        if (player == null) return;

        player.setDisplayName(Utils.getDisplayName(player));
        Utils.updateTab(player);
    }
}
