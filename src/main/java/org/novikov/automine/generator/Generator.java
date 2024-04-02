package org.novikov.automine.generator;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Generator {

	private HashMap<Player, Location> players;
	protected Area area1, area2;
	
	public Generator(Area area1, Area area2, HashMap<Player, Location> players) {
		this.area1 = area1;
		this.area2 = area2;
		this.players = players;
	}
	
	public void generate() {
		
		Location location;
		
		int botBlockX = (Math.min(area1.x, area2.x));
		int topBlockX = (Math.max(area1.x, area2.x));
		int botBlockY = (Math.min(area1.y, area2.y));
		int topBlockY = (Math.max(area1.y, area2.y));
		int botBlockZ = (Math.min(area1.z, area2.z));
		int topBlockZ = (Math.max(area1.z, area2.z));


		for (int x = botBlockX; x <= topBlockX; x++) 
		{
            for (int z = botBlockZ; z <= topBlockZ; z++)
            {
                for (int y = botBlockY; y <= topBlockY; y++)
                {
					getAllPlayersInMine(x, y, z);

                	location = new Location(area1.world, x, y, z);
                	System.out.println(area1.world + " " + x + " " + y + " " + z);
                	location.getBlock().setType(random());
                }
            }
        }
		
	}
	
	private Material random() {
		Object[] blocks = {Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.IRON_ORE, Material.COAL_ORE, Material.DIAMOND_ORE, Material.GOLD_ORE};
		
		int rand = new Random().nextInt(blocks.length);
		
		return (Material) blocks[rand];
		
	}

	private void getAllPlayersInMine(int x, int y, int z) {

		World world = Bukkit.getWorld("world");
		Location location = new Location(world, 127, 128, -140);

		Bukkit.getServer().getOnlinePlayers().stream().filter(
				player -> player.getLocation().getX() == x &&
				player.getLocation().getY() == y &&
				player.getLocation().getZ() == z).forEach(player -> player.teleport(location));
	}
		
}