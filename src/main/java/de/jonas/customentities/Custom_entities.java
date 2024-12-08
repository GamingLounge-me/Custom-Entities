package de.jonas.customentities;

import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.jonas.customentities.commands.Admin;
import de.jonas.customentities.commands.Remove;
import de.jonas.customentities.commands.Spawn;
import de.jonas.customentities.commands.User;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;


public final class Custom_entities extends JavaPlugin {

    public static Custom_entities INSTANCE;
    public static FileConfiguration CONFIG;

    @Override
    public void onLoad() {
        INSTANCE = this;
        CONFIG = this.getConfig();

        if (!CommandAPI.isLoaded()) CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
        new Spawn();
        new Remove();
        new Admin();
        new User();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        CommandAPI.onEnable();

        getLogger().log(Level.INFO, "Activated Plugin");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        CommandAPI.onDisable();

        getLogger().log(Level.INFO, "Plugin disabled!");
    }
}
