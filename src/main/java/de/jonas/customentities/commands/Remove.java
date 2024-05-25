package de.jonas.customentities.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;

public class Remove {

    public Remove() {

        new CommandAPICommand("custom-entities:remove")
                .withAliases("ce:remove")
                .withPermission("CustomEntities.remove")
                .executesPlayer(((player, commandArguments) -> {



                }))
                .register();

    }

}
