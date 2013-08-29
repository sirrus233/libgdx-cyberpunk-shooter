package bhs.cyberpunk;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NPC {
	private Sprite sprite;
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

	public boolean inLineOfFire(float pCenterX, float pCenterY, float theta) {		
		//Get the center coordinates of the player, and the theta of the targeting reticle.
		//Based on player's position in relation to the NPC, calculate a min/max theta necessary
		//for a hit. Min/max thetas are calculated by calculating what the necessary theta would
		//be for each vertex of the NPC sprite.
		float vertices[] = getVertices();
		
		float theta1 = calcTheta(vertices[0], vertices[1], pCenterX, pCenterY);
		float theta2 = calcTheta(vertices[2], vertices[3], pCenterX, pCenterY);
		float theta3 = calcTheta(vertices[4], vertices[5], pCenterX, pCenterY);
		float theta4 = calcTheta(vertices[6], vertices[7], pCenterX, pCenterY);
		
		float minTheta = Math.min(Math.min(theta1,theta2), Math.min(theta3,theta4));
		float maxTheta = Math.max(Math.max(theta1,theta2), Math.max(theta3,theta4));
		
		System.out.println(theta1+","+theta2+","+theta3+","+theta4);
		System.out.println(theta);
		System.out.println();
		
		return (theta > minTheta && theta < maxTheta);
	}
	
	private float calcTheta(float vertexX, float vertexY, float pCenterX, float pCenterY) {
		float theta = (float) Math.atan((vertexY - pCenterY)/(vertexX - pCenterX));
		if (pCenterX > vertexX) {theta = (float) (theta + Math.PI);}
		if (theta < 0) {theta += 2*Math.PI;}
		
		return theta;
	}
	
	private float[] getVertices() {
		float[] vertices = new float[8];
		
		vertices[0] = sprite.getX();
		vertices[1] = sprite.getY(); 
		vertices[2] = sprite.getX(); 
		vertices[3] = sprite.getY() + sprite.getHeight();
		vertices[4] = sprite.getX() + sprite.getWidth();
		vertices[5] = sprite.getY();	
		vertices[6] = sprite.getX() + sprite.getWidth();
		vertices[7] = sprite.getY() + sprite.getHeight();
		
		return vertices;
	}
	
	public float getDistanceToPlayer() {
		float[] vertices = getVertices();
		
		float dist1 = (float) Math.hypot(vertices[0], vertices[1]);
		float dist2 = (float) Math.hypot(vertices[2], vertices[3]);
		float dist3 = (float) Math.hypot(vertices[4], vertices[5]);
		float dist4 = (float) Math.hypot(vertices[6], vertices[7]);
		
		return Math.max(Math.max(dist1,dist2), Math.max(dist3,dist4));
	}
	
	public void kill() {
		alive = false;
	}
}
