package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.InvalidBankOperationException;

import static org.junit.Assert.*;

public class WyplataTest {

    RachunekBankowy konto;
    Bank bank;
    Klient klient;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("Bank testowy", 1);
        klient = new Klient(1, bank);
        konto = new RachunekBankowy(klient, "1237129371", bank);
        konto.setSrodki(100);
    }

    @Test
    public void testWykonajKwotaDodatnia() throws Exception {
        Wyplata wyplata = new Wyplata(konto, 100, "test wyplata");
        konto.wykonajOperacje(wyplata);
        assertEquals("Wynik wyplaty: ", 0, konto.getSrodki(), 0.001);
    }

    @Test(expected = InvalidBankOperationException.class)
    public void testWykonajKwotaUjemna() throws Exception {
        Wyplata wyplata = new Wyplata(konto, -100, "test wyplata");
        konto.wykonajOperacje(wyplata);
    }

    @Test(expected = InvalidBankOperationException.class)
    public void testWykonajKwotaZbytDuza() throws Exception {
        Wyplata wyplata = new Wyplata(konto, 200, "test wyplata");
        konto.wykonajOperacje(wyplata);
    }

    @Test
    public void testWyplataDebet() throws Exception {
        Debet debet = new Debet(1000, 0);
        konto.setDebet(debet);
        Wyplata wyplata = new Wyplata(konto, 200, "test wyplata");
        konto.wykonajOperacje(wyplata);
        assertEquals("Wynik wyplaty: ", 900, konto.getSrodki(), 0.001);
    }
}