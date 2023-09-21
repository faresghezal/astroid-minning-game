package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPanel inventoryPanel;
	public Controller c;
	private JPanel panel;
	public JLabel label_iron, label_carbon, label_uranium, label_water;
	private JPanel panel_1;
	Image image = new ImageIcon(MainWindow.class.getResource("/img/background.png")).getImage();
	/**
	 * Launch the application.
	 */
	List<AsteroidView> asteroidViews = new ArrayList<AsteroidView> (); // List that stores asteroid views
	List<SettlerView> settlerViews = new ArrayList<SettlerView>(); // List that stores settler views
	
	//gets x and y parameters and creates the asteroids graphical representation
	public AsteroidView createAsteroidView(int X, int Y)
	{
		JLabel newL = new JLabel();
		AsteroidView view = new AsteroidView(newL);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = X;
		gbc_label.gridy = Y;
		panel.add(newL, gbc_label);
		asteroidViews.add(view);
		newL.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (c.targetAsteroid[0] != -1 && c.targetAsteroid[1] != -1)
					c.getAsts().get(c.targetAsteroid[0]).untarget();
				c.targetAsteroid = new int[] {X, Y};
				view.update(true);
			}
		});
		return view;
	}
	//gets a number of settlers as a parameter and creates the settler graphical representation
	public SettlerView createSettlerView(int settlerNumber) {
		 JLabel headerLabel = new JLabel("Settler "+ settlerNumber);
		 JLabel settlerLabel = new JLabel();
		 SettlerView settlerView = new SettlerView(settlerLabel);
		 settlerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 headerLabel.setForeground(Color.WHITE);
		 
		 panel_1.add(headerLabel);
		 panel_1.add(settlerLabel);
		 
		 return settlerView;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					frame.c = new Controller(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {

		Image img;
		contentPane = new JPanel() { // Creates the main Panel
		         @Override
		         public void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            g.drawImage(image, 0, 0, null);
		         }
		      };
		      
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 589);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);  
		
		panel = new JPanel();
		
		panel.setBackground(new Color(5,5,5,5));
		contentPane.add(panel, BorderLayout.CENTER); // Adds a new panel to the center of the main one
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{177, 0, 267, 0};
		gbl_panel.rowHeights = new int[]{201, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Inventory"); // Adds a new label for the inventory
		lblNewLabel_1.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 6;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		inventoryPanel = new JPanel();
		inventoryPanel.setBackground(new Color(5,5,5,5)); GridBagConstraints gbc_panel_2 = new
		GridBagConstraints(); gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH; gbc_panel_2.gridx = 0; gbc_panel_2.gridy = 5;
		panel.add(inventoryPanel, gbc_panel_2); inventoryPanel.setLayout(new GridLayout(1, 1, 1, 1));
		inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		
		label_iron = new JLabel();
		label_iron.setText("0");
		label_iron.setForeground(Color.WHITE);

		img = new ImageIcon(this.getClass().getResource("/iron.png")).getImage(); // Gets an iron image
		label_iron.setIcon(new ImageIcon(img)); // Sets the image to the label
		GridBagConstraints consts = new GridBagConstraints();
		consts.gridx = 0; consts.gridy = 0;
		inventoryPanel.add(label_iron, consts);
		
		label_uranium = new JLabel();
		label_uranium.setText("0");
		label_uranium.setForeground(Color.WHITE);
		img = new ImageIcon(MainWindow.class.getResource("/img/uranium.png")).getImage();
		label_uranium.setIcon(new ImageIcon(img));
		GridBagConstraints consts_2 = new GridBagConstraints();
		consts_2.gridx = 1; consts_2.gridy = 0;
		inventoryPanel.add(label_uranium, consts_2);
		
		label_water = new JLabel();
		label_water.setText("0");
		label_water.setForeground(Color.WHITE);
		img = new ImageIcon(MainWindow.class.getResource("/img/ice.png")).getImage();
		label_water.setIcon(new ImageIcon(img));
		GridBagConstraints consts_3 = new GridBagConstraints();
		consts_3.gridx = 0; consts_3.gridy = 1;
		inventoryPanel.add(label_water, consts_3);
		
		label_carbon = new JLabel();
		label_carbon.setText("0");
		label_carbon.setForeground(Color.WHITE);
		img = new ImageIcon(MainWindow.class.getResource("/img/coal.png")).getImage();
		label_carbon.setIcon(new ImageIcon(img));
		GridBagConstraints consts_4 = new GridBagConstraints();
		consts_4.gridx = 1; consts_4.gridy = 1;
		inventoryPanel.add(label_carbon, consts_4);

		Border border = BorderFactory.createLineBorder(Color.BLACK);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0,0,0,0));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JButton btnNewButton = new JButton(" Travel ");
		// Adding an action listener for the button "Travel"
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.takeAction("travel");
			}
		});
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton.setBackground(Color.magenta);
		btnNewButton.setFocusPainted(false);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton(" Drill  ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.takeAction("drill");
			}
		});
		btnNewButton_1.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton_1.setBackground(Color.magenta);
		btnNewButton_1.setFocusPainted(false);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton(" Mine   ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.takeAction("mine");
			}
		});
		btnNewButton_2.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton_2.setBackground(Color.magenta);
		btnNewButton_2.setFocusPainted(false);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton(" Drop   ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.takeAction("drop");
			}
		});
		btnNewButton_3.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton_3.setBackground(Color.magenta);
		btnNewButton_3.setFocusPainted(false);
		panel_1.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Pick up ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.takeAction("pick up");
			}
		});
		btnNewButton_4.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton_4.setBackground(Color.magenta);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setPreferredSize(new Dimension(100, 10));
		panel_1.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton(" Build R");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.takeAction("build robot");
			}
		});
		btnNewButton_5.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton_5.setBackground(Color.magenta);
		btnNewButton_5.setFocusPainted(false);
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton(" Build G");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.takeAction("build gate");
			}
		});
		btnNewButton_6.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton_6.setBackground(Color.magenta);
		btnNewButton_6.setFocusPainted(false);
		panel_1.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Deploy G");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.takeAction("deploy gate");
			}
		});
		btnNewButton_7.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton_7.setBackground(Color.magenta);
		btnNewButton_7.setFocusPainted(false);
		panel_1.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Sunstorm");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.takeAction("sunstorm");
			}
		});
		btnNewButton_8.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton_8.setBackground(Color.magenta);
		btnNewButton_8.setFocusPainted(false);		
		panel_1.add(btnNewButton_8);
		
		//dont need hide according to requirements
		
		JLabel lblNewLabel = new JLabel("Settlers:");
		lblNewLabel.setForeground(Color.WHITE);
		panel_1.add(lblNewLabel);
	}

}
