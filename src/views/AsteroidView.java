package views;

import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AsteroidView {

	private String imageAddr;
	private Image img;
	private JLabel label;
	
	//constructor to initialise an asteroid's view
	public AsteroidView(JLabel label) {
		 this.label = label;
		 imageAddr = "/img/asteroid.png";
		 img = new ImageIcon(this.getClass().getResource(imageAddr)).getImage();
		 this.label.setIcon(new ImageIcon(img));
	}
        
	//gets a boolean type parameter and updates the imageAddr attribute
	public void update(boolean selected) {
		if (selected)
			imageAddr = "/img/asteroid_current.png";
		else 
			imageAddr = "/img/asteroid.png";
		img = new ImageIcon(this.getClass().getResource(imageAddr)).getImage();
		label.setIcon(new ImageIcon(img));
	}
}
