package Shark.game.item;

import java.awt.Graphics;

public interface Button {
	// ��ư �������̽�
	void draw(Graphics g);
	void update();
	void clicked(boolean isClicked);
	void entered(boolean isEntered);
	boolean isPointIn(int x, int y);
}
