package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class RachunekBankowyTest {

    RachunekBankowy rachunekBankowy;
    Klient klient;
    Bank bank;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("bank testowy", 1, new KIR());
        klient = new Klient(1, bank);
        rachunekBankowy = new RachunekBankowy(klient, "123", bank);
    }

    @Test
    public void setSrodki() throws Exception {
        rachunekBankowy.setSrodki(100);
        assertEquals(100, rachunekBankowy.srodki, 0.001);
    }

    @Test
    public void getSrodki() throws Exception {
        rachunekBankowy.srodki = 100;
        assertEquals(100, rachunekBankowy.getSrodki(), 0.001);
    }

}