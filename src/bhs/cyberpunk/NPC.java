package bhs.cyberpunk;

import java.awt.geom.Point2D;

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
		
		float theta1 = calcTheta(vertices[0], playerCenter);
		float theta2 = calcTheta(vertices[1], playerCenter);
		float theta3 = calcTheta(vertices[2], playerCenter);
		float theta4 = calcTheta(vertices[3], playerCenter);
		
		float minTheta = Math.min(Math.min(theta1,theta2), Math.min(theta3,theta4));
		float maxTheta = Math.max(Math.max(theta1,theta2), Math.max(theta3,theta4));
		
		System.out.println(theta1+","+theta2+","+theta3+","+theta4);
		System.out.println(theta);
		System.out.println();
		
		return (theta > minTheta && theta < maxTheta);
	}
	
	private float calcTheta(Point2D.Float vertex, Point2D.Float playerCenter) {
		float theta = (float) Math.atan((vertex.y - playerCenter.y)/(vertex.x - playerCenter.x));
		if (playerCenter.x > vertex.x) {theta = (float) (theta + Math.PI);}
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
