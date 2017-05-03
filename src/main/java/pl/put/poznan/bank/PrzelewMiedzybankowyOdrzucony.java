package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import java.util.GregorianCalendar;

public class PrzelewMiedzybankowyOdrzucony extends OperacjaBankowa implements IOperacjaBankowa {

    private ProduktBankowy kontoZrodlowe;
    private ProduktBankowy kontoDocelowe;
    private Bank bankZrodlowy;
    private Bank bankDocelowy;

    public PrzelewMiedzybankowyOdrzucony(PrzelewMiedzybankowy przelewMiedzybankowy, String opis) {
        super(przelewMiedzybankowy.getKwota(), opis);
        kontoZrodlowe = przelewMiedzybankowy.getKontoDocelowe();
        bankZrodlowy = przelewMiedzybankowy.getBankDocelowy();
        kontoDocelowe = przelewMiedzybankowy.getKontoZrodlowe();
        bankDocelowy = przelewMiedzybankowy.getBankZrodlowy();
        typ = TypyOperacjiBankowych.PRZELEW_MIEDZYBANKOWY_ODRZUCONY;
    }

    public void wykonaj(ProduktBankowy konto) throws InvalidBankOperationException {
        if (konto != null && kwota > 0) {
            if (!wykonana) {
                this.konto = konto;
                double stanSrodkow = konto.getSrodki();
                stanSrodkow += kwota;
                konto.setSrodki(stanSrodkow);
                dodajDoHistorii();
                wykonana = true;
            } else {
                throw new InvalidBankOperationException("Niewystarczajace srodki.");
            }
        } else {
            throw new InvalidBankOperationException("Konto nie istnieje lub podana kwota jest ujemna");
        }
    }

    public ProduktBankowy getKontoDocelowe() {
        return kontoDocelowe;
    }

    public Bank getBankDocelowy() {
        return bankDocelowy;
    }
}
