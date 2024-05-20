package de.jonas.customentities.commands;

import dev.jorel.commandapi.CommandAPICommand;
import io.papermc.paper.math.Rotations;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class Spawn {

    public NamespacedKey isCustomEntity = new NamespacedKey("custom-entities",
            "is_custom_entity_armour_stand_identifier");

    public Spawn() {
        new CommandAPICommand("custom-entites:spawn")
                .withAliases("ce:spawn")
                .withSubcommand(new CommandAPICommand("spore_blossom")
                        .executesPlayer((player, args) -> {
                            Location loc = player.getLocation().subtract(0, 0.9, 0);

                            ArmorStand armorStand = loc.getWorld().spawn(loc, ArmorStand.class);
                            armorStand.setGravity(false);
                            armorStand.setInvisible(true);
                            armorStand.setInvulnerable(true);
                            ItemStack item = new ItemStack(Material.SPORE_BLOSSOM);
                            armorStand.getEquipment().setHelmet(item);
                            armorStand.getPersistentDataContainer().set(isCustomEntity, PersistentDataType.BOOLEAN,
                                    true);
                            armorStand.setHeadPose(new EulerAngle(-600.0 ,0.0 ,0.0));
                        })
                )
                .register();
    }
}
