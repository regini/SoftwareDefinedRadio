package it.uniroma3.GUI;
import it.uniroma3.energyDetector.Simulatore;
import it.uniroma3.model.GenericSignal;
import it.uniroma3.reader.LettoreSegnale;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SimulatoreSequenzaGUI extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private final static String PATHFILES1F1 = "Campioni/Sequenza_1/output_1.dat";
	private final static String PATHFILES1F2 = "Campioni/Sequenza_1/output_2.dat";
	private final static String PATHFILES1F3 = "Campioni/Sequenza_1/output_3.dat";
	private final static String PATHFILES1F4 = "Campioni/Sequenza_1/output_4.dat";
	
	private final static String PATHFILES2F1 = "Campioni/Sequenza_2/output_1.dat";
	private final static String PATHFILES2F2 = "Campioni/Sequenza_2/output_2.dat";
	private final static String PATHFILES2F3 = "Campioni/Sequenza_2/output_3.dat";
	private final static String PATHFILES2F4 = "Campioni/Sequenza_2/output_4.dat";
	
	private final static String PATHFILES3F1 = "Campioni/Sequenza_3/output_1.dat";
	private final static String PATHFILES3F2 = "Campioni/Sequenza_3/output_2.dat";
	private final static String PATHFILES3F3 = "Campioni/Sequenza_3/output_3.dat";
	private final static String PATHFILES3F4 = "Campioni/Sequenza_3/output_4.dat";
	
	private Frame f;
	private Choice choice;

	private TextField tfProveOffLine;
	private TextField tfProveOnLine; 

	private Label lblProveOffLine;
	private Label lblProveOnLine;

	private Panel panelNorth;
	private Panel panelCenter;
	private Panel panelDisplay;

	private Button avviaSimulazioneSequenza;

	private Label lblSnr;
	private Label lblSoglia;
	private Label lblDetection;

	private TextField tfSnr;
	private TextField tfSoglia;
	private TextField tfDetection;

	public SimulatoreSequenzaGUI(){
		f = new Frame();
		f.setLayout(new BorderLayout());
		f.setSize(400, 400);

		panelNorth = new Panel();
		panelCenter = new Panel(new GridLayout(2, 2));
		panelDisplay = new Panel(new GridLayout(3,2));

		f.setLayout(new FlowLayout());
		choice = new Choice();
		choice.add("Sequenza_1 -> output_1.dat");
		choice.add("Sequenza_1 -> output_2.dat");
		choice.add("Sequenza_1 -> output_3.dat");
		choice.add("Sequenza_1 -> output_4.dat");

		choice.add("Sequenza_2 -> output_1.dat");
		choice.add("Sequenza_2 -> output_2.dat");
		choice.add("Sequenza_2 -> output_3.dat");
		choice.add("Sequenza_2 -> output_4.dat");

		choice.add("Sequenza_3 -> output_1.dat");
		choice.add("Sequenza_3 -> output_2.dat");
		choice.add("Sequenza_3 -> output_3.dat");
		choice.add("Sequenza_3 -> output_4.dat");

		panelNorth.add(choice);

		lblProveOffLine = new Label("Prove OffLine: ");

		lblProveOnLine = new Label("Prove OnLine: ");

		tfProveOffLine = new TextField();
		tfProveOffLine.setEditable(true);

		tfProveOnLine = new TextField();
		tfProveOnLine.setEditable(true);

		panelCenter.add(lblProveOffLine);
		panelCenter.add(tfProveOffLine);
		panelCenter.add(lblProveOnLine);
		panelCenter.add(tfProveOnLine);

		avviaSimulazioneSequenza= new Button("Avvia Simulazione Sequenza");
		avviaSimulazioneSequenza.addActionListener(this);

		lblSnr = new Label("L' SNR è: ");
		lblSoglia = new Label("La soglia è: ");
		lblDetection = new Label("La detection è: ");

		tfSnr = new TextField("",20);
		tfSnr.setEditable(false);
		
		tfSoglia = new TextField("",20);
		tfSoglia.setEditable(false);

		tfDetection = new TextField("",20);
		tfDetection.setEditable(false);

		panelDisplay.add(lblSnr);
		panelDisplay.add(tfSnr);
		panelDisplay.add(lblSoglia);
		panelDisplay.add(tfSoglia);
		panelDisplay.add(lblDetection);
		panelDisplay.add(tfDetection);

		f.add(panelNorth, BorderLayout.NORTH);
		f.add(panelCenter, BorderLayout.CENTER);
		f.add(avviaSimulazioneSequenza, BorderLayout.CENTER);
		f.add(panelDisplay, BorderLayout.SOUTH);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}});
		f.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Simulatore simulatore = new Simulatore();
		String pathFile ="";
		String selezioneFile = this.choice.getSelectedItem();
		System.out.println(selezioneFile);
		if(selezioneFile.equals("Sequenza_1 -> output_1.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES1F1;
		if(selezioneFile.equals("Sequenza_1 -> output_2.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES1F2;
		if(selezioneFile.equals("Sequenza_1 -> output_3.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES1F3;
		if(selezioneFile.equals("Sequenza_1 -> output_4.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES1F4;
		
		if(selezioneFile.equals("Sequenza_2 -> output_1.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES2F1;
		if(selezioneFile.equals("Sequenza_2 -> output_2.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES2F2;
		if(selezioneFile.equals("Sequenza_2 -> output_3.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES2F3;
		if(selezioneFile.equals("Sequenza_2 -> output_4.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES2F4;
		
		if(selezioneFile.equals("Sequenza_3 -> output_1.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES3F1;
		if(selezioneFile.equals("Sequenza_3 -> output_2.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES3F2;
		if(selezioneFile.equals("Sequenza_3 -> output_3.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES3F3;
		if(selezioneFile.equals("Sequenza_3 -> output_4.dat"))
			pathFile = SimulatoreSequenzaGUI.PATHFILES3F4;
		
		LettoreSegnale ls = new LettoreSegnale(pathFile);
		GenericSignal signal_noise = ls.letturaFile();
		simulatore.runSimulatoreFile(signal_noise, Integer.parseInt(tfProveOffLine.getText()), 
				Integer.parseInt(tfProveOnLine.getText()));	
		
		this.tfSnr.setText(String.valueOf(simulatore.getSnr()));
		this.tfSoglia.setText(String.valueOf(simulatore.getSoglia())); 
		this.tfDetection.setText(simulatore.getDetection()+"%"); 

	}	

}
