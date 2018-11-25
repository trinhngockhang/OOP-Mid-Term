import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.tools.Tool;


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
		this.setVisible(true);
		this.initial();
	}
	
	public void clear() {
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
	}

	void initial() {
		System.out.println("khoi tao start");
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblSelectYourLevel = new JLabel("Select your level");
		lblSelectYourLevel.setFont(new Font("Luminari", Font.PLAIN, 14));
		lblSelectYourLevel.setBounds(353, 503, 201, 33);
		contentPanel.add(lblSelectYourLevel);	
		
		comboBox.setBounds(349, 549, 120, 27);
		contentPanel.add(comboBox);
		
		GoButton.setBounds(349, 607, 117, 29);
		GoButton.addActionListener(this);
		contentPanel.add(GoButton);
		
		JLabel lblByTrinhNgoc = new JLabel("By Trinh Ngoc Khang");
		lblByTrinhNgoc.setFont(new Font("Luminari", Font.PLAIN, 15));
		lblByTrinhNgoc.setBounds(333, 662, 248, 41);
		contentPanel.add(lblByTrinhNgoc);
		
		JLabel lblListeningTesting = new JLabel("Listening testing");
		lblListeningTesting.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblListeningTesting.setBounds(344, 44, 276, 73);
		contentPanel.add(lblListeningTesting);
		
		imageStart imgStart = new imageStart();
		imgStart.setLocation(141, 171);
		imgStart.setSize(561, 279);
		contentPanel.add(imgStart);
		this.setVisible(true);
	}
	public void NewTest() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start(state);
					frame.setVisible(true);
					frame.initial();
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
			getContentPane().add(new Testing(state,this));
			this.setVisible(true);
			
		}
	}
}



