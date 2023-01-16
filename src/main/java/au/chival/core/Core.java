package au.chival.core;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        //cmd-reg

        //listener-reg

    }

    @Override
    public void onDisable() {
    }
}
