package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.NotDebetException;

public interface IWizytor{
	  public void visit(RachunekBankowy produkt);
	  public void visit(ProduktBankowy produkt);
	  public void visit(RachunekBankowyDebetowy produkt) throws NotDebetException, InvalidBankOperationException;
	}
