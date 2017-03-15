package pl.put.poznan.bank;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;

import pl.put.poznan.utils.InvalidInputException;

public class Raport {
	private ArrayList<ProduktBankowy> raportDebetowy;
	
	public Raport(){
		this.raportDebetowy = new ArrayList<ProduktBankowy>();
	}
	
	public ArrayList<ProduktBankowy> getRaport() {
		return raportDebetowy;
	}

	public void setRaport(ArrayList<ProduktBankowy> raport) {
		this.raportDebetowy = raport;
	}
	
	public void dodajProdukt(ProduktBankowy produkt) throws InvalidInputException{
		if(produkt != null){
			Double stanSrodkow = produkt.getStanSrodkow();
			if(stanSrodkow<0){
				raportDebetowy.add(produkt);
				System.out.println("Dodano do raportu produkt z debetem");
			}
			else{
				System.out.println("Na tym produkcie nie ma debetu");
			}
		}
		else{
			throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
		}
	}
	
	public void usunProdukt(ProduktBankowy produkt){
		raportDebetowy.remove(produkt);
	}
}
