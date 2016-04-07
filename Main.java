import javax.swing.*;
import java.awt.*;

public class Main{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Space war");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout()); 
		frame.setVisible(true);
	}
} 