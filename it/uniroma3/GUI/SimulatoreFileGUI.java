package it.uniroma3.GUI;
import it.uniroma3.energyDetector.Simulatore;
import it.uniroma3.model.GenericSignal;
import it.uniroma3.reader.LettoreSegnale;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
public class SimulatoreFileGUI extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private Frame f;

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
	
	private JFilePicker filePicker;

	public SimulatoreFileGUI(){
		f = new Frame();
		f.setLayout(new BorderLayout());
		f.setSize(800, 170);

		panelNorth = new Panel();
		panelCenter = new Panel(new GridLayout(2, 2));
		panelDisplay = new Panel(new GridLayout(3,2));

		f.setLayout(new FlowLayout());
		filePicker = new JFilePicker("Seleziona un file", "Browse...");
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addFileTypeFilter(".dat", ".dat");
		
		JFileChooser fileChooser = filePicker.getFileChooser();
		fileChooser.setCurrentDirectory(new File("D:/"));
		setSize(700, 100);
		setLocationRelativeTo(null);   
		
		panelNorth.add(filePicker);

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
		String pathFile = this.filePicker.getSelectedFilePath();
		
		System.out.println(pathFile);
	
		
		LettoreSegnale ls = new LettoreSegnale(pathFile);
		GenericSignal signal_noise = ls.letturaFile();
		simulatore.runSimulatoreFile(signal_noise, Integer.parseInt(tfProveOffLine.getText()), 
				Integer.parseInt(tfProveOnLine.getText()));
		this.tfSnr.setText(String.valueOf(simulatore.getSnr()));
		this.tfSoglia.setText(String.valueOf(simulatore.getSoglia())); 
		this.tfDetection.setText(simulatore.getDetection()+"%"); 

	}	

}
