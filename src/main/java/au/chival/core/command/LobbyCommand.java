package au.chival.core.command;

import au.chival.core.util.Utils;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static au.chival.core.Core.PLUGIN;

public class LobbyCommand extends CommandBase {

    public LobbyCommand() {
        super("lobby", false, null);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("lobby");
        try { player.sendPluginMessage(PLUGIN, "BungeeCord", out.toByteArray()); } catch (Exception e) { System.out.println("Error send player to server. Is this a on a proxy? " + e); }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {
        return Utils.EMPTY_LIST;
    }
}
