package pl.put.poznan.bank;

import java.util.Date;

public class RachunekBankowy extends ProduktBankowy {
	private Date dataZalozenia;

	public RachunekBankowy(Klient klient, String nr, Bank bank){
		this.klient = klient;
		this.numerRachunku = nr;
        this.bank = bank;
		historia = new Historia();
	}

	@Override
	public void setSrodki(double sumaSrodkow) {
        double dostepneSrodki = getSrodki();
        double saldo = sumaSrodkow - dostepneSrodki;
        if (saldo < 0) {
            if (czyPosiadaDebet()) {
                if ((srodki + saldo) >= 0) {
                    srodki += saldo;
                } else {
                    double dodatkowyDebet = (srodki + saldo) * -1;
                    srodki = 0;
                    debet.setKwotaDebetu(debet.getKwotaDebetu() + dodatkowyDebet);
                }
            } else {
                srodki += saldo;
            }
        } else {
            if (czyPosiadaDebet() && debet.getKwotaDebetu() > 0 ) {
                double pozostaleSrodki = debet.getKwotaDebetu() - saldo;
                if (pozostaleSrodki >= 0) {
                    debet.setKwotaDebetu(pozostaleSrodki);
                } else {
                    debet.setKwotaDebetu(0);
                    srodki -= pozostaleSrodki;
                }
            } else {
               	srodki = sumaSrodkow;
            }
        }
	}

	public Date getDataZalozenia() {
		return dataZalozenia;
	}
	public void setDataZalozenia(Date dataZalozenia) {
		this.dataZalozenia = dataZalozenia;
	}
	public void setNumerRachunku(String numerRachunku) {
		this.numerRachunku = numerRachunku;
	}
}
