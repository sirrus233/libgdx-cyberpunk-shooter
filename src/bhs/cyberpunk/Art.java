package bhs.cyberpunk;

import com.badlogic.gdx.graphics.Texture;

public class Art {
	public static final int NULL = 0;
	public static final int PLAYER_LEFT = 1;
	public static final int PLAYER_RIGHT = 2;
	public static final int TARGET_TRACK = 3;
	public static final int TARGET_RETICLE = 4;
	
	public static Texture[] textures = new Texture[64];
	
	public static void load() {
		textures[NULL] = new Texture("art\\null_texture.png");
		textures[PLAYER_LEFT] = new Texture("art\\player_left.png");
		textures[PLAYER_RIGHT] = new Texture("art\\player_right.png");
		textures[TARGET_TRACK] = new Texture("art\\aiming_track.png");
		textures[TARGET_RETICLE] = new Texture("art\\targeting_reticle.png");
	}
}
