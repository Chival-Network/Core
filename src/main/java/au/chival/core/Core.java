package au.chival.core;

import au.chival.core.commands.*;
import au.chival.core.events.*;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public final class Core extends JavaPlugin {

    public static JavaPlugin PLUGIN;
    public static List<World> LOBBYWORLDS = new ArrayList<>();

    @Override
    public void onEnable() {
        //start
        PLUGIN = this;
        this.saveDefaultConfig();

        // Init commands
        new HelpCommand();
        new FlyCommand();
        new SpeedCommand();
        new SudoCommand();
        new GamemodeCommand();
        new RankCommand();
        new HealCommand();
        new SetSpawnCommand();

        // Init listeners
        new LuckpermsEvents(PLUGIN, LuckPermsProvider.get());
        getServer().getPluginManager().registerEvents(new JoinEvent(), PLUGIN);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), PLUGIN);
        getServer().getPluginManager().registerEvents(new ChatEvent(), PLUGIN);
        getServer().getPluginManager().registerEvents(new DeathEvent(), PLUGIN);
        getServer().getPluginManager().registerEvents(new LobbyEvents(), PLUGIN);
        getServer().getPluginManager().registerEvents(new VoidSaveEvent(), PLUGIN);

        // lobby-worlds
        Core.PLUGIN.getConfig().getStringList("lobby-worlds").forEach(value -> {

            World world = Bukkit.getWorld(value);

            if (world == null) {
                getLogger().warning("World " + value + " in config.yml does not exist; skipping...");
                return;
            }

            LOBBYWORLDS.add(world);

        });

        // end
        Logger.getLogger("Core").info("ChivalCore v" + this.getDescription().getVersion() + " successfully loaded");
    }

    @Override
    public void onDisable() {}
}
