package GitHub.f2_56110;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 12;
	private boolean alive = true;
	BufferedImage ena;
	
	public Enemy(int x, int y) {
		super(x, y, 20, 17);
		
	}

	@Override
	public void draw(Graphics2D g) {
		try {
			ena = ImageIO.read(new File("GitHub\\f2_56110\\img\\lois.png"));
		} catch (IOException exp) {
            
        }
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.drawImage(ena, x, y, width, height, null);
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}

	public void setAlive(boolean del){
		alive = del;
	}
}