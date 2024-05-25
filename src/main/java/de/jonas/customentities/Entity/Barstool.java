package de.jonas.customentities.Entity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Barstool {

    public Barstool(Location loc) {

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

        ArmorStand slab = loc.getWorld().spawn(loc.subtract(0,0.7,0), ArmorStand.class);
        slab.setGravity(false);
        slab.setInvisible(true);
        slab.setInvulnerable(true);
        wool.setDisabledSlots(EquipmentSlot.HEAD);
        ItemStack slabI = new ItemStack(Material.POLISHED_BLACKSTONE_SLAB);
        slab.getEquipment().setHelmet(slabI);

    }

}
