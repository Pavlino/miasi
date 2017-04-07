package pl.put.poznan.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import pl.put.poznan.utils.InvalidInputException;

public class Historia {
	private ArrayList<IOperacjaBankowa> historia;
	
	public ArrayList<IOperacjaBankowa> getHistoria() {
		return historia;
	}

	public void setHistoria(ArrayList<IOperacjaBankowa> raport) {
		historia = raport;
	}

	public Historia(){
		historia = new ArrayList<IOperacjaBankowa>();
	}
	
	public class CustomComparator implements Comparator<IOperacjaBankowa> {
	    @Override
	    public int compare(IOperacjaBankowa o1, IOperacjaBankowa o2) {
	        return o1.getData().compareTo(o2.getData());
	    }
	}
	
	public void dodajOperacje(IOperacjaBankowa op) throws InvalidInputException{
		if(op != null){
			historia.add(op);
			Collections.sort(historia, new CustomComparator());
		}
		else{
			throw new InvalidInputException("Podano pust� warto��");
		}
	}

	
}
