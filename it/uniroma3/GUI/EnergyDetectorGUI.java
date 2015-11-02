package it.uniroma3.GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class EnergyDetectorGUI {
	private Frame mainFrame;
	private Label headerLabel;
	private Label aboutLabel;
	private Panel controlPanel;
	private Label infoLabel;



	public EnergyDetectorGUI(){
		prepareGUI();
	}

	private void prepareGUI(){
		mainFrame = new Frame("EnergyDetector");
		mainFrame.setSize(700,400);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    
		headerLabel = new Label("Per avviare una simulazione clicca su: Window ->");
		headerLabel.setAlignment(Label.CENTER);

		ImagePanel panel = new ImagePanel(new ImageIcon("image/iee.jpg").getImage());
		mainFrame.add(panel);

		aboutLabel = new Label();        
		aboutLabel.setAlignment(Label.CENTER);
		aboutLabel.setSize(350,100);

		infoLabel = new Label();
		infoLabel.setAlignment(Label.CENTER);
		infoLabel.setSize(350,100);


		controlPanel = new Panel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(infoLabel);
		mainFrame.add(aboutLabel);

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(aboutLabel);
		mainFrame.setVisible(true);  
	}

	public void showGUI(){
		final MenuBar menuBar = new MenuBar();

	
		Menu fileMenu = new Menu("Window");

		MenuItem simulatore = 
				new MenuItem("Simulatore",new MenuShortcut(KeyEvent.VK_S));
		simulatore.setActionCommand("Simulatore");

		MenuItem simulatoreSequenza = new MenuItem("Simulazione da Sequenza", new MenuShortcut(KeyEvent.VK_D));
		simulatoreSequenza.setActionCommand("SimulatoreSequenza");

		MenuItem simulatoreFile = new MenuItem("Simulazione da File",new MenuShortcut(KeyEvent.VK_F));
		simulatoreFile.setActionCommand("SimulatoreFile");

		MenuItem about = new MenuItem("About");
		about.setActionCommand("About");

		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setActionCommand("Exit");

		MenuItemListener menuItemListener = new MenuItemListener();

		simulatore.addActionListener(menuItemListener);
		simulatoreSequenza.addActionListener(menuItemListener);
		simulatoreFile.addActionListener(menuItemListener);
		exitMenuItem.addActionListener(menuItemListener);
		about.addActionListener(menuItemListener);

		fileMenu.add(simulatore);
		fileMenu.add(simulatoreSequenza);
		fileMenu.add(simulatoreFile);
		fileMenu.addSeparator();
		fileMenu.add(about);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		mainFrame.setMenuBar(menuBar);
		mainFrame.setVisible(true);  
	}

	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {  
			if(e.getActionCommand().equals("About")){
				headerLabel.setText("Created by Niccolò Regini e Stefano Silvi");
			}else if(e.getActionCommand().equals("Exit")){
				System.exit(0);
			}else{
				String className = "it.uniroma3.GUI."+e.getActionCommand()+"GUI";
				try {
					Class.forName(className).newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}    
	}
}