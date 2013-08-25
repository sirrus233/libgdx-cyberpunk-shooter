package bhs.cyberpunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int WEAPON = 4;
	public static final int SLOW = 5;
	public static final int FAST = 6;
	
	public boolean[] buttons = new boolean[64];

	public int mouseX = 0;
	public int mouseY = 0;
	public boolean mouseLeft = false;
	
	public void set(int key, boolean down) {
		int button = -1;
		
		if (key == Keys.W) {button = UP;}
		if (key == Keys.S) {button = DOWN;}
		if (key == Keys.A) {button = LEFT;}
		if (key == Keys.D) {button = RIGHT;}
		
		if (key == Keys.F) {button = WEAPON;}
		
		if (key == Keys.O) {button = SLOW;}
		if (key == Keys.P) {button = FAST;}
		
		if (button >= 0) {
			this.buttons[button] = down;
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		set(keycode, true);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		set(keycode, false);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		mouseLeft = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		mouseLeft = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = Main.HEIGHT - screenY;  //To put y=0 along the bottom of the frame
		
		if (mouseX < 0) {Gdx.input.setCursorPosition(0, mouseY);}
		if (mouseX > Main.WIDTH) {Gdx.input.setCursorPosition(Main.WIDTH, mouseY);}
		if (mouseY < 0) {Gdx.input.setCursorPosition(mouseX, 0);}
		if (mouseY > Main.HEIGHT) {Gdx.input.setCursorPosition(mouseX, Main.HEIGHT);}
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
