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

	public void visit(RachunekBankowyDebetowy produkt) throws NotDebetException, InvalidBankOperationException {
		if (produkt != null) {
			//Double stanSrodkow = produkt.getDebet().getKwotaDebetu();
			if (produkt.getKwotaDebetu() > 0) {
				listaKont.put(produkt.getNumerRachunku(), produkt);
				System.out.println("Dodano do raportu produkt z debetem");
			} else {
				throw new NotDebetException("Na tym produkcie nie ma debetu");
			}
		} else {
			throw new InvalidBankOperationException("Produkt nie istnieje");
		}
	}
	
	public HashMap<String, ProduktBankowy> getListaKont() {
	    return listaKont;
	  }

	@Override
	public void visit(RachunekBankowy produkt) {
		// TODO w tej chwili nic nie robi, bo nie ma nic robiæ
		
	}

	@Override
	public void visit(ProduktBankowy produkt) {
		// TODO j/w
		
	}


}
