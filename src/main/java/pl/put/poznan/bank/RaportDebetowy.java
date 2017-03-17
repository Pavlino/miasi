package pl.put.poznan.bank;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotDebetException;

public class RaportDebetowy implements IRaport {
	public ArrayList<ProduktBankowy> getRaportDebetowy() {
		return raportDebetowy;
	}

	public void setRaportDebetowy(ArrayList<ProduktBankowy> raportDebetowy) {
		this.raportDebetowy = raportDebetowy;
	}

	private ArrayList<ProduktBankowy> raportDebetowy;
	
	public RaportDebetowy(){
		this.raportDebetowy = new ArrayList<ProduktBankowy>();
	}
	
	public ArrayList<ProduktBankowy> generujRaport(HashMap<Long, ProduktBankowy> listaProduktow) throws NotDebetException {
        for (ProduktBankowy produktBankowy : listaProduktow.values()) {
            try {
                this.dodajProdukt((RachunekBankowy) produktBankowy);
            } catch (InvalidInputException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return this.raportDebetowy;
	}
	
	public void dodajProdukt(RachunekBankowy produkt) throws InvalidInputException, NotDebetException {
		if(produkt != null){
			Double stanSrodkow = produkt.getDebet().getKwotaDebetu();
			if(stanSrodkow>0){
				raportDebetowy.add(produkt);
				System.out.println("Dodano do raportu produkt z debetem");
			}
			else{
				throw new NotDebetException("Na tym produkcie nie ma debetu");
			}
		}
		else{
			throw new InvalidInputException("Produkt nie istnieje");
		}
	}

}
