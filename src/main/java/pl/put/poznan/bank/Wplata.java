package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import java.util.GregorianCalendar;

public class Wplata extends OperacjaBankowa implements IOperacjaBankowa {

    public Wplata(double kwota, String opis) {
        super(kwota, opis);
        typ = TypyOperacjiBankowych.WPLATA;
    }

    public Wplata(double kwota, String opis, int typ) {
        super(kwota, opis, typ);
    }

    public Wplata(GregorianCalendar data, double kwota, String opis) {
        super(data, kwota, opis);
        typ = TypyOperacjiBankowych.WPLATA;
    }

    public void wykonaj(ProduktBankowy konto) throws InvalidBankOperationException {
        if (konto != null && kwota > 0 && !wykonana) {
            this.konto = konto;
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
