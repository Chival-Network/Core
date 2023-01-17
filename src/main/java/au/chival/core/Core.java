package au.chival.core;

import au.chival.core.commands.Fly;
import au.chival.core.commands.Help;
import au.chival.core.commands.Speed;
import au.chival.core.listeners.Chat;
import au.chival.core.listeners.Join;
import au.chival.core.listeners.LuckpermEvents;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Core extends JavaPlugin {
    public static JavaPlugin PLUGIN;

    @Override
    public void onEnable() {
        PLUGIN = this;

        this.saveDefaultConfig();

        //cmd-reg
        new Help();
        new Fly();
        new Speed();
        //

        //listener-reg
        new LuckpermEvents(PLUGIN, LuckPermsProvider.get());
        getServer().getPluginManager().registerEvents(new Join(), PLUGIN);
        getServer().getPluginManager().registerEvents(new Chat(), PLUGIN);
        //

        //other

        //

        Logger.getLogger("Chival").info("ChivalCore v" + this.getDescription().getVersion() + " successfully loaded");
    }

    @Override
    public void onDisable() {}
}
