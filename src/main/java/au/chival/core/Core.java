package au.chival.core;

import au.chival.core.commands.*;
import au.chival.core.events.*;
import au.chival.core.events.LuckpermsEvents;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class Core extends JavaPlugin {
    public static JavaPlugin PLUGIN;

    @Override
    public void onEnable() {
        PLUGIN = this;

        this.saveDefaultConfig();

        // Init commands
        new HelpCommand();
        new FlyCommand();
        new SpeedCommand();
        new TabCommand();
        new SudoCommand();
        new GamemodeCommand();
        // heal
        // feed
        // clear inventory


        // Init listeners
        new LuckpermsEvents(PLUGIN, LuckPermsProvider.get());
        getServer().getPluginManager().registerEvents(new JoinEvent(), PLUGIN);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), PLUGIN);
        getServer().getPluginManager().registerEvents(new ChatEvent(), PLUGIN);
        getServer().getPluginManager().registerEvents(new DeathEvent(), PLUGIN);


        Logger.getLogger("Core").info("ChivalCore v" + this.getDescription().getVersion() + " successfully loaded");
    }

    @Override
    public void onDisable() {}
}
