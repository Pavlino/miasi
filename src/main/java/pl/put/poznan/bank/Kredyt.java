package pl.put.poznan.bank;

import pl.put.poznan.utils.TypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidBankOperationException;

public class Kredyt extends ProduktBankowy {

    private enum StatusKredytu { SPLACONY, NIESPLACONY, PRZETWARZANY };
	private RachunekBankowy rachunekPowiazany;
	private StatusKredytu status;
    private double odsetki;
	
	public Kredyt(Klient klient, String rachunek, RachunekBankowy rachunekPowiazany, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidBankOperationException {
		this.klient = klient;
		this.numerRachunku = rachunek;
        this.rachunekPowiazany = rachunekPowiazany;
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
        status = StatusKredytu.NIESPLACONY;
        this.bank = rachunekPowiazany.getBank();
        historia = new Historia();
	}

    public void zaciagnijKredyt(double kwota) throws InvalidBankOperationException {
        Wplata wplata = new Wplata(kwota, "Zaciagniecie kredytu", TypyOperacjiBankowych.ZACIAGNIECIE_KREDYTU);
        rachunekPowiazany.wykonajOperacje(wplata);
        srodki = kwota;
    }

    public void splacKredyt() throws InvalidBankOperationException {
        Przelew przelew = new Przelew(this, srodki + odsetki, "Splata kredytu", TypyOperacjiBankowych.SPLATA_KREDYTU);
        status = StatusKredytu.PRZETWARZANY;
        try {
            rachunekPowiazany.wykonajOperacje(przelew);
            status = StatusKredytu.SPLACONY;
        } catch(InvalidBankOperationException e) {
            status = StatusKredytu.NIESPLACONY;
            throw new InvalidBankOperationException(e.getMessage());
        }
    }

    public void przyjmijWizytatora(IRaport wizytator) {
        wizytator.odwiedz(this);
    }

    @Override
    public void setSrodki(double srodki) {
        if (this.srodki > 0 && status != StatusKredytu.PRZETWARZANY) {
            throw new UnsupportedOperationException("Kredyt nie zostal splacony.");
        }
        this.srodki = srodki;
    }

    @Override
    public void setOdsetki(double odsetki) {
        this.odsetki += odsetki;
    }

    public double getOdsetki() {
        return odsetki;
    }

}
