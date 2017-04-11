package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import java.util.GregorianCalendar;

public class Przelew extends OperacjaBankowa implements IOperacjaBankowa {

    private ProduktBankowy kontoDocelowe;

    public Przelew(ProduktBankowy kontoDocelowe, double kwota, String opis) {
        super(kwota, opis);
        this.kontoDocelowe = kontoDocelowe;
        typ = TypyOperacjiBankowych.PRZELEW;
    }

    public Przelew(ProduktBankowy kontoDocelowe, double kwota, String opis, int typ) {
        super(kwota, opis, typ);
        this.kontoDocelowe = kontoDocelowe;
    }

    public Przelew(GregorianCalendar data, ProduktBankowy kontoDocelowe, double kwota, String opis) {
        super(data, kwota, opis);
        this.kontoDocelowe = kontoDocelowe;
    }

    public void wykonaj(ProduktBankowy konto) throws InvalidBankOperationException {
        if (konto != null && kontoDocelowe != null && kwota > 0 && !konto.equals(kontoDocelowe)) {
            this.konto = konto;
            double stanSrodkow = konto.getSrodki();
			if (stanSrodkow >= kwota) {
                stanSrodkow -= kwota;
                konto.setSrodki(stanSrodkow);
                stanSrodkow = kontoDocelowe.getSrodki();
                stanSrodkow += kwota;
                kontoDocelowe.setSrodki(stanSrodkow);
                dodajDoHistorii();
			} else {
				throw new InvalidBankOperationException("Niewystarczajace srodki.");
			}
		} else {
			throw new InvalidBankOperationException("Konto nie istnieje lub podana kwota jest ujemna");
		}
    }

    @Override
    void dodajDoHistorii() throws InvalidBankOperationException {
        konto.dodajOperacjeDoHistorii(this);
        konto.dodajOperacjeDoHistoriiBanku(this);
        kontoDocelowe.dodajOperacjeDoHistorii(this);
        kontoDocelowe.dodajOperacjeDoHistoriiBanku(this);
    }

}
