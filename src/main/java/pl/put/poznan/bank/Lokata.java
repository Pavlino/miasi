package pl.put.poznan.bank;

import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Lokata extends ProduktBankowy {
	private Calendar dataKonca;
	private RachunekBankowy rachunekPowiazany;
    private double odsetki;

    public Lokata(Klient klient, String nr, RachunekBankowy rachunekPowiazany, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidInputException, NotEnoughFundsException {
        this.klient = klient;
        this.numerRachunku = nr;
        this.rachunekPowiazany = rachunekPowiazany;
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
        this.bank = rachunekPowiazany.getBank();
        this.historia = new Historia();
    }

    public void otworzLokate(double kwota, Calendar dataKonca) throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Otworzenie lokaty", ITypyOperacjiBankowych.OTWORZENIE_LOKATY);
        operacjaBankowa.przelew(this.rachunekPowiazany, this, kwota);
        this.bank = rachunekPowiazany.getBank();
        this.dataKonca = dataKonca;
    }

    public void zerwijLokate() throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Zerwanie lokaty", ITypyOperacjiBankowych.ZERWANIE_LOKATY);
        this.dataKonca = new GregorianCalendar(1,1,1);
        operacjaBankowa.przelew(this, this.rachunekPowiazany, this.srodki);
        this.odsetki = 0;
    }

    public void rozwiazLokate() throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Rozwiazanie lokaty", ITypyOperacjiBankowych.ROZWIAZANIE_LOKATY);
        this.srodki += odsetki;
        operacjaBankowa.przelew(this, this.rachunekPowiazany, this.srodki);
        this.odsetki = 0;
    }

    @Override
    public void setOdsetki(double odsetki) {
        this.odsetki += odsetki - this.srodki;
    }

    public double getOdsetki() {
        return this.odsetki;
    }

    @Override
    public void setSrodki(double srodki) {
        if (this.srodki > 0 && this.dataKonca.after(new GregorianCalendar())) {
            throw new UnsupportedOperationException("Lokata nie zostala jeszcze rozwiazana,");
        }
        this.srodki = srodki;
    }

    public Calendar getDataKonca() {
		return dataKonca;
	}
	public void setDataKonca(Calendar dataKonca) {
		this.dataKonca = dataKonca;
	}
	public RachunekBankowy getRachunekPowiazany() {
		return this.rachunekPowiazany;
	}
	public void setRachunekPowiazany(RachunekBankowy rachunekPowiazany) {
		this.rachunekPowiazany = rachunekPowiazany;
	}

}
