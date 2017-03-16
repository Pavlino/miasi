package pl.put.poznan.bank;

import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;

import java.util.Date;

public class Lokata extends RachunekBankowy {
	int id;
	Date dataKonca;
	RachunekBankowy rachunekPowiazany;
    double kwota;
    double odsetki;
	
	public Lokata(int id, String nr){
		super(id, nr);
	}

    public Lokata(int id, String nr, RachunekBankowy rachunekPowiazany, IMechanizmOdsetkowy mechanizmOdsetkowy, double kwota) {
        super(id, nr);
        this.rachunekPowiazany = rachunekPowiazany;
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
        this.kwota = kwota;
    }

    public void zerwijLokate() throws InvalidInputException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Zerwanie lokaty", ITypyOperacjiBankowych.ZERWANIE_LOKATY);
        operacjaBankowa.wplata(this.rachunekPowiazany, this.kwota);
        this.rachunekPowiazany = null;
    }

    public void rozwiazLokate() throws InvalidInputException {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Wyplata lokaty", ITypyOperacjiBankowych.WYPLATA_LOKATY);
        operacjaBankowa.wplata(this.rachunekPowiazany, this.kwota + this.odsetki);
    }

    @Override
    public void setStanSrodkow(double stanSrodkow) {
        this.odsetki += stanSrodkow - this.kwota;
    }

    @Override
    public double getStanSrodkow() {
        return this.kwota;
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
    public double getOdsetki() {
        return this.odsetki;
    }
    public void setOdsetki(double odsetki) {
        this.odsetki = odsetki;
    }

}
