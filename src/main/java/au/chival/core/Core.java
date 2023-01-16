package au.chival.core;

import au.chival.core.command.Fly;
import au.chival.core.command.Help;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {
    public static JavaPlugin PLUGIN;

    @Override
    public void onEnable() {
        PLUGIN = this;

        this.saveDefaultConfig();

        //cmd-reg
        new Help();
        new Fly();
        //

        //listener-reg

    }

    @Override
    public void onDisable() {}
}
