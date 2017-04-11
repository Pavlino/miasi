package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class LokataTest {

    Klient klient;
    RachunekBankowy konto;
    Bank bank;
    Lokata lokata;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("Bank testowy", 1);
        klient = new Klient(1, bank);
        konto = new RachunekBankowy(klient, "1237129371", bank);
        konto.setSrodki(100);
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        lokata = new Lokata(klient, "123", this.konto, mechanizmOdsetkowyLiniowy);
    }

    @Test
    public void testOtworzLokate() throws Exception {
        lokata.otworzLokate(50, new GregorianCalendar(2017, 10, 10));
        assertEquals("Stan konta powiazanego: ", 50, konto.getSrodki(), 0.001);
        assertEquals("Stan lokaty: ", 50, lokata.getSrodki(), 0.001);
    }

    @Test
    public void testZerwijLokate() throws Exception {
        lokata.otworzLokate(50, new GregorianCalendar(2017, 10, 10));
        lokata.getMechanizmOdsetkowy().naliczOdsetki(lokata);
        lokata.zerwijLokate();
        assertEquals("Stan konta powiazanego: ", 100, konto.getSrodki(), 0.001);
        assertEquals("Stan lokaty: ", 0, lokata.getSrodki(), 0.001);
        assertEquals("Stan odsetek: ", 0, lokata.getSrodki(), 0.001);

    }

    @Test
    public void testRozwiazLokate() throws Exception {
        lokata.otworzLokate(50, new GregorianCalendar(2017, 1, 10));
        lokata.getMechanizmOdsetkowy().naliczOdsetki(lokata);
        lokata.rozwiazLokate();
        assertEquals("Stan konta powiazanego: ", 105, this.konto.getSrodki(), 0.001);
        assertEquals("Stan lokaty: ", 0, this.lokata.getSrodki(), 0.001);
        assertEquals("Stan odsetek: ", 0, this.lokata.getSrodki(), 0.001);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLokataWyplataPrzedKoncem() throws Exception {
        lokata.otworzLokate(50, new GregorianCalendar(2017, 10, 10));
        lokata.rozwiazLokate();
    }
}