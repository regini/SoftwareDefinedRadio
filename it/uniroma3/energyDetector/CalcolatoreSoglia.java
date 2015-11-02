package it.uniroma3.energyDetector;

import it.uniroma3.function.Functions;
import it.uniroma3.model.GenericSignal;
import it.uniroma3.model.Noise;

/**
 *Questa classe ha la responsabilitò di calcolare la soglia
 *da testare sul segnale ricevuto
 */
public class CalcolatoreSoglia {

	public CalcolatoreSoglia(){
	}

	/**
	 * Questo metodo calcola la soglia
	 * @param snr (Signal to Noise Ratio)
	 * @param length lunghezza del segnale
	 * @param prove numero di prove da fare OffLine
	 * @param pfa Probabilità di falso allarme
	 * @return il valore della soglia
	 */
	public double calcoloSoglia(double snr, int length, int prove, double pfa) {
		double[] energia = this.valutaEnergie(snr, length, prove);
		double th=0;
		double media = Functions.media(energia);
		double varianza = Functions.varianza(energia);
		try {
			th = media + (Math.sqrt(2*varianza) * Functions.invErf(1-2*pfa));
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return th;
	}
	
	/**
	 * Questo metodo per ogni prova genera un rumore randomico ne calcola l'energia e la salva in un array
	 * @param snr (Signal to Noise Ratio)
	 * @param length lunghezza del segnale
	 * @param prove numero di prove da fare OffLine
	 * @return array di energie
	 */
	private double[] valutaEnergie(double snr, int length, int prove){
		double[] energia= new double[prove];

		for(int i=0; i<prove; i++){
			GenericSignal noise = new Noise(snr, length);
			energia[i] = noise.evaluateEnergy(); 
		}
		return energia;
	}

	
}
