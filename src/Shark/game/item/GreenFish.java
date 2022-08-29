package Shark.game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import Shark.game.ui.GameCanvas;

public class GreenFish implements Fish {
	
	private String color = "green";
	private static Image img;	// 전역변수(static) -> 한번만 만들어지고 다시 만들어지지 않는다
	private int imgIndex;	// 0->1->2->....11->0->1
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
				.getImage("res/sharkImages/greenFish2.png");
	}
	
	public GreenFish() {
		observer = GameCanvas.getInstance();
		rand = new Random();
		
		this.x = getPositionX();
		this.y = getPositionY();
		
		
		speed = 1;
		imgIndex = 0;
		imgIndexInterval = 0;
		
		width = 66;
		height = 60;
	}
	
	public String getColor() {
		return color;
	}


	@Override
	public void draw(Graphics g) {
		
		int x = (int) this.x;	// 실수형 -> 정수형
		int y = (int) this.y;	// 실수형 -> 정수형
		
		// 이미지 방향 전환
		if(dx < this.x)
			img = Toolkit.getDefaultToolkit().getImage("res/sharkImages/greenFish1.png");
		else if(dx > this.x)
			img = Toolkit.getDefaultToolkit().getImage("res/sharkImages/greenFish2.png");
		
		
		
		int w = width;
		int h = height;
		
		
		int offX = w/2;	// 전투기 이미지 크기의 반(32)
		int offY = h/2;	// 전투기 이미지 크기의 반(32)
		
		// 출력 위치 좌표
		int dx1 = x-offX-15;	// 출력 위치 x좌표
		int dy1 = y-offY-15;	// 출력 위치 y좌표
		int dx2 = dx1+w+15;	// 출력 위치 x좌표
		int dy2 = dy1+h+15;	// 출력 위치 y좌표
		
		
		// 자를 이미지 위치 좌표
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
		
		if((dx-speed < x && x < dx+speed) && (dy-speed <y && y < dy+speed)) {	// 목적지에 도달했다면
			vx = 0;
			vy = 0;			
		}
		
//		// 멈췄을 때 새로운 목적지 지정
		if(vx == 0 && vy == 0) {
			
			double x = this.x + rand.nextInt(934)+66;
			double y = rand.nextInt(550)+50;
			
			// 믈고기가 왼쪽에서 생성되었을 때
			if(this.x < 0) {
				x = this.x + rand.nextInt(934)+66;
				y = rand.nextInt(550)+50;
			} 	// 물고기가 오른쪽에서 생성되었을 때
			else if(this.x > 1000) {
				x = this.x - rand.nextInt(934)+66;
				y = rand.nextInt(550)+50;
			}
			
			speed = rand.nextInt(5)+1;
			move(x,y);
			
		}	// 이동하기
		else {
			x += vx*speed;
			y += vy*speed;
		}
		
		
		// 이미지 인덱스 바꾸기
		if(imgIndexInterval == 0) {
			imgIndex++;
			imgIndex %= 6;
		}
		
		imgIndexInterval++;
		imgIndexInterval %= 3;
		
	}
	
	public void move(double x, double y) {
		
		dx = x;
		dy = y;
		
		double w = dx-this.x;
		double h = dy-this.y;
		
		double d = Math.sqrt(w*w + h*h);	// 일정한 거리(거리공식)
		
		vx = w/d;
		vy = h/d;

	}


	@Override
	public int getPositionX() {
		
		int position = rand.nextInt(2);
		int x;
		
		// 0~1000 영역 밖에 있을 때로 물고기 위치 잡기
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


	@Override
	public boolean isbite() {
		int sharkR;
		int fishR;
		
		getDistance();
		
		
		sharkR = shark.getOffX()/2;
		fishR = this.width / 3;
		
		
		return (sharkR + fishR > distance);
	}


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
