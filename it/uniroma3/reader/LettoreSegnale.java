package it.uniroma3.reader;

import it.uniroma3.model.GenericSignal;
import it.uniroma3.model.Signal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Classe che dato un percorso file calcola la lunghezza del file 
 * e genera un segnale GenericSignal in base ai campioni del file
 */
public class LettoreSegnale {

	String percorsoFile;

	public LettoreSegnale(String percorsoFile){
		this.percorsoFile=percorsoFile;
	}
	
	/**
	 * Metodo che genera un GenericSignal a partire da un file.
	 * @return un GenericSignal iniziallizzato con i dati letti da file
	 */
	public GenericSignal letturaFile() {
		Scanner scan;
		File file = new File(percorsoFile);
		int length = this.getLunghezzaFile();
		GenericSignal signal = new Signal(length);
		try {
			scan = new Scanner(file);
			int i=0;
			while(scan.hasNext()) {
				signal.addParteReale(i, Double.parseDouble(scan.next()));
				signal.addParteImmaginaria(i,Double.parseDouble(scan.next()));
				i++;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return signal;
	}

	/**
	 * Metodo che calcola la lunghezza in righe del file
	 * @return numero di righe del file
	 */
	public int getLunghezzaFile(){
		BufferedReader reader;
		int righeFile = 0;
		try {
			reader = new BufferedReader(new FileReader(this.percorsoFile));
			while (reader.readLine() != null) 
				righeFile++;
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return righeFile;
	}
}
