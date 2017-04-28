package pl.put.poznan.bank;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

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
	
	public ArrayList<IOperacjaBankowa> paczkaKir(GregorianCalendar dataPoprzedniKir, GregorianCalendar dataAktualnyKir){
		ArrayList<IOperacjaBankowa> kir = new ArrayList<IOperacjaBankowa>();
		int index = operacje.size()-1;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String poprzedniKir = format1.format(dataPoprzedniKir.getTimeInMillis());
		String aktualnyKir = format1.format(dataAktualnyKir.getTimeInMillis());
		while(index >= 0 && operacje.get(index).getData().after(dataPoprzedniKir)){
			String aktualnadata = format1.format(operacje.get(index).getData().getTimeInMillis());
			if(!operacje.get(index).getData().before(dataAktualnyKir)){
				index--;
				continue;
			}
			if(operacje.get(index).getTyp() == 9){
				kir.add(operacje.get(index));
				index--;
			}
			
		}
		
		return kir;
	}

	
}
