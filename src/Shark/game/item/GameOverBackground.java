package Shark.game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.IntroCanvas;

public class GameOverBackground {

	private Image img;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public GameOverBackground() {
		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/sharkImages/background4.jpg");
		
		x = 300;
		y = 100;
	}
	
	public void draw(Graphics g) {
		int y = this.y;
		
		IntroCanvas observer = IntroCanvas.getInstance();
		width = observer.getWidth();
		height = observer.getHeight();
		
		g.drawImage(img, x, y, width, height, observer);

	}

}
