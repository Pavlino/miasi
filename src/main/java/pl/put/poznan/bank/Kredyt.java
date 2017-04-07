package pl.put.poznan.bank;

import pl.put.poznan.utils.TypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.GregorianCalendar;

public class Kredyt extends ProduktBankowy {

    private enum StatusKredytu { SPLACONY, NIESPLACONY, PRZETWARZANY };
	private RachunekBankowy rachunekPowiazany;
	private StatusKredytu status;
    private double odsetki;
	
	public Kredyt(Klient klient, String rachunek, RachunekBankowy rachunekPowiazany, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidInputException {
		this.klient = klient;
		this.numerRachunku = rachunek;
        this.rachunekPowiazany = rachunekPowiazany;
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
        status = StatusKredytu.NIESPLACONY;
        this.bank = rachunekPowiazany.getBank();
        historia = new Historia();
	}

    public void zaciagnijKredyt(double kwota) throws InvalidInputException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Zaciagniecie kredytu", TypyOperacjiBankowych.ZACIAGNIECIE_KREDYTU);
        operacjaBankowa.wplata(rachunekPowiazany, kwota);
        srodki = kwota;
    }

    public void splacKredyt() throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Splata kredytu", TypyOperacjiBankowych.SPLATA_KREDYTU);
       status = StatusKredytu.PRZETWARZANY;
        try {
            operacjaBankowa.przelew(rachunekPowiazany, this, srodki + odsetki);
            status = StatusKredytu.SPLACONY;
        } catch(NotEnoughFundsException e) {
            status = StatusKredytu.NIESPLACONY;
            throw new NotEnoughFundsException();
        }
    }

    @Override
    public void setSrodki(double srodki) {
        if (this.srodki > 0 && status != StatusKredytu.PRZETWARZANY) {
            throw new UnsupportedOperationException("Kredyt nie zostal splacony.");
        }
        this.srodki = srodki;
    }

    @Override
    public void setOdsetki(double odsetki) {
        this.odsetki += odsetki - srodki;
    }

    public double getOdsetki() {
        return odsetki;
    }

}
