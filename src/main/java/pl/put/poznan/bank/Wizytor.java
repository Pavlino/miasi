package pl.put.poznan.bank;

import java.util.HashMap;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.NotDebetException;

public class Wizytor implements IWizytor {
	
	private HashMap<String, ProduktBankowy> listaKont;
	
	public Wizytor()
	{
		listaKont = new HashMap<String, ProduktBankowy>();
	}

	public void visit(RachunekBankowy produkt) throws NotDebetException, InvalidBankOperationException {
		if (produkt != null) {
			//Double stanSrodkow = produkt.getDebet().getKwotaDebetu();
			if (produkt.czyPosiadaDebet()) {
				if (produkt.getKwotaDebetu() > 0) {
					listaKont.put(produkt.getNumerRachunku(), produkt);
					System.out.println("Dodano do raportu produkt z debetem");
				} else {
					throw new NotDebetException("Na tym produkcie nie ma debetu");
				}
			} else {
				throw new NotDebetException("Konto nie jest debetowe");
			}
		} else {
			throw new InvalidBankOperationException("Produkt nie istnieje");
		}		
	}
	
	public HashMap<String, ProduktBankowy> getListaKont() {
	    return listaKont;
	  }


}
