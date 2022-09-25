package Shark.game.item.fish;

import java.awt.Graphics;

public interface Fish {
	// 물고기 인터페이스
	void draw(Graphics g);
	void update();
	int getPositionX();
	int getPositionY();
	void move(double x, double y);
	boolean isbite();
	int getDistance();
	String getColor();
}
