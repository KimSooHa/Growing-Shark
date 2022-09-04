package Shark.game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.GameFrame;
import Shark.game.ui.GameOverCanvas;
import Shark.game.ui.IntroCanvas;
import Shark.game.ui.MissionClearCanvas;

public class ReplayButton implements Button {

	private Image img;
	private Image updateImg;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private GameFrame gameFrame;
	
	
	public ReplayButton(int x, int y) {
		
		width = 190;
		height = 190;
		
		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/sharkImages/replay.png");
		updateImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		this.x = x;
		this.y = y;
		
	}
	
	
	public void draw(Graphics g) {
		
		IntroCanvas observer = IntroCanvas.getInstance();
		
		g.drawImage(updateImg, x, y, width, height, observer);

	}
	

	@Override
	public boolean isPointIn(int x, int y) {
		// TODO Auto-generated method stub
		return ((this.x < x && x <this.x + 170) &&
				(this.y < y && y < this.y + 170));
	}

	@Override
	public void entered(boolean isEntered) {
		if(isEntered) {
			width = 220;
			height = 220;
			x = 300;
			y = 370;
		}
		else {
			width = 190;
			height = 190;
			x = 310;
			y = 390;
		}
	}
	
	// 클릭되면 게임 재시작
	@Override
	public void clicked(boolean isClicked) {
		gameFrame = GameFrame.getInstance();
		gameFrame.gameOverToGameCanvas();
	}


	public void isClicked(boolean isClicked) {
		gameFrame = GameFrame.getInstance();
		gameFrame.missionClearToGameCanvas();
	}
	

}
