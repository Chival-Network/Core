package au.chival.core;

import au.chival.core.command.*;
import au.chival.core.listener.*;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;


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
        new SudoCommand();
        new GamemodeCommand();
        new RankCommand();
        new HealCommand();
        new FeedCommand();

        // Init listeners
        new LuckpermsEvents(PLUGIN, LuckPermsProvider.get());
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), PLUGIN);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), PLUGIN);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), PLUGIN);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), PLUGIN);


        this.getLogger().info("Core v" + this.getDescription().getVersion() + " successfully loaded");
    }

    @Override
    public void onDisable() {}
}
