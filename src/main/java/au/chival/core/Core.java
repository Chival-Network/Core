package au.chival.core;

import au.chival.core.commands.Fly;
import au.chival.core.commands.Help;
import au.chival.core.commands.Speed;
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
        new Speed();
        //

        //listener-reg

    }

    @Override
    public void onDisable() {}
}
