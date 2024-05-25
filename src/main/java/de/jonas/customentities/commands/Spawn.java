package de.jonas.customentities.commands;

import de.jonas.customentities.CustomEntitiesList;
import de.jonas.customentities.Entity.Barstool;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.util.Transformation;

import java.util.Objects;

public class Spawn {

    MiniMessage mm = MiniMessage.miniMessage();
    CustomEntitiesList cel = new CustomEntitiesList();

    public Spawn() {
        new CommandAPICommand("custom-entities:spawn")
                .withAliases("ce:spawn")
                .withPermission("CustomEntities.Spawn")
                .withSubcommand(new CommandAPICommand("sign")
                        .withArguments(new FloatArgument("size"))
                        .withArguments(new GreedyStringArgument("Text"))
                        .executesPlayer(((player, commandArguments) -> {
                            Location loc = player.getLocation();

                            String c = (String) commandArguments.get("Text");
                            float s = (float) commandArguments.get("size");

                            TextDisplay t = (TextDisplay) loc.getWorld().spawnEntity(loc, EntityType.TEXT_DISPLAY);
                            t.text(mm.deserialize(Objects.requireNonNull(c)));
                            Transformation tr = t.getTransformation();
                            tr.getScale().set(s);
                            t.setTransformation(tr);

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
                            cel.setToList(player.getWorld(), name, player);
                        }))
                )
                .register();
    }
}
