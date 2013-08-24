package bhs.cyberpunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Audio {
	public static final int GUNSHOT = 0;
	
	public static Sound[] sounds = new Sound[64];
	
	public static void load() {
		sounds[GUNSHOT] = Gdx.audio.newSound(Gdx.files.internal("sound\\gunshot.wav"));
	}
}
