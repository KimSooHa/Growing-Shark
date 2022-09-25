package Shark.game.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import Shark.game.item.Title;
import Shark.game.item.background.IntroBackground;
import Shark.game.item.button.StartButton;

public class IntroCanvas extends Canvas implements Runnable {
	
	private IntroBackground introBackground;
	private Title title;
	private StartButton startButton;
	
	private boolean running;

	private static IntroCanvas instance;
	
	public static IntroCanvas getInstance() {
		if(instance == null)
			instance = new IntroCanvas();
		
		return instance;
	}
	
	public IntroCanvas() {
		introBackground = new IntroBackground();
		title = new Title();
		startButton = new StartButton(400, 400);
		
		running = true;
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				if(startButton.isPointIn(x, y)) {
					startButton.clicked(true);
					
				}
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				//System.out.printf("x: %d, y: %d", x, y);
				
				if(startButton.isPointIn(x, y)) {
					
					startButton.entered(true);
					return;
				}
				else
					startButton.entered(false);
			}
		});
		
		Thread introThread = new Thread(this);
		introThread.start();
		
//		repaint();
	}
	
	@Override
	public void update(Graphics g) {
//		super.update(g);
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		// 이미지 생성(메모리상의 도화지)
		Image buf = createImage(getWidth(), getHeight());	// 캔버스와 동일한 크기의 도화지를 생성
		
		// 이미지를 그리는 도구
		Graphics g2 = buf.getGraphics();
		
		introBackground.draw(g2);
		title.draw(g2);
		startButton.draw(g2);
		
		
		// 최종 결과물인 이미지를 화면에 붙이기.
		g.drawImage(buf, 0, 0, this);	// 그린 이미지를 canvas에 붙이기
	}

	@Override
	public void run() {
		while(running) {
			
			
			repaint();
			
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void stop() {
		running = false;
		
	}
}
