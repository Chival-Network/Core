package au.chival.core;

import au.chival.core.commands.Help;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {

        //1.0

        plugin = this;

        //cmd-reg
            //help
            getCommand("help").setExecutor(new Help());
            getCommand("help").setTabCompleter(new Help());
        //

        //listener-reg

    }

    @Override
    public void onDisable() {
    }
}
