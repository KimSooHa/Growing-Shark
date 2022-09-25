package Shark.game.item.button;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import Shark.game.ui.GameFrame;
import Shark.game.ui.IntroCanvas;

public class StartButton implements Button {

	private Image img;
	private Image updateImg;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private GameFrame gameFrame;
	
	public StartButton(int x, int y) {
		
		width = 170;
		height = 170;
		
		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/sharkImages/play.png");
		updateImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		this.x = x;
		this.y = y;
	}
	
	
	public void draw(Graphics g) {		
		IntroCanvas observer = IntroCanvas.getInstance();
		
		g.drawImage(updateImg, x, y, width, height, observer);

	}
	

	public boolean isPointIn(int x, int y) {
		// TODO Auto-generated method stub
		return ((this.x < x && x <this.x + 170) &&
				(this.y < y && y < this.y + 170));
	}


	public void entered(boolean isEntered) {
		if(isEntered) {
			width = 200;
			height = 200;
			x = 390;
		}
		else {
			width = 170;
			height = 170;
			x = 400;
		}
		
	}
	
	
	public void clicked(boolean isClicked) {
		gameFrame = GameFrame.getInstance();
		gameFrame.introToGameCanvas();

	}
}
