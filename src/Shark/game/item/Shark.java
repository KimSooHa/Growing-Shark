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
	
	// ��� ����
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
		offX = width/2 -5;	// ��� �̹��� ũ���� ��(70)
		offY = height/2 -5;	// ��� �̹��� ũ���� ��(70)

		
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
			
			// �̹��� �ε��� �ٲٱ�
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
			// ������ġ�� ���� ��谪�� ������ ��谪 ������ �Ÿ��� speed���� ���� ��
			if(dx1 < speed)
				x += dx1;	// ��ġ�� �и���ŭ �ٽ� �������ֱ�(��ü�� ȭ�� ����� �� ������ǥ ����)
			else 
				x -= speed;
		}
		
		
		if((direction & MOVE_UP) == MOVE_UP) {
			
			if(sharkInterval == 0 && speed < 7) 
				speed += speedUp;
			
			sharkInterval++;
			sharkInterval %= 15;

			// ������ġ�� ���� ��谪�� ������ ��谪 ������ �Ÿ��� speed���� ���� ��
			if(dy1 < speed)
				y += dy1;	// ��ġ�� �и���ŭ �ٽ� �������ֱ�(��ü�� ȭ�� ����� �� ������ǥ ����)
			else
				y -= speed;
		}
		
		if((direction & MOVE_RIGHT) == MOVE_RIGHT) {
			img = Toolkit.getDefaultToolkit().getImage("res/sharkImages/sharkSprite-R.png");
			
			// �̹��� �ε��� �ٲٱ�
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
			
			
			// ������ġ�� ������ ��谪�� ������ ��谪 ������ �Ÿ��� speed���� Ŭ ��
			if(dx2 + speed > screenWidth)
				x -= dx2 - screenWidth;	// ��ġ�� �и���ŭ �ٽ� �������ֱ�(��ü�� ȭ�� ����� �� ������ǥ ����)
			else
				x += speed;

		}
		
		if((direction & MOVE_DOWN) == MOVE_DOWN) {
			
			if(sharkInterval == 0 && speed < 7) 
				speed += speedDown;

			sharkInterval++;
			sharkInterval %= 15;			

			// ������ġ�� �Ʒ��� ��谪�� ������ ��谪 ������ �Ÿ��� speed���� Ŭ ��
			if(dy2 + speed > screenHeight)
				y -= dy2 - screenHeight;	// ��ġ�� �и���ŭ �ٽ� �������ֱ�(��ü�� ȭ�� ����� �� ������ǥ ����)
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
		int x = (int) this.x;	// �Ǽ��� -> ������
		int y = (int) this.y;	// �Ǽ��� -> ������
		
		GameCanvas observer = GameCanvas.getInstance();
		
		int w = width;
		int h = height;
		
		
		// ��� ��ġ ��ǥ
		int dx1 = x-offX;	// ��� ��ġ x��ǥ
		int dy1 = y-offY;	// ��� ��ġ y��ǥ
		int dx2 = dx1+offX;	// ��� ��ġ x��ǥ
		int dy2 = dy1+offY;	// ��� ��ġ y��ǥ
		
		
		// �ڸ� �̹��� ��ġ ��ǥ
		int sx1 = 0+w*imgIndex;
		int sy1 = 0;
		int sx2 = sx1+w;
		int sy2 = sy1+h;
		
		g.drawImage(img, dx1, dy1, dx2, dy2
				, sx1, sy1, sx2, sy2
				, observer);
	}


	// ��� ��ġ x��
	public double getX() {
		
		return this.x;
		
	}

	// ��� ��ġ y��
	public double getY() {

		return this.y;
		
	}


	// ��� ũ�� Ű���
	public void sizeUp() {
		offX += 5;
		offY += 5;
	}


	// ��� ����ϴ� ���γʺ�(����)
	public int getOffX() {
		
		return offX;
		
	}


	// ��� ����ϴ� ���γʺ�(����)
	public int getOffY() {
		
		return offY;
	}



}
