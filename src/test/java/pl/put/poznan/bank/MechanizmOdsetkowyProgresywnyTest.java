package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.InvalidBankOperationException;

import static org.junit.Assert.*;

public class MechanizmOdsetkowyProgresywnyTest {

    RachunekBankowy konto;
    Bank bank;
    Klient klient;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("Bank testowy", 1);
        klient = new Klient(1, bank);
        konto = new RachunekBankowy(klient, "1237129371", bank);
        konto.setSrodki(100);
        MechanizmOdsetkowyProgresywny mechanizmOdsetkowyProgresywny = new MechanizmOdsetkowyProgresywny();
        konto.setMechanizmOdsetkowy(mechanizmOdsetkowyProgresywny);
    }

    @Test
    public void testNaliczOdsetkiProgresywny100() throws InvalidBankOperationException {
        konto.getMechanizmOdsetkowy().naliczOdsetki(this.konto);
        assertEquals("Wynik odsetek: ", 101, this.konto.getSrodki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiProgresywny10100() throws InvalidBankOperationException {
    	konto.setSrodki(10100);
        konto.getMechanizmOdsetkowy().naliczOdsetki(this.konto);
        assertEquals("Wynik odsetek: ", 10211.1, this.konto.getSrodki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiProgresywny20100() throws InvalidBankOperationException {
    	konto.setSrodki(20100);
        konto.getMechanizmOdsetkowy().naliczOdsetki(this.konto);
        assertEquals("Wynik odsetek: ", 20341.2, this.konto.getSrodki(), 0.001);
    }
}