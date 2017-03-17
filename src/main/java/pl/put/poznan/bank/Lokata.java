package pl.put.poznan.bank;

import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.Date;

public class Lokata extends ProduktBankowy {
	private Date dataKonca;
	private RachunekBankowy rachunekPowiazany;
    private double kwota;

    public Lokata(int id, String nr, RachunekBankowy rachunekPowiazany, IMechanizmOdsetkowy mechanizmOdsetkowy, double kwota) throws InvalidInputException, NotEnoughFundsException {
        this.idKlienta = id;
        this.numerRachunku = nr;
        this.rachunekPowiazany = rachunekPowiazany;
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Otworzenie lokaty", ITypyOperacjiBankowych.OTWORZENIE_LOKATY);
        operacjaBankowa.przelew(this.rachunekPowiazany, this, this.kwota);
        this.kwota = kwota;
        this.bank = rachunekPowiazany.getBank();
    }

    public void zerwijLokate() throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Zerwanie lokaty", ITypyOperacjiBankowych.ZERWANIE_LOKATY);
        operacjaBankowa.przelew(this, this.rachunekPowiazany, this.kwota);
        this.stanOdsetek = 0;
    }

    public void rozwiazLokate() throws InvalidInputException, NotEnoughFundsException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Rozwiazanie lokaty", ITypyOperacjiBankowych.ROZWIAZANIE_LOKATY);
        operacjaBankowa.przelew(this, this.rachunekPowiazany, this.kwota + this.stanOdsetek);
    }

    @Override
    public double getStanSrodkow() {
        return this.kwota;
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
    public double getKwota() {
        return this.kwota;
    }
    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

}
