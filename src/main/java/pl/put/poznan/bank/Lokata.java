package pl.put.poznan.bank;

import pl.put.poznan.utils.TypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.Calendar;
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
        bank = rachunekPowiazany.getBank();
        historia = new Historia();
    }

    public void otworzLokate(double kwota, Calendar dataKonca) throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Otworzenie lokaty", TypyOperacjiBankowych.OTWORZENIE_LOKATY);
        operacjaBankowa.przelew(this.rachunekPowiazany, this, kwota);
        bank = rachunekPowiazany.getBank();
        this.dataKonca = dataKonca;
    }

    public void zerwijLokate() throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Zerwanie lokaty", TypyOperacjiBankowych.ZERWANIE_LOKATY);
        dataKonca = new GregorianCalendar(1,1,1);
        operacjaBankowa.przelew(this, rachunekPowiazany, srodki);
        odsetki = 0;
    }

    public void rozwiazLokate() throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Rozwiazanie lokaty", TypyOperacjiBankowych.ROZWIAZANIE_LOKATY);
        srodki += odsetki;
        operacjaBankowa.przelew(this, rachunekPowiazany, srodki);
        odsetki = 0;
    }

    @Override
    public void setOdsetki(double odsetki) {
        this.odsetki += odsetki - this.srodki;
    }

    public double getOdsetki() {
        return odsetki;
    }

    @Override
    public void setSrodki(double srodki) {
        if (this.srodki > 0 && dataKonca.after(new GregorianCalendar())) {
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
		return rachunekPowiazany;
	}
	public void setRachunekPowiazany(RachunekBankowy rachunekPowiazany) {
		this.rachunekPowiazany = rachunekPowiazany;
	}

}
