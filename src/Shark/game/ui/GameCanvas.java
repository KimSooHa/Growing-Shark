package Shark.game.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import Shark.game.item.Level;
import Shark.game.item.Life;
import Shark.game.item.Mission;
import Shark.game.item.Music;
import Shark.game.item.Shark;
import Shark.game.item.YellowFish;
import Shark.game.item.background.Background;
import Shark.game.item.background.BottomBackground;
import Shark.game.item.fish.BlueFish;
import Shark.game.item.fish.BossShark;
import Shark.game.item.fish.Fish;
import Shark.game.item.fish.GreenFish;
import Shark.game.item.fish.PurpleFish;


public class GameCanvas extends Canvas implements Runnable {

	private Shark shark;
	private Background background;
	private BlueFish blueFish;
	private YellowFish yellowFish;
	private GreenFish greenFish;
	private PurpleFish purpleFish;
	private BossShark bossShark;
	private Level level;
	private Mission mission;
	private Life life;
	
	private BottomBackground bottomBg;
	private GameFrame gameFrame;
	private Music backgroundMusic;
	private Music bubbleSound;
	
	
	
	
	private int direction;
	private Fish[] fishes;
	private int fishIndex;
	private int blueFishIndex;
	private int yellowFishIndex;
	private int greenFishIndex;
	private int purpleFishIndex;
	private int fishesMax;
	private int fishInterval;
	private Random rand;
	private boolean running;
	
	
	private static GameCanvas instance;
		
		public static GameCanvas getInstance() {
			if(instance == null)
				instance = new GameCanvas();
			
			return instance;
		}
		
		public static void reset() {
			instance = null;
		}
		
		public GameCanvas() {
			
			shark = new Shark(400, 300);
			background = new Background();
			level = Level.getInstance();
			mission = new Mission();
			life = new Life();
			backgroundMusic = new Music("res/audio/backgroundMusic.wav", true);	// boolean: isLoop
			backgroundMusic.start();
			
			
			bottomBg = new BottomBackground();
			fishIndex = 0;
			fishesMax = 40;
			fishes = new Fish[fishesMax];
			blueFishIndex = 0;
			yellowFishIndex = 0;
			greenFishIndex = 0;
			purpleFishIndex = 0;
			
			direction = 0;
			fishInterval = 0;
			rand = new Random();
			running = false;
			
			
			// 키 이벤트
			this.addKeyListener(new KeyAdapter() {
				
				// 키 눌렀을 때
				@Override
				public void keyPressed(KeyEvent e) {
					switch(e.getKeyCode()) {
					// 왼쪽키
					case KeyEvent.VK_LEFT:
						direction |= Shark.MOVE_LEFT;
						break;
					// 위쪽 키
					case KeyEvent.VK_UP:
						direction |= Shark.MOVE_UP;
						break;
					// 오른쪽 키
					case KeyEvent.VK_RIGHT:
						direction |= Shark.MOVE_RIGHT;
						break;
					// 아래쪽 키
					case KeyEvent.VK_DOWN:
						direction |= Shark.MOVE_DOWN;
						break;
					}
					
					shark.moveBy(direction);
				}
				
				// 키 뗐을 때
				@Override
				public void keyReleased(KeyEvent e) {

					switch(e.getKeyCode()) {
					
					case KeyEvent.VK_LEFT:
						direction &= ~Shark.MOVE_LEFT;
						break;
					case KeyEvent.VK_UP:
						direction &= ~Shark.MOVE_UP;
						break;
					case KeyEvent.VK_RIGHT:
						direction &= ~Shark.MOVE_RIGHT;
						break;
					case KeyEvent.VK_DOWN:
						direction &= ~Shark.MOVE_DOWN;
						break;
					}
					
					shark.moveBy(direction);
				}
				
			});
			
			
			// 스레드 생성
			Thread gameThread = new Thread(this);
			gameThread.start();
			
		}
		

