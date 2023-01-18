package au.chival.core.listeners;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.user.UserDataRecalculateEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static au.chival.core.util.Formating.foramtDisplayName;
import static au.chival.core.util.Formating.formatTabNames;

public class LuckpermEvents {

    public LuckpermEvents(JavaPlugin plugin, LuckPerms luckPerms) {

        EventBus eventBus = luckPerms.getEventBus();

        eventBus.subscribe(plugin, UserDataRecalculateEvent.class, this::onUserPromote);
    }

    private void onUserPromote(UserDataRecalculateEvent event) {
        formatTabNames(Bukkit.getPlayer(event.getUser().getUniqueId()));
        formatTabNames(Bukkit.getPlayer(event.getUser().getUniqueId()));
        foramtDisplayName(Bukkit.getPlayer(event.getUser().getUniqueId()));
    }
}
