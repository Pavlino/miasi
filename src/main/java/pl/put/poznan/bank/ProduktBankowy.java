package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;

public abstract class ProduktBankowy {

    Klient klient;
	String numerRachunku;
	double srodki;
	IMechanizmOdsetkowy mechanizmOdsetkowy;
    Historia historia;
    Bank bank;

	public ProduktBankowy() {
		mechanizmOdsetkowy = new MechanizmOdsetkowyLiniowy(0.1);
	}
	public void dodajOperacjeDoHistorii(IOperacjaBankowa operacjaBankowa) throws InvalidBankOperationException {
		historia.dodajOperacje(operacjaBankowa);
	}

	public void dodajOperacjeDoHistoriiBanku(IOperacjaBankowa operacjaBankowa) throws InvalidBankOperationException {
		bank.dodajOperacjeDoHistorii(operacjaBankowa);
	}

	public IOperacjaBankowa pobierzOperacjeBankowaZHistorii(int indeks) {
		return historia.getOperacje().get(indeks);
	}

	public IOperacjaBankowa pobierzOperacjeBankowaZHistoriiBanku(int indeks) {
		return bank.pobierzOperacjeZHistorii(indeks);
	}

	public void wykonajOperacje(IOperacjaBankowa operacjaBankowa) throws InvalidBankOperationException {
		operacjaBankowa.wykonaj();
	}

	public void naliczOdsetki() throws InvalidBankOperationException {
		mechanizmOdsetkowy.naliczOdsetki(this);
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
        this.srodki += odsetki;
    }
}
