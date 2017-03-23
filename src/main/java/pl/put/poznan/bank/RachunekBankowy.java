package pl.put.poznan.bank;

import java.util.Date;

public class RachunekBankowy extends ProduktBankowy {
	private Date dataZalozenia;
	private Debet debet;

	public RachunekBankowy(Klient klient, String nr, Bank bank){
		this.klient = klient;
		this.numerRachunku = nr;
        this.bank = bank;
		this.historia = new Historia();
	}

	public RachunekBankowy(Klient klient, String numer, Debet debet, Bank bank) {
		this.klient = klient;
		this.numerRachunku = numer;
		this.debet = debet;
        this.bank = bank;
		this.historia = new Historia();
	}

	public boolean czyPosiadaDebet() {
		return debet != null;
	}

	@Override
	public double getSrodki() {
		if (this.czyPosiadaDebet()) {
			double pozostalyDebet = this.debet.getMaxKwotaDebetu() - this.debet.getKwotaDebetu();
			return this.srodki + pozostalyDebet;
		}
		return this.srodki;
	}

	@Override
	public void setSrodki(double sumaSrodkow) {
        double dostepneSrodki = this.getSrodki();
        double saldo = sumaSrodkow - dostepneSrodki;
        if (saldo < 0) {
            if (this.czyPosiadaDebet()) {
                if ((this.srodki + saldo) >= 0) {
                    this.srodki += saldo;
                } else {
                    double dodatkowyDebet = (this.srodki + saldo) * -1;
                    this.srodki = 0;
                    this.debet.setKwotaDebetu(this.debet.getKwotaDebetu() + dodatkowyDebet);
                }
            } else {
                this.srodki += saldo;
            }
        } else {
            if (this.czyPosiadaDebet() && this.debet.getKwotaDebetu() > 0 ) {
                double pozostaleSrodki = this.debet.getKwotaDebetu() - saldo;
                if (pozostaleSrodki >= 0) {
                    this.debet.setKwotaDebetu(pozostaleSrodki);
                } else {
                    this.debet.setKwotaDebetu(0);
                    this.srodki -= pozostaleSrodki;
                }
            } else {
                this.srodki = sumaSrodkow;
            }
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
