import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


class imageStart extends JPanel{
	
	Image image;
	public imageStart() {
		image=getToolkit().getImage("Its-Time-to-Listen.jpg"); 
        setSize(200,100);
        setVisible(true);
	}
	public void paint(Graphics g) {    
        g.drawImage(image, 0,0,this);  
    }  
}