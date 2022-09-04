package Shark.game.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import Shark.game.item.ExitButton;
import Shark.game.item.IntroBackground;
import Shark.game.item.MissionClear;
import Shark.game.item.ReplayButton;

public class MissionClearCanvas extends Canvas implements Runnable {
	

	private MissionClear missionClear; 
	
	private ReplayButton replayButton;
	private ExitButton exitButton;
	private IntroBackground introBackground;
	
	private boolean running;

	private static MissionClearCanvas instance;
	
	public static MissionClearCanvas getInstance() {
		if(instance == null)
			instance = new MissionClearCanvas();
		
		return instance;
	}
	
	public MissionClearCanvas() {
		introBackground = new IntroBackground();
		missionClear = new MissionClear();
		replayButton = new ReplayButton(310, 390);
		exitButton = new ExitButton(530, 400);
		
		running = true;
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				if(replayButton.isPointIn(x, y)) {
					replayButton.isClicked(true);
				} else if(exitButton.isPointIn(x, y)) {
					exitButton.clicked(true);
				}
			}
			
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				System.out.printf("x: %d, y: %d", x, y);
				
				if(replayButton.isPointIn(x, y)) {
					
					replayButton.entered(true);
					return;
				}
				else
					replayButton.entered(false);
				
				if(exitButton.isPointIn(x, y)) {
					
					exitButton.entered(true);
					return;
				}
				else
					exitButton.entered(false);
			}
		});
		
		Thread overThread = new Thread(this);
		overThread.start();
		
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
		missionClear.draw(g2);
		replayButton.draw(g2);
		exitButton.draw(g2);
		
		
		// 최종 결과물인 이미지를 화면에 붙이자.
		g.drawImage(buf, 0, 0, this);	// 그린 이미지를 canvas에 붙이기
	}

	@Override
	public void run() {
		while(running) {
			
			
			repaint();
			
			try {
				Thread.sleep(17);
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
