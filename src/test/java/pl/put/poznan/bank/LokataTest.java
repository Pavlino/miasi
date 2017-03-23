package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class LokataTest {

    Klient klient;
    RachunekBankowy konto;
    Bank bank;
    Lokata lokata;

    @Before
    public void setUp() throws Exception {
        this.bank = new Bank("Bank testowy", 1);
        this.klient = new Klient(1, this.bank);
        this.konto = new RachunekBankowy(this.klient, "1237129371", this.bank);
        this.konto.setSrodki(100);
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        this.lokata = new Lokata(this.klient, "123", this.konto, mechanizmOdsetkowyLiniowy);
    }

    @Test
    public void testOtworzLokate() throws Exception {
        this.lokata.otworzLokate(50, new GregorianCalendar(2017, 10, 10));
        assertEquals("Stan konta powiazanego: ", 50, this.konto.getSrodki(), 0.001);
        assertEquals("Stan lokaty: ", 50, this.lokata.getSrodki(), 0.001);
    }

    @Test
    public void testZerwijLokate() throws Exception {
        this.lokata.otworzLokate(50, new GregorianCalendar(2017, 10, 10));
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Odsetki dla lokaty", ITypyOperacjiBankowych.ODSETKI_LOKATA);
        operacjaBankowa.naliczOdsetki(lokata);
        this.lokata.zerwijLokate();
        assertEquals("Stan konta powiazanego: ", 100, this.konto.getSrodki(), 0.001);
        assertEquals("Stan lokaty: ", 0, this.lokata.getSrodki(), 0.001);
        assertEquals("Stan odsetek: ", 0, this.lokata.getSrodki(), 0.001);

    }

    @Test
    public void testRozwiazLokate() throws Exception {
        this.lokata.otworzLokate(50, new GregorianCalendar(2017, 1, 10));
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Odsetki dla lokaty", ITypyOperacjiBankowych.ODSETKI_LOKATA);
        operacjaBankowa.naliczOdsetki(lokata);
        System.out.println(lokata.getSrodki());
        this.lokata.rozwiazLokate();
        assertEquals("Stan konta powiazanego: ", 105, this.konto.getSrodki(), 0.001);
        assertEquals("Stan lokaty: ", 0, this.lokata.getSrodki(), 0.001);
        assertEquals("Stan odsetek: ", 0, this.lokata.getSrodki(), 0.001);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLokataWyplataPrzedKoncem() throws Exception {
        this.lokata.otworzLokate(50, new GregorianCalendar(2017, 10, 10));
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Wyplata lokaty", ITypyOperacjiBankowych.WYPLATA);
        operacjaBankowa.przelew(lokata, konto, 10);
    }
}