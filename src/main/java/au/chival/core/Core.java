package au.chival.core;

import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {

        //1.0

        plugin = this;

        //cmd-reg

        //listener-reg

    }

    @Override
    public void onDisable() {
    }
}
