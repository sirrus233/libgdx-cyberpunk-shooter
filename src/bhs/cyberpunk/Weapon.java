package bhs.cyberpunk;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class Weapon {
	private Sprite player;
	private Sprite track;
	private Sprite reticle;
	private Circle trackCircle;
	private float reticleDiameter;
	private float trackDiameter;
	private boolean weaponDrawn;
	private boolean weaponButtonPressed;
	private boolean fireButtonPressed;
	
	
	public Weapon(Sprite p) {
		player = p;
		track = new Sprite(Art.textures[Art.TARGET_TRACK]);
		reticle = new Sprite(Art.textures[Art.TARGET_RETICLE]);
		trackCircle = new Circle();
		
		reticleDiameter = 40;
		trackDiameter = 175;
		
		weaponDrawn = false;
		weaponButtonPressed = false;
		fireButtonPressed = false;
	}
	
	public void draw(SpriteBatch batch) {
		if (!weaponDrawn) {return;}
		track.draw(batch);
		reticle.draw(batch);
	}
	
	public void tick(Input input) {
		//Draw or holster the weapon from user input
		if (input.buttons[Input.WEAPON]) {
			weaponButtonPressed = true;
		} else if (weaponButtonPressed) {
			weaponDrawn = !weaponDrawn;
			weaponButtonPressed = false;
		}
		
		if (!weaponDrawn) {return;}
		
		//On a mouse click, fire the gun
		if (input.mouseLeft) {
			fireButtonPressed = true;
		} else if (fireButtonPressed) {
			Audio.sounds[Audio.GUNSHOT].play();
			fireButtonPressed = false;
		}
		
		//Update targeting graphic position
		updateTrack(input);
		updateReticle(input);		
	}
	
	private void updateTrack(Input input) {
		float trackXOffset = -(trackDiameter-player.getWidth())/2;
		float trackYOffset = -(trackDiameter-player.getHeight())/2;
		float trackX = player.getX()+trackXOffset;
		float trackY = player.getY()+trackYOffset;
		track.setBounds(trackX, trackY, trackDiameter, trackDiameter);
		
		float circleCenterX = player.getX()+(player.getWidth()/2);
		float circleCenterY = player.getY()+(player.getHeight()/2);
		trackCircle.set(circleCenterX, circleCenterY, trackDiameter/2);
	}
	
	private void updateReticle(Input input) {
		float tanTheta = (input.mouseY - trackCircle.y)/(input.mouseX - trackCircle.x);
		float theta = (float) Math.atan(tanTheta);
		if (input.mouseX < trackCircle.x) {
			theta = (float) (theta + Math.PI);
		}
		
		float reticleXOffset = (float) (trackCircle.radius*Math.cos(theta) + reticleDiameter/4);
		float reticleYOffset = (float) (trackCircle.radius*Math.sin(theta) + reticleDiameter/4);
		float reticleX = player.getX() + reticleXOffset;
		float reticleY = player.getY() + reticleYOffset;
		reticle.setBounds(reticleX, reticleY, reticleDiameter, reticleDiameter);
	}
}
