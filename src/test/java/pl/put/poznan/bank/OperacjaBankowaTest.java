package pl.put.poznan.bank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class OperacjaBankowaTest {

    RachunekBankowy konto;
    Bank bank;
    Klient klient;

    @Before
    public void setUp() throws Exception {
        this.bank = new Bank("Bank testowy", 1);
        this.klient = new Klient(1, this.bank);
        this.konto = new RachunekBankowy(this.klient, "1237129371", this.bank);
        this.konto.setSrodki(100);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWplataKwotaDodatnia() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wplata", ITypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(this.konto, 100);
        assertEquals("Wynik wplaty: ", 200, konto.getSrodki(), 0.001);
    }

    @Test(expected = InvalidInputException.class)
    public void testWplataKwotaUjemna() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wplata", ITypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(this.konto, -100);
    }

    @Test(expected = InvalidInputException.class)
    public void testWplataKwotaZero() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wplata", ITypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(this.konto, 0);
    }

    @Test
    public void testWyplataKwotaDodatnia() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wyplata", ITypyOperacjiBankowych.WYPLATA);
        operacjaBankowa.wyplata(this.konto, 50);
        assertEquals("Wynik wyplaty: ", 50, this.konto.getSrodki(), 0.001);
    }

    @Test
    public void testWyplataDebet() throws Exception {
        Debet debet = new Debet(1000, 0);
        this.konto.setDebet(debet);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wyplata", ITypyOperacjiBankowych.WYPLATA);
        operacjaBankowa.wyplata(this.konto, 200);
        assertEquals("Wynik wyplaty: ", 900, this.konto.getSrodki(), 0.001);
    }

    @Test
    public void testPrzelew() throws Exception {
        Klient klientPrzelew = new Klient(2, this.bank);
        RachunekBankowy kontoPrzelew = new RachunekBankowy(klientPrzelew, "123", this.bank);
        kontoPrzelew.setSrodki(100);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowy przelew", ITypyOperacjiBankowych.PRZELEW);
        operacjaBankowa.przelew(this.konto, kontoPrzelew, 50);
        assertEquals("Wynik przelewu nadawca: ", 50, this.konto.getSrodki(), 0.001);
        assertEquals("Wynik przelewu odbiorca: ", 150, kontoPrzelew.getSrodki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiRachunek() throws Exception {
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowe odsetki", ITypyOperacjiBankowych.ODSETKI_RACHUNEK);
        this.konto.setMechanizmOdsetkowy(mechanizmOdsetkowyLiniowy);
        operacjaBankowa.naliczOdsetki(this.konto);
        assertEquals("Wynik odsetek: ", 110, this.konto.getSrodki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiKredyt() throws Exception {
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        Kredyt kredyt = new Kredyt(this.klient, "123", this.konto, mechanizmOdsetkowyLiniowy);
        kredyt.zaciagnijKredyt(50);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowe odsetki", ITypyOperacjiBankowych.ODSETKI_RACHUNEK);
        kredyt.setMechanizmOdsetkowy(mechanizmOdsetkowyLiniowy);
        operacjaBankowa.naliczOdsetki(kredyt);
        operacjaBankowa.naliczOdsetki(kredyt);
        assertEquals("Wynik odsetek: ", 10, kredyt.getOdsetki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiLokata() throws Exception {
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        Lokata lokata = new Lokata(this.klient, "123", this.konto, mechanizmOdsetkowyLiniowy);
        lokata.otworzLokate(50, new GregorianCalendar(2017, 10, 10));
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowe odsetki", ITypyOperacjiBankowych.ODSETKI_RACHUNEK);
        lokata.setMechanizmOdsetkowy(mechanizmOdsetkowyLiniowy);
        operacjaBankowa.naliczOdsetki(lokata);
        operacjaBankowa.naliczOdsetki(lokata);
        assertEquals("Wynik odsetek: ", 10, lokata.getOdsetki(), 0.001);
    }

    @Test
    public void testDodajDoHistorii() throws Exception {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowa wplata", ITypyOperacjiBankowych.WPLATA);
        operacjaBankowa.wplata(this.konto, 100);
        assertEquals("Rozmiar historii: ", 1, this.konto.getHistoria().getHistoria().size());
        assertEquals("Typ wpisu: ", ITypyOperacjiBankowych.WPLATA, this.konto.getHistoria().getHistoria().get(0).getTyp());
    }
    
    @Test
    public void testNaliczOdsetkiA100() throws InvalidInputException{
    	konto.setSrodki(100);
    	MechanizmOdsetkowyProgresywny mechanizmOdsetkowy = new MechanizmOdsetkowyProgresywny();
    	OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowe odsetki", ITypyOperacjiBankowych.ODSETKI_RACHUNEK);
    	this.konto.setMechanizmOdsetkowy(mechanizmOdsetkowy);
        operacjaBankowa.naliczOdsetki(this.konto);
        assertEquals("Wynik odsetek: ", 101, this.konto.getSrodki(), 0.001);
    }
    
    @Test
    public void testNaliczOdsetkiA10100() throws InvalidInputException{
    	konto.setSrodki(10100);
    	MechanizmOdsetkowyProgresywny mechanizmOdsetkowy = new MechanizmOdsetkowyProgresywny();
    	OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowe odsetki", ITypyOperacjiBankowych.ODSETKI_RACHUNEK);
    	this.konto.setMechanizmOdsetkowy(mechanizmOdsetkowy);
        operacjaBankowa.naliczOdsetki(this.konto);
        assertEquals("Wynik odsetek: ", 10211.1, this.konto.getSrodki(), 0.001);
    }
    
    @Test
    public void testNaliczOdsetkiA20100() throws InvalidInputException{
    	konto.setSrodki(20100);
    	MechanizmOdsetkowyProgresywny mechanizmOdsetkowy = new MechanizmOdsetkowyProgresywny();
    	OperacjaBankowa operacjaBankowa = new OperacjaBankowa(new GregorianCalendar(), "Testowe odsetki", ITypyOperacjiBankowych.ODSETKI_RACHUNEK);
    	this.konto.setMechanizmOdsetkowy(mechanizmOdsetkowy);
        operacjaBankowa.naliczOdsetki(this.konto);
        assertEquals("Wynik odsetek: ", 20341.2, this.konto.getSrodki(), 0.001);
    }
}