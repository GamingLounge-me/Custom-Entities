package de.jonas.customentities.Entity;

import de.jonas.customentities.CustomEntitiesList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.C;

public class Barstool {

    CustomEntitiesList customEntitiesList = new CustomEntitiesList();

    public Barstool(Location loc, String name) {

        BlockData block = Material.END_ROD.createBlockData();

        loc.getWorld().setBlockData(loc, block);

        ArmorStand wool = loc.getWorld().spawn(loc.subtract(0,0.6,0), ArmorStand.class);
        wool.setGravity(false);
        wool.setInvisible(true);
        wool.setInvulnerable(true);
        wool.setSmall(true);
        wool.setDisabledSlots(EquipmentSlot.HEAD);
        ItemStack woolI = new ItemStack(Material.WHITE_WOOL);
        wool.getEquipment().setHelmet(woolI);
        wool.getPersistentDataContainer().set(customEntitiesList.entities, PersistentDataType.STRING, name);

        ArmorStand slab = loc.getWorld().spawn(loc.subtract(0,0.7,0), ArmorStand.class);
        slab.setGravity(false);
        slab.setInvisible(true);
        slab.setInvulnerable(true);
        wool.setDisabledSlots(EquipmentSlot.HEAD);
        ItemStack slabI = new ItemStack(Material.POLISHED_BLACKSTONE_SLAB);
        slab.getEquipment().setHelmet(slabI);
        slab.getPersistentDataContainer().set(customEntitiesList.entities, PersistentDataType.STRING, name);

    }

}
