package Shark.game.item;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	
	private Clip clip;
	private File audioFile;
	private AudioInputStream audioInputStream;
	private FloatControl gainControl;
	private boolean isLoop;
	
	public Music(String pathName, boolean isLoop) {
		
		try {
			clip = AudioSystem.getClip();	// 오디오 재생에 사용할 수 있는 클립 받아오기
			audioFile = new File(pathName);	// 경로명에 있는 파일
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);	// 파일로부터 오디오 입력 스트림 가져오기
			clip.open(audioInputStream);	// 클립에 오디오 입력 스트림 받아오기
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); 
			gainControl.setValue(-10.0f); // Reduce volume by 10 decibels(볼륨 데시벨 조절)
			this.isLoop = isLoop;
		} catch(LineUnavailableException e) {
			e.printStackTrace();			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	// 클립 재생
	public void start() {
		clip.setFramePosition(0);	// 클립을 파일의 처음을 가리키게 하기
		clip.start();	// 재생
		if(isLoop)	// 생성자에서 받아온 무한반복 여부를 통해 무한반복일 경우 구현
			clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	// 재생파일 멈춤
	public void stop() {
		clip.stop();
	}
	

}
