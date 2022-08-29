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
			clip = AudioSystem.getClip();	// ����� ����� ����� �� �ִ� Ŭ�� �޾ƿ���
			audioFile = new File(pathName);	// ��θ� �ִ� ����
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);	// ���Ϸκ��� ����� �Է� ��Ʈ�� ��������
			clip.open(audioInputStream);	// Ŭ���� ����� �Է� ��Ʈ�� �޾ƿ���
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); 
			gainControl.setValue(-10.0f); // Reduce volume by 10 decibels(���� ���ú� ����)
			this.isLoop = isLoop;
		} catch(LineUnavailableException e) {
			e.printStackTrace();			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	// Ŭ�� ���
	public void start() {
		clip.setFramePosition(0);	// Ŭ���� ������ ó���� ����Ű�� �ϱ�
		clip.start();	// ���
		if(isLoop)	// �����ڿ��� �޾ƿ� ���ѹݺ� ���θ� ���� ���ѹݺ��� ��� ����
			clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	// ������� ����
	public void stop() {
		clip.stop();
	}
	

}
