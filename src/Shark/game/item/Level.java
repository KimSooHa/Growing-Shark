package Shark.game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.GameCanvas;

public class Level {
	private int level;
	private Image numImg;	
	private Image levelImg;
	private int imgIndex;	
	
	private int x;
	private int y;
	
	private Music levelUpSound;
	
	
	private int width;
	private int height;
	
	private GameCanvas observer;
	
	private static Level instance;
	
	public static Level getInstance() {
		if(instance == null)
			instance = new Level();
		
		return instance;
	}
	
	
	public Level() {
		level = 1;
		
		numImg = Toolkit
				.getDefaultToolkit()
				.getImage("res/sharkImages/number-bg-remove-1.png");
		levelImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/level-removebg-preview.png");

		width = 54;
		height = 80;
		x = 120;
		y = 615;
		
		imgIndex = 1;
		
		
	}
	
	public void draw(Graphics g) {
		GameCanvas observer = GameCanvas.getInstance();
		
		int w = width;
		int h = height;
		
		int x = this.x;
		int y = this.y;
		
		
		
		// 출력 위치 좌표
		int dx1 = x;	// 출력 위치 x좌표
		int dy1 = y;	// 출력 위치 y좌표
		int dx2 = dx1+w-10;	// 출력 위치 x좌표
		int dy2 = dy1+h-20;	// 출력 위치 y좌표
		
		
		// 자를 이미지 위치 좌표
		int sx1 = 0+w*imgIndex;
		int sy1 = 0;
		int sx2 = sx1+w;
		int sy2 = sy1+h;
		
		g.drawImage(numImg, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
		g.drawImage(levelImg, x-120, y-20, x-120+130, y-20+100, 0, 100, 500, 400, observer);

	}
	
	public void update() {
			
		GameCanvas observer = GameCanvas.getInstance();

		// 이미지 인덱스 바꾸기		
		for(int i = 1; i < 8; i++) {
			if(level == i)
				imgIndex = i;
		}
		
			
	}
	// 레벨업
	public void levelUp() {
		level ++;
		
		levelUpSound = new Music("res/audio/mixkit-instant-win-2021.wav", false);
		levelUpSound.start();	// 레벨업 효과음
	}
	
		
	public int getLevel() {
		return level;
	}
	
	// 레벨 1로 리셋
	public void resetLevel() {
		
		level = 1;
		imgIndex = 1;
	}
	 
}
