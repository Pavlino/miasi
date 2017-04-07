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
        bank = new Bank("bank testowy", 1);
        klient = new Klient(1, bank);
        rachunekBankowy = new RachunekBankowy(klient, "123", bank);
    }

    @Test
    public void czyNiePosiadaDebet() throws Exception {
        assertFalse(rachunekBankowy.czyPosiadaDebet());
    }

    @Test
    public void czyPosiadaDebet() throws Exception {
        Debet debet = new Debet(100);
        rachunekBankowy.setDebet(debet);
        assertTrue(rachunekBankowy.czyPosiadaDebet());
    }

    @Test
    public void setSrodki() throws Exception {
        rachunekBankowy.setSrodki(100);
        assertEquals(100, rachunekBankowy.srodki, 0.001);
    }

    @Test
    public void getSrodkiDebet() throws Exception {
        rachunekBankowy.setSrodki(100);
        Debet debet = new Debet(100, 50);
        rachunekBankowy.setDebet(debet);
        assertEquals(150, rachunekBankowy.getSrodki(), 0.001);
    }

}