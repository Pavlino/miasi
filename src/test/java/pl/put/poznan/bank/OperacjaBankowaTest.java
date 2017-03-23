package pl.put.poznan.bank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;

import java.util.Date;

import static org.junit.Assert.*;

public class OperacjaBankowaTest {

    RachunekBankowy konto;

    @Before
    public void setUp() throws Exception {
        Bank bank = new Bank("Bank testowy", 1);
        this.konto = new RachunekBankowy(1, "1237129371", bank);
        this.konto.setSrodki(100);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWplataKwotaDodatnia() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Testowa wplata", ITypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(this.konto, 100);
        assertEquals("Wynik wplaty: ", 200, konto.getSrodki(), 0.001);
    }

    @Test(expected = InvalidInputException.class)
    public void testWplataKwotaUjemna() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Testowa wplata", ITypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(this.konto, -100);
    }

    @Test(expected = InvalidInputException.class)
    public void testWplataKwotaZero() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Testowa wplata", ITypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(this.konto, 0);
    }

    @Test
    public void testWyplataKwotaDodatnia() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Testowa wyplata", ITypyOperacjiBankowych.WYPLATA);
        operacjaBankowa.wyplata(this.konto, 50);
        assertEquals("Wynik wyplaty: ", 50, this.konto.getSrodki(), 0.001);
    }

    @Test
    public void testWyplataDebet() throws Exception {
        Debet debet = new Debet(1000, 0);
        this.konto.setDebet(debet);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new Date(), "Testowa wyplata", ITypyOperacjiBankowych.WYPLATA);
        operacjaBankowa.wyplata(this.konto, 200);
        assertEquals("Wynik wyplaty: ", 900, this.konto.getSrodki(), 0.001);
    }

    @Test
    public void testWplataOdsetek() throws Exception {

    }

    @Test
    public void testNaliczOdsetki() throws Exception {

    }
}