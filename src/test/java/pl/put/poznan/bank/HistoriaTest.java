package pl.put.poznan.bank;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import pl.put.poznan.utils.InvalidBankOperationException;

public class HistoriaTest {
	
	Klient klient;
	Bank bank;
	
	@Before
	public void setUp(){
		bank = new Bank("a", 1, new KIR());
		klient = new Klient(1, bank);
	}
	
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
	
	@Test
	public void historiaKirTest() throws InvalidBankOperationException {
		RachunekBankowy rach = new RachunekBankowy(klient, "1", bank);
		Historia historia = new Historia();
		PrzelewMiedzybankowy przel1 = new PrzelewMiedzybankowy(new GregorianCalendar(2017, 0, 2,1,1), rach, bank, 10, "testowy przelew miedzybankowy");
		PrzelewMiedzybankowy przel2 = new PrzelewMiedzybankowy(new GregorianCalendar(2017, 1, 2,1,1), rach, bank, 10, "testowy przelew miedzybankowy");
		PrzelewMiedzybankowy przel3 = new PrzelewMiedzybankowy(new GregorianCalendar(2017, 2, 2,1,1), rach, bank, 10, "testowy przelew miedzybankowy");
		PrzelewMiedzybankowy przel4 = new PrzelewMiedzybankowy(new GregorianCalendar(2017, 3, 2,1,1), rach, bank, 10, "testowy przelew miedzybankowy");
		PrzelewMiedzybankowy przel5 = new PrzelewMiedzybankowy(new GregorianCalendar(2017, 4, 2,1,1), rach, bank, 10, "testowy przelew miedzybankowy");
		Przelew przel6 = new Przelew(rach, 100, "testowy przelew");
		historia.dodajOperacje(przel1);
		historia.dodajOperacje(przel2);
		historia.dodajOperacje(przel3);
		historia.dodajOperacje(przel4);
		historia.dodajOperacje(przel5);
		historia.dodajOperacje(przel6);
		ArrayList<IOperacjaBankowa> kir = historia.stworzPaczkeDoKir(new GregorianCalendar(2017, 1, 1,1,1), new GregorianCalendar(2017, 4, 1,1,1));
		
		assertEquals(3, kir.size());
	}
}
