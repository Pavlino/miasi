package pl.put.poznan.bank;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import pl.put.poznan.utils.InvalidInputException;

public class RaportDebetowy implements IRaport {
	private ArrayList<ProduktBankowy> raportDebetowy;
	
	public RaportDebetowy(){
		this.raportDebetowy = new ArrayList<ProduktBankowy>();
	}
	
	public ArrayList<ProduktBankowy> generujRaport(HashMap<Long, ProduktBankowy> listaProduktow) {
        for (ProduktBankowy produktBankowy : listaProduktow.values()) {
            try {
                this.dodajProdukt(produktBankowy);
            } catch (InvalidInputException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return this.raportDebetowy;
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

}
