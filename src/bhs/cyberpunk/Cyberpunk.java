package bhs.cyberpunk;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class Cyberpunk implements ApplicationListener {
	private Input input;
	private Screen screen;

	//Runs only once, at the beginning of the game
	public void create() {
		Art.load();
		Audio.loadEffects();
		Audio.loadMusic();
		input = new Input();
		screen = new Screen();
		
		Gdx.input.setInputProcessor(input);
		Gdx.input.setCursorCatched(true);
	}

	//Continuously looped over, and is the highest level of abstraction for everything else
	public void render() {
		//Set the clear color of the background, and clear the screen
		Gdx.gl.glClearColor(0.80f, 0.52f, 0.25f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		screen.tick(input);
		screen.draw();
	}

	@Override
	public void dispose() {
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
