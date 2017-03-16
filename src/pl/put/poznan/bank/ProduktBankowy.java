package pl.put.poznan.bank;

public abstract class ProduktBankowy {
	int idKlienta;
	long numerRachunku;
	double stanSrodkow;
	IMechanizmOdsetkowy mechanizmOdsetkowy;
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
	public long getNumerRachunku() {
		return numerRachunku;
	}
	public IMechanizmOdsetkowy getMechanizmOdsetkowy() {
		return mechanizmOdsetkowy;
	}
	public void setMechanizmOdsetkowy(IMechanizmOdsetkowy mechanizmOdsetkowy) {
		this.mechanizmOdsetkowy = mechanizmOdsetkowy;
	}
}
