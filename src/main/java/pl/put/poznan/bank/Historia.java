package pl.put.poznan.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Historia {
	private ArrayList<OperacjaBankowa> historia;
	
	public ArrayList<OperacjaBankowa> getHistoria() {
		return historia;
	}

	public void setRaport(ArrayList<OperacjaBankowa> raport) {
		this.historia = raport;
	}

	public Historia(){
		this.historia = new ArrayList<OperacjaBankowa>();
	}
	
	public class CustomComparator implements Comparator<OperacjaBankowa> {
	    @Override
	    public int compare(OperacjaBankowa o1, OperacjaBankowa o2) {
	        return o1.getData().compareTo(o2.getData());
	    }
	}
	
	public void dodajOperacje(OperacjaBankowa op){
		historia.add(op);
		Collections.sort(historia, new CustomComparator());
	}

	
}
