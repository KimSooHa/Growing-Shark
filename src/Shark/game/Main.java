package Shark.game;

import javax.swing.JFrame;

import Shark.game.ui.GameFrame;


public class Main {

	public static void main(String[] args) {
		// Jframe 객체를 생성해서 프레임 윈도우를 띄운다.
		// 1. JFrame 형식의 win 객체를 생성하고 화면 출력을 위해 setVisible() 메소드를 true 값을 전달하면서 호출하도록 하자.
		JFrame win = GameFrame.getInstance();
//		win.setVisible(true);
//		
//		// 2. win 객체의 초기 상태가 맘에 안들어서 크기를 변경하는 setSize를 통해서 크기를 설정하고, 
//		// 		setDefaultCloseOpertion 메소드를 통해서 JFrame.EXIT_ON_CLOSE 옵션을 설정하자.
//		win.setSize(1000, 600);
//		win.setResizable(false);
//		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		// 3. Canvas 객체 canvas를 생성하고 그것을 win 객체의 add() 메소드를 이용해서 프레임윈도우에 포함시키도록 하자.
////			Canvas canvas = new Canvas();
//		Canvas canvas = Shark.game.ui.GameCanvas.getInstance(); //new GameCanvas();	// 부모형식으로 참조하는게 사용하는 범위 내에서 참조하는 것이 좋다
//		win.add(canvas);
//		canvas.requestFocus();
//		
//		win.validate();
////			win.pack();	// 부모의 크기를 자식크기에 맞춰서 위임
		
		// 4. Canvas에 그림을 그리기 위해 Canvas 윈도우에 그림을 그리기 위한 도구 canvas의 Graphics 형식의 객체가 필요하다.
		//	  canvas의 getGraphics() 메소드를 통해서 Graphics 객체를 얻은 후 그 객체의 이름을 g라고 선언하자.
//			Graphics g = canvas.getGraphics();
		
		// 5. 그림을 그리는 도구인 Graphics 형식의 객체인 g를 이용해서 drawLine() 메소드를 호출해서 
		// 	(0,0) ~ (100,100) 위치의 선을 그려보도록 하자.
//			g.drawLine(0, 0, 100, 100);
		
		// 왜 선이 안보이지? 그 이유는 그림을 그리는 절차를 이해해야 한다.
		// 1. main 에서 canvas를 생성하고 그 영역에 선을 그린다.
		// 2. win 객체가 화면에 보여지면서 canvas 객체도 화면에 보여진다.
		// ?? 그럼 canvas 화면에 그림을 그린 후에 화면이 나타나면.. 내 그림은 유지가 안되는 건가요? -> 네~
		// App 윈도우는 항상 다른 윈도우와 영역을 가리고 영역을 다시 그리는 작업이 끝없이 이루어진다.
		// 그래서 윈도우 영역을 다시 표현해야 할 때마다 영역을 다시 그리기 위해서 paint 관련 기능이 다음처럼 재호출 되도록 구현하고 있다.
		// setVisible() -> update() -> paint()
		// repaint()	-> update() -> paint()
		// Min => Max	-> update() -> paint()
		// resize()		-> update() -> paint()
		// .. 기타 등등 	-> update() -> paint()
		// 만약에 윈도우가 다시 그려지더라도 자신의 그림이 유지되기를 원한다면???
		// paint 메소드에 그려야 한다. -> paint 메소드에? Canvas 소스코드에? 아님 재정의(override)?
		
		// 6. 그럼 paint 오버라이드를 위해서 Canvas 클래스를 is a 상속하는 com.newlecture.game.ui.GameCanvas를 정의하고 3번 코드를주석처리 한 후에
		// 	  GameCanvas 형식의 객체를 만들어서 canvas로 명명하고 그것을 win 객체에 add 하자.
		//	새로 정의한 GameCanvas에서 paint 메소드를 오버라이드 하고 인자로 전달된 g 객체를 이용해서 drawLine()를 호출해서 선을 그리는 코드를 작성하시오.
		// 4번 5번 코드는 주석으로 지워주시기 바랍니다.
			
		
		}

	}

