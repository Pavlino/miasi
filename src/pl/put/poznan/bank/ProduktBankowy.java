package pl.put.poznan.bank;

public abstract class ProduktBankowy {
	int idKlienta;
	double stanSrodkow;
	int idOdsetek;
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
	public int getIdOdsetek() {
		return idOdsetek;
	}
	public void setIdOdsetek(int idOdsetek) {
		this.idOdsetek = idOdsetek;
	}
}
