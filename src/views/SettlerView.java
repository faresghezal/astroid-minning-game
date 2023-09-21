package views;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SettlerView {
	private String imageAddr;
	private Image img;
	private JLabel label;

	//constructor to initialize a settler's view
	public SettlerView(JLabel label) {
		this.label = label;
		imageAddr = "/img/settler_alive.png";
		img = new ImageIcon(SettlerView.class.getResource(imageAddr)).getImage();
		this.label.setIcon(new ImageIcon(img));
		
	}
        
	//gets a boolean type parameter and adds an image to the label
	public void update(boolean selected) {
		if (selected)
			imageAddr = "/img/settler_current.png";
		else 
			imageAddr = "/img/settler_alive.png";
		img = new ImageIcon(SettlerView.class.getResource(imageAddr)).getImage();
		label.setIcon(new ImageIcon(img));
	}
}
