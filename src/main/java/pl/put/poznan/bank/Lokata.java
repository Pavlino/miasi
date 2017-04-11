package pl.put.poznan.bank;

import pl.put.poznan.utils.TypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidBankOperationException;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Lokata extends ProduktBankowy {
	private Calendar dataKonca;
	private RachunekBankowy rachunekPowiazany;
    private double odsetki;
    private boolean aktywna;

    public Lokata(Klient klient, String nr, RachunekBankowy rachunekPowiazany, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidBankOperationException {
        this.klient = klient;
        this.numerRachunku = nr;
        this.rachunekPowiazany = rachunekPowiazany;
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
        bank = rachunekPowiazany.getBank();
        historia = new Historia();
        aktywna = false;
    }

    public void otworzLokate(double kwota, Calendar dataKonca) throws InvalidBankOperationException {
        Przelew przelew = new Przelew(rachunekPowiazany, this, kwota, "Otworzenie lokaty", TypyOperacjiBankowych.OTWORZENIE_LOKATY);
        rachunekPowiazany.wykonajOperacje(przelew);
        bank = rachunekPowiazany.getBank();
        this.dataKonca = dataKonca;
        aktywna = true;
    }

    public void zerwijLokate() throws InvalidBankOperationException {
        dataKonca = new GregorianCalendar(1,1,1);
        Przelew przelew = new Przelew(this, rachunekPowiazany, srodki, "Zerwanie lokaty", TypyOperacjiBankowych.ZERWANIE_LOKATY);
        rachunekPowiazany.wykonajOperacje(przelew);
        odsetki = 0;
    }

    public void rozwiazLokate() throws InvalidBankOperationException {
        GregorianCalendar czasObecny = new GregorianCalendar();
        aktywna = false;
        srodki += odsetki;
        Przelew przelew = new Przelew(this, rachunekPowiazany, srodki, "Rozwiazanie lokaty", TypyOperacjiBankowych.ROZWIAZANIE_LOKATY);
        rachunekPowiazany.wykonajOperacje(przelew);
        odsetki = 0;
    }

    @Override
    public void setOdsetki(double odsetki) {
        this.odsetki += odsetki;
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
