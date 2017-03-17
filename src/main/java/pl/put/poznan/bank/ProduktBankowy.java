package pl.put.poznan.bank;

public abstract class ProduktBankowy {

    int idKlienta;
	String numerRachunku;
	double stanSrodkow;
	double stanOdsetek;
	IMechanizmOdsetkowy mechanizmOdsetkowy;
    Historia historia;
    Bank bank;

	public int getIdKlienta() {
		return idKlienta;
	}
	public void setIdKlienta(int idKlienta) {
		this.idKlienta = idKlienta;
	}
	public double getStanSrodkow() {
		return stanSrodkow;
	}
	public void setStanSrodkow(double stanSrodkow) {
		this.stanSrodkow = stanSrodkow;
	}
    public void setStanOdsetek(double stanOdsetek) {
        this.stanOdsetek = stanOdsetek; }
    public double getStanOdsetek() {
        return this.stanOdsetek;
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
    public void setbank(Bank bank) {
        this.bank = bank;
    }
}
