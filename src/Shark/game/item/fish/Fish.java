package Shark.game.item.fish;

import java.awt.Graphics;

public interface Fish {
	// ����� �������̽�
	void draw(Graphics g);
	void update();
	int getPositionX();
	int getPositionY();
	void move(double x, double y);
	boolean isbite();
	int getDistance();
	String getColor();
}
