import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

public class LineSound extends JPanel  implements Runnable{

	/**
	 * Create the panel.
	 */
	private Boolean stop = false;
	JLabel label = new JLabel("00:00");
	JProgressBar progressBar = new JProgressBar();
	JLabel label_1 = new JLabel();
	State state;
	public LineSound(State state) {
		setLayout(null);
		this.state = state;
		
		progressBar.setBounds(45, 5, 554, 20);
		add(progressBar);
		
		
		label.setBounds(45, 27, 61, 20);
		add(label);
		
		
		label_1.setBounds(559, 27, 61, 20);
		add(label_1);

	}
	@Override
	public void run() {
		
			   int minusFixed  = (int) (state.getAudio().getMedia().durationProperty().getValue().toSeconds() / 60);
			   int secondsFixed = (int) (state.getAudio().getMedia().durationProperty().getValue().toSeconds()% 60);
			   String secondFixedString = secondsFixed < 10 ? ("0" + secondsFixed) : ("" + secondsFixed);
			   String displayTimeFixed = minusFixed + ":" + secondFixedString;
			   label_1.setText(displayTimeFixed);
			   		while(true) {
			   		try {
			            // thread to sleep for 100 milliseconds
			            Thread.sleep(1000);
			         } catch (Exception e) {
			            System.out.println(e);
			         }
		    		   double currentTime = state.getAudio().getCurrentTime().toMillis();
		    		   double fixedTime = state.getAudio().getMedia().durationProperty().getValue().toMillis();
		    		   int minusCurrent  = (int) (state.getAudio().getCurrentTime().toSeconds() / 60);
		    		   int secondsCurrent = (int) (state.getAudio().getCurrentTime().toSeconds() % 60);
	 	    		   progressBar.setValue((int) (currentTime/fixedTime * 100));
	 	    		   String secondCurrentString = secondsCurrent < 10 ? ("0" + secondsCurrent) : ("" + secondsCurrent);
	 	    		   String displayTimeCurrent = minusCurrent + ":" + secondCurrentString;
	 	    		   label.setText(displayTimeCurrent);
				   
			   	}
			
			
	}
}
