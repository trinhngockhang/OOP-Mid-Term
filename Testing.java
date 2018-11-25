import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import javafx.application.Platform;

import javax.swing.ScrollPaneConstants;
 
public class Testing extends JPanel implements ActionListener {
	String answer ;
	JButton btnPlay = new JButton("Start");
	JButton btnCheck = new JButton("Check");
	JLabel lblYourAnswer = new JLabel("Your Answer");
	JLabel lblScript = new JLabel("Correct Script");
	JLabel lblYourScoreText = new JLabel("Your Score");
	JTextPane ScriptText = new JTextPane();
	JLabel yourScoreIntLb = new JLabel("0");
	Thread thread;
	JButton btnHome = new JButton("Home");
	JButton pauseButton = new JButton("Pause");
	JButton historyButton = new JButton("History");
	JButton againButton = new JButton("Again");
	JTextPane textPane = new JTextPane();
	JButton stopButton = new JButton("Stop");
	State state;
	Start start;
	public Testing(State state,Start start) {
		setLayout(null);
		this.state = state;
		this.start = start;
		answer = state.getAnswer();
		
		state.getLS().setSize(611, 47);
		state.getLS().setLocation(49, 6);
		add(state.getLS());
		
		// check 
		btnCheck.setBounds(462, 514, 83, 29);
		btnCheck.addActionListener(this);
		add(btnCheck);
		
		// Start listening
		btnPlay.setBounds(140, 244, 75, 29);
		btnPlay.addActionListener(this);
		add(btnPlay);
		// label answer
		
		lblYourAnswer.setBounds(126, 67, 89, 16);
		add(lblYourAnswer);
		
		// label script
		lblScript.setBounds(126, 288, 89, 16);
		add(lblScript);
		//score
		
		JLabel lblYourScoreText = new JLabel("Your Score");
		lblYourScoreText.setBounds(411, 627, 134, 23);
		add(lblYourScoreText);
		
		
		yourScoreIntLb.setBounds(519, 627, 134, 23);
		add(yourScoreIntLb);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(136, 316, 409, 147);
		add(scrollPane_1);
		scrollPane_1.setViewportView(ScriptText);
		//ScriptText.setVerticalAlignment(SwingConstants.TOP);
		
		
		ScriptText.setBounds(95, 407, 409, 137);
		ScriptText.setEditable(false);
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(136, 95, 409, 137);
		add(scrollPane_2);
		
		
		scrollPane_2.setViewportView(textPane);
		
		
		
		btnHome.setBounds(140, 514, 83, 29);
		add(btnHome);
		
		
		pauseButton.setBounds(297, 244, 75, 29);
		add(pauseButton);
		
		stopButton.setBounds(454, 244, 75, 29);
		add(stopButton);
		
		btnHome.addActionListener(this);
		pauseButton.addActionListener(this);
		stopButton.addActionListener(this);
		
		
		historyButton.setBounds(244, 514, 83, 29);
		add(historyButton);
		historyButton.addActionListener(this);
		
		againButton.setBounds(356, 514, 83, 29);
		add(againButton);
	}
	public void clear() {
		this.removeAll();
		this.repaint();
	}
	
	//action button
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnPlay) {
			state.setTruePlaying();
			state.getAudio().play();
			
		}
		if(e.getSource() == historyButton) {
			Platform.runLater(new Runnable() {
		        @Override
		        public void run() {
		        	new Chart(state.getLevel());
		        }
		   });
		}
		if(e.getSource() == pauseButton) {
			state.setFalsePlaying();
			state.getAudio().pause();
			
		}
		if(e.getSource() == stopButton) {
			state.setFalsePlaying();
			state.getAudio().stop();
			
		}
		if(e.getSource() == btnCheck) {
			String userAnswer = textPane.getText();
			String answerToTest = textPane.getText();
			textPane.setEditable(false);
			answerToTest  = answerToTest.replaceAll("\\W", " ");
			SimpleAttributeSet sas = new SimpleAttributeSet();
			StyleConstants.setForeground(sas, Color.GREEN);
			   
			textPane.setText(userAnswer);
			StyledDocument document = textPane.getStyledDocument();
			document.setCharacterAttributes(0,0,sas,true);
			ArrayList arr1 = LCS.convertArrList(answerToTest,true);
			
			ArrayList arr2 = LCS.convertArrList(answer.replaceAll("\\W", " "),false);
			double maxWords = arr2.size();
			double rightWords = LCS.LCS(arr1, arr2).size();
			int scoreInt = (int) ( (double)(rightWords/maxWords) * 100);
			String textScore ="" +  scoreInt;
			yourScoreIntLb.setText(textScore);
			ScriptText.setText(answer);
			for(int i = 0 ; i < LCS.startIndex.size();i++) {
				int start = (int) LCS.startIndex.get(i);
				int end = (int) LCS.endIndex.get(i) ;
				System.out.println("  la : " + start + " ket: " + end + " dung " + LCS.trueIndex.get(i));
				if((boolean) LCS.trueIndex.get(i) == true)	document.setCharacterAttributes(start,end - start,sas, true);
			}
			//remove true index
			LCS.trueIndex.removeAll(LCS.trueIndex);
			//save history
			saveHistory(scoreInt);
		}
		if(e.getSource() == btnHome) {
			clear();
			start.clear();
			state.setFalsePlaying();
			state.getAudio().stop();
			//state.getAudio().
			start.initial();
		}
	}
	
	public void saveHistory(int score) {
		Date a = new Date();
		String date = a.getDate() + "/" + (a.getMonth()+1) + "/" + (a.getYear() + 1900);
		String text = "\n" + state.getLevel()+ "," + score + "," +date;
		Function.appendText("history.txt", text);
 		
	}
}
