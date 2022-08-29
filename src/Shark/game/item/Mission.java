package Shark.game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Shark.game.ui.GameCanvas;

public class Mission {
	private Image blueNumImg;
	private Image yellowNumImg;
	private Image greenNumImg;
	private Image purpleNumImg;
	
	private Image blueFishImg;
	private Image yellowFishImg;
	private Image greenFishImg;
	private Image purpleFishImg;
	private Image bossSharkImg;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private int blueMission;
	private int yellowMission;
	private int greenMission;
	private int purpleMission;
	
	private int blueFish;
	private int yellowFish;
	private int greenFish;
	private int purpleFish;
	
	private GameCanvas observer;
	private Level level;
	
	private int blueNumIndex;
	private int yellowNumIndex;
	private int greenNumIndex;
	private int purpleNumIndex;
	
	
	
	public Mission() {

		blueNumImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/number-bg-remove-1.png");
		yellowNumImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/number-bg-remove-1.png");
		greenNumImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/number-bg-remove-1.png");
		purpleNumImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/number-bg-remove-1.png");
		
		blueFishImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/mission-bluefish.png");
		yellowFishImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/mission-yellowfish.png");
		greenFishImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/mission-greenfish.png");
		purpleFishImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/mission-purplefish.png");
		bossSharkImg = Toolkit.getDefaultToolkit().getImage("res/sharkImages/mission-bigshark.png");
		
		x = 280;
		y = 665;
		
		width = 54;
		height = 80;
		
		blueNumIndex = 0;
		yellowNumIndex = 0;
		greenNumIndex = 0;
		purpleNumIndex = 0;
		
		blueMission = 0;
		yellowMission = 0;
		greenMission = 0;
		purpleMission = 0;
		
	}
	
	public void draw(Graphics g) {
		GameCanvas observer = GameCanvas.getInstance();
		level = Level.getInstance();
		
		int w = width;
		int h = height;
		
		int x = this.x;
		int y = this.y;
		
		
		// 출력 위치 좌표
		int dx1 = x;	// 출력 위치 x좌표
		int dy1 = y;	// 출력 위치 y좌표
		int dx2 = dx1+w-30;	// 출력 위치 x좌표
		int dy2 = dy1+h-40;	// 출력 위치 y좌표
		
		
		// 자를 이미지 위치 좌표
		
		// 파란 물고기 인덱스 이미지
		int blueSx1 = 0+w*blueNumIndex;
		int sy1 = 0;
		int blueSx2 = blueSx1+w;
		int sy2 = sy1+h;
		
		g.drawImage(blueNumImg, dx1, dy1, dx2, dy2, blueSx1, sy1, blueSx2, sy2, observer);
		g.drawImage(blueFishImg, x-30, y-65, x-30+75, y-65+60, 0, 0, 200, 150, observer);
		
		// 노란 물고기 인덱스 이미지(레벨 2일때 그리기)
		int yellowSx1 = 0+w*yellowNumIndex;
		int yellowSx2 = yellowSx1+w;
		if(level.getLevel() >= 2) {
			g.drawImage(yellowNumImg, dx1+130, dy1, dx2+130, dy2, yellowSx1, sy1, yellowSx2, sy2, observer);
		}	// 노란 물고기 이미지
			g.drawImage(yellowFishImg, x+100, y-65, x+100+80, y-65+60, 0, 0, 200, 150, observer);
		
		// 초록 물고기 인덱스 이미지(레벨 4일때 그리기)
		int greenSx1 = 0+w*greenNumIndex;
		int greenSx2 = greenSx1+w;
		if(level.getLevel() >= 4) {
			g.drawImage(greenNumImg, dx1+240, dy1, dx2+240, dy2, greenSx1, sy1, greenSx2, sy2, observer);
		}
		// 초록 물고기 이미지
		if(level.getLevel() >= 2) {
			g.drawImage(greenFishImg, x+220, y-65, x+220+75, y-65+60, 0, 0, 200, 150, observer);
		}
		
		// 보라 물고기 인덱스 이미지(레벨 6일때 그리기)
		int purpleSx1 = 0+w*purpleNumIndex;
		int purpleSx2 = purpleSx1+w;
		if(level.getLevel() >= 6) {
			g.drawImage(purpleNumImg, dx1+350, dy1, dx2+350, dy2, purpleSx1, sy1, purpleSx2, sy2, observer);
		}
		// 보라 물고기 이미지
		if(level.getLevel() >= 4) {
			g.drawImage(purpleFishImg, x+330, y-65, x+330+75, y-65+60, 0, 0, 200, 150, observer);
		}
		
		// 보스상어 이미지(레벨 6일때 그리기)
		if(level.getLevel() >= 6) {
			g.drawImage(bossSharkImg, x+440, y-65, x+440+120, y-65+70, 0, 0, 250, 150, observer);
		}
		
		
	}
	
