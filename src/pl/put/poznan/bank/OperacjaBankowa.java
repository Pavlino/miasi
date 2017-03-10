package pl.put.poznan.bank;

import java.util.Date;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

public class OperacjaBankowa {
	private Date data;
	private String opis;
	private int typ;
	
	public void wplata(ProduktBankowy konto, double kwota) throws InvalidInputException {
		if (konto != null && kwota > 0) {
			konto.setStanKonta(kwota);
		} else {
			throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
		}
	}
	
	public void wyplata(ProduktBankowy konto, double kwota) throws InvalidInputException, NotEnoughFundsException {
		if (konto != null && kwota > 0) {
			double stanSrodkow = konto.getStanKonta();
			if (stanSrodkow >= kwota) {
				stanSrodkow -= kwota;
				konto.setStanKonta(stanSrodkow);
			} else {
				throw new NotEnoughFundsException();
			}
		} else {
			throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
		}
	}
	
	public void przelew(ProduktBankowy kontoZ, ProduktBankowy kontoDo, double kwota) throws InvalidInputException, NotEnoughFundsException {
		if (kontoZ != null && kontoDo != null && kwota > 0) {
			double stanSrodkow = kontoZ.getStanKonta();
			if (stanSrodkow >= kwota) {
				stanSrodkow -= kwota;
			} else {
				throw new NotEnoughFundsException();
			}
		} else {
			throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
		}
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getOpis() {
		return opis;
	}
	public void setDescription(String opis) {
		this.opis = opis;
	}
	public int getTyp() {
		return typ;
	}
	public void setTyp(int typ) {
		this.typ = typ;
	}
}
