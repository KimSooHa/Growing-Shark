package Shark.game.item.fish;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import Shark.game.item.Shark;
import Shark.game.ui.GameCanvas;

public class BossShark implements Fish {

	private String color = "whiteBlue";
	private static Image img;	// ��������(static) -> �ѹ��� ��������� �ٽ� ��������� �ʴ´�
	private int imgIndex;
	private int imgIndexInterval;
	
	private double x;
	private double y;
	private double vx;
	private double vy;
	private double dx;
	private double dy;
	private int speed;
	private int distance;
	
	private int width;
	private int height;
	
	private GameCanvas observer;
	private Random rand;
	private Shark shark;
	
	static {
		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/sharkImages/bigSharkL.png");
	}
	
	public BossShark() {
		rand = new Random();
		observer = GameCanvas.getInstance();
		
		this.x = getPositionX();
		this.y = getPositionY();
		
		
		speed = 1;
		imgIndex = 0;
		imgIndexInterval = 0;
		
		width = 105;
		height = 60;
	}
	
	public String getColor() {
		return color;
	}

	@Override
	public void draw(Graphics g) {
		
		int x = (int) this.x;	// �Ǽ��� -> ������
		int y = (int) this.y;	// �Ǽ��� -> ������
		
		// �̹��� ���� ��ȯ
		if(dx < this.x)
			img = Toolkit.getDefaultToolkit().getImage("res/sharkImages/bigSharkL.png");
		else if(dx > this.x)
			img = Toolkit.getDefaultToolkit().getImage("res/sharkImages/bigSharkR.png");
		
		GameCanvas observer = GameCanvas.getInstance();
		
		int w = width;
		int h = height;
		
		
		int offX = w/2;	// �̹��� ũ���� ��
		int offY = h/2;	// �̹��� ũ���� ��
		
		// ��� ��ġ ��ǥ
		int dx1 = x-offX-30;	// ��� ��ġ x��ǥ
		int dy1 = y-offY-30;	// ��� ��ġ y��ǥ
		int dx2 = dx1+w+30;	// ��� ��ġ x��ǥ
		int dy2 = dy1+h+20;	// ��� ��ġ y��ǥ
		
		
		// �ڸ� �̹��� ��ġ ��ǥ
		int sx1 = 0+w*imgIndex;
		int sy1 = 0;
		int sx2 = sx1+w;
		int sy2 = sy1+h;
		
		g.drawImage(img, dx1, dy1, dx2, dy2
				, sx1, sy1, sx2, sy2
				, observer);
	}

	@Override
	public void update() {
		
		if((dx-speed < x && x < dx+speed) && (dy-speed <y && y < dy+speed)) {	// �������� �����ߴٸ�
			vx = 0;
			vy = 0;			
		}
		
		// ������ �� ���ο� ������ ����
		if(vx == 0 && vy == 0) {
			
			double x = this.x + rand.nextInt(934)+66;
			double y = rand.nextInt(500)+50;
			
			// �ɰ��Ⱑ ���ʿ��� �����Ǿ��� ��
			if(this.x < 0) {
				x = this.x + rand.nextInt(934)+66;
				y = rand.nextInt(500)+50;
			} 	// �����Ⱑ �����ʿ��� �����Ǿ��� ��
			else if(this.x > 1000) {
				x = this.x - rand.nextInt(934)+66;
				y = rand.nextInt(500)+50;
			}
			
			speed = rand.nextInt(5)+1;
			move(x,y);
			
		}	// �̵��ϱ�
		else {
			x += vx*speed;
			y += vy*speed;
		}
		
		
		// �̹��� �ε��� �ٲٱ�
		if(imgIndexInterval == 0) {
			imgIndex++;
			imgIndex %= 4;
		}
		
		imgIndexInterval++;
		imgIndexInterval %= 20;
		
	}
	
	@Override
	public void move(double x, double y) {
		
		dx = x;
		dy = y;
		
		double w = dx-this.x;
		double h = dy-this.y;
		
		double d = Math.sqrt(w*w + h*h);	// ������ �Ÿ�(�Ÿ�����)
		
		vx = w/d;
		vy = h/d;

	}


	@Override
	public int getPositionX() {
		
		int position = rand.nextInt(2);
		int x;
		
		// 0~1000 ���� �ۿ� ���� ���� ������ ��ġ ���
		if(position == 0)
			x = rand.nextInt(21)-20;
		else
			x = rand.nextInt(100)+1000;
		
		return x;
	}


	@Override
	public int getPositionY() {
		int y;
		
		y = rand.nextInt(601);

		return y;
	}


	// ���� �浹
	@Override
	public boolean isbite() {
		int sharkR;
		int fishR;
		
		getDistance();
		
		sharkR = shark.getOffX()/2;
		fishR = this.width / 4;
		
		
		return (sharkR + fishR > distance);
	}

	// ������ �Ÿ� ���
	@Override
	public int getDistance() {
		double sharkX;
		double sharkY;
		
		shark = observer.getShark();
		
		sharkX = shark.getX();
		sharkY = shark.getY();
		
		
		double w = (this.x - sharkX);
		double h = (this.y - sharkY);
		
		distance = (int) Math.sqrt(w*w + h*h);
		
		return distance;
		
	}
	
}