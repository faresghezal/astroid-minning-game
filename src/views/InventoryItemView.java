package views;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class InventoryItemView {

	private String imageAddr;
	private Image img;
	private JLabel label;
	private String type;
	private int quantity;
	
	public InventoryItemView(JLabel label, String type, int quantity) {
		this.type = type;
		this.label = label;
		if (type == "Iron")
			imageAddr = "/img/iron.png";
		else if (type == "Carbon")
			imageAddr = "/img/carbon.png";
		else if (type == "Uranium")
			imageAddr = "/img/uranium.png";
		else if (type == "Water")
			imageAddr = "/img/ice.png";
		this.quantity = quantity;
		this.label.setText(Integer.toString(quantity));
		this.label.setForeground(Color.WHITE);
		img = new ImageIcon(InventoryItemView.class.getResource(imageAddr)).getImage();
		this.label.setIcon(new ImageIcon(img));
	}
	
	public void update(String type, int quantity) {
		if (type == "Iron")
			imageAddr = "/img/iron.png";
		else if (type == "Carbon")
			imageAddr = "/img/carbon.png";
		else if (type == "Uranium")
			imageAddr = "/img/uranium.png";
		else if (type == "Water")
			imageAddr = "/img/ice.png";
		this.label.setText(Integer.toString(quantity));
		img = new ImageIcon(InventoryItemView.class.getResource(imageAddr)).getImage();
		this.label.setIcon(new ImageIcon(img));
	}
	
	public JLabel getLabel() {
		return label;
	}
}
