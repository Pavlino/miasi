package pl.put.poznan.bank;

import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.Date;

public class Lokata extends ProduktBankowy {
	private Date dataKonca;
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

    public void otworzLokate(double kwota) throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Otworzenie lokaty", ITypyOperacjiBankowych.OTWORZENIE_LOKATY);
        operacjaBankowa.przelew(this.rachunekPowiazany, this, kwota);
        this.bank = rachunekPowiazany.getBank();
    }

    public void zerwijLokate() throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Zerwanie lokaty", ITypyOperacjiBankowych.ZERWANIE_LOKATY);
        operacjaBankowa.przelew(this, this.rachunekPowiazany, this.srodki);
        this.odsetki = 0;
    }

    public void rozwiazLokate() throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Rozwiazanie lokaty", ITypyOperacjiBankowych.ROZWIAZANIE_LOKATY);
        operacjaBankowa.przelew(this, this.rachunekPowiazany, this.srodki + this.odsetki);
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
        if (this.dataKonca.after(new Date())) {
            throw new UnsupportedOperationException("Lokata nie zostala jeszcze rozwiazana,");
        }
    }


    public Date getDataKonca() {
		return dataKonca;
	}
	public void setDataKonca(Date dataKonca) {
		this.dataKonca = dataKonca;
	}
	public RachunekBankowy getRachunekPowiazany() {
		return this.rachunekPowiazany;
	}
	public void setRachunekPowiazany(RachunekBankowy rachunekPowiazany) {
		this.rachunekPowiazany = rachunekPowiazany;
	}

}
