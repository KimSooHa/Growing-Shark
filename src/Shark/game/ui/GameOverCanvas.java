package Shark.game.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import Shark.game.item.ExitButton;
import Shark.game.item.GameOver;
import Shark.game.item.IntroBackground;
import Shark.game.item.Music;
import Shark.game.item.ReplayButton;

public class GameOverCanvas extends Canvas implements Runnable {
	

	private GameOver gameOver;
	
	private ReplayButton replayButton;
	private ExitButton exitButton;
	private IntroBackground introBackground;
	
	
	
	private boolean running;

	private static GameOverCanvas instance;
	
	public static GameOverCanvas getInstance() {
		if(instance == null)
			instance = new GameOverCanvas();
		
		return instance;
	}
	
	public static void reset() {
		instance = null;
	}
	
	public GameOverCanvas() {
		introBackground = new IntroBackground();
		gameOver = new GameOver();
		replayButton = new ReplayButton(310, 390);
		exitButton = new ExitButton(530, 400);
		
		running = true;
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				if(replayButton.isPointIn(x, y)) {
					replayButton.clicked(true);
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
		// �̹��� ����(�޸𸮻��� ��ȭ��)
		Image buf = createImage(getWidth(), getHeight());	// ĵ������ ������ ũ���� ��ȭ���� ����
		
		// �̹����� �׸��� ����
		Graphics g2 = buf.getGraphics();
		
		introBackground.draw(g2);
		gameOver.draw(g2);
		replayButton.draw(g2);
		exitButton.draw(g2);
		
		
		// ���� ������� �̹����� ȭ�鿡 ������.
		g.drawImage(buf, 0, 0, this);	// �׸� �̹����� canvas�� ���̱�
	}

	@Override
	public void run() {
		while(running) {
			
			replayButton.update();
			exitButton.update();
			
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
