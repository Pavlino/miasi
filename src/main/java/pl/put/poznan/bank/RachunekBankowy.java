package pl.put.poznan.bank;

import java.util.Date;

public class RachunekBankowy extends ProduktBankowy {
	private Date dataZalozenia;
	private Debet debet;

	public RachunekBankowy(int id, String nr, Bank bank){
		this.idKlienta = id;
		this.numerRachunku = nr;
		this.stanOdsetek = 0;
        this.bank = bank;
		this.historia = new Historia();
	}

	public RachunekBankowy(int id, String numer, Debet debet, Bank bank) {
		this.idKlienta = id;
		this.numerRachunku = numer;
		this.debet = debet;
		this.stanOdsetek = 0;
        this.bank = bank;
		this.historia = new Historia();
	}

	public boolean czyPosiadaDebet() {
		return debet != null;
	}

	@Override
	public double getStanSrodkow() {
		if (this.czyPosiadaDebet()) {
			double pozostalyDebet = this.debet.getMaxKwotaDebetu() - this.debet.getKwotaDebetu();
			return this.stanSrodkow + this.stanOdsetek + pozostalyDebet;
		}
		return this.stanSrodkow + this.stanOdsetek;
	}

	@Override
	public void setStanSrodkow(double stanSrodkow) {
		if (this.czyPosiadaDebet()) {
		    double dostepneSrodki = this.stanSrodkow + this.stanOdsetek + this.debet.getMaxKwotaDebetu() - this.debet.getKwotaDebetu();
			double kwotaWyplaty = dostepneSrodki - stanSrodkow;
            double kwotaDebetu = this.stanSrodkow + this.stanOdsetek - kwotaWyplaty;
			if (kwotaDebetu >= 0) {
			    if (stanSrodkow - this.stanSrodkow > 0) {
			        double stanOdsetek = stanSrodkow - this.stanSrodkow;
                    this.stanSrodkow = 0;
                    this.stanOdsetek -= stanOdsetek;
                } else {
                    this.stanSrodkow -= stanSrodkow;
                }
			} else {
				this.stanSrodkow = 0;
				this.stanOdsetek = 0;
				this.debet.setKwotaDebetu(this.debet.getKwotaDebetu() - kwotaDebetu);
			}
		} else {
            this.stanSrodkow = stanSrodkow;
        }
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

	public void setNumerRachunku(String numerRachunku) {
		this.numerRachunku = numerRachunku;
	}
}
