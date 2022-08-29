package Shark.game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.GameCanvas;

import Shark.game.ui.GameFrame;

public class Shark {
	
	private Image img;
	private Image images[];
	private int imgIndex;
	private int screenWidth;
	private int screenHeight;
	private double x;
	private double y;
	private double speedLeft;
	private double speedUp;
	private double speedRight;
	private double speedDown;
	private double vx;
	private double vy;
	private double dx;
	private double dy;
	private int imageX;
	private int imageY;
	private int sharkInterval;
	private int width;
	private int height;
	private int offX;
	private int offY;
	
	private int direction;
	private double speed;
	private int sizeUpX;
	private int sizeUpY;
	private int imgIndexInterval;
	
	private GameCanvas observer;
	
	// 상수 변수
	public static final int MOVE_NONE = 0;
	public static final int MOVE_UP = 1;
	public static final int MOVE_RIGHT = 2;
	public static final int MOVE_DOWN = 4;
	public static final int MOVE_LEFT = 8;
	
	
	
	public Shark(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.speedLeft = 1;
		this.speedUp = 1;
		this.speedRight = 1;
		this.speedDown = 1;
		this.speed = 2;
		imageX = 70;
		imageY = 70;
		direction = MOVE_NONE;
		sharkInterval = 0;
		imgIndex = 2;
		imgIndexInterval = 0;
		width = 140;
		height = 140;
		offX = width/2 -5;	// 상어 이미지 크기의 반(70)
		offY = height/2 -5;	// 상어 이미지 크기의 반(70)

		
//		images = new Image[imgIndex];
//		for(int i = 0; i < imgIndex; i++)
//			images[i] = Toolkit.getDefaultToolkit().getImage("res/sharkImages/shark" + i + ".png");
			
		
		img = Toolkit.getDefaultToolkit().getImage("res/sharkImages/sharkSprite-R.png");

	}
	
	
	
	public void moveBy(int direction) {
		this.direction = direction;
		
	}
	
	public void update() {
		
		int offX = imageX / 2;
		int offY = imageY / 2;
		
		int dx1 = (int) (this.x - offX);
		int dy1 = (int) (this.y - offY);
		int dx2 = dx1 + offX;
		int dy2 = dy1 + offY;
		
		observer = GameCanvas.getInstance();
		screenWidth = observer.getWidth();
		screenHeight = observer.getHeight() - 100;
				
		
		
		if((direction & MOVE_LEFT) == MOVE_LEFT) {
			img = Toolkit.getDefaultToolkit().getImage("res/sharkImages/sharkSprite-L.png");
			
			// 이미지 인덱스 바꾸기
			if(imgIndexInterval == 0) {
				imgIndex++;
				imgIndex %= 4;
			}
			
			imgIndexInterval++;
			imgIndexInterval %= 8;
			
			if(sharkInterval == 0 && speed < 7)
				speed += speedLeft;

			sharkInterval++;
			sharkInterval %= 15;

//			if(diff < 0)
//				x -= speed + diff;
			// 현재위치의 왼쪽 경계값과 프레임 경계값 사이의 거리가 speed보다 작을 때
			if(dx1 < speed)
				x += dx1;	// 위치를 밀린만큼 다시 조정해주기(물체가 화면 벗어나는 것 목적좌표 조정)
			else 
				x -= speed;
		}
		
		
		if((direction & MOVE_UP) == MOVE_UP) {
			
			if(sharkInterval == 0 && speed < 7) 
				speed += speedUp;
			
			sharkInterval++;
			sharkInterval %= 15;

			// 현재위치의 위쪽 경계값과 프레임 경계값 사이의 거리가 speed보다 작을 때
			if(dy1 < speed)
				y += dy1;	// 위치를 밀린만큼 다시 조정해주기(물체가 화면 벗어나는 것 목적좌표 조정)
			else
				y -= speed;
		}
		
		if((direction & MOVE_RIGHT) == MOVE_RIGHT) {
			img = Toolkit.getDefaultToolkit().getImage("res/sharkImages/sharkSprite-R.png");
			
			// 이미지 인덱스 바꾸기
			if(imgIndexInterval == 0) {
				imgIndex++;
				imgIndex %= 4;
			}
			
			imgIndexInterval++;
			imgIndexInterval %= 8;
			
			
			if(sharkInterval == 0 && speed < 7) 
				speed += speedRight;

			sharkInterval++;
			sharkInterval %= 15;
			
			
			// 현재위치의 오른쪽 경계값과 프레임 경계값 사이의 거리가 speed보다 클 때
			if(dx2 + speed > screenWidth)
				x -= dx2 - screenWidth;	// 위치가 밀린만큼 다시 조정해주기(물체가 화면 벗어나는 것 목적좌표 조정)
			else
				x += speed;

		}
		
		if((direction & MOVE_DOWN) == MOVE_DOWN) {
			
			if(sharkInterval == 0 && speed < 7) 
				speed += speedDown;

			sharkInterval++;
			sharkInterval %= 15;			

			// 현재위치의 아래쪽 경계값과 프레임 경계값 사이의 거리가 speed보다 클 때
			if(dy2 + speed > screenHeight)
				y -= dy2 - screenHeight;	// 위치가 밀린만큼 다시 조정해주기(물체가 화면 벗어나는 것 목적좌표 조정)
			else 
				y += speed;
		}
		
//		if((direction & MOVE_LEFT) == MOVE_LEFT && (direction & MOVE_UP) == MOVE_UP) {
//			
//			speedLeft = 0;
//			speedUp = 0;
//			int speedLU = 1;
//			
//			if(sharkInterval == 0 && speed < 10) {
//				speed += speedLU;
//			}
//			
//			sharkInterval++;
//			sharkInterval %= 10;
//
//			x -= speed;
//			y -= speed;
//		
//		
//		}
			
			
		if((direction & MOVE_DOWN) != MOVE_DOWN && (direction & MOVE_RIGHT) != MOVE_RIGHT
				&& (direction & MOVE_UP) != MOVE_UP && (direction & MOVE_LEFT) != MOVE_LEFT) {
			imgIndex = 0;
			speed = 0;
		}
		
		
			
//		System.out.println("speed: " + speed);
	}
	
	public void draw(Graphics g) {
		int x = (int) this.x;	// 실수형 -> 정수형
		int y = (int) this.y;	// 실수형 -> 정수형
		
		GameCanvas observer = GameCanvas.getInstance();
		
		int w = width;
		int h = height;
		
		
		// 출력 위치 좌표
		int dx1 = x-offX;	// 출력 위치 x좌표
		int dy1 = y-offY;	// 출력 위치 y좌표
		int dx2 = dx1+offX;	// 출력 위치 x좌표
		int dy2 = dy1+offY;	// 출력 위치 y좌표
		
		
		// 자를 이미지 위치 좌표
		int sx1 = 0+w*imgIndex;
		int sy1 = 0;
		int sx2 = sx1+w;
		int sy2 = sy1+h;
		
		g.drawImage(img, dx1, dy1, dx2, dy2
				, sx1, sy1, sx2, sy2
				, observer);
	}


	// 상어 위치 x축
	public double getX() {
		
		return this.x;
		
	}

	// 상어 위치 y축
	public double getY() {

		return this.y;
		
	}


	// 상어 크기 키우기
	public void sizeUp() {
		offX += 5;
		offY += 5;
	}


	// 상어 출력하는 가로너비(절반)
	public int getOffX() {
		
		return offX;
		
	}


	// 상어 출력하는 세로너비(절반)
	public int getOffY() {
		
		return offY;
	}



}
