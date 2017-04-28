package pl.put.poznan.bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.NotDebetException;

public class RaportDebetowyTest {
	
	Bank bank;
	Klient klient;
	KIR kir;
	
	@Before
	public void setUp(){
		kir = new KIR();
		bank = new Bank("Bank testowy", 1, kir);
		klient = new Klient(1, bank);
	}
	
	/*@Test(expected = NotDebetException.class)
    public void raportDodanieKontaNiedebetowegoTest() throws Exception {
		RaportDebetowy raport = new RaportDebetowy();
		RachunekBankowy rachunek = new RachunekBankowy(klient, "0001", bank);
		raport.dodajProdukt(rachunek);
    }*/
	
	/*@Test(expected = NotDebetException.class)
    public void raportDodanieProduktuDebetowegoBezDebetuTest() throws Exception {
		RaportDebetowy raport = new RaportDebetowy();
		RachunekBankowy rachunek = new RachunekBankowy(klient, "0001", new Debet(100, 0), bank);
		raport.dodajProdukt(rachunek);
    }*/
	/*@Test()
    public void raportDodanieProduktuDebetowego() throws Exception {
		RaportDebetowy raport = new RaportDebetowy();
		RachunekBankowy rachunek = new RachunekBankowy(klient, "0001", new Debet(100, 1), bank);
		raport.dodajProdukt(rachunek);
		assertEquals(1, raport.getRaportDebetowy().size());
    }*/
	
	@Test()
	public void raportGenerowanieRaportuTest() throws Exception{
		RaportDebetowy raport = new RaportDebetowy();
		RachunekBankowy rachunek = new RachunekBankowy(klient, "0001", new Debet(100, 1), bank);
		// todo reszta
	}
}
