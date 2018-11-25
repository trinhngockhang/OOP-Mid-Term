
import java.io.File;

import javax.swing.JPanel;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class State {
	private int level;
	private boolean playing = false;
	private MediaPlayer audio;
	private JPanel Panel;
	private LineSound ls;
	private String answer;
	String audioStringArr[] = {"level1.mp3","level2.mp3","level3.mp3","level4.mp3","level5.mp3","level6.mp3","level7.mp3","level8.mp3","level9.mp3","level10.mp3","level11.mp3","level12.mp3"};
	String textStringArr[] = {"level1.txt","level2.txt","level3.txt","level4.txt","level5.txt","level6.txt","level7.txt","level8.txt","level9.txt","level10.txt","level11.txt","level12.txt"};
	public State() {
		ls = new LineSound(this);
	}
	public void setLevel(int lv) {
		System.out.println("level dc chon la : " + lv);
		level = lv;
		String bip = audioStringArr[lv -1];
		Media hit = new Media(new File(bip).toURI().toString());
	    audio = new MediaPlayer(hit);
	    try {
	    	   //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
	    	   String nameFile = "level" + lv + ".txt";
	    	   FileInputStream fis = new FileInputStream(nameFile);
	    	   DataInputStream dis = new DataInputStream(fis);

	    	   //Bước 2: Đọc dữ liệu
	    	   StringBuffer inputLine = new StringBuffer();
	            String tmp; 
	            while ((tmp = dis.readLine()) != null) {
	                inputLine.append(tmp);
	                inputLine.append(System.lineSeparator());;
	                //inputLine.append(String.format("%n", ""));
	            }
	    	   //Bước 3: Đóng luồng
	    	   fis.close();
	    	   dis.close();
	    	   this.setAnswer(inputLine.toString());
	    	  } catch (IOException ex) {
	    	    ex.printStackTrace();
	    	  }
		   audio.setOnReady(() -> {
		    	Thread t = new Thread(ls);
		    	t.start();
		    });
	    
	}
	
	public int getLevel() {
		return level;
	}

	
	public MediaPlayer getAudio() {
		return audio;
	}
	
	public LineSound getLS() {
		return this.ls;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		System.out.println("ans dc set la: " + answer);
		this.answer = answer;
	}
	
	
}
