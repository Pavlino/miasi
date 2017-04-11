package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import java.util.GregorianCalendar;

public class Wyplata extends OperacjaBankowa implements IOperacjaBankowa {

    public Wyplata(ProduktBankowy konto, double kwota, String opis) {
        super(konto, kwota, opis);
        typ = TypyOperacjiBankowych.WYPLATA;
    }

    public Wyplata(ProduktBankowy konto, double kwota, String opis, int typ) {
        super(konto, kwota, opis, typ);
    }

    public Wyplata(GregorianCalendar data, ProduktBankowy konto, double kwota, String opis) {
        super(data, konto, kwota, opis);
    }

    public void wykonaj() throws InvalidBankOperationException {
        if (konto != null && kwota > 0) {
			double stanSrodkow = konto.getSrodki();
			if (stanSrodkow >= kwota) {
				stanSrodkow -= kwota;
                konto.setSrodki(stanSrodkow);
                dodajDoHistorii();
            } else {
				throw new InvalidBankOperationException("Brak wystarczajacych srodkow na koncie");
			}
		} else {
			throw new InvalidBankOperationException("Konto nie istnieje lub podana kwota jest ujemna");
		}
    }

}
