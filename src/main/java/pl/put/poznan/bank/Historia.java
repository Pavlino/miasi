package pl.put.poznan.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import pl.put.poznan.utils.InvalidBankOperationException;

public class Historia {
	private ArrayList<IOperacjaBankowa> operacje;
	
	public ArrayList<IOperacjaBankowa> getOperacje() {
		return operacje;
	}

	public void setOperacje(ArrayList<IOperacjaBankowa> raport) {
		operacje = raport;
	}

	public Historia(){
		operacje = new ArrayList<IOperacjaBankowa>();
	}
	
	public class CustomComparator implements Comparator<IOperacjaBankowa> {
	    @Override
	    public int compare(IOperacjaBankowa o1, IOperacjaBankowa o2) {
	        return o1.getData().compareTo(o2.getData());
	    }
	}
	
	public void dodajOperacje(IOperacjaBankowa op) throws InvalidBankOperationException{
		if(op != null){
			operacje.add(op);
			Collections.sort(operacje, new CustomComparator());
		}
		else{
			throw new InvalidBankOperationException("Podano pusta wartosc");
		}
	}

	
}
