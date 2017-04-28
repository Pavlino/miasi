package pl.put.poznan.bank;

import java.util.Date;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.NotDebetException;

public class RachunekBankowy extends ProduktBankowy {
	private Date dataZalozenia;
	private Debet debet;

	public RachunekBankowy(Klient klient, String nr, Bank bank){
		this.klient = klient;
		this.numerRachunku = nr;
        this.bank = bank;
		historia = new Historia();
	}

	public RachunekBankowy(Klient klient, String numer, Debet debet, Bank bank) {
		this.klient = klient;
		this.numerRachunku = numer;
		this.debet = debet;
        this.bank = bank;
		historia = new Historia();
	}
	
	//accept the visitor
	public void accept(IWizytor visitor) throws NotDebetException, InvalidBankOperationException {
	    visitor.visit(this);
	  }

	public boolean czyPosiadaDebet() {
		return debet != null;
	}

	@Override
	public double getSrodki() {
		if (czyPosiadaDebet()) {
			double pozostalyDebet = debet.getMaxKwotaDebetu() - debet.getKwotaDebetu();
			return srodki + pozostalyDebet;
		}
		return srodki;
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

	public double getKwotaDebetu() {
		return debet.getKwotaDebetu();
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
		return debet;
	}

	public void setNumerRachunku(String numerRachunku) {
		this.numerRachunku = numerRachunku;
	}
}
