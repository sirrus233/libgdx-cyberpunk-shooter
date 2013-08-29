package bhs.cyberpunk;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NPC {
	private Sprite sprite;
	private Sprite spriteAlive;
	private Sprite spriteDead;
	private boolean alive;
	
	public NPC() {
		sprite = new Sprite(Art.textures[Art.NULL], 64, 64);
		spriteAlive = new Sprite(Art.textures[Art.NPC_GREEN], 64, 64);
		spriteDead = new Sprite(Art.textures[Art.NPC_RED], 64, 64);
		alive = true;
	}

	public void draw(SpriteBatch batch) {
		if (alive) {spriteAlive.draw(batch);}
		else {spriteDead.draw(batch);}
	}
	
	public void tick(Input input) {
		//TODO Instead of setBounds, we may want to use something like "Translate" after setting
		//size in the constructor. Apply this to Weapon.java as well for the track and reticle.
		
		//TODO There must be a better way to handle animations than making a ton of sprite objects. Investigate
		sprite.setBounds(400, 300, 64, 64);
		spriteAlive.setPosition(sprite.getX(), sprite.getY());
		spriteDead.setPosition(sprite.getX(), sprite.getY());
		
		if (input.buttons[Input.RESSURECT]) {alive = true;}
	}

	public void determineHit(float pCenterX, float pCenterY, float theta) {		
		//Get the center coordinates of the player, and the theta of the targeting reticle.
		//Based on player's position in relation to the NPC, calculate a min/max theta necessary
		//for a hit. Min/max thetas are calculated by calculating what the necessary theta would
		//be for each vertex of the NPC sprite.
		
		float theta1 = calcTheta(sprite.getX(), sprite.getY(), pCenterX, pCenterY);
		float theta2 = calcTheta(sprite.getX(), sprite.getY() + sprite.getHeight(), pCenterX, pCenterY);
		float theta3 = calcTheta(sprite.getX() + sprite.getWidth(), sprite.getY(), pCenterX, pCenterY);
		float theta4 = calcTheta(sprite.getX() + sprite.getWidth(), sprite.getY() + sprite.getHeight(), pCenterX, pCenterY);
		
		float minTheta = Math.min(Math.min(theta1,theta2), Math.min(theta3,theta4));
		float maxTheta = Math.max(Math.max(theta1,theta2), Math.max(theta3,theta4));
		
		if (theta > minTheta && theta < maxTheta) {
			alive = false;
		}
	}
	
	private float calcTheta(float vertexX, float vertexY, float pCenterX, float pCenterY) {
		float theta = (float) Math.atan((vertexY - pCenterY)/(vertexX - pCenterX));
		if (pCenterX > vertexX) {theta = (float) (theta + Math.PI);}
		if (theta < 0) {theta += 2*Math.PI;}
		
		return theta;
	}
}
