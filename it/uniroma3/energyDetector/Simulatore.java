package it.uniroma3.energyDetector;

import it.uniroma3.model.GenericSignal;
import it.uniroma3.model.Signal;
/**
 * Classe che implementa le simulazioni, e metodi di appoggio all'interfaccia grafica.
 * Eventuali errori vengono catturati dalla simulazione e stampati su Console.
 */
public class Simulatore {
	private static final double PFA = Math.pow(10, -3);

	private CalcolatoreSoglia calcolatoreSoglia;
	private TestSoglia testSoglia;

	private double snr;
	private double soglia;
	private double detection;
	private String error;


	public Simulatore(){
		this.calcolatoreSoglia = new CalcolatoreSoglia();
		this.testSoglia = new TestSoglia();
	}
	/**
	 * Metodo che avvia una simulazione su un segnale letto da file
	 * @param signal_noise segnale e rumore contenuti nel file di input
	 * @param proveOffLine numero di prove da fare OffLine
	 * @param proveOnLine numero di prove da fare OnLine
	 */
	public void runSimulatoreFile(GenericSignal signal_noise, int proveOffLine, int proveOnLine){
		try{
			this.snr = signal_noise.getSNR();
			this.soglia = this.calcolatoreSoglia.calcoloSoglia(this.snr, signal_noise.getLength()/proveOnLine, proveOffLine, PFA);
			this.detection = this.testSoglia.calcolaDetectionFile(signal_noise, proveOnLine, soglia);
		}catch(Exception e){
			System.out.println(e.getMessage());
			this.error = e.getMessage();
		}
	}
	/**
	 * Metodo che avvia una simulazione su un segnale randomico di potenza unitaria
	 * e su un rumore bianco Gaussiano additivo con valore atteso nullo e varianza unitaria.
	 * @param snr (Signal to Noise Ratio)
	 * @param length numero di campioni del segnale s[n]
	 * @param proveOffLine numero di prove da fare OffLine
	 * @param proveOnLine numero di prove da fare OnLine
	 */
	public void runSimulatore(double snr, int length, int proveOffLine, int proveOnLine) {
		try{
			this.snr = snr;
			Signal signal = new Signal(length);
			signal.generateRandomSignal();
			this.soglia = this.calcolatoreSoglia.calcoloSoglia(snr, signal.getLength(), proveOffLine, PFA);
			this.detection = this.testSoglia.calcolaDetection(signal, snr, proveOnLine, soglia);
		}catch(Exception e){
			System.out.println(e.getMessage());
			this.error = e.getMessage();
		}
	}

	/**
	 * @return valore snr (Signal to Noise Ratio)
	 */
	public double getSnr() {
		return this.snr;
	}

	/**
	 * @return valore della soglia
	 */
	public double getSoglia() {
		return this.soglia;
	}

	/**
	 * @return colore della probabilità di detection espresa in percentuale
	 */
	public double getDetection() {
		return this.detection;
	}

	/**
	 * @return evuntuali errori che si sono verificati nella simulazione
	 */
	public String getError() {
		return this.error;
	}

}
