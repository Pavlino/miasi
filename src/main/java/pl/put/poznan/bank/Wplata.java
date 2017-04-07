package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

import java.util.GregorianCalendar;

public class Wplata implements IOperacjaBankowa {

    private ProduktBankowy konto;
    private double kwota;
    private GregorianCalendar data;

    public Wplata(ProduktBankowy konto, double kwota) {
        this.konto = konto;
        this.kwota = kwota;
        data = new GregorianCalendar();
    }

    public void wykonaj() throws InvalidInputException {
        if (konto != null && kwota > 0) {
            double stanSrodkow = konto.getSrodki();
            stanSrodkow += kwota;
            konto.setSrodki(stanSrodkow);
            dodajDoHistorii();
        } else {
            throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
        }
    }

    public GregorianCalendar getData() {
        return data;
    }

    private void dodajDoHistorii() throws InvalidInputException {
        konto.dodajOperacjeDoHistorii(this);
        konto.dodajOperacjeDoHistoriiBanku(this);
    }
}