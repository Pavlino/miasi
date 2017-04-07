package pl.put.poznan.bank;

import java.util.Calendar;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

public class OperacjaBankowa implements IOperacjaBankowa {
	private Calendar data;
	private String opis;
	private int typ;

	public OperacjaBankowa(Calendar data, String opis, int typ) {
        this.data = data;
        this.opis = opis;
        this.typ = typ;
    }
	
	public void wplata(final ProduktBankowy konto, final double kwota) throws InvalidInputException {
        if (konto != null && kwota > 0) {
            double stanSrodkow = konto.getSrodki();
            stanSrodkow += kwota;
            konto.setSrodki(stanSrodkow);
            dodajDoHistorii(konto);
        } else {
            throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
        }
    }
	
	public void wyplata(final ProduktBankowy konto, final double kwota) throws InvalidInputException, NotEnoughFundsException {
		if (konto != null && kwota > 0) {
			double stanSrodkow = konto.getSrodki();
			if (stanSrodkow >= kwota) {
				stanSrodkow -= kwota;
                konto.setSrodki(stanSrodkow);
                dodajDoHistorii(konto);
            } else {
				throw new NotEnoughFundsException();
			}
		} else {
			throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
		}
	}
	
	public void przelew(final ProduktBankowy kontoZ, final ProduktBankowy kontoDo, final double kwota) throws InvalidInputException, NotEnoughFundsException {
		if (kontoZ != null && kontoDo != null && kwota > 0) {
			double stanSrodkow = kontoZ.getSrodki();
			if (stanSrodkow >= kwota) {
				wyplata(kontoZ, kwota);
                wplata(kontoDo, kwota);
			} else {
				throw new NotEnoughFundsException();
			}
            dodajDoHistorii(kontoZ);
            dodajDoHistorii(kontoDo);
		} else {
			throw new InvalidInputException("Konto nie istnieje lub podana kwota jest ujemna");
		}
	}

    private void wplataOdsetek(final ProduktBankowy konto, final double kwotaOdsetek) throws InvalidInputException {
        if (konto != null && kwotaOdsetek > 0) {
            double stanKonta = konto.getSrodki();
            stanKonta += kwotaOdsetek;
            konto.setOdsetki(stanKonta);
            dodajDoHistorii(konto);
        }
    }

    public void naliczOdsetki(final ProduktBankowy produktBankowy) throws InvalidInputException {
        IMechanizmOdsetkowy mechanizmOdsetkowy = produktBankowy.getMechanizmOdsetkowy();
        double wartoscOdsetek = mechanizmOdsetkowy.naliczOdsetki(produktBankowy);
        wplataOdsetek(produktBankowy, wartoscOdsetek);
    }

    private void dodajDoHistorii(ProduktBankowy produktBankowy) throws InvalidInputException {
        produktBankowy.dodajOperacjeDoHistorii(this);
        produktBankowy.dodajOperacjeDoHistoriiBanku(this);
    }

	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
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
