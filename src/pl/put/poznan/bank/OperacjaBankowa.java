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
            double stanSrodkow = konto.getStanSrodkow();
            stanSrodkow += kwota;
            konto.setStanSrodkow(stanSrodkow);
            konto.getHistoria().dodajOperacje(this);
        } else {
            throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
        }
    }
	
	public void wyplata(final ProduktBankowy konto, final double kwota) throws InvalidInputException, NotEnoughFundsException {
		if (konto != null && kwota > 0) {
			double stanSrodkow = konto.getStanSrodkow();
            double stanOdsetek = konto.getStanOdsetek();
			if (stanSrodkow + stanOdsetek >= kwota) {
				stanSrodkow -= kwota;
                if (stanSrodkow < 0) {
                    stanOdsetek += stanSrodkow;
                    konto.setStanSrodkow(0);
                    konto.setStanOdsetek(stanOdsetek);
                } else {
                    konto.setStanSrodkow(stanSrodkow);
                }
                konto.getHistoria().dodajOperacje(this);
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
            kontoZ.getHistoria().dodajOperacje(this);
            kontoDo.getHistoria().dodajOperacje(this);
		} else {
			throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
		}
	}

    public void wplataOdsetek(final ProduktBankowy konto, final double kwotaOdsetek) {
        if (konto != null && kwotaOdsetek > 0) {
            double stanOdsetek = konto.getStanOdsetek();
            stanOdsetek += kwotaOdsetek;
            konto.setStanOdsetek(stanOdsetek);
            konto.getHistoria().dodajOperacje(this);
        }
    }

    public void naliczOdsetki(final ProduktBankowy produktBankowy) throws InvalidInputException {
        IMechanizmOdsetkowy mechanizmOdsetkowy = produktBankowy.getMechanizmOdsetkowy();
        Odsetki odsetki = new Odsetki(mechanizmOdsetkowy);
        double wartoscOdsetek = odsetki.naliczOdsetki(produktBankowy);
        this.wplataOdsetek(produktBankowy, wartoscOdsetek);
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
