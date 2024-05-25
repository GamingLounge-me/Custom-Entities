package de.jonas.customentities.Entity;

import de.jonas.customentities.CustomEntitiesList;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;

import java.util.Objects;

public class TextDisplay {

    MiniMessage mm = MiniMessage.miniMessage();
    CustomEntitiesList customEntitiesList = new CustomEntitiesList();

    public TextDisplay(Player player, String direction, String text, float size, String name) {

        int directionZ, directionX;
        Location location = player.getLocation().toCenterLocation().add(0,0.5,0);
        switch (direction){
            case "north":
                directionX = 0;
                directionZ = 180;
                location.subtract(0,0,0.49  );
                break;
            case "east":
                directionX = -90;
                directionZ = 0;
                location.add(0.49,0,0);
                break;
            case "south":
                directionX = 0;
                directionZ = -90;
                location.add(0,0,0.49);
                break;
            case "west":
                directionX = 90;
                directionZ = 0;
                location.subtract(0.49,0,0);
                break;
            default:
                directionX = 404;
                directionZ = 404;
                break;
        }

        Vector vec = player.getLocation().getDirection().setX(directionX).setY(0).setZ(directionZ);
        Location loc = location.setDirection(vec);

        org.bukkit.entity.TextDisplay t = (org.bukkit.entity.TextDisplay) loc.getWorld().spawnEntity(loc, EntityType.TEXT_DISPLAY);
        t.text(mm.deserialize(Objects.requireNonNull(text)));
        Transformation tr = t.getTransformation();
        tr.getScale().set(size);
        t.setTransformation(tr);
        t.getPersistentDataContainer().set(customEntitiesList.entities, PersistentDataType.STRING, name);
        t.setBillboard(Display.Billboard.FIXED);

    }

}
