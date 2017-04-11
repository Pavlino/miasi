package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;

import java.util.GregorianCalendar;

public abstract class OperacjaBankowa implements IOperacjaBankowa {
    ProduktBankowy konto;
    double kwota;
    GregorianCalendar data;
    boolean wykonana;
    int typ;
    String opis;

    public OperacjaBankowa(double kwota, String opis) {
        this.kwota = kwota;
        this.opis = opis;
        data = new GregorianCalendar();
        wykonana = false;
    }

    public OperacjaBankowa(double kwota, String opis, int typ) {
        this.kwota = kwota;
        this.opis = opis;
        this.typ = typ;
        data = new GregorianCalendar();
        wykonana = false;
    }

    public OperacjaBankowa(GregorianCalendar data, double kwota, String opis) {
        this.data = data;
        this.kwota = kwota;
        this.opis = opis;
        wykonana = false;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public int getTyp() {
        return typ;
    }

    void dodajDoHistorii() throws InvalidBankOperationException {
        konto.dodajOperacjeDoHistorii(this);
        konto.dodajOperacjeDoHistoriiBanku(this);
    }

}
