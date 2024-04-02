package org.novikov.automine;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.novikov.automine.task.RepeatMine;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public final class Automine extends JavaPlugin {

    private ArmorStand armorStand;

    @Override
    public void onEnable() {
        World world = Bukkit.getServer().getWorld("world");
        Location location = new Location(world, 127, 128, -140);

        world.getNearbyEntities(location, 5, 5, 5).stream()
                .filter(entity -> entity instanceof ArmorStand)
                .forEach(Entity::remove);

        armorStand = location.getWorld().spawn(location, ArmorStand.class);

        armorStand.setInvisible(true);
        armorStand.setVisible(false);
        armorStand.setGravity(false);

        armorStand.setCustomName("5 минут 0 секунд");
        armorStand.setCustomNameVisible(true);

        BukkitTask task = new RepeatMine(this, armorStand).runTaskTimer(this, 0L, 20L);
    }

    @Override
    public void onDisable() {

    }
}
