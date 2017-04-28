package pl.put.poznan.bank;

import java.util.Date;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.NotDebetException;

public class RachunekBankowy extends ProduktBankowy {
	private Date dataZalozenia;

	public RachunekBankowy(Klient klient, String nr, Bank bank){
		this.klient = klient;
		this.numerRachunku = nr;
        this.bank = bank;
		historia = new Historia();
	}
	
	//accept the visitor
	public void accept(IWizytor visitor) {
	    visitor.visit(this);
	}

	public RachunekBankowyDebetowy setDebet(Debet debet) {
        RachunekBankowyDebetowy rachunekBankowyDebetowy = new RachunekBankowyDebetowy(this, debet);
        bank.getListaRachunkow().put(rachunekBankowyDebetowy.getNumerRachunku(), rachunekBankowyDebetowy);
	    return new RachunekBankowyDebetowy(this, debet);
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
