package Shark.game;

import javax.swing.JFrame;

import Shark.game.ui.GameFrame;


public class Main {

	public static void main(String[] args) {
		// Jframe ��ü�� �����ؼ� ������ �����츦 ����.
		// 1. JFrame ������ win ��ü�� �����ϰ� ȭ�� ����� ���� setVisible() �޼ҵ带 true ���� �����ϸ鼭 ȣ���ϵ��� ����.
		JFrame win = GameFrame.getInstance();
//		win.setVisible(true);
//		
//		// 2. win ��ü�� �ʱ� ���°� ���� �ȵ� ũ�⸦ �����ϴ� setSize�� ���ؼ� ũ�⸦ �����ϰ�, 
//		// 		setDefaultCloseOpertion �޼ҵ带 ���ؼ� JFrame.EXIT_ON_CLOSE �ɼ��� ��������.
//		win.setSize(1000, 600);
//		win.setResizable(false);
//		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		// 3. Canvas ��ü canvas�� �����ϰ� �װ��� win ��ü�� add() �޼ҵ带 �̿��ؼ� �����������쿡 ���Խ�Ű���� ����.
////			Canvas canvas = new Canvas();
//		Canvas canvas = Shark.game.ui.GameCanvas.getInstance(); //new GameCanvas();	// �θ��������� �����ϴ°� ����ϴ� ���� ������ �����ϴ� ���� ����
//		win.add(canvas);
//		canvas.requestFocus();
//		
//		win.validate();
////			win.pack();	// �θ��� ũ�⸦ �ڽ�ũ�⿡ ���缭 ����
		
		// 4. Canvas�� �׸��� �׸��� ���� Canvas �����쿡 �׸��� �׸��� ���� ���� canvas�� Graphics ������ ��ü�� �ʿ��ϴ�.
		//	  canvas�� getGraphics() �޼ҵ带 ���ؼ� Graphics ��ü�� ���� �� �� ��ü�� �̸��� g��� ��������.
//			Graphics g = canvas.getGraphics();
		
		// 5. �׸��� �׸��� ������ Graphics ������ ��ü�� g�� �̿��ؼ� drawLine() �޼ҵ带 ȣ���ؼ� 
		// 	(0,0) ~ (100,100) ��ġ�� ���� �׷������� ����.
//			g.drawLine(0, 0, 100, 100);
		
		// �� ���� �Ⱥ�����? �� ������ �׸��� �׸��� ������ �����ؾ� �Ѵ�.
		// 1. main ���� canvas�� �����ϰ� �� ������ ���� �׸���.
		// 2. win ��ü�� ȭ�鿡 �������鼭 canvas ��ü�� ȭ�鿡 ��������.
		// ?? �׷� canvas ȭ�鿡 �׸��� �׸� �Ŀ� ȭ���� ��Ÿ����.. �� �׸��� ������ �ȵǴ� �ǰ���? -> ��~
		// App ������� �׻� �ٸ� ������� ������ ������ ������ �ٽ� �׸��� �۾��� ������ �̷������.
		// �׷��� ������ ������ �ٽ� ǥ���ؾ� �� ������ ������ �ٽ� �׸��� ���ؼ� paint ���� ����� ����ó�� ��ȣ�� �ǵ��� �����ϰ� �ִ�.
		// setVisible() -> update() -> paint()
		// repaint()	-> update() -> paint()
		// Min => Max	-> update() -> paint()
		// resize()		-> update() -> paint()
		// .. ��Ÿ ��� 	-> update() -> paint()
		// ���࿡ �����찡 �ٽ� �׷������� �ڽ��� �׸��� �����Ǳ⸦ ���Ѵٸ�???
		// paint �޼ҵ忡 �׷��� �Ѵ�. -> paint �޼ҵ忡? Canvas �ҽ��ڵ忡? �ƴ� ������(override)?
		
		// 6. �׷� paint �������̵带 ���ؼ� Canvas Ŭ������ is a ����ϴ� com.newlecture.game.ui.GameCanvas�� �����ϰ� 3�� �ڵ带�ּ�ó�� �� �Ŀ�
		// 	  GameCanvas ������ ��ü�� ���� canvas�� ����ϰ� �װ��� win ��ü�� add ����.
		//	���� ������ GameCanvas���� paint �޼ҵ带 �������̵� �ϰ� ���ڷ� ���޵� g ��ü�� �̿��ؼ� drawLine()�� ȣ���ؼ� ���� �׸��� �ڵ带 �ۼ��Ͻÿ�.
		// 4�� 5�� �ڵ�� �ּ����� �����ֽñ� �ٶ��ϴ�.
			
		
		}

	}

