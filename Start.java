import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Start extends JFrame implements ActionListener {
	private static String[] levelChoice = {"Level 1", "Level 2", "Level 3","Level 4","Level 5","Level 6","Level 7","Level 8","Level 9","Level 10","Level 11"};
	private JPanel contentPanel;
	State state;
	JButton GoButton = new JButton("Go!");
	JComboBox comboBox = new JComboBox(levelChoice);
	public Start(State state) {
		this.state = state;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 750);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblSelectYourLevel = new JLabel("Select your level");
		lblSelectYourLevel.setFont(new Font("Luminari", Font.PLAIN, 14));
		lblSelectYourLevel.setBounds(360, 228, 201, 33);
		contentPanel.add(lblSelectYourLevel);
		
		
		comboBox.setBounds(397, 352, 120, 27);
		contentPanel.add(comboBox);
		
		
		GoButton.setBounds(400, 475, 117, 29);
		GoButton.addActionListener(this);
		contentPanel.add(GoButton);
	}
	
	public void clear() {
		this.getContentPane().removeAll();
		this.repaint();
	}

	
	public void NewTest() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start(state);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == GoButton) {
			String msg = (String)comboBox.getSelectedItem();
			System.out.println(msg);
			clear();
			int level = Integer.parseInt(msg.substring(msg.indexOf(" ") + 1, msg.length()));
			state.setLevel(level); 
			getContentPane().add(new Testing(state));
			this.setVisible(true);
		}
	}
}
