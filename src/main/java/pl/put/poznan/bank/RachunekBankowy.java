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

	public RachunekBankowyDebetowy setDebet(Debet debet) {
        RachunekBankowyDebetowy rachunekBankowyDebetowy = new RachunekBankowyDebetowy(this, debet);
        bank.getListaRachunkow().put(rachunekBankowyDebetowy.getNumerRachunku(), rachunekBankowyDebetowy);
	    return new RachunekBankowyDebetowy(this, debet);
    }

	public Date getDataZalozenia() {
		return dataZalozenia;
	}

	public void przyjmijWizytatora(IRaport wizytator) {
		wizytator.odwiedz(this);
	}

	public void setDataZalozenia(Date dataZalozenia) {
		this.dataZalozenia = dataZalozenia;
	}
	public void setNumerRachunku(String numerRachunku) {
		this.numerRachunku = numerRachunku;
	}
}
