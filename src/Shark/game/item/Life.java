package Shark.game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.GameCanvas;
import Shark.game.ui.GameFrame;

public class Life {
	private static Image img;	// ��������(static) -> �ѹ��� ��������� �ٽ� ��������� �ʴ´�
	private int imgIndex;
	private int imgIndexInterval;
	private int lifeDownInterval;
	
	private int x;
	private int y;
	
	
	private int width;
	private int height;
	
	private GameCanvas observer;
	private GameFrame gameFrame;
	private Level level;
	
	private Music crashSound;
	private int soundInterval;
	
	static {
		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/sharkImages/lifebar.png");
	}
	
	public Life() {
		
		width = 250;
		height = 80;
		
		x = 40;
		y = 30;
		
		imgIndex = 0;
		imgIndexInterval = 0;
		lifeDownInterval = 0;
		level = Level.getInstance();
		
		crashSound = new Music("res/audio/crashSound.wav", false);
		soundInterval = 0;
	}
	
	public void draw(Graphics g) {
		GameCanvas observer = GameCanvas.getInstance();
		
		int w = width;
		int h = height;
		
		
		int offX = w/2;
		int offY = h/2;
		
		// ��� ��ġ ��ǥ
		int dx1 = x;	// ��� ��ġ x��ǥ
		int dy1 = y;	// ��� ��ġ y��ǥ
		int dx2 = dx1+w-50;	// ��� ��ġ x��ǥ
		int dy2 = dy1+h-15;	// ��� ��ġ y��ǥ
		
		
		// �ڸ� �̹��� ��ġ ��ǥ
		int sx1 = 0;
		int sy1 = 0+h*imgIndex;
		int sx2 = sx1+w;
		int sy2 = sy1+h;
		
		g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
		
	}
	
	public void update() {
		
//		GameCanvas observer = GameCanvas.getInstance();
		gameFrame = GameFrame.getInstance();
		// �̹��� �ε��� �ٲٱ�
		if(imgIndexInterval == 250) {
			imgIndex++;
			
			if(imgIndex >= 17) {
				imgIndex = 17;
				gameFrame.gameToGameOverCanvas();
			}
			
//			if(imgIndex == 18) {
//			}
			
//			imgIndex %= 19;
		}
		
		imgIndexInterval++;
		imgIndexInterval %= 251;
		
	}
	
	public void lifeUp() {
		
		if(0 < imgIndex)
		imgIndex -= 1;
		
	}
	
	public void lifeDown() {
		if(imgIndex < 17 && lifeDownInterval == 0) 
			imgIndex += 1;
		
		lifeDownInterval++;
		if(level.getLevel() >= 6)
			lifeDownInterval %= 6;
		else if(level.getLevel() >= 4)
			lifeDownInterval %= 8;
		else
			lifeDownInterval %= 10;
		
		if(imgIndex == 17) {
			gameFrame.gameToGameOverCanvas();
		}
		
		// �浹�� �� ȿ����
		if(soundInterval == 0)
			crashSound.start();
		
		soundInterval++;
		soundInterval %= 4;
	}
}
