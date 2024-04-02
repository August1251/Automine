package org.novikov.automine.generator;

import org.bukkit.World;

public class Area {

	protected World world;
	protected int x, y, z;
	
	public Area(int x, int y, int z, World world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}
	
}