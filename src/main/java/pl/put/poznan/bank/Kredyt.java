package pl.put.poznan.bank;

import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.Date;

public class Kredyt extends ProduktBankowy {
	private RachunekBankowy rachunekPowiazany;
	private double kwota; // pozostala do splacenia
	private boolean splacony;//
	
	public Kredyt(int id, long rachunek, RachunekBankowy rachunekPowiazany, IMechanizmOdsetkowy mechanizmOdsetkowy, double kwota) throws InvalidInputException {
		this.idKlienta = id;
		this.numerRachunku = rachunek;
        this.rachunekPowiazany = rachunekPowiazany;
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Zaciagniecie kredytu", ITypyOperacjiBankowych.ZACIAGNIECIE_KREDYTU);
        operacjaBankowa.wplata(rachunekPowiazany, kwota);
        this.kwota = kwota;
        this.splacony = false;
        this.bank = rachunekPowiazany.getBank();
	}

    public void splacKredyt() throws NotEnoughFundsException, InvalidInputException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Splata kredytu", ITypyOperacjiBankowych.SPLATA_KREDYTU);
        operacjaBankowa.przelew(rachunekPowiazany, this, this.kwota + this.stanOdsetek);
        this.splacony = true;
    }

    @Override
    public double getStanSrodkow() {
        return this.kwota;
    }

}
