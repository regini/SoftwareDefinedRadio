package it.uniroma3.model;

/**
 * Classe astratta che rappresenta un generico segnale.
 * I campioni sono rappresentati da due Array una per la parte reale ed uno per la parte immaginaria per motivi 
 * di ottimizzazione del codice.
 */
public abstract class GenericSignal {
	private int length;
	private double[] parteReale;
	private double[] parteImmaginaria;

	public GenericSignal(int length){
		this.length = length;
		this.parteReale = new double[length];
		this.parteImmaginaria = new double[length];
	}

	/**
	 * @param index posizione del segnale campionato in cui aggiungere il valore della parte reale value
	 * @param value valore da inserire in posizione index
	 */
	public void addParteReale(int index, double value){
		this.parteReale[index]=value;
	}
	
	/**
	 * @param index posizione del segnale campionato in cui aggiungere il valore della parte immaginaria value
	 * @param value valore da inserire in posizione index
	 */
	public void addParteImmaginaria(int index, double value){
		this.parteImmaginaria[index]=value;
	}

	/**
	 * @return numero di campioni del segnale
	 */
	public int getLength(){
		return this.length;
	}
	
	/**
	 * @param index posizione di cui si vuole avere la parte reale del segnale campionato
	 * @return valore in posizione index del segnale
	 */
	public double getParteReale(int index){
		return this.parteReale[index];
	}

	/**
	 * @param index posizione di cui si vuole avere la parte immaginaria del segnale campionato
	 * @return valore in posizione index del segnale
	 */
	public double getParteImmaginaria(int index){
		return this.parteImmaginaria[index];
	}

	/**
	 * Calcola l'energia del segnale
	 * @return energia del segnale
	 */
	public double evaluateEnergy() {
		double z;
		double somma = 0;
		for(int i=0; i<this.length; i++){
			double moduloQuadro = Math.pow(this.getParteReale(i), 2) + Math.pow(this.getParteImmaginaria(i), 2);
			somma = somma + moduloQuadro;
		}
		z=somma/this.getLength();
		return z;
	}

	/**
	 * Calcola SNR del segnale
	 * @return il valore in dB del Signal to Noise Ratio
	 * @exception lancia l'eccezione se l'energia del rumore è negativa
	 */
	public double getSNR() throws Exception{
		double energiaRumore = this.evaluateEnergy() - 1;
		if(energiaRumore<0) {
			throw new Exception("Energia negativa");
		}
		if(energiaRumore > 0)
			return 10*Math.log10(1/energiaRumore);
		return Double.POSITIVE_INFINITY;
	}
}
