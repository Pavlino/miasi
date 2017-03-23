package pl.put.poznan.bank;

import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.Date;

public class Kredyt extends ProduktBankowy {
	private RachunekBankowy rachunekPowiazany;
	private boolean splacony;
    private double odsetki;
	
	public Kredyt(Klient klient, String rachunek, RachunekBankowy rachunekPowiazany, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidInputException {
		this.klient = klient;
		this.numerRachunku = rachunek;
        this.rachunekPowiazany = rachunekPowiazany;
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
        this.splacony = false;
        this.bank = rachunekPowiazany.getBank();
        this.historia = new Historia();
	}

    public void zaciagnijKredyt(double kwota) throws InvalidInputException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Zaciagniecie kredytu", ITypyOperacjiBankowych.ZACIAGNIECIE_KREDYTU);
        operacjaBankowa.wplata(rachunekPowiazany, kwota);
        this.srodki = kwota;
    }

    public void splacKredyt() throws NotEnoughFundsException, InvalidInputException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Splata kredytu", ITypyOperacjiBankowych.SPLATA_KREDYTU);
        operacjaBankowa.przelew(rachunekPowiazany, this, this.srodki + this.odsetki);
        this.splacony = true;
    }

    @Override
    public void setSrodki(double srodki) {
        if (this.srodki > 0 && !this.splacony) {
            throw new UnsupportedOperationException("Kredyt nie zostal splacony.");
        }
        this.srodki = srodki;
    }

    @Override
    public void setOdsetki(double odsetki) {
        this.odsetki += odsetki - this.srodki;
    }

    public double getOdsetki() {
        return this.odsetki;
    }

}
