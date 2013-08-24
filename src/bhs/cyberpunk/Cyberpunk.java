package bhs.cyberpunk;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class Cyberpunk implements ApplicationListener {
	private Input input;
	private Screen screen;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Art.load();
		Audio.load();
		input = new Input();
		screen = new Screen();
		
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
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
