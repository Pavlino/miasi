package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import java.util.GregorianCalendar;

public class PrzelewMiedzybankowyOdrzucony extends OperacjaBankowa implements IOperacjaBankowa{

    private Bank bankDocelowy;

    public PrzelewMiedzybankowyOdrzucony(Bank bankDocelowy, double kwota, String opis) {
        super(kwota, opis);
        this.bankDocelowy = bankDocelowy;
        typ = TypyOperacjiBankowych.PRZELEW_MIEDZYBANKOWY_ODRZUCONY;
    }

    public PrzelewMiedzybankowyOdrzucony(GregorianCalendar data, Bank bankDocelowy, double kwota, String opis) {
        super(data, kwota, opis);
        this.bankDocelowy = bankDocelowy;
        typ = TypyOperacjiBankowych.WPLATA;
    }

    public void wykonaj(ProduktBankowy konto) throws InvalidBankOperationException {
        dodajDoHistorii();
        wykonana = true;
    }
}
