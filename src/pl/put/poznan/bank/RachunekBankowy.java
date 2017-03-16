package pl.put.poznan.bank;

import java.util.Date;

public class RachunekBankowy extends ProduktBankowy {
	private Date dataZalozenia;
	private Debet debet;

	public RachunekBankowy(int id, long nr, Bank bank){
		this.idKlienta = id;
		this.numerRachunku = nr;
		this.stanOdsetek = 0;
        this.bank = bank;
	}

	public RachunekBankowy(int id, long numer, Debet debet, Bank bank) {
		this.idKlienta = id;
		this.numerRachunku = numer;
		this.debet = debet;
		this.stanOdsetek = 0;
        this.bank = bank;
	}

	public boolean czyPosiadaDebet() {
		return debet != null;
	}

	@Override
	public double getStanSrodkow() {
		if (this.czyPosiadaDebet()) {
			double pozostalyDebet = this.debet.getMaxKwotaDebetu() - this.debet.getKwotaDebetu();
			return this.stanSrodkow + pozostalyDebet + this.stanOdsetek;
		}
		return this.stanSrodkow + this.stanOdsetek;
	}

	public Date getDataZalozenia() {
		return dataZalozenia;
	}
	public void setDataZalozenia(Date dataZalozenia) {
		this.dataZalozenia = dataZalozenia;
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
