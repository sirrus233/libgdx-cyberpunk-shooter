package bhs.cyberpunk.actors;

import bhs.cyberpunk.Art;
import bhs.cyberpunk.Input;
import bhs.cyberpunk.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Actor {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int RIGHT = 2;
	public static final int LEFT = 3;
	
	private Sprite spriteUp;
	private Sprite spriteDown;
	private Sprite spriteLeft;
	private Sprite spriteRight;
	private Weapon weapon;
	private int speed;
	private int orientation;	
	
	public Player(int x, int y) {
		sprite = new Sprite(Art.textures[Art.NULL], 64, 64);
		spriteUp = new Sprite(Art.textures[Art.NULL], 64, 64);
		spriteDown = new Sprite(Art.textures[Art.NULL], 64, 64);
		spriteLeft = new Sprite(Art.textures[Art.PLAYER_LEFT], 64, 64);
		spriteRight = new Sprite(Art.textures[Art.PLAYER_RIGHT], 64, 64);
		weapon = new Weapon(this);
		speed = 200;
		orientation = RIGHT;
		
		sprite.setBounds(x, y, 64, 64);
	}
	
	//Draws the player to the screen
	public void draw(SpriteBatch batch) {
		if (orientation == UP) {spriteUp.draw(batch);}
		if (orientation == DOWN) {spriteDown.draw(batch);}
		if (orientation == LEFT) {spriteLeft.draw(batch);}
		if (orientation == RIGHT) {spriteRight.draw(batch);}
		if (weapon.weaponDrawn()) {weapon.draw(batch);}
	}
	
	//Updates player position and attributes
	public void tick(Input input) {
		//Set position and orientation when weapon is not drawn (from keyboard)
		if (input.buttons[Input.UP]) {
			sprite.translateY(speed * Gdx.graphics.getDeltaTime());
			orientation = UP;
		}
		if (input.buttons[Input.DOWN]) {
			sprite.translateY(-speed * Gdx.graphics.getDeltaTime());
			orientation = DOWN;
		}
		if (input.buttons[Input.LEFT]) {
			sprite.translateX(-speed * Gdx.graphics.getDeltaTime());
			orientation = LEFT;
		}
		if (input.buttons[Input.RIGHT]) {
			sprite.translateX(speed * Gdx.graphics.getDeltaTime());
			orientation = RIGHT;
		}
		
		//If the weapon is drawn, orientation from keyboard is overwritten with orientation from mouse
		//TODO fix this to work with all positive weapon.getThetas (if that is final decision)
		if (weapon.weaponDrawn()) {
			if (weapon.getTheta() < -Math.PI/4) {orientation = DOWN;}
			else if (weapon.getTheta() < Math.PI/4) {orientation = RIGHT;}
			else if (weapon.getTheta() < 3*Math.PI/4) {orientation = UP;}
			else if (weapon.getTheta() < 5*Math.PI/4) {orientation = LEFT;}
			else {orientation = DOWN;} //TODO is this necessary?
		}
		
		//Prevent player from escaping off edge of screen
		if (sprite.getX() < 0) {sprite.setX(0);}
		if (sprite.getX() > Gdx.graphics.getWidth() - sprite.getWidth()) {sprite.setX(Gdx.graphics.getWidth() - sprite.getWidth());}
		if (sprite.getY() < 0) {sprite.setY(0);;}
		if (sprite.getY() > Gdx.graphics.getHeight() - sprite.getHeight()) {sprite.setY(Gdx.graphics.getHeight() - sprite.getHeight());}
		
		//Copy final sprite position to directional sprites
		spriteUp.setPosition(sprite.getX(), sprite.getY());
		spriteDown.setPosition(sprite.getX(), sprite.getY());
		spriteLeft.setPosition(sprite.getX(), sprite.getY());
		spriteRight.setPosition(sprite.getX(), sprite.getY());
		
		//Tick the active weapon
		weapon.tick(input);
		
		//Adjust speed
		if (input.buttons[Input.SLOW]) {
			changeSpeed(-5);
		}
		if (input.buttons[Input.FAST]) {
			changeSpeed(5);
		}
	}
	
	public void setOrientation(int o) {
		if ((o >= 0) && (o <= 3)) {orientation = o;}
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void changeSpeed(int delta) {
		int newSpeed = speed + delta;
		if (newSpeed >= 0) {speed = newSpeed;} 
		else {speed = 0;}		
	}
}
