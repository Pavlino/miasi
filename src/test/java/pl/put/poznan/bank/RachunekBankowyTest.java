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
        this.bank = new Bank("bank testowy", 1);
        this.klient = new Klient(1, this.bank);
        this.rachunekBankowy = new RachunekBankowy(klient, "123", bank);
    }

    @Test
    public void czyNiePosiadaDebet() throws Exception {
        assertFalse(this.rachunekBankowy.czyPosiadaDebet());
    }

    @Test
    public void czyPosiadaDebet() throws Exception {
        Debet debet = new Debet(100);
        this.rachunekBankowy.setDebet(debet);
        assertTrue(this.rachunekBankowy.czyPosiadaDebet());
    }

    @Test
    public void setSrodki() throws Exception {
        this.rachunekBankowy.setSrodki(100);
        assertEquals(100, this.rachunekBankowy.srodki, 0.001);
    }

    @Test
    public void getSrodkiDebet() throws Exception {
        this.rachunekBankowy.setSrodki(100);
        Debet debet = new Debet(100, 50);
        this.rachunekBankowy.setDebet(debet);
        assertEquals(150, this.rachunekBankowy.getSrodki(), 0.001);
    }

}