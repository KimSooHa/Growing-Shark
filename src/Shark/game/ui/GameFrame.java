package Shark.game.ui;

import java.awt.Canvas;

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
		
		setVisible(true);	// 화면 출력
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		introCanvas = IntroCanvas.getInstance();
		missionClearCanvas = MissionClearCanvas.getInstance();
		gameOverCanvas = GameOverCanvas.getInstance();
		
		
		add(introCanvas);
		introCanvas.requestFocus();
		
		
		validate();	// 화면 유효성 체크

		
		gameoverSound = new Music("res/audio/mixkit-arcade-retro-game-over-213.wav", false);
		missionClearSound = new Music("res/audio/mixkit-video-game-win-2016.wav", false);
		
	}
	
	public void introToGameCanvas() {
		introCanvas.stop();
		gameCanvas = GameCanvas.getInstance();	//new GameCanvas();	// 부모형식으로 참조하는게 사용하는 범위 내에서 참조하는 것이 좋다
		gameCanvas.start();

		changeCanvas(gameCanvas, introCanvas);

	}

	public void gameOverToGameCanvas() {
		GameCanvas.reset();
		gameCanvas = GameCanvas.getInstance();
		gameCanvas.resetGame();
		gameCanvas.start();

		changeCanvas(gameCanvas, gameOverCanvas);
	}
	
	public void missionClearToGameCanvas() {
		GameCanvas.reset();
		gameCanvas = GameCanvas.getInstance();
		gameCanvas.resetGame();
		gameCanvas.start();

		changeCanvas(gameCanvas, missionClearCanvas);
	}

	public void gameToGameOverCanvas() {
		gameOverCanvas = GameOverCanvas.getInstance();
		gameCanvas.stop();

		changeCanvas(gameOverCanvas, gameCanvas);
		
		gameoverSound.start();
	}
	
	public void gameToMissionClearCanvas() {
		gameCanvas.stop();
		changeCanvas(missionClearCanvas, gameCanvas);
		
		missionClearSound.start();
	}

	// 게임 종료
	public void exit() {
		System.exit(0);
	}
	
	public void changeCanvas(Canvas addCanvas, Canvas removeCanvas) {
		add(addCanvas);
		addCanvas.requestFocus();
		remove(removeCanvas);
		validate();	// performs relayout
	}
}
