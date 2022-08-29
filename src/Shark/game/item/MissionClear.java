package Shark.game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.GameCanvas;

public class MissionClear {
	private Image img;
	private int x;
	private int y;
	private int width;
	private int height;

	

	public MissionClear() {
		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/sharkImages/mission-clear.png");
		
		width = 500;
		height = 400;
		x = 250;
		y = 200;
	}
	

	public void draw(Graphics g) {
		int x = this.x;
		int y = this.y;
		
		GameCanvas observer = GameCanvas.getInstance();
		
		g.drawImage(img, x, y, x+width, y+height, 0, 0, 500, 500, observer);
		
	}




}

