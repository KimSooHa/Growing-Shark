package Shark.game.item.background;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.GameCanvas;

public class BottomBackground {
	private Image img;
	private int x;
	private int y;
	private int width;
	private int height;

	

	public BottomBackground() {
		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/images/bottombg.png");
		
		width = 1000;
		height = 600;
		x = 0;
		y = 600;
	}
	

	public void draw(Graphics g) {
		int x = this.x;
		int y = this.y;
		
		GameCanvas observer = GameCanvas.getInstance();
		
		g.drawImage(img, x, y, 1000, 750, 0, 300, 960, 640, observer);
		
	}




}
