package pl.put.poznan.bank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.TypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class OperacjaBankowaTest {

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

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWplataKwotaDodatnia() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wplata", TypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(konto, 100);
        assertEquals("Wynik wplaty: ", 200, konto.getSrodki(), 0.001);
    }

    @Test(expected = InvalidInputException.class)
    public void testWplataKwotaUjemna() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wplata", TypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(konto, -100);
    }

    @Test(expected = InvalidInputException.class)
    public void testWplataKwotaZero() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wplata", TypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(konto, 0);
    }

    @Test
    public void testWyplataKwotaDodatnia() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wyplata", TypyOperacjiBankowych.WYPLATA);
        operacjaBankowa.wyplata(konto, 50);
        assertEquals("Wynik wyplaty: ", 50, konto.getSrodki(), 0.001);
    }

    @Test
    public void testWyplataDebet() throws Exception {
        Debet debet = new Debet(1000, 0);
        konto.setDebet(debet);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wyplata", TypyOperacjiBankowych.WYPLATA);
        operacjaBankowa.wyplata(konto, 200);
        assertEquals("Wynik wyplaty: ", 900, konto.getSrodki(), 0.001);
    }

    @Test
    public void testPrzelew() throws Exception {
        Klient klientPrzelew = new Klient(2, bank);
        RachunekBankowy kontoPrzelew = new RachunekBankowy(klientPrzelew, "123", bank);
        kontoPrzelew.setSrodki(100);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowy przelew", TypyOperacjiBankowych.PRZELEW);
        operacjaBankowa.przelew(konto, kontoPrzelew, 50);
        assertEquals("Wynik przelewu nadawca: ", 50, konto.getSrodki(), 0.001);
        assertEquals("Wynik przelewu odbiorca: ", 150, kontoPrzelew.getSrodki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiRachunek() throws Exception {
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowe odsetki", TypyOperacjiBankowych.ODSETKI_RACHUNEK);
        konto.setMechanizmOdsetkowy(mechanizmOdsetkowyLiniowy);
        operacjaBankowa.naliczOdsetki(konto);
        assertEquals("Wynik odsetek: ", 110, konto.getSrodki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiKredyt() throws Exception {
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        Kredyt kredyt = new Kredyt(klient, "123", konto, mechanizmOdsetkowyLiniowy);
        kredyt.zaciagnijKredyt(50);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowe odsetki", TypyOperacjiBankowych.ODSETKI_RACHUNEK);
        kredyt.setMechanizmOdsetkowy(mechanizmOdsetkowyLiniowy);
        operacjaBankowa.naliczOdsetki(kredyt);
        operacjaBankowa.naliczOdsetki(kredyt);
        assertEquals("Wynik odsetek: ", 10, kredyt.getOdsetki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiLokata() throws Exception {
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        Lokata lokata = new Lokata(klient, "123", konto, mechanizmOdsetkowyLiniowy);
        lokata.otworzLokate(50, new GregorianCalendar(2017, 10, 10));
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowe odsetki", TypyOperacjiBankowych.ODSETKI_RACHUNEK);
        lokata.setMechanizmOdsetkowy(mechanizmOdsetkowyLiniowy);
        operacjaBankowa.naliczOdsetki(lokata);
        operacjaBankowa.naliczOdsetki(lokata);
        assertEquals("Wynik odsetek: ", 10, lokata.getOdsetki(), 0.001);
    }

    @Test
    public void testDodajDoHistorii() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wplata", TypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(konto, 100);
        assertEquals("Rozmiar historii: ", 1, konto.getHistoria().getHistoria().size());
        assertEquals("Typ wpisu: ", TypyOperacjiBankowych.WPLATA, konto.getHistoria().getHistoria().get(0).getTyp());
    }
}