package de.jonas.customentities.commands;

import java.util.ArrayList;
import java.util.List;

import de.jonas.customentities.CustomEntitiesList;
import de.jonas.customentities.Entity.Barstool;
import de.jonas.customentities.Entity.SporeBlossom;
import de.jonas.customentities.Entity.TextDisplay;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Spawn {

    MiniMessage mm = MiniMessage.miniMessage();
    CustomEntitiesList cel = new CustomEntitiesList();

    public Spawn() {
        List<Argument<?>> arguments = new ArrayList<>();
        arguments.add(new StringArgument("direction").replaceSuggestions(ArgumentSuggestions.strings(
                "north", "east", "south", "west"
        )));

        new CommandAPICommand("custom-entities:spawn")
            .withAliases(config.getStringList("Commands.Spawn.aliases").toArray(num -> new String[num]))
            .withPermission(config.getString("Commands.Spawn.permission"))
                .withSubcommand(new CommandAPICommand("sign")
                        .withArguments(new StringArgument("Name"))
                        .withArguments(arguments)
                        .withArguments(new FloatArgument("size"))
                        .withArguments(new GreedyStringArgument("Text"))
                        .executesPlayer(((player, commandArguments) -> {
                            String direction = (String) commandArguments.get("direction");
                            String text = (String) commandArguments.get("Text");
                            Float size = (Float) commandArguments.get("size");
                            if (size == null) {
                                size = 1f;
                            }
                            String name = (String) commandArguments.get("Name");
                            if (cel.hasName(player.getWorld(), name)) {
                                player.sendMessage(mm.deserialize("<red>Name already used, choose other!"));
                                return;
                            }
                            new TextDisplay(player, direction, text, size, name);
                            cel.setToList(player.getWorld(), name);
                        }))
                )
                .withSubcommand(new CommandAPICommand("Bar_Hocker")
                        .withArguments(new StringArgument("Name"))
                        .executesPlayer(((player, commandArguments) -> {
                            String name = (String) commandArguments.get("Name");
                            if (cel.hasName(player.getWorld(), name)) {
                                player.sendMessage(mm.deserialize("<red>Name already used, choose other!"));
                                return;
                            }
                            new Barstool(player.getLocation().toCenterLocation(), name);
                            cel.setToList(player.getWorld(), name);
                        }))
                )
                .withSubcommand(new CommandAPICommand("Sporeblossom")
                        .withArguments(new StringArgument("Name"))
                        .executesPlayer(((sender, args) -> {
                            String name = (String) args.get("Name");
                            if (cel.hasName(sender.getWorld(), name)) {
                                sender.sendMessage(mm.deserialize("<red>Name already used, choose other!"));
                                return;
                            }
                            new SporeBlossom(sender.getLocation().toCenterLocation(), name);
                            cel.setToList(sender.getWorld(), name);
                        }))
                )
                .register();
    }
}
