import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;


import javax.swing.JToolBar;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
 
public class Testing extends JPanel implements ActionListener {
	String demo = "mot hai ba bon nam sau bay tam chin muoi";
	JButton btnPlay = new JButton("Start");
	JButton btnCheck = new JButton("Check");
	JButton btnPause = new JButton("Pause");
	JLabel lblYourAnswer = new JLabel("Your Answer");
	JLabel lblScript = new JLabel("Correct Script");
	Label AnswerLAbel = new Label();
	JLabel lblYourScoreText = new JLabel("Your Score");
	JLabel ScriptText = new JLabel("");
	JLabel yourScoreIntLb = new JLabel("0");
	JTextPane textPane = new JTextPane();
	JTextArea textArea = new JTextArea();
	State state;
	public Testing(State state) {
		setLayout(null);
		this.state = state;
		// pause audio
		btnPause.setBounds(252, 223, 80, 29);
		btnPause.addActionListener(this);
		add(btnPause);
		LineSound lineSound = new LineSound(this.state);
		lineSound.setSize(611, 47);
		lineSound.setLocation(6, 6);
		add(lineSound);
		Thread thread = new Thread(lineSound);
		thread.start();
		// check 
		btnCheck.setBounds(451, 223, 83, 29);
		btnCheck.addActionListener(this);
		add(btnCheck);
		
		// Start listening
		btnPlay.setBounds(87, 223, 75, 29);
		btnPlay.addActionListener(this);
		add(btnPlay);
		
		// text to fill
		textArea.setBounds(95, 82, 409, 107);
		add(textArea);
		
		// scroll 
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(95, 82, 409, 103);
		add(scrollPane);
		// label answer
		
		lblYourAnswer.setBounds(95, 264, 89, 16);
		add(lblYourAnswer);
		
		// label script
		lblScript.setBounds(95, 454, 89, 16);
		add(lblScript);
		ScriptText.setVerticalAlignment(SwingConstants.TOP);
		
		
		ScriptText.setBounds(95, 407, 409, 137);
		add(ScriptText);
		//score
		
		JLabel lblYourScoreText = new JLabel("Your Score");
		lblYourScoreText.setBounds(95, 660, 134, 16);
		add(lblYourScoreText);
		
		
		yourScoreIntLb.setBounds(194, 660, 134, 16);
		add(yourScoreIntLb);
		
		JScrollPane scrollPane_1 = new JScrollPane(ScriptText);
		scrollPane_1.setBounds(95, 500, 409, 137);
		add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(95, 300, 409, 137);
		add(scrollPane_2);
		
		
		scrollPane_2.setViewportView(textPane);
		AnswerLAbel.setBounds(561, 304, 405, 133);
		add(AnswerLAbel);
		
		
		AnswerLAbel.setVerticalAlignment(SwingConstants.TOP);

	}

	//action button
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnPlay) {
			state.setTruePlaying();
			state.getAudio().play();
		}
		if(e.getSource() == btnPause) {
			state.setFalsePlaying();
			state.getAudio().pause();
			System.out.println("aaa");
		}
		if(e.getSource() == btnCheck) {
			String userAnswer = textArea.getText();
			String answerToTest = textArea.getText();
			
			answerToTest  = answerToTest.replaceAll("\n", " ");
			answerToTest = answerToTest.replaceAll(","," ");
			answerToTest = answerToTest.replaceAll("\\."," ");
			System.out.println(userAnswer + " userAns");
			System.out.println(answerToTest + " userAnstoTest");
			//userAnswer = userAnswer.replaceAll("\n","<br>");
			SimpleAttributeSet sas = new SimpleAttributeSet();
			StyleConstants.setForeground(sas, Color.RED);
			   
			textPane.setText(userAnswer);
			StyledDocument document = textPane.getStyledDocument();
			document.setCharacterAttributes(0,0,sas,true);
			ArrayList arr1 = LCS.convertArrList(answerToTest,true);
			
			ArrayList arr2 = LCS.convertArrList(demo,false);
			double maxWords = arr2.size();
			double rightWords = LCS.LCS(arr1, arr2).size();
			String textScore ="" +  (int) ( (double)(rightWords/maxWords) * 100);
			yourScoreIntLb.setText(textScore);
			ScriptText.setText(demo);
			for(int i = 0 ; i < LCS.startIndex.size();i++) {
				int start = (int) LCS.startIndex.get(i);
				int end = (int) LCS.endIndex.get(i) ;
				System.out.println("  la : " + start + " ket: " + end + " dung " + LCS.trueIndex.get(i));
				if((boolean) LCS.trueIndex.get(i) == true)	document.setCharacterAttributes(start,end - start,sas, true);
			}
		}
	}
	
	
	// @Override to highlight word
	
	static class Label extends JLabel
	{
	    private static final long serialVersionUID = 1L;
	    private ArrayList start;
	    private ArrayList end;
	    private ArrayList indexTrue;
	    @Override
	    public void paint(Graphics g)
	    {
	    	if(start != null) {
	    		FontMetrics fontMetrics = g.getFontMetrics();
		        g.setColor(new Color( 0x33, 0xCC, 0x00));
		        
		        for(int i = 0 ; i< start.size();i++) {
		        	if((boolean) indexTrue.get(i) == true) {
		        		String startString = getText().substring(0, (int) start.get(i));
				        String text = getText().substring((int) start.get(i), (int) end.get(i));
				        int startX = fontMetrics.stringWidth(startString) ;
				        int startY = 0;
				        int length = fontMetrics.stringWidth(text);
				        int height = fontMetrics.getHeight();
				        g.fillRect(startX, startY, length, height);
		        	}      	
		        }
		        
	    	}else {
	    		System.out.println("khoi tao");
	    	}
	        super.paint(g);
	    }

	    public void highlightRegion(ArrayList start, ArrayList end,ArrayList indexTrue)
	    {
	        this.start = start;
	        this.end = end;
	        this.indexTrue = indexTrue;
	    }
	}
}
