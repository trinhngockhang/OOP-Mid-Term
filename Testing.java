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
 
public class Testing extends JPanel implements ActionListener {
	String demo = "mot hai ba bon nam sau bay tam chin muoi";
	JProgressBar progressBar = new JProgressBar(0,1000);
	JButton btnPlay = new JButton("Start");
	JButton btnCheck = new JButton("Check");
	JButton btnPause = new JButton("Pause");
	JLabel lblYourAnswer = new JLabel("Your Answer");
	JLabel lblScript = new JLabel("Correct Script");
	Label AnswerLAbel = new Label();
	JLabel lblYourScoreText = new JLabel("Your Score");
	JLabel ScriptText = new JLabel("New label");
	JTextArea textArea = new JTextArea();
	State state;
	public Testing(State state) {
		setLayout(null);
		this.state = state;
		// pause audio
		btnPause.setBounds(274, 41, 80, 29);
		btnPause.addActionListener(this);
		add(btnPause);
		
		// progree bar
		progressBar.setBounds(95, 6, 409, 20);
		add(progressBar);
		
		// check 
		btnCheck.setBounds(461, 41, 83, 29);
		btnCheck.addActionListener(this);
		add(btnCheck);
		
		// Start listening
		btnPlay.setBounds(73, 41, 75, 29);
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
		
		lblYourAnswer.setBounds(95, 197, 89, 16);
		add(lblYourAnswer);
		
		// label script
		lblScript.setBounds(95, 376, 89, 16);
		add(lblScript);
		ScriptText.setVerticalAlignment(SwingConstants.TOP);
		
		
		ScriptText.setBounds(95, 407, 409, 137);
		add(ScriptText);
		//score
		
		JLabel lblYourScoreText = new JLabel("Your Score");
		lblYourScoreText.setBounds(95, 621, 134, 16);
		add(lblYourScoreText);
		
		JLabel yourScoreIntLb = new JLabel("Your Score");
		yourScoreIntLb.setBounds(310, 621, 134, 16);
		add(yourScoreIntLb);
		
		JScrollPane scrollPane_1 = new JScrollPane(ScriptText);
		scrollPane_1.setBounds(95, 404, 409, 137);
		add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(95, 221, 409, 137);
		add(scrollPane_2);
		scrollPane_2.setViewportView(AnswerLAbel);
		
		
		AnswerLAbel.setVerticalAlignment(SwingConstants.TOP);

	}

	//action button
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnPlay) {
			state.getAudio().play();
		}
		if(e.getSource() == btnPause) {
			state.getAudio().pause();
			System.out.println("aaa");
		}
		if(e.getSource() == btnCheck) {
			String userAnswer = textArea.getText();
			userAnswer = userAnswer.replaceAll("\n", " ");
			System.out.println(userAnswer);
			AnswerLAbel.setText(userAnswer);
			AnswerLAbel.setVerticalAlignment(JLabel.TOP);
			AnswerLAbel.highlightRegion(0, 3);
			ArrayList arr2 = LCS.convertArrList(demo);
			
			ArrayList arr1 = LCS.convertArrList(userAnswer);
			ScriptText.setText(demo);
			System.out.println("nhung tu tra loi dung la : " + LCS.LCS(arr1,arr2));	
		}
	}
	
	
	// overide to highlight word
	
	static class Label extends JLabel
	{
	    private static final long serialVersionUID = 1L;
	    private int start;
	    private int end;

	    @Override
	    public void paint(Graphics g)
	    {
	        FontMetrics fontMetrics = g.getFontMetrics();

	        String startString = getText().substring(0, start);
	        String text = getText().substring(start, end);

	        int startX = fontMetrics.stringWidth(startString);
	        int startY = 0;

	        int length = fontMetrics.stringWidth(text);
	        int height = fontMetrics.getHeight();

	        g.setColor(new Color(0x33, 0x66, 0xFF, 0x66));
	        g.fillRect(startX, startY, length, height);

	        super.paint(g);
	    }

	    public void highlightRegion(int start, int end)
	    {
	        this.start = start;
	        this.end = end;
	    }
	}
}
