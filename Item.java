package GitHub.f2_56110;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item extends Enemy{

	private String item_type;
	private enum Choices {
    	HP, IMM;
	}
	
	public Item(int x, int y, String name) {
		super(x, y);
    	try {
    		Choices eType = Choices.valueOf(name.toUpperCase());
    		item_type = name.toLowerCase();

    	} catch (IllegalArgumentException ex) {  
        	item_type = "lois";
  		}
		
	}

	public String getItemType(){
		return item_type;
	}

	@Override
	public void draw(Graphics2D g) {
		try {
			ena = ImageIO.read(new File("GitHub\\f2_56110\\img\\" + item_type + ".png"));
		} catch (IOException exp) {
            
        }
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.drawImage(ena, x, y, width, height, null);
	}

}