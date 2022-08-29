package Shark.game.ui;

import javax.swing.JFrame;

import Shark.game.item.Music;


public class GameFrame extends JFrame {
	
	private IntroCanvas introCanvas;
	private GameCanvas gameCanvas;
	private GameOverCanvas gameOverCanvas;
	private MissionClearCanvas missionClearCanvas;
	
	
	private Music gameoverSound;
	private Music missionClearSound;
	
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 750;
	
	private static GameFrame instance;
	
	public static GameFrame getInstance() {
		if(instance == null)
			instance = new GameFrame();
		
		return instance;
	}
	
	public GameFrame() {
		
		setVisible(true);
		setSize(1000, 750);
		setResizable(false);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		introCanvas = IntroCanvas.getInstance();
		gameOverCanvas = GameOverCanvas.getInstance();
		missionClearCanvas = MissionClearCanvas.getInstance();
		
		gameoverSound = new Music("res/audio/mixkit-arcade-retro-game-over-213.wav", false);
		missionClearSound = new Music("res/audio/mixkit-video-game-win-2016.wav", false);
		
		add(introCanvas);
		introCanvas.requestFocus();
		
		
		validate();
//			win.pack();	// 부모의 크기를 자식크기에 맞춰서 위임
		
	}
	
	public void introToGameCanvas() {
		introCanvas.stop();
		gameCanvas = GameCanvas.getInstance();	//new GameCanvas();	// 부모형식으로 참조하는게 사용하는 범위 내에서 참조하는 것이 좋다
		gameCanvas.start();
		add(gameCanvas);
		gameCanvas.requestFocus();
		remove(introCanvas);
//		revalidate();
		validate();
	}

	public void gameOverToGameCanvas() {
		GameCanvas.reset();
		gameCanvas = GameCanvas.getInstance();
		gameCanvas.resetGame();
		gameCanvas.start();
		gameOverCanvas.stop();
		add(gameCanvas);
		gameCanvas.requestFocus();
		remove(gameOverCanvas);
		validate();
	}
	
	public void missionClearToGameCanvas() {
		GameCanvas.reset();
		gameCanvas = GameCanvas.getInstance();
		gameCanvas.resetGame();
		gameCanvas.start();
		missionClearCanvas.stop();
		add(gameCanvas);
		gameCanvas.requestFocus();
		remove(missionClearCanvas);
		validate();
	}

	public void gameToGameOverCanvas() {
		GameOverCanvas.reset();
		gameOverCanvas = GameOverCanvas.getInstance();
		gameCanvas.stop();
		add(gameOverCanvas);
		gameOverCanvas.requestFocus();
		remove(gameCanvas);
		validate();
		
		gameoverSound.start();
	}
	
	public void gameToMissionClearCanvas() {
		gameCanvas.stop();
		add(missionClearCanvas);
		missionClearCanvas.requestFocus();
		remove(gameCanvas);
		validate();
		
		missionClearSound.start();
	}

	
	public void exit() {
		System.exit(0);
	}
}
