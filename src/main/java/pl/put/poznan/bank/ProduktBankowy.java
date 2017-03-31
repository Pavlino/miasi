package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

public abstract class ProduktBankowy {

    Klient klient;
	String numerRachunku;
	double srodki;
	IMechanizmOdsetkowy mechanizmOdsetkowy;
    Historia historia;
    Bank bank;

	public void dodajOperacjeDoHistorii(OperacjaBankowa operacjaBankowa) throws InvalidInputException {
		historia.dodajOperacje(operacjaBankowa);
	}

	public void dodajOperacjeDoHistoriiBanku(OperacjaBankowa operacjaBankowa) throws InvalidInputException {
		bank.dodajOperacjeDoHistorii(operacjaBankowa);
	}

	public Klient getKlient() {
		return klient;
	}
	public void setKlient(Klient klient) {
		this.klient = klient;
	}
	public double getSrodki() {
		return srodki;
	}
	public void setSrodki(double srodki) {
		this.srodki = srodki;
	}
	public String getNumerRachunku() {
		return numerRachunku;
	}
	public IMechanizmOdsetkowy getMechanizmOdsetkowy() {
		return mechanizmOdsetkowy;
	}
	public void setMechanizmOdsetkowy(IMechanizmOdsetkowy mechanizmOdsetkowy) {
		this.mechanizmOdsetkowy = mechanizmOdsetkowy;
	}
    public Historia getHistoria() {
        return historia;
    }
    public Bank getBank() {
        return bank;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    public void setOdsetki(double odsetki) {
        this.srodki = odsetki;
    }
}
