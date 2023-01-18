package au.chival.core;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static net.luckperms.api.node.NodeType.INHERITANCE;

public class Rank {

    public static void setRank(Player player, Group group) {

        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        assert user != null;

        if (group == null) {
            user.setPrimaryGroup("default");
            return;
        }

        user.getNodes().forEach(value -> {
            if (value.getType().equals(INHERITANCE)) {
                user.data().remove(value);
            }
        });

        InheritanceNode node = InheritanceNode.builder(group.getName()).value(true).build();
        user.data().add(node);
        player.sendMessage(ChatColor.GREEN + "Your rank has been changed to " + group.getName());
    }
}
