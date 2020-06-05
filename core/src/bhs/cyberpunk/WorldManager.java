package bhs.cyberpunk;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import bhs.cyberpunk.actors.NPC;
import bhs.cyberpunk.actors.Player;

public class WorldManager {
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static ArrayList<NPC> npcs = new ArrayList<NPC>();
	
	public static void initPlayers() {
		players.add(new Player(10, 10));
	}
	
	public static ArrayList<Player> getPlayers() {
		return players;
	}
	
	public static void initNPCs() {
		npcs.add(new NPC(400, 300));
		npcs.add(new NPC(300, 100));
	}
	
	public static ArrayList<NPC> getNPCs() {
		return npcs;
	}
	
	public static void calcHit(Player player, float theta) {
		Point2D.Float center = player.getCenter();
		NPC hit = null;
		
		for (NPC npc : npcs) {
			if (npc.inLineOfFire(center.x, center.y, theta)) {
				if ((hit == null) || (npc.getDistanceToPlayer(center) < hit.getDistanceToPlayer(center))) {hit = npc;}
			}
		}
		
		if (hit != null) {hit.kill();}
	}
}
