package Shark.game.item.background;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.IntroCanvas;

public class IntroBackground {

	private Image img;
	private int y;
	private int width;
	private int height;
	
	public IntroBackground() {
		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/sharkImages/background4.jpg");
		y = 0;
	}
	
	public void draw(Graphics g) {
		int y = this.y;
		
		IntroCanvas observer = IntroCanvas.getInstance();
		width = observer.getWidth();
		height = observer.getHeight();
		
		g.drawImage(img, 0, y, width, height, observer);

	}
}
