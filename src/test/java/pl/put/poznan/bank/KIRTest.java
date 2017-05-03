package pl.put.poznan.bank;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KIRTest {
	
	KIR kir;
	Bank bankZrodlowy;
	Bank bankDocelowy;
	Klient klient1;
	Klient klient2;
	RachunekBankowy rachunekZrodlowy;
	RachunekBankowy rachunekDocelowy;

	@Before
    public void setUp() throws Exception {
        kir = new KIR();
		bankZrodlowy = new Bank("test1", 1, kir);
		bankDocelowy = new Bank("test2", 2, kir);
		klient1 = bankZrodlowy.stworzKlienta();
		klient2 = bankDocelowy.stworzKlienta();
		klient1.otworzRachunek();
		rachunekZrodlowy = (RachunekBankowy) klient1.getListaProduktow().get("1");
		klient2.otworzRachunek();
		rachunekDocelowy = (RachunekBankowy) klient2.getListaProduktow().get("1");
		rachunekZrodlowy.setSrodki(100);
		rachunekDocelowy.setSrodki(100);
    }

	@Test
	public void testRozliczPrzelewy() throws Exception {
		PrzelewMiedzybankowy przelewMiedzybankowy = new PrzelewMiedzybankowy(rachunekDocelowy, bankDocelowy, 50, "testowy przelew");
		rachunekZrodlowy.wykonajOperacje(przelewMiedzybankowy);
		bankZrodlowy.wyslijPaczkeDoKIR(new GregorianCalendar(2017, Calendar.MAY, 1), new GregorianCalendar(2017, Calendar.MAY, 5));
		assertEquals("Stan konta zrodlowego: ", 50, rachunekZrodlowy.getSrodki(), 0.001);
		assertEquals("Stan konta docelowego: ", 150, rachunekDocelowy.getSrodki(), 0.001);
	}

	@Test
	public void testRozliczPrzelewyNiepoprawne() throws Exception {
		RachunekBankowy kontoNieprzypisane = new RachunekBankowy(klient2, "123", bankDocelowy);
		kontoNieprzypisane.setSrodki(100);
		PrzelewMiedzybankowy przelewMiedzybankowy = new PrzelewMiedzybankowy(kontoNieprzypisane, bankDocelowy, 50, "testowy przelew");
		rachunekZrodlowy.wykonajOperacje(przelewMiedzybankowy);
		bankZrodlowy.wyslijPaczkeDoKIR(new GregorianCalendar(2017, Calendar.MAY, 1), new GregorianCalendar(2017, Calendar.MAY, 5));
		assertEquals("Stan konta zrodlowego: ", 100, rachunekZrodlowy.getSrodki(), 0.001);
		assertEquals("Stan konta docelowego: ", 100, rachunekDocelowy.getSrodki(), 0.001);
	}

}
