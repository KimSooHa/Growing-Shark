package Shark.game.item;

import java.awt.Graphics;

public interface Button {
	// 버튼 인터페이스
	void draw(Graphics g);
	void update();
	void clicked(boolean isClicked);
	void entered(boolean isEntered);
	boolean isPointIn(int x, int y);
}
