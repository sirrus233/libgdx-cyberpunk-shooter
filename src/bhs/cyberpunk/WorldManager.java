package bhs.cyberpunk;

import java.util.ArrayList;

public class WorldManager {
	private static ArrayList<NPC> enemies = new ArrayList<NPC>();
	
	public static void initEnemies() {
		enemies.add(new NPC(400, 300));
		enemies.add(new NPC(300, 100));
	}
	
	public static ArrayList<NPC> getNPCs() {
		return enemies;
	}
	
	public static void calcHit(ArrayList<NPC> targets) {
		NPC hit = targets.get(0);
		
		for (NPC target : targets) {
			if (target.getDistanceToPlayer() < hit.getDistanceToPlayer()) {
				hit = target;
			}
		}
		
		hit.kill();
	}
}
