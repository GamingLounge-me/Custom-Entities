package de.jonas.customentities;

import de.jonas.customentities.commands.Spawn;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Custom_entities extends JavaPlugin {

    public static Custom_entities INSTANCE;
    public static String PREFIX;
    public Logger logger;

    public void onLoad() {
        INSTANCE = this;
        this.logger = this.getLogger();

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));

        new Spawn();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        PREFIX = "[CE] ";

        CommandAPI.onEnable();

        logger.log(Level.INFO, "Activated Plugin");
    }

    @Override
    public void onDisable() {

    }
}
