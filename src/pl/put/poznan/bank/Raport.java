package pl.put.poznan.bank;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Raport {
	private ArrayList<ProduktBankowy> raportDebetowy;
	
	public ArrayList<ProduktBankowy> getRaport() {
		return raportDebetowy;
	}

	public void setRaport(ArrayList<ProduktBankowy> raport) {
		this.raportDebetowy = raport;
	}

	public Raport(){
		this.raportDebetowy = new ArrayList<ProduktBankowy>();
	}
	
	public void dodajProdukt(ProduktBankowy produkt){
		raportDebetowy.add(produkt);
	}
	
	public void usunProdukt(ProduktBankowy produkt){
		raportDebetowy.remove(produkt);
	}
}
