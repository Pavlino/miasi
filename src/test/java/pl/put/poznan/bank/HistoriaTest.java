package pl.put.poznan.bank;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import pl.put.poznan.utils.InvalidInputException;

public class HistoriaTest {
	
	@Test
	public void historiaSortTest() throws InvalidInputException{
		Historia historia = new Historia();
		OperacjaBankowa op = new OperacjaBankowa(new GregorianCalendar(115, 1, 1), "opis", 1);
		OperacjaBankowa op1 = new OperacjaBankowa(new GregorianCalendar(115,1,1), "opis", 1);
		OperacjaBankowa op2 = new OperacjaBankowa(new GregorianCalendar(117,1,1), "opis", 1);
		OperacjaBankowa op3 = new OperacjaBankowa(new GregorianCalendar(116,5,1), "opis", 1);
		OperacjaBankowa op4 = new OperacjaBankowa(new GregorianCalendar(117,2,1), "opis", 1);
		historia.dodajOperacje(op);
		historia.dodajOperacje(op1);
		historia.dodajOperacje(op2);
		historia.dodajOperacje(op3);
		historia.dodajOperacje(op4);
		boolean sort = true;
		for(int i=1; i<historia.getHistoria().size(); i++){
			if(historia.getHistoria().get(i-1).getData().after(historia.getHistoria().get(i).getData())){
				sort=false;
				break;
			}
		}
		assertEquals(true, sort);
	}
}
