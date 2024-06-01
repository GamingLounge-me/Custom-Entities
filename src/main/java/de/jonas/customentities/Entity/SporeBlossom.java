package de.jonas.customentities.Entity;

import de.jonas.customentities.CustomEntitiesList;
import de.jonas.customentities.Custom_entities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SporeBlossom {

    CustomEntitiesList customEntitiesList = new CustomEntitiesList();
    Custom_entities ce = Custom_entities.INSTANCE;

    public SporeBlossom(Location loc, String name) {

        loc.add(0, -0.5, 0);
        loc.setPitch(0);
        loc.setYaw(0);
        org.bukkit.entity.BlockDisplay t = (org.bukkit.entity.BlockDisplay) loc.getWorld().spawnEntity(loc, EntityType.BLOCK_DISPLAY);
        Transformation tr = new Transformation(new Vector3f(-0.5f,1f,-0.5f), new Quaternionf(), new Vector3f(1,-1,1), new Quaternionf());
        t.setTransformation(tr);
        t.setBlock(Bukkit.getServer().createBlockData(Material.SPORE_BLOSSOM));
        t.getPersistentDataContainer().set(customEntitiesList.entities, PersistentDataType.STRING, name);
        t.setBillboard(Display.Billboard.FIXED);

    }

}
