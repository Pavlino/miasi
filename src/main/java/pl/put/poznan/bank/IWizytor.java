package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.NotDebetException;

public interface IWizytor{
	  public void visit(RachunekBankowy produkt) throws NotDebetException, InvalidBankOperationException;
	  
	  //visit other concrete items
	  //public void visit(CD cd);
	  //public void visit(DVD dvd);
	}
