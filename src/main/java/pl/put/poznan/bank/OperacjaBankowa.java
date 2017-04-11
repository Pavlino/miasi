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

    public OperacjaBankowa(ProduktBankowy konto, double kwota, String opis) {
        this.konto = konto;
        this.kwota = kwota;
        this.opis = opis;
        data = new GregorianCalendar();
        wykonana = false;
    }

    public OperacjaBankowa(ProduktBankowy konto, double kwota, String opis, int typ) {
        this.konto = konto;
        this.kwota = kwota;
        this.opis = opis;
        this.typ = typ;
        data = new GregorianCalendar();
        wykonana = false;
    }

    public OperacjaBankowa(GregorianCalendar data, ProduktBankowy konto, double kwota, String opis) {
        this.data = data;
        this.konto = konto;
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
