package GitHub.f2_56110;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class SpaceShip extends Sprite{

	int step = 8;
	BufferedImage player;
	int blood = 3;
	long immTimeStamp = 0;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		try {
			player = ImageIO.read(new File("GitHub\\f2_56110\\img\\stewie.png"));
			
		} catch (IOException exp) {
            
        }
        if(getImm()){
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		}
		g.drawImage(player, x, y, width, height, null);
				
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}

	public void decBlood(){
		if(!getImm()){
			blood--;
		}
	}

	public void incBlood(){
		blood++;
	}

	public boolean getImm(){
		Date date = new Date();
		return date.getTime() - immTimeStamp < 50000;

	}

	public void setImm(){
		Date date = new Date();
		immTimeStamp = date.getTime();
	}

	public int getBlood(){
		return blood;
	}
}