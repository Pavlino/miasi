package pl.put.poznan.bank;

public abstract class ProduktBankowy {

    Klient klient;
	String numerRachunku;
	double srodki;
	IMechanizmOdsetkowy mechanizmOdsetkowy;
    Historia historia;
    Bank bank;

	public Klient getKlient() {
		return this.klient;
	}
	public void setKlient(Klient klient) {
		this.klient = klient;
	}
	public double getSrodki() {
		return this.srodki;
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
        return this.historia;
    }
    public Bank getBank() {
        return this.bank;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    public void setOdsetki(double odsetki) {
        this.srodki = odsetki;
    }
}
