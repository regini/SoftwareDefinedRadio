package it.uniroma3.GUI;
import it.uniroma3.energyDetector.Simulatore;

import java.awt.*;
import java.awt.event.*;

public class SimulatoreGUI extends Frame implements ActionListener{	

	private static final long serialVersionUID = 1L;
	private TextField tfDisplaySoglia;
	private Label lblSoglia;

	private TextField tfDisplayDetection;
	private Label lblDetection;

	private TextField tfSnr; 
	private TextField tfNumeroCampioni;
	private TextField tfProveOffLine;
	private TextField tfProveOnLine; 

	private Label lblSnr;
	private Label lblNumeroCampioni;
	private Label lblProveOffLine;
	private Label lblProveOnLine;

	private Button buttonAvvioSimulazione;
	private Panel panel;

	public SimulatoreGUI () {
		this.panel = new Panel();
		Panel panelInputDataSignal = new Panel(new GridLayout(4, 4));

		lblSnr = new Label("SNR: ");
		panelInputDataSignal.add(lblSnr);

		tfSnr = new TextField();
		tfSnr.setEditable(true);
		panelInputDataSignal.add(tfSnr);

		lblNumeroCampioni = new Label("Numero Campioni: ");
		panelInputDataSignal.add(lblNumeroCampioni);

		tfNumeroCampioni = new TextField();
		tfNumeroCampioni.setEditable(true);
		panelInputDataSignal.add(tfNumeroCampioni);

		lblProveOffLine = new Label("Prove Off-Line: ");
		panelInputDataSignal.add(lblProveOffLine);

		tfProveOffLine = new TextField();
		tfProveOffLine.setEditable(true);
		panelInputDataSignal.add(tfProveOffLine);

		lblProveOnLine = new Label("Prove On-Line: ");
		panelInputDataSignal.add(lblProveOnLine);

		tfProveOnLine = new TextField();
		tfProveOnLine.setEditable(true);
		panelInputDataSignal.add(tfProveOnLine);

		panel.add(panelInputDataSignal);

		Panel panelDisplay = new Panel(new GridLayout(2, 2));

		lblSoglia = new Label("La soglia è: ");
		panelDisplay.add(lblSoglia);

		tfDisplaySoglia = new TextField("", 20);
		panelDisplay.add(tfDisplaySoglia);

		lblDetection = new Label("La detection è: ");
		panelDisplay.add(lblDetection);

		tfDisplayDetection = new TextField("", 20);
		panelDisplay.add(tfDisplayDetection);

		panel.add(panelDisplay);

		Panel panelAvvioSimulazione = new Panel();
		buttonAvvioSimulazione = new Button("Avvio Simulazione");
		buttonAvvioSimulazione.addActionListener(this);
		panelAvvioSimulazione.add(buttonAvvioSimulazione);
		panel.add(panelAvvioSimulazione);

		setLayout(new BorderLayout());  
		add(panelInputDataSignal, BorderLayout.NORTH);
		add(panelAvvioSimulazione, BorderLayout.CENTER);
		add(panelDisplay, BorderLayout.SOUTH);

		setTitle("Energy Detector"); 
		setSize(400, 200);        
		setVisible(true);        


		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				dispose();
			}        
		});  
	}

	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Simulatore simulatore = new Simulatore();
		simulatore.runSimulatore(Double.parseDouble(tfSnr.getText()), Integer.parseInt(tfNumeroCampioni.getText()), 
				Integer.parseInt(tfProveOffLine.getText()), Integer.parseInt(tfProveOnLine.getText()));
		tfDisplaySoglia.setText(String.valueOf(simulatore.getSoglia()));
		tfDisplayDetection.setText(simulatore.getDetection()+"%"); 
	}
}