package org.novikov.automine.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.novikov.automine.generator.Area;
import org.novikov.automine.generator.Generator;

import java.util.HashMap;
import java.util.Set;

public class RepeatMine extends BukkitRunnable {

	private HashMap<Player, Location> players;
    private ArmorStand armorStand;
	private World world;
	private Area area1;
	private Area area2;
	
	private Generator generator;
	
	private final JavaPlugin plugin;
	private int time = 300;
	
	public RepeatMine(JavaPlugin plugin, ArmorStand armorStand) {
		this.plugin = plugin;
		this.armorStand = armorStand;
		
		world = Bukkit.getServer().getWorld("world");
    	
    	area1 = new Area(123, 125, -132, world);
    	area2 = new Area(131, 119, -138, world);

		players = new HashMap<>();

    	generator = new Generator(area1, area2, players);
	}
	
	@Override
	public void run() {
		switch (time) {
			case 0:
				sendMessageToAllPlayers("Шахта обновлена.");

		    	generator.generate();

				time = 300;
			case 60, 120, 180, 240, 300:
				sendMessageToAllPlayers("Шахта будет обновлена через: " + time / 60 + " минут.");
		}
		armorStand.setCustomName(time / 60 + " минут. " + time % 60 + " cекунд.");
		time--;
	}

	private void sendMessageToAllPlayers(String msg) {
		Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(msg));
	}

}