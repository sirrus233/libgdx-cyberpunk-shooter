package bhs.cyberpunk;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Screen {
	private final OrthographicCamera camera = new OrthographicCamera();
	private final SpriteBatch batch = new SpriteBatch();
	private final BitmapFont font = new BitmapFont();
	private Player player = new Player();
	private static NPC[] enemies = new NPC[1];
	
	//Constructs a screen object, which consists of setting the camera
	public Screen() {
		//Sets the camera to view the entire screen
		camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
		
		Audio.music[Audio.THEME].play();
		Audio.music[Audio.THEME].setLooping(true);
		Audio.music[Audio.THEME].setVolume(0.1f);
		
		enemies[0] = new NPC();
	}
	
	//Uses a SpriteBatch to draw to the screen
	public void draw() {
		batch.setProjectionMatrix(camera.combined);	
		
		batch.begin();
		for (NPC enemy : enemies) {enemy.draw(batch);}
		player.draw(batch);
		font.draw(batch, "Speed: " + player.getSpeed(), Main.WIDTH - 100, Main.HEIGHT - 20);
		batch.end();
	}
	
	//Ticks all the action on screen
	public void tick(Input input) {
		for (NPC enemy : enemies) {enemy.tick(input);}
		player.tick(input);
	}
	
	public static NPC[] getNPCs() {
		return enemies;
	}
}
