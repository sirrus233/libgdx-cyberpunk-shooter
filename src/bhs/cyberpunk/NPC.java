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
		//for a hit.

		float theta1 = (float) Math.atan((sprite.getY() - pCenterY)/(sprite.getX() - pCenterX));
		float theta2 = (float) Math.atan((sprite.getY() + sprite.getHeight() - pCenterY)/(sprite.getX() - pCenterX));
		float theta3 = (float) Math.atan((sprite.getY() - pCenterY)/(sprite.getX() + sprite.getWidth() - pCenterX));
		float theta4 = (float) Math.atan((sprite.getY() + sprite.getHeight() - pCenterY)/(sprite.getX() + sprite.getWidth() - pCenterX));
		
		float minTheta = Math.min(Math.min(theta1,theta2), Math.min(theta3,theta4));
		float maxTheta = Math.max(Math.max(theta1,theta2), Math.max(theta3,theta4));
		
		if (theta > minTheta && theta < maxTheta) {
			alive = false;
		}
	}
}
