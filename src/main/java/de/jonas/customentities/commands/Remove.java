package de.jonas.customentities.commands;

import de.jonas.customentities.CustomEntitiesList;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;

public class Remove {

    CustomEntitiesList cel = new CustomEntitiesList();

    public Remove() {

        new CommandAPICommand("custom-entities:remove")
                .withAliases("ce:remove")
                .withPermission("CustomEntities.remove")
                .withArguments(new StringArgument("name"))
                .executesPlayer(((player, commandArguments) -> {
                    String name = (String) commandArguments.get("name");
                    cel.removeEntity(player.getWorld(), name);
                    cel.removeFromList(player.getWorld(), name);
                }))
                .register();

    }

}
