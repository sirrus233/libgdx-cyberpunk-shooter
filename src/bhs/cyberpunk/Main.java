package bhs.cyberpunk;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public static void main(String[] args) {
		//Creates the application window, setting its name and resolution
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Cyberpunk";
		cfg.width = WIDTH;
		cfg.height = HEIGHT;
		new LwjglApplication(new Cyberpunk(), cfg);
	}
}
