package Shark.game.item.background;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.GameCanvas;

public class Background {

	private Image img;
	private int y;
	private int width;
	private int height;
	
	public Background() {

		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/sharkImages/background4.jpg");
		
		width = 1000;
		height = 600;
		y = 0;
		
	}
	
	public void draw(Graphics g) {
		int y = this.y;
		
		GameCanvas observer = GameCanvas.getInstance();
		
		g.drawImage(img, 0, y, width, height, observer);

	}
	
}
