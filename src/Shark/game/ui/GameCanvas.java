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
			
			
			// Ű �̺�Ʈ
			this.addKeyListener(new KeyAdapter() {
				
				// Ű ������ ��
				@Override
				public void keyPressed(KeyEvent e) {
					switch(e.getKeyCode()) {
					// ����Ű
					case KeyEvent.VK_LEFT:
						direction |= Shark.MOVE_LEFT;
						break;
					// ���� Ű
					case KeyEvent.VK_UP:
						direction |= Shark.MOVE_UP;
						break;
					// ������ Ű
					case KeyEvent.VK_RIGHT:
						direction |= Shark.MOVE_RIGHT;
						break;
					// �Ʒ��� Ű
					case KeyEvent.VK_DOWN:
						direction |= Shark.MOVE_DOWN;
						break;
					}
					
					shark.moveBy(direction);
				}
				
				// Ű ���� ��
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
			
			
			// ������ ����
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
		
		
	// �������̵带 ���� ������Ʈ(�����)�ϴ� ���� ���ְ� paint�ϱ�
	@Override
	public void update(Graphics g) {
//		super.update(g);
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		
		// ���� ���۸�
		// �̹��� ����(�޸𸮻��� ��ȭ��)
		Image buf = createImage(getWidth(), getHeight());
		
		// �̹����� �׸��� ����
		Graphics g2 = buf.getGraphics();
		
		
		// canvas�� �̹��� �׸���
		background.draw(g2);
		bottomBg.draw(g2);
		level.draw(g2);
		mission.draw(g2);
		life.draw(g2);
		shark.draw(g2);
		
		synchronized (fishes) {	// ����ȭ
			for(int i = 0; i < fishIndex; i++)
				fishes[i].draw(g2);	
		}
		
		
		// ���� ������� �̹����� ȭ�鿡 ���̱�
		g.drawImage(buf, 0, 0, this);
		
	}
	
	
	@Override
	public void run() {
		while(running) {
			
			shark.update();
			life.update();
			mission.update();
			level.update();
			
			
			//========== items �迭�� ���� �ø���===============
			synchronized (fishes) {
				if(fishIndex > fishesMax-10) {	// ���� ������ �迭�� �ε����� ������ �� ū �迭 �����ؼ� �ű��
					
					Fish[] temp = new Fish[fishesMax+40];	// 40�� �� ���� �迭 ����
					
					for(int i = 0; i < fishIndex; i++)
						temp[i] = fishes[i];	// ������ �� ū �迭�� �ű��
					
					fishes = temp;	// ū �迭�� �ٽ� ������ �迭�� �����ϱ�
					fishesMax += 40;	// �ø� �迭��ŭ �ε����ִ� �ø���
				}
			}
			
			// =======items ���� ������Ʈ============================
			synchronized (fishes) {
				for(int i = 0; i <fishIndex; i++)
					fishes[i].update();
			}
			
			
			// =======����� ���� ============================
			synchronized (fishes) {
				
				// ����Ⱑ ȭ�� ���� �ۿ� ���� �� ����			
				if(fishInterval == 0) {
					blueFish = new BlueFish();
					fishes[fishIndex++] = blueFish;

					yellowFish = new YellowFish();
					fishes[fishIndex++] = yellowFish;
					
					// �̼�2 �Ϸ��ϸ� �ʷ� ����� ������Ű��
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
				
				// ������ ���� ����� �����ϴ� ���� �����ϱ�
				if(level.getLevel() >= 4)
					fishInterval %= rand.nextInt(120)+50;	// �����ϴ� ����
				else if(level.getLevel() >= 6)
					fishInterval %= rand.nextInt(160)+70;	// �����ϴ� ����
				else
					fishInterval %= rand.nextInt(100)+20;	// �����ϴ� ����
				
			}
			
			
			
			//============ ����� �浹�� �� =================
			synchronized (fishes) {
				for(int i = 0; i <fishIndex; i++) {
					if(fishes[i].isbite()) {
						
						
						// ���� ����Ⱑ ��������� �ε��� �ø���
						if(fishes[i].getColor().equals("yellow")) {
							
							// ���� 1�϶� ������
							if(level.getLevel() == 1) {
								life.lifeDown();	// ������ ���̱�
								
								
							} else {
								yellowFishIndex++;
								life.lifeUp();
								biteFish(i);
							}

							
						} else if(fishes[i].getColor().equals("green")) {
							
							if(level.getLevel() == 2 || level.getLevel() == 3) {	// ���� 2, 3�϶� ������
								life.lifeDown();	// ������ ���̱�
								
							} else {
								// ���� ����Ⱑ �ʷϹ����� �ε��� �ø���
								greenFishIndex++;
								life.lifeUp();
								biteFish(i);
							}
							
						} else if(fishes[i].getColor().equals("purple")) {
							
							if(level.getLevel() == 4 || level.getLevel() == 5) {	// ���� 4,5�϶� ������
								life.lifeDown();	// ������ ���̱�
								
							} else {
								// ���� ����Ⱑ ���󹰰��� �ε��� �ø���
								purpleFishIndex++;
								life.lifeUp();
								biteFish(i);
							}
							
						} else if(fishes[i].getColor().equals("blue")) {
							// ���� ����Ⱑ �Ķ������� �ε��� �ø���
							blueFishIndex++;
							life.lifeUp();
							biteFish(i);
							
						} else if(fishes[i].getColor().equals("whiteBlue")) {	// �������� ������
							life.lifeDown();
						}
						
						
					}
				}
				
				
			}
			
			
			
			synchronized (fishes) {
				
				// ���� i�� �� �̼� i �ϼ��ϸ� ���� �ø���
				for(int i = 0; i < 8; i++) {
					if(level.getLevel() == i && mission.missionComplete(i, blueFishIndex, yellowFishIndex, greenFishIndex, purpleFishIndex)) {
						if(1 <= i && i <= 6) {	// ���� 1~6�϶� �̼� �ϼ��ϸ�
							
							shark.sizeUp(); // ��� ũ�� Ű���
							this.resetFishIndex(blueFishIndex, yellowFishIndex, greenFishIndex, purpleFishIndex);	// ����� �ε��� ����
							level.levelUp();	// ���� �ø���
						
						} else if(i == 7) {	// ���� 7(������ ����)�϶� �̼� �ϼ��ϸ�
							gameFrame = GameFrame.getInstance();
							gameFrame.gameToMissionClearCanvas();	// �̼ǿϷ� ĵ������ ��ȯ
						}
					}
				}
				
				
			}
			
			
			repaint();	// �ʴ� 60�� �ٽ� �׸���
			
			try {	// ����ó��
				Thread.sleep(17);	// �ʴ� 60��(60fps)�� �ӵ��� ��� ���ư�
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	// ����� ���� ��
	public void biteFish(int i) {
		bubbleSound = new Music("res/audio/mixkit-liquid-bubble-3000.wav", false);
		bubbleSound.start();
		
		// ���� ����� �ε��� ���ְ� ��ü �ε��� ���̱�
		for(int j = 0; j < fishIndex-i-1; j++)
			fishes[i+j] = fishes[i+1+j];

		fishIndex--;
			
	}
	
	// ����� �ε��� �ʱ�ȭ
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
