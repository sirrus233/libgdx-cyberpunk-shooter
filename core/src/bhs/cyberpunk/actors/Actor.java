package bhs.cyberpunk.actors;

import java.awt.geom.Point2D;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Actor {
	Sprite sprite;
	
	public float getX() {
		return sprite.getX();
	}
	
	public float getY() {
		return sprite.getY();
	}
	
	public float getWidth() {
		return sprite.getWidth();
	}
	
	public float getHeight() {
		return sprite.getHeight();
	}
	
	public Point2D.Float getCenter() {
		return new Point2D.Float(getX()+(getWidth()/2), getY()+(getHeight()/2));
	}
	
	public Point2D.Float[] getVertices() {
		Point2D.Float[] vertices = new Point2D.Float[4];
		
		vertices[0] = new Point2D.Float(getX(), getY());
		vertices[1] = new Point2D.Float(getX(), getY() + getHeight());
		vertices[2] = new Point2D.Float(getX() + getWidth(), getY());
		vertices[3] = new Point2D.Float(getX() + getWidth(), getY() + getHeight());
		
		return vertices;
	}
}
