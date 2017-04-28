package pl.put.poznan.bank;

import static org.junit.Assert.*;

import pl.put.poznan.utils.NotDebetException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WizytorTest {

	Bank bank;
	Klient klient;
	KIR kir;
	
	@Before
	public void setUp(){
		kir = new KIR();
		bank = new Bank("Bank testowy", 1, kir);
		klient = new Klient(1, bank);
	}
	@After
	public void tearDown() throws Exception {
		klient = null;
		bank = null;
		kir = null;
	}

	@Test(expected = NotDebetException.class)
    public void raportDodanieKontaNiedebetowegoTest() throws Exception {
		RachunekBankowy rachunek = new RachunekBankowy(klient, "0001", bank);
		Wizytor wizytor = new Wizytor();
		rachunek.accept(wizytor);
    }
	
	@Test(expected = NotDebetException.class)
    public void raportDodanieProduktuDebetowegoBezDebetuTest() throws Exception {
		RachunekBankowy rachunek = new RachunekBankowy(klient, "0001", new Debet(100, 0), bank);
		Wizytor wizytor = new Wizytor();
		rachunek.accept(wizytor);
    }
    
	@Test()
    public void raportDodanieProduktuDebetowego() throws Exception {
		RachunekBankowy rachunek = new RachunekBankowy(klient, "0001", new Debet(100, 1), bank);
		Wizytor wizytor = new Wizytor();
		rachunek.accept(wizytor);
		assertEquals(1, wizytor.getListaKont().size());
    }

}