	public Shark getShark() {
		return this.shark;
	}
	
	public int getBlueFishIndex() {
				
		return blueFishIndex;
	}
	
	public int getYellowFishIndex() {
		
		return yellowFishIndex;
	}
	
	public int getGreenFishIndex() {

		return greenFishIndex;
	}
	
	public int getPurpleFishIndex() {

		return purpleFishIndex;
	}
		
		
	// 오버라이드를 통해 업데이트(지우는)하는 것을 없애고 paint하기
	@Override
	public void update(Graphics g) {
//		super.update(g);
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		
		// 더블 버퍼링
		// 이미지 생성(메모리상의 도화지)
		Image buf = createImage(getWidth(), getHeight());
		
		// 이미지를 그리는 도구
		Graphics g2 = buf.getGraphics();
		
		
		// canvas에 이미지 그리기
		background.draw(g2);
		bottomBg.draw(g2);
		level.draw(g2);
		mission.draw(g2);
		life.draw(g2);
		shark.draw(g2);
		
		synchronized (fishes) {	// 동기화
			for(int i = 0; i < fishIndex; i++)
				fishes[i].draw(g2);	
		}
		
		
		// 최종 결과물인 이미지를 화면에 붙이기
		g.drawImage(buf, 0, 0, this);
		
	}
	
	
	@Override
	public void run() {
		while(running) {
			
			shark.update();
			life.update();
			mission.update();
			level.update();
			
			
			//========== items 배열의 공간 늘리기===============
			synchronized (fishes) {
				if(fishIndex > fishesMax-10) {	// 현재 아이템 배열의 인덱스가 넘으면 더 큰 배열 생성해서 옮기기
					
					Fish[] temp = new Fish[fishesMax+40];	// 40개 더 넓은 배열 생성
					
					for(int i = 0; i < fishIndex; i++)
						temp[i] = fishes[i];	// 생성한 더 큰 배열로 옮기기
					
					fishes = temp;	// 큰 배열을 다시 아이템 배열에 대입하기
					fishesMax += 40;	// 늘린 배열만큼 인덱스최댓값 늘리기
				}
			}
			
			// =======items 상태 업데이트============================
			synchronized (fishes) {
				for(int i = 0; i <fishIndex; i++)
					fishes[i].update();
			}
			
			
			// =======물고기 생성 ============================
			synchronized (fishes) {
				
				// 물고기가 화면 영역 밖에 있을 때 생성			
				if(fishInterval == 0) {
					blueFish = new BlueFish();
					fishes[fishIndex++] = blueFish;

					yellowFish = new YellowFish();
					fishes[fishIndex++] = yellowFish;
					
					// 미션2 완료하면 초록 물고기 생성시키기
					if(level.getLevel() >= 2) {
						greenFish = new GreenFish();
						fishes[fishIndex++] = greenFish;
					}
					
					if(level.getLevel() >= 4) {
						purpleFish = new PurpleFish();
						fishes[fishIndex++] = purpleFish;
					}
					
					if(level.getLevel() >= 6) {
						bossShark = new BossShark();
						fishes[fishIndex++] = bossShark;
					}
				}
				
				fishInterval++;
				
				// 레벨에 따라 물고기 생성하는 간격 조절하기
				if(level.getLevel() >= 4)
					fishInterval %= rand.nextInt(120)+50;	// 생성하는 간격
				else if(level.getLevel() >= 6)
					fishInterval %= rand.nextInt(160)+70;	// 생성하는 간격
				else
					fishInterval %= rand.nextInt(100)+20;	// 생성하는 간격
				
			}
			
			
			
			//============ 물고기 충돌할 때 =================
			synchronized (fishes) {
				for(int i = 0; i <fishIndex; i++) {
					if(fishes[i].isbite()) {
						
						
						// 먹은 물고기가 노란물고기면 인덱스 늘리기
						if(fishes[i].getColor().equals("yellow")) {
							
							// 레벨 1일때 닿으면
							if(level.getLevel() == 1) {
								life.lifeDown();	// 라이프 줄이기
								
								
							} else {
								yellowFishIndex++;
								life.lifeUp();
								biteFish(i);
							}

							
						} else if(fishes[i].getColor().equals("green")) {
							
							if(level.getLevel() == 2 || level.getLevel() == 3) {	// 레벨 2, 3일때 닿으면
								life.lifeDown();	// 라이프 줄이기
								
							} else {
								// 먹은 물고기가 초록물고기면 인덱스 늘리기
								greenFishIndex++;
								life.lifeUp();
								biteFish(i);
							}
							
						} else if(fishes[i].getColor().equals("purple")) {
							
							if(level.getLevel() == 4 || level.getLevel() == 5) {	// 레벨 4,5일때 닿으면
								life.lifeDown();	// 라이프 줄이기
								
							} else {
								// 먹은 물고기가 보라물고기면 인덱스 늘리기
								purpleFishIndex++;
								life.lifeUp();
								biteFish(i);
							}
							
						} else if(fishes[i].getColor().equals("blue")) {
							// 먹은 물고기가 파란물고기면 인덱스 늘리기
							blueFishIndex++;
							life.lifeUp();
							biteFish(i);
							
						} else if(fishes[i].getColor().equals("whiteBlue")) {	// 보스상어랑 닿으면
							life.lifeDown();
						}
						
						
					}
				}
				
				
			}
			
			
			
			synchronized (fishes) {
				
				// 레벨 i일 때 미션 i 완수하면 레벨 올리기
				for(int i = 0; i < 8; i++) {
					if(level.getLevel() == i && mission.missionComplete(i, blueFishIndex, yellowFishIndex, greenFishIndex, purpleFishIndex)) {
						if(1 <= i && i <= 6) {	// 레벨 1~6일때 미션 완수하면
							
							shark.sizeUp(); // 상어 크기 키우기
							this.resetFishIndex(blueFishIndex, yellowFishIndex, greenFishIndex, purpleFishIndex);	// 물고기 인덱스 리셋
							level.levelUp();	// 레벨 올리기
						
						} else if(i == 7) {	// 레벨 7(마지막 레벨)일때 미션 완수하면
							gameFrame = GameFrame.getInstance();
							gameFrame.gameToMissionClearCanvas();	// 미션완료 캔버스로 전환
						}
					}
				}
				
				
			}
			
			
			repaint();	// 초당 60번 다시 그리기
			
			try {	// 예외처리
				Thread.sleep(17);	// 초당 60번(60fps)의 속도로 계속 돌아감
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	// 물고기 먹을 때
	public void biteFish(int i) {
		bubbleSound = new Music("res/audio/mixkit-liquid-bubble-3000.wav", false);
		bubbleSound.start();
		
		// 먹힌 물고기 인덱스 없애고 전체 인덱스 줄이기
		for(int j = 0; j < fishIndex-i-1; j++)
			fishes[i+j] = fishes[i+1+j];

		fishIndex--;
			
	}
	
	// 물고기 인덱스 초기화
	private void resetFishIndex(int blueFishIndex, int yellowFishIndex, int greenFishIndex, int purpleFishIndex) {
		
		blueFishIndex = 0;
		yellowFishIndex = 0;
		greenFishIndex = 0;
		purpleFishIndex = 0;
		
		this.blueFishIndex = blueFishIndex;
		this.yellowFishIndex = yellowFishIndex;
		this.greenFishIndex = greenFishIndex;
		this.purpleFishIndex = purpleFishIndex;
				
	}

	public void stop() {
		running = false;
		backgroundMusic.stop();
		
	}

	public void start() {
		running = true;
	}
	
	public void resetGame() {
		resetFishIndex(blueFishIndex, yellowFishIndex, greenFishIndex, purpleFishIndex);
		level.resetLevel();
	}

	

}
