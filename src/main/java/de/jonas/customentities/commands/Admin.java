package de.jonas.customentities.commands;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;

import de.jonas.customentities.CustomEntitiesList;
import de.jonas.customentities.Custom_entities;
import dev.jorel.commandapi.CommandAPICommand;

public class Admin {

    CustomEntitiesList cel = new CustomEntitiesList();
    FileConfiguration config = Custom_entities.CONFIG;

    public Admin() {

        new CommandAPICommand("custom-entities:admin")
            .withAliases(config.getStringList("Commands.Admin.aliases").toArray(num -> new String[num]))
                .withPermission(config.getString("Commands.Admin.permission"))
                .withSubcommand(new CommandAPICommand("list")
                        .executesPlayer(((player, commandArguments) -> {
                            player.sendMessage(String.valueOf(cel.getList(player.getWorld())));
                        }))
                )
                .withSubcommand(new CommandAPICommand("clear")
                        .executesPlayer(((player, commandArguments) -> {
                            ArrayList<String> list = cel.getList(player.getWorld());
                            list.forEach(string -> {
                               cel.removeFromList(player.getWorld(), string);
                               cel.removeEntity(player.getWorld(), string);
                            });
                        }))
                )
                .register();

    }

}
