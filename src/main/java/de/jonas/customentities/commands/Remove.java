package de.jonas.customentities.commands;

import org.bukkit.configuration.file.FileConfiguration;

import de.jonas.customentities.CustomEntitiesList;
import de.jonas.customentities.Custom_entities;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;

public class Remove {

    CustomEntitiesList cel = new CustomEntitiesList();
    FileConfiguration config = Custom_entities.CONFIG;

    public Remove() {

        new CommandAPICommand("custom-entities:remove")
            .withAliases(config.getStringList("Commands.Remove.aliases").toArray(num -> new String[num]))
            .withPermission(config.getString("Commands.Remove.permission"))
                .withArguments(new StringArgument("name"))
                .executesPlayer(((player, commandArguments) -> {
                    String name = (String) commandArguments.get("name");
                    cel.removeEntity(player.getWorld(), name);
                    cel.removeFromList(player.getWorld(), name);
                }))
                .register();

    }

}
