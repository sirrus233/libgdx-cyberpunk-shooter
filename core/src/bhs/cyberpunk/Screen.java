package bhs.cyberpunk;

import bhs.cyberpunk.actors.NPC;
import bhs.cyberpunk.actors.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Screen {
	private final OrthographicCamera camera = new OrthographicCamera();
	private final SpriteBatch batch = new SpriteBatch();
	private final BitmapFont font = new BitmapFont();
	
	//Constructs a screen object, which consists of setting the camera
	public Screen() {
		//Sets the camera to view the entire screen
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		Audio.music[Audio.THEME].play();
		Audio.music[Audio.THEME].setLooping(true);
		Audio.music[Audio.THEME].setVolume(0.1f);
		
		WorldManager.initPlayers();
		WorldManager.initNPCs();
	}
	
	//Uses a SpriteBatch to draw to the screen
	public void draw() {
		batch.setProjectionMatrix(camera.combined);	
		
		batch.begin();
		for (Player player : WorldManager.getPlayers()) {player.draw(batch);}
		for (NPC enemy : WorldManager.getNPCs()) {enemy.draw(batch);}
		font.draw(batch, "Speed: " + WorldManager.getPlayers().get(0).getSpeed(), Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 20);
		batch.end();
	}
	
	//Ticks all the action on screen
	public void tick(Input input) {
		for (Player player : WorldManager.getPlayers()) {player.tick(input);}
		for (NPC enemy : WorldManager.getNPCs()) {enemy.tick(input);}
	}
}
