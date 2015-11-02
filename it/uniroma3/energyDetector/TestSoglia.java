package it.uniroma3.energyDetector;

import it.uniroma3.model.GenericSignal;
import it.uniroma3.model.Signal;

/**
 *Quella classe ha la responsabilità di calcolare l'energia del segnale, confrontarla con la soglia
 *e determinare la probabilità di detection.
 */
public class TestSoglia {

	/**
	 * Questo metodo calcola la probabilità di detection del segnale letto da file e passato come parametro
	 * @param signal segnale su cui fare la simulazione da file
	 * @param prove numero di prove da fare OnLine
	 * @param soglia valore sopra la quale viene identificato il Primary User
	 * @return valore in percentuale della detection
	 */
	public double calcolaDetectionFile(GenericSignal signal, int prove, double soglia){
		double cont = 0;
		int length = signal.getLength()/prove;
		for(int i=0; i<prove; i++){
			int k=0;
			GenericSignal s = new Signal(length);
			for(int j=i*length; j<(i+1)*length; j++) {
				s.addParteReale(k, signal.getParteReale(j));
				s.addParteImmaginaria(k, signal.getParteImmaginaria(j));
				k++;
			}
			if(s.evaluateEnergy() > soglia)
				cont++;
		}
		return (cont/prove)*100;
	}

	/**
	 * Questo metodo calcola la probabilità di detection del segnale passato come parametro
	 * @param signal segnale su cui fare la simulazione
	 * @param snr (Signal to Noise Ratio)
	 * @param prove numero di prove da fare OnLine
	 * @param soglia valore sopra la quale viene identificato il Primary User
	 * @return valore in percentuale della detection
	 */
	public double calcolaDetection(Signal signal, double snr, int prove, double soglia) {
		double cont = 0;
		for(int i = 0; i<prove; i++) {
			GenericSignal signal_noise = signal.aggiungiRumore(snr);
			if(signal_noise.evaluateEnergy() > soglia)
				cont++;
		}
		return (cont/prove)*100;
	}
}