package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import java.util.GregorianCalendar;

public class Wplata extends OperacjaBankowa implements IOperacjaBankowa {

    public Wplata(ProduktBankowy konto, double kwota, String opis) {
        super(konto, kwota, opis);
        typ = TypyOperacjiBankowych.WPLATA;
    }

    public Wplata(ProduktBankowy konto, double kwota, String opis, int typ) {
        super(konto, kwota, opis, typ);
    }

    public Wplata(GregorianCalendar data, ProduktBankowy konto, double kwota, String opis) {
        super(data, konto, kwota, opis);
    }

    public void wykonaj() throws InvalidBankOperationException {
        if (konto != null && kwota > 0 && !wykonana) {
            if (typ == TypyOperacjiBankowych.ODSETKI) {
                konto.setOdsetki(kwota);
            } else {
                double stanSrodkow = konto.getSrodki();
                stanSrodkow += kwota;
                konto.setSrodki(stanSrodkow);
            }
            dodajDoHistorii();
            wykonana = true;
        } else {
            throw new InvalidBankOperationException("Konto nie istnieje lub podana kwota jest ujemna");
        }
    }

}
