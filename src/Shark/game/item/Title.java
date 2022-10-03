package Shark.game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.IntroCanvas;

public class Title {
	
	private Image img;
	private Image updateImg;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Title() {
		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/images/title1.png");
		updateImg = img.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		
		x = 310;
		y = 70;
	}
	
	public void draw(Graphics g) {
		int x = this.x;
		int y = this.y;
		
		IntroCanvas observer = IntroCanvas.getInstance();
		width = 350;
		height = 350;
		
		g.drawImage(updateImg, x, y, width, height, observer);

	}
	
}
