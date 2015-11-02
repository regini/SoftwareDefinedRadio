package it.uniroma3.model;

/**
 * Classe segnale che estende la classe GenericSignal.
 * Questa classe genera un segnale randomico con Potenza Unitaria
 * e definisce anche un metodo aggiungi rumore che aggiunge rumore ad un segnale
 */
public class Signal extends GenericSignal{
	
	public Signal(int length){
		super(length);
	}
	
	/**
	 * Inizializza il segnale con valori randomici
	 */
	public void generateRandomSignal() {
		for(int i = 0; i < this.getLength(); i++) {
			double v = Math.random();
			if(v < 0.5)
				super.addParteReale(i, 1/Math.sqrt(2));
			else
				super.addParteReale(i, -1/Math.sqrt(2));
			
			double p = Math.random();
			if(p < 0.5)
				super.addParteImmaginaria(i, 1/Math.sqrt(2));
			else
				super.addParteImmaginaria(i, -1/Math.sqrt(2));
		}
	}
	
	/**
	 * Metodo che aggiunge rumore al segnale
	 * @param snr (Signal to Noise Ratio)
	 * @return segnale utile s[n] più segnale rumore w[n]
	 */
	public GenericSignal aggiungiRumore(double snr){
		GenericSignal noise = new Noise(snr, this.getLength());
		
		GenericSignal signal_noise = new Signal(this.getLength());
		for(int i=0; i<this.getLength(); i++){
			double sommaReale = this.getParteReale(i)+noise.getParteReale(i);
			signal_noise.addParteReale(i, sommaReale);
			double sommaImmaginaria = this.getParteImmaginaria(i)+noise.getParteImmaginaria(i);
			signal_noise.addParteImmaginaria(i, sommaImmaginaria);
		}
		return signal_noise;
	}
}

