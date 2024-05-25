package de.jonas.customentities.commands;

import de.jonas.customentities.Entity.Barstool;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;

import java.util.Objects;

public class Spawn {

    public NamespacedKey isCustomEntity = new NamespacedKey("custom-entities",
            "is_custom_entity_armour_stand_identifier");
    MiniMessage mm = MiniMessage.miniMessage();

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
                        .executesPlayer(((player, commandArguments) -> {
                            new Barstool(player.getLocation().toCenterLocation());
                        }))
                )
                .register();
    }
}
