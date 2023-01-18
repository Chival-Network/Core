package au.chival.core;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

public class Rank {

    public static void setRank(Player player, Group group) {

        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        assert user != null;

        if (group == null) {
            user.setPrimaryGroup("default");
            return;
        }

        user.setPrimaryGroup(group.getName());
        luckPerms.getUserManager().saveUser(user);

    }
}
