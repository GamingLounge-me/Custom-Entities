package de.jonas.customentities.commands;

import de.jonas.customentities.CustomEntitiesList;
import dev.jorel.commandapi.CommandAPICommand;

import java.util.ArrayList;

public class Admin {

    CustomEntitiesList cel = new CustomEntitiesList();

    public Admin() {

        new CommandAPICommand("ce:admin")
                .withAliases("custom-entities:admin")
                .withPermission("CustomEntities.admin")
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
