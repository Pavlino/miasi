package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import java.util.GregorianCalendar;

public class PrzelewMiedzybankowy extends OperacjaBankowa implements IOperacjaBankowa {

    private ProduktBankowy kontoZrodlowe;
    private ProduktBankowy kontoDocelowe;
    private Bank bankZrodlowy;
    private Bank bankDocelowy;

    public PrzelewMiedzybankowy(ProduktBankowy kontoDocelowe, Bank bankDocelowy, double kwota, String opis) {
        super(kwota, opis);
        this.kontoDocelowe = kontoDocelowe;
        this.bankDocelowy = bankDocelowy;
        typ = TypyOperacjiBankowych.PRZELEW_MIEDZYBANKOWY;
    }

    public ProduktBankowy getKontoDocelowe() {
		return kontoDocelowe;
	}

	public void setKontoDocelowe(ProduktBankowy kontoDocelowe) {
		this.kontoDocelowe = kontoDocelowe;
	}

	public Bank getBankDocelowy() {
		return bankDocelowy;
	}

	public void setBankDocelowy(Bank bankDocelowy) {
		this.bankDocelowy = bankDocelowy;
	}

    public Bank getBankZrodlowy() {
        return bankZrodlowy;
    }

    public ProduktBankowy getKontoZrodlowe() {
        return kontoZrodlowe;
    }

	public PrzelewMiedzybankowy(ProduktBankowy kontoDocelowe, Bank bankDocelowy, double kwota, String opis, int typ) {
        super(kwota, opis, typ);
        this.kontoDocelowe = kontoDocelowe;
        this.bankDocelowy = bankDocelowy;
    }

    public PrzelewMiedzybankowy(GregorianCalendar data, ProduktBankowy kontoDocelowe, Bank bankDocelowy, double kwota, String opis) {
        super(data, kwota, opis);
        this.kontoDocelowe = kontoDocelowe;
        this.bankDocelowy = bankDocelowy;
        typ = TypyOperacjiBankowych.PRZELEW_MIEDZYBANKOWY;
    }

    public void wykonaj(ProduktBankowy konto) throws InvalidBankOperationException {
        if (konto != null && kwota > 0 && !konto.equals(kontoDocelowe)) {
            if (!wykonana) {
                this.konto = konto;
                double stanSrodkow = konto.getSrodki();
                if (stanSrodkow >= kwota) {
                    stanSrodkow -= kwota;
                    konto.setSrodki(stanSrodkow);
                    dodajDoHistorii();
                    kontoZrodlowe = konto;
                    bankZrodlowy = konto.getBank();
                    wykonana = true;
                } else {
                    throw new InvalidBankOperationException("Niewystarczajace srodki.");
                }
            } else {
                if (bankDocelowy.czyZawieraKonto(kontoDocelowe)) {
                    konto = kontoDocelowe;
                    double stanSrodkow = konto.getSrodki();
                    stanSrodkow += kwota;
                    konto.setSrodki(stanSrodkow);
                    dodajDoHistorii();
                } else {
                    throw new InvalidBankOperationException("Konto nie istnieje");
                }
            }
        } else {
            throw new InvalidBankOperationException("Konto nie istnieje lub podana kwota jest ujemna");
        }
    }

}
