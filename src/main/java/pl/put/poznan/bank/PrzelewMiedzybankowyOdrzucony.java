package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import java.util.GregorianCalendar;

public class PrzelewMiedzybankowyOdrzucony extends PrzelewMiedzybankowy implements IOperacjaBankowa {

    private ProduktBankowy kontoDocelowe;
    private Bank bankDocelowy;

    public PrzelewMiedzybankowyOdrzucony(ProduktBankowy kontoDocelowe, Bank bankDocelowy, double kwota, String opis) {
        super(kontoDocelowe, bankDocelowy, kwota, opis);
        typ = TypyOperacjiBankowych.PRZELEW_MIEDZYBANKOWY_ODRZUCONY;
    }

    public PrzelewMiedzybankowyOdrzucony(ProduktBankowy kontoDocelowe, Bank bankDocelowy, double kwota, String opis, int typ) {
        super(kontoDocelowe, bankDocelowy, kwota, opis, typ);
        this.typ = TypyOperacjiBankowych.WPLATA;
    }

    public PrzelewMiedzybankowyOdrzucony(GregorianCalendar data, ProduktBankowy kontoDocelowe, Bank bankDocelowy, double kwota, String opis) {
        super(data, kontoDocelowe, bankDocelowy, kwota, opis);
        typ = TypyOperacjiBankowych.PRZELEW_MIEDZYBANKOWY_ODRZUCONY;
    }

    public void wykonaj(ProduktBankowy konto) throws InvalidBankOperationException {
        dodajDoHistorii();
        wykonana = true;
    }

    public ProduktBankowy getKontoDocelowe() {
        return kontoDocelowe;
    }

    public Bank getBankDocelowy() {
        return bankDocelowy;
    }
}
