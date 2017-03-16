package pl.put.poznan.bank;

public abstract class ProduktBankowy {
	int idKlienta;
	long numerRachunku;
	double stanSrodkow;
	double stanOdsetek;
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
    public void setStanOdsetek(double stanOdsetek) {
        this.stanOdsetek = stanOdsetek; }
    public double getStanOdsetek() {
        return this.stanOdsetek;
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
