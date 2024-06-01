package de.jonas.customentities;

import de.jonas.customentities.commands.Admin;
import de.jonas.customentities.commands.Remove;
import de.jonas.customentities.commands.Spawn;
import de.jonas.customentities.commands.User;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;


public final class Custom_entities extends JavaPlugin {

    public static Custom_entities INSTANCE;
    public static String PREFIX;
    @Override
    public void onLoad() {
        INSTANCE = this;

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
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
