package GitHub.f2_56110;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	private Image img;
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	

	public GamePanel() {
		try {
			bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
			img = ImageIO.read(new File("GitHub\\f2_56110\\img\\space.jpg"));
			big = (Graphics2D) bi.getGraphics();
			big.setBackground(Color.BLACK);
		} catch (IOException exp) {
            exp.printStackTrace();
        }
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big.drawImage(img, 0, 0, 400, 600, null);
		big.setColor(Color.WHITE);		
		big.drawString(String.format("Score = %08d", reporter.getScore()), 180, 20);
		big.drawString(String.format("Blood = %d", reporter.getBlood_v()), 300, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}