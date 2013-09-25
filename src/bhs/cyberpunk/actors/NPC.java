package bhs.cyberpunk.actors;

import java.awt.geom.Point2D;

import bhs.cyberpunk.Art;
import bhs.cyberpunk.Input;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NPC extends Actor {
	private Sprite spriteAlive;
	private Sprite spriteDead;
	private boolean alive;
	
	public NPC(int x, int y) {
		sprite = new Sprite(Art.textures[Art.NULL], 64, 64);
		spriteAlive = new Sprite(Art.textures[Art.NPC_GREEN], 64, 64);
		spriteDead = new Sprite(Art.textures[Art.NPC_RED], 64, 64);
		alive = true;
		
		sprite.setBounds(x, y, 64, 64);
	}

	public void draw(SpriteBatch batch) {
		if (alive) {spriteAlive.draw(batch);}
		else {spriteDead.draw(batch);}
	}
	
	public void tick(Input input) {
		//TODO Instead of setBounds, we may want to use something like "Translate" after setting
		//size in the constructor. Apply this to Weapon.java as well for the track and reticle.
		
		//TODO There must be a better way to handle animations than making a ton of sprite objects. Investigate
		spriteAlive.setPosition(sprite.getX(), sprite.getY());
		spriteDead.setPosition(sprite.getX(), sprite.getY());
		
		if (input.buttons[Input.RESSURECT]) {alive = true;}
	}

	public boolean inLineOfFire(float playerCenterX, float playerCenterY, float theta) {		
		//Get the center coordinates of the player, and the theta of the targeting reticle.
		//Based on player's position in relation to the NPC, calculate a min/max theta necessary
		//for a hit. Min/max thetas are calculated by calculating what the necessary theta would
		//be for each vertex of the NPC sprite.
		Point2D.Float vertices[] = getVertices();
		Point2D.Float playerCenter = new Point2D.Float(playerCenterX, playerCenterY);
		
		boolean inQuad1 = false;
		boolean inQuad4 = false;
		
		//Calculate the theta between the player and each vertex of the NPC.
		//Detect if any of the NPC thetas is in quadrant 1 or 4 of the XY plane.
		float[] npcThetas = new float[4];		
		for (int i = 0; i < 4; i++) {
			npcThetas[i] = calcTheta(vertices[i], playerCenter);
			if (npcThetas[i] <= (Math.PI/2)) {inQuad1 = true;}
			else if (npcThetas[i] >= (3*Math.PI/2)) {inQuad4 = true;}
		}

		float maxTheta = 0;
		float minTheta = 0;
		
		//If there is an NPC theta in quadrants 1 AND 4, then simply choosing the largest and smallest
		//values for theta will yield incorrect results, since the "smallest" theta will actually be
		//quite big (near 2pi) and the "largest" theta will be small. To correct for this, convert all
		//angles in the 4th quadrant to negative values, including the players aiming angle.
		if (inQuad1 && inQuad4) {
			for (int i = 0; i < 4; i++) {
				if (npcThetas[i] >= (3*Math.PI/2)) {npcThetas[i] -= 2*Math.PI;}
			}
			if (theta >= (3*Math.PI/2)) {theta -= 2*Math.PI;}
		} 
		
		//Calculate the max and min angles necessary for a hit, and return whether or not theta lies between them.
		maxTheta = Math.max(Math.max(npcThetas[0],npcThetas[1]), Math.max(npcThetas[2],npcThetas[3]));
		minTheta = Math.min(Math.min(npcThetas[0],npcThetas[1]), Math.min(npcThetas[2],npcThetas[3]));	

		return (theta > minTheta && theta < maxTheta);
	}
	
	private float calcTheta(Point2D.Float vertex, Point2D.Float playerCenter) {
		float theta = (float) Math.atan2((vertex.y - playerCenter.y),(vertex.x - playerCenter.x));
		if (theta < 0) {theta += 2*Math.PI;}
		
		return theta;
	}
	
	public float getDistanceToPlayer(Point2D.Float pCenter) {
		Point2D.Float[] vertices = getVertices();
		
		float dist1 = (float) vertices[0].distance(pCenter);
		float dist2 = (float) vertices[1].distance(pCenter);
		float dist3 = (float) vertices[2].distance(pCenter);
		float dist4 = (float) vertices[3].distance(pCenter);
		
		return Math.max(Math.max(dist1,dist2), Math.max(dist3,dist4));
	}
	
	public void kill() {
		alive = false;
	}
}
