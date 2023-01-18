package au.chival.core.commands;

import au.chival.core.CommandBase;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static au.chival.core.Rank.setRank;

public class RankCommand extends CommandBase {

    public RankCommand() {super("rank", true, "chival.rank");}

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {

        if (args.length != 2) {
            sender.sendMessage(tl("rank-command.invalid-args"));
            return;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(tl("player-not-found"));
            return;
        }

        if (LuckPermsProvider.get().getGroupManager().getGroup(args[1]) == null) {
            sender.sendMessage(tl("rank-command.invalid-group"));
            return;
        }

        Group group = LuckPermsProvider.get().getGroupManager().getGroup(args[1]);
        Player target = Bukkit.getPlayer(args[0]);

        setRank(target, group);
        sender.sendMessage(tl("rank-command.success", target.getName(), group.getName()));

    }

    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {

        LinkedList<String> tab = new LinkedList<>();

        if (args.length == 1) {
            return null;
        }

        if (args.length == 2) {

            LuckPermsProvider.get().getGroupManager().loadAllGroups();

            LuckPermsProvider.get().getGroupManager().getLoadedGroups().forEach(value -> {
                tab.add(value.getName());
            });

            if (tab == null) {
                return Collections.EMPTY_LIST;
            }

            return StringUtil.copyPartialMatches(args[1], tab, new ArrayList<>());
        }

        return Collections.EMPTY_LIST;
    }
}
