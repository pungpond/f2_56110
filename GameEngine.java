package GitHub.f2_56110;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter {
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private SpaceShip v;	
	
	private Timer timer;
	private int level;
	private int levelTh;
	private int score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;
		level=1;
		levelTh=5000;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}

	private void generateEnemy(){
	 	Enemy e = new Enemy((int)(Math.random()*390), 30);
	 	gp.sprites.add(e);
	 	enemies.add(e);
	}

	private void generateItem(String name){
 		Item t = new Item((int)(Math.random()*390), 30, name);
 	 	gp.sprites.add(t);
 		items.add(t);
	}

	private void process(){
		if(levelTh < score){
			level++;
			levelTh*=2;
		}

		if(Math.random() < difficulty){
			generateEnemy();
			
		}

		if(Math.random() < difficulty/2){
			generateItem("hp");
		}

		if(Math.random() < difficulty/3){
			generateItem("imm");
		}

		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed(level);
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}

		Iterator<Item> item_iter = items.iterator();
		while(item_iter.hasNext()){
			Item it = item_iter.next();
			it.proceed(0);
			if(!it.isAlive()){
				item_iter.remove();
				gp.sprites.remove(it);
			}
		}




		gp.updateGameUI(this);
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double t_it;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				e.setAlive(false);
				v.decBlood();
				gp.sprites.remove(e);
				enemies.remove(e);
				if(v.getBlood() == 0)
					die();
				return;
			}
		}

		for(Item it : items){
			t_it = it.getRectangle();
			if(t_it.intersects(vr)){
				switch(it.getItemType()){
					case "hp":
						v.incBlood();
						break;
					case "imm":
						v.setImm();
						break;
				}
				it.setAlive(false);
				gp.sprites.remove(it);
				items.remove(it);
				return;
			}
		}


	}

	public void die(){
		timer.stop();
	}

	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		}
	}
	
	
	public int getScore(){
		return score;
	}

	public int getBlood_v(){
		return v.getBlood();
	}

	public int getLevel_v(){
		return level;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
