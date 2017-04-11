package pl.put.poznan.bank;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import pl.put.poznan.utils.InvalidBankOperationException;

public class HistoriaTest {
	
	@Test
	public void historiaSortTest() throws InvalidBankOperationException {
		Historia historia = new Historia();
		Wplata wplata1 = new Wplata(new GregorianCalendar(115, 1, 1), 100, "opis1");
		Wplata wplata2 = new Wplata(new GregorianCalendar(114, 1, 1), 100, "opis2");
		Wplata wplata3 = new Wplata(new GregorianCalendar(117, 1, 1), 100, "opis3");
		Wplata wplata4 = new Wplata(new GregorianCalendar(116, 5, 1), 100, "opis4");
		Wplata wplata5 = new Wplata(new GregorianCalendar(117, 2, 1), 100, "opis5");
		historia.dodajOperacje(wplata1);
		historia.dodajOperacje(wplata2);
		historia.dodajOperacje(wplata3);
		historia.dodajOperacje(wplata4);
		historia.dodajOperacje(wplata5);
		boolean sort = true;
		for(int i = 1; i<historia.getOperacje().size(); i++){
			if(historia.getOperacje().get(i-1).getData().after(historia.getOperacje().get(i).getData())){
				sort=false;
				break;
			}
		}
		assertEquals(true, sort);
	}
}