	public void update() {
			
		GameCanvas observer = GameCanvas.getInstance();
		Level level = Level.getInstance();
		
		blueFish = observer.getBlueFishIndex();
		yellowFish = observer.getYellowFishIndex();
		greenFish = observer.getGreenFishIndex();
		purpleFish = observer.getPurpleFishIndex();
		
		
		// 레벨이 i일때 미션 세팅하기
		for(int i = 1; i < 8; i++) {
			if(level.getLevel() == i)
				setMission(i);
		}
			
		
		// 미션 수행위해 현재 먹어야 하는 나머지 물고기 개수
		if(blueMission > 0) 
			blueNumIndex = restFishIndex(blueMission, blueFish);
		
		if(yellowMission > 0) 
			yellowNumIndex = restFishIndex(yellowMission, yellowFish);
		
		if(greenMission > 0) 			
			greenNumIndex = restFishIndex(greenMission, greenFish);
		
		if(purpleMission > 0) 			
			purpleNumIndex = restFishIndex(purpleMission, purpleFish);

	}
	
	// 나머지 먹어야 할 물고기 개수
	public int restFishIndex(int mission, int fish) {
		
		int numIndex = mission - fish; 
		
		if(numIndex < 0)
			numIndex = 0;
		
		return numIndex;
	}

	// 미션별로 먹어야 하는 물고기 갯수 지정
	public void setMission(int i) {
			
		if(i == 1) {
			blueMission = 5;
			
		} else if(i == 2) {
			blueMission = 7;
			yellowMission = 3;
			
		} else if(i == 3) {
			blueMission = 7;
			yellowMission = 7;
		} else if(i == 4) {
			blueMission = 6;
			yellowMission = 7;
			greenMission = 3;
			
		} else if(i == 5) {
			blueMission = 7;
			yellowMission = 8;
			greenMission = 5;
			
		} else if(i == 6) {
			blueMission = 7;
			yellowMission = 7;
			greenMission = 5;
			purpleMission = 3;
			
		} else if(i == 7) {
			blueMission = 6;
			yellowMission = 6;
			greenMission = 6;
			purpleMission = 7;
		}
	}
	
	
	// 미션완료
	public boolean missionComplete(int i, int blueFishIndex, int yellowFishIndex, int greenFishIndex, int purpleFishIndex) {
		if(i == 1)
			blueFish = 5;
		
		else if(i == 2) {
			blueFish = 7;
			yellowFish = 3;
			
		} else if(i == 3) {
			blueFish = 7;
			yellowFish = 7;
			
		} else if(i == 4) {
			blueFish = 6;
			yellowFish = 7;
			greenFish = 3;
			
		} else if(i == 5) {
			blueFish = 7;
			yellowFish = 8;
			greenFish = 5;
			
		} else if(i == 6) {
			blueFish = 7;
			yellowFish = 7;
			greenFish = 5;
			purpleFish = 3;
			
		} else if(i == 7) {
			blueFish = 6;
			yellowFish = 6;
			greenFish = 6;
			purpleFish = 7;
		}
		return (blueFishIndex >= blueFish && yellowFishIndex >= yellowFish && greenFishIndex >= greenFish && purpleFishIndex >= purpleFish);
	}
	
}
