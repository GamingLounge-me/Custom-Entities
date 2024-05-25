package de.jonas.customentities;

import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.util.ArrayList;

public class CustomEntitiesList {

    public NamespacedKey names = new NamespacedKey("customentities", "list_of_customentities");
    public NamespacedKey entities = new NamespacedKey("customentities", "entity_list_of_customentities");

    public void setToList(World world, String name) {
        PersistentDataContainer container = world.getPersistentDataContainer();
        if (world.getPersistentDataContainer().has(names)) {
            String get = container.get(names, PersistentDataType.STRING);
            String set = get + "\n" + name;
            container.set(names, PersistentDataType.STRING, set);
        } else {
            container.set(names, PersistentDataType.STRING, name);
        }
    }

    public void removeFromList(World world, String name) {
        PersistentDataContainer container = world.getPersistentDataContainer();
        if (world.getPersistentDataContainer().has(names)) {
            String get = container.get(names, PersistentDataType.STRING);
            String[] list = get.split("\n");
            ArrayList<String> re = new ArrayList<>();
            for (String s : list) {
                int la = re.lastIndexOf(s);
                re.add(la + 1, s);
            }
            re.remove(name);
            String set = re.toArray(String[]::new).toString();
            container.set(names, PersistentDataType.STRING, set);
        } else {
            container.set(names, PersistentDataType.STRING, name);
        }
    }

    public ArrayList<String> getList(World world) {
        PersistentDataContainer container = world.getPersistentDataContainer();
        if (container.get(names, PersistentDataType.STRING) == null) return null;

        String get = container.get(names, PersistentDataType.STRING);
        String[] list = get.split("\n");
        ArrayList<String> re = new ArrayList<>();
        for (String s : list) {
            int la = re.lastIndexOf(s);
            re.add(la + 1, s);
        }
        return re;
    }

    public void removeEntity(World world, String name) {
        world.getEntities().forEach(entity -> {
            if (isSeachedEntity(entity, name)) entity.remove();
        });
    }

    public boolean isSeachedEntity(Entity entity, String name) {
        PersistentDataContainer container = entity.getPersistentDataContainer();
        if (!container.has(entities)) return false;
        return container.get(entities, PersistentDataType.STRING).equalsIgnoreCase(name);
    }

    public boolean hasName(World world, String name) {
        ArrayList<String> list = getList(world);
        if (list == null) return false;
        return list.contains(name);
    }

}
