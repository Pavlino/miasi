package pl.put.poznan.bank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Historia {
	private ArrayList<OperacjaBankowa> raport;
	
	public ArrayList<OperacjaBankowa> getRaport() {
		return raport;
	}

	public void setRaport(ArrayList<OperacjaBankowa> raport) {
		this.raport = raport;
	}

	public Historia(){
		this.raport = new ArrayList<OperacjaBankowa>();
	}
	
	public class CustomComparator implements Comparator<OperacjaBankowa> {
	    @Override
	    public int compare(OperacjaBankowa o1, OperacjaBankowa o2) {
	    	//TODO
	    	return 0;
	        //return o1.getDate().compareTo(o2.getDate());
	    }
	}
	
	public void dodajOperacje(OperacjaBankowa op){
		raport.add(op);
	}
	
	
}
