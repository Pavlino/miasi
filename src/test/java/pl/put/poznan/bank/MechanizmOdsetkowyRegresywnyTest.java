package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MechanizmOdsetkowyRegresywnyTest {

    RachunekBankowy konto;
    Bank bank;
    Klient klient;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("Bank testowy", 1, new KIR());
        klient = new Klient(1, bank);
        konto = new RachunekBankowy(klient, "1237129371", bank);
        konto.setSrodki(100);
        MechanizmOdsetkowyRegresywny mechanizmOdsetkowyRegresywny = new MechanizmOdsetkowyRegresywny();
        konto.setMechanizmOdsetkowy(mechanizmOdsetkowyRegresywny);
    }

    @Test
    public void testNaliczOdsetkiRegresywneRachunekPierwszyProg() throws Exception {
        konto.naliczOdsetki();
        assertEquals("Wynik odsetek: ", 120, konto.getSrodki(), 0.001);
    }
    @Test
    public void testNaliczOdsetkiRegresywneRachunekTrzeciProg() throws Exception {
        konto.setSrodki(10010);
        konto.naliczOdsetki();
        assertEquals("Wynik odsetek: ", 10510.5 , konto.getSrodki(), 0.001);
    }
    @Test
    public void testNaliczOdsetkiRegresywneRachunekDrugiProg() throws Exception {
        konto.setSrodki(1250);
        konto.naliczOdsetki();
        assertEquals("Wynik odsetek: ", 1375, konto.getSrodki(), 0.001);
    }

}