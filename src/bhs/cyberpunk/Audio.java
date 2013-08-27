package bhs.cyberpunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Audio {
	public static final int THEME = 0;
	
	public static final int GUNSHOT = 0;
	
	public static Music[] music = new Music[64];
	public static Sound[] effects = new Sound[64];
	
	public static void loadMusic() {
		music[THEME] = Gdx.audio.newMusic(Gdx.files.internal("sound\\music\\theme.mp3"));
	}
	
	public static void loadEffects() {
		effects[GUNSHOT] = Gdx.audio.newSound(Gdx.files.internal("sound\\effects\\gunshot.wav"));
	}
}
