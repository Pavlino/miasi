package pl.put.poznan.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import pl.put.poznan.utils.InvalidInputException;

public class Historia {
	private ArrayList<OperacjaBankowa> historia;
	
	public ArrayList<OperacjaBankowa> getHistoria() {
		return historia;
	}

	public void setHistoria(ArrayList<OperacjaBankowa> raport) {
		historia = raport;
	}

	public Historia(){
		historia = new ArrayList<OperacjaBankowa>();
	}
	
	public class CustomComparator implements Comparator<OperacjaBankowa> {
	    @Override
	    public int compare(OperacjaBankowa o1, OperacjaBankowa o2) {
	        return o1.getData().compareTo(o2.getData());
	    }
	}
	
	public void dodajOperacje(OperacjaBankowa op) throws InvalidInputException{
		if(op != null){
			historia.add(op);
			Collections.sort(historia, new CustomComparator());
		}
		else{
			throw new InvalidInputException("Podano pust� warto��");
		}
	}

	
}
