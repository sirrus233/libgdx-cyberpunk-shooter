package bhs.cyberpunk;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Screen {
	private final OrthographicCamera camera = new OrthographicCamera();
	private final SpriteBatch batch = new SpriteBatch();
	private final BitmapFont font = new BitmapFont();
	private Player player = new Player();
	
	public Screen() {
		camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
	}
	
	public void draw() {
		batch.setProjectionMatrix(camera.combined);	
		batch.begin();
		player.draw(batch);
		font.draw(batch, "Speed: " + player.getSpeed(), Main.WIDTH - 100, Main.HEIGHT - 20);
		batch.end();
	}
	
	public void tick(Input input) {
		player.tick(input);
	}
}
