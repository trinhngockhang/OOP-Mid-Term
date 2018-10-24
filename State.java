
import java.io.File;

import javax.swing.JPanel;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class State {
	private int level;
	private boolean playing = false;
	private MediaPlayer audio;
	private JPanel Panel;
	String audioStringArr[] = {"level1.mp3","level2.mp3","level3.mp3","level4.mp3","level5.mp3","level6.mp3","level7.mp3","level8.mp3","level9.mp3","level10.mp3","level11.mp3","level12.mp3"};
	String textStringArr[] = {"level1.txt","level2.txt","level3.txt","level4.txt","level5.txt","level6.txt","level7.txt","level8.txt","level9.txt","level10.txt","level11.txt","level12.txt"};
	public void setLevel(int lv) {
		System.out.println("level dc chon la : " + lv);
		level = lv;
		String bip = audioStringArr[lv -1];
		Media hit = new Media(new File(bip).toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(hit);
	    setAudio(mediaPlayer);
	}
	
	public int getLevel() {
		return level;
	}

	public void setAudio(MediaPlayer audio) {
		this.audio = audio;
	}
	
	public MediaPlayer getAudio() {
		return audio;
	}
	
	public boolean getPlayingBool() {
		return playing;
	}
	
	public void setTruePlaying() {
		playing = true;
	}
	
	public void setFalsePlaying() {
		playing = false;
	}

	public JPanel getPanel() {
		return Panel;
	}

	public void setPanel(JPanel panel) {
		Panel = panel;
	}
}
