package de.jonas.customentities.commands;

import de.jonas.customentities.CustomEntitiesList;
import de.jonas.customentities.Entity.Barstool;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class User {

    MiniMessage mm = MiniMessage.miniMessage();
    CustomEntitiesList cel = new CustomEntitiesList();

    public User() {

        new CommandAPICommand("custom-entities:user")
                .withAliases("ce:user")
                .withPermission("CustomEntities.user")
                .withSubcommand(
                        new CommandAPICommand("spawn")
                                .withSubcommand(new CommandAPICommand("Bar_Hocker")
                                        .withArguments(new StringArgument("Name"))
                                        .executesPlayer(((player, commandArguments) -> {
                                            String name = (String) commandArguments.get("Name");
                                            if (cel.hasName(player.getWorld(), name)) {
                                                player.sendMessage(mm.deserialize("<red>Name already used, choose other"));
                                                return;
                                            }
                                            name = player.getUniqueId() + "-" + name;
                                            new Barstool(player.getLocation().toCenterLocation(), name);
                                            cel.setToList(player.getWorld(), name);
                                        }))
                                )
                )
                .withSubcommand(new CommandAPICommand("remove")
                        .withArguments(new StringArgument("name"))
                        .executesPlayer(((player, commandArguments) -> {
                            String name = (String) commandArguments.get("name");
                            name = player.getUniqueId() + "-" + name;
                            cel.removeEntity(player.getWorld(), name);
                            cel.removeFromList(player.getWorld(), name);
                        }))
                )
                .withSubcommand(new CommandAPICommand("list")
                        .executesPlayer(((player, commandArguments) -> {
                            ArrayList<String> list = cel.getList(player.getWorld());
                            AtomicReference<String> message = new AtomicReference<>("<green>--Deine Entities--<br><reset>");
                            list.forEach(string -> {
                                if (string.startsWith(player.getUniqueId().toString())) {
                                    string = string.replace(player.getUniqueId().toString() + "-", "");
                                    message.set(message + string + "<br>");
                                }
                            });
                            message.set(message + "<green>---------------<reset>");
                            player.sendMessage(mm.deserialize(message.toString()));
                        }))
                )
                .register();

    }

}
