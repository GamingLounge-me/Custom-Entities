package de.jonas.customentities.commands;

import de.jonas.customentities.CustomEntitiesList;
import de.jonas.customentities.Entity.Barstool;
import de.jonas.customentities.Entity.TextDisplay;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import java.util.ArrayList;
import java.util.List;

public class Spawn {

    MiniMessage mm = MiniMessage.miniMessage();
    CustomEntitiesList cel = new CustomEntitiesList();

    public Spawn() {
        List<Argument<?>> arguments = new ArrayList<>();
        arguments.add(new StringArgument("direction").replaceSuggestions(ArgumentSuggestions.strings(
                "north", "east", "south", "west"
        )));

        new CommandAPICommand("custom-entities:spawn")
                .withAliases("ce:spawn")
                .withPermission("CustomEntities.Spawn")
                .withSubcommand(new CommandAPICommand("sign")
                        .withArguments(arguments)
                        .withArguments(new FloatArgument("size"))
                        .withArguments(new GreedyStringArgument("Text"))
                        .executesPlayer(((player, commandArguments) -> {
                            String direction = (String) commandArguments.get("direction");
                            String text = (String) commandArguments.get("Text");
                            float size = (float) commandArguments.get("size");
                            new TextDisplay(player, direction, text, size);
                        }))
                )
                .withSubcommand(new CommandAPICommand("Bar_Hocker")
                        .withArguments(new StringArgument("Name"))
                        .executesPlayer(((player, commandArguments) -> {
                            String name = (String) commandArguments.get("Name");
                            if (cel.hasName(player.getWorld(), name)) {
                                player.sendMessage(mm.deserialize("<red>Name already used, choose other"));
                                return;
                            }
                            new Barstool(player.getLocation().toCenterLocation(), name);
                            cel.setToList(player.getWorld(), name);
                        }))
                )
                .register();
    }
}
