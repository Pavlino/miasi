package pl.put.poznan.bank;

import java.util.Date;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

public class OperacjaBankowa implements IOperacjaBankowa {
	private Date data;
	private String opis;
	private int typ;

	public OperacjaBankowa(Date data, String opis, int typ) {
        this.data = data;
        this.opis = opis;
        this.typ = typ;
    }
	
	public void wplata(final ProduktBankowy konto, final double kwota) throws InvalidInputException {
		if (konto != null && kwota > 0) {
			konto.setStanSrodkow(kwota);
		} else {
			throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
		}
	}
	
	public void wyplata(final ProduktBankowy konto, final double kwota) throws InvalidInputException, NotEnoughFundsException {
		if (konto != null && kwota > 0) {
			double stanSrodkow = konto.getStanSrodkow();
			if (stanSrodkow >= kwota) {
				stanSrodkow -= kwota;
				konto.setStanSrodkow(stanSrodkow);
			} else {
				throw new NotEnoughFundsException();
			}
		} else {
			throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
		}
	}
	
	public void przelew(final ProduktBankowy kontoZ, final ProduktBankowy kontoDo, final double kwota) throws InvalidInputException, NotEnoughFundsException {
		if (kontoZ != null && kontoDo != null && kwota > 0) {
			double stanSrodkow = kontoZ.getStanSrodkow();
			if (stanSrodkow >= kwota) {
				this.wyplata(kontoZ, kwota);
                this.wplata(kontoDo, kwota);
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
