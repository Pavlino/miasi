package pl.put.poznan.bank;

import java.util.Date;

public class RachunekBankowy extends ProduktBankowy {
	int id;
	String numer;
	Date dataZalozenia;
	int idHistorii;
	long numerRachunku;
	Debet debet;

	public RachunekBankowy(int id, String nr){
		this.id = id;
		this.numer = nr;
	}
	
	public RachunekBankowy(int id, long nr){
		this.id = id;
		this.numerRachunku = nr;
	}

	public RachunekBankowy(int id, String numer, Debet debet) {
		this.id = id;
		this.numer = numer;
		this.debet = debet;
	}

	public boolean czyPosiadaDebet() {
		return debet != null;
	}

	@Override
	public double getStanSrodkow() {
		if (this.czyPosiadaDebet()) {
			double pozostalyDebet = this.debet.getMaxKwotaDebetu() - this.debet.getKwotaDebetu();
			return this.stanSrodkow + pozostalyDebet;
		}
		return this.stanSrodkow;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumer() {
		return numer;
	}
	public void setNumer(String nrRachunku) {
		this.numer = nrRachunku;
	}
	public Date getDataZalozenia() {
		return dataZalozenia;
	}
	public void setDataZalozenia(Date dataZalozenia) {
		this.dataZalozenia = dataZalozenia;
	}
	public int getIdHistorii() {
		return idHistorii;
	}
	public void setIdHistorii(int idHistorii) {
		this.idHistorii = idHistorii;
	}
	public long getNumerRachunku() {
		return numerRachunku;
	}
	public void setDebet(Debet debet) {
		this.debet = debet;
	}
	public Debet getDebet() {
		return this.debet;
	}

	public void setNumerRachunku(long numerRachunku) {
		this.numerRachunku = numerRachunku;
	}
}
