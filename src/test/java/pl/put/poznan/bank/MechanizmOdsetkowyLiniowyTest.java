package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class MechanizmOdsetkowyLiniowyTest {

    RachunekBankowy konto;
    Bank bank;
    Klient klient;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("Bank testowy", 1, new KIR());
        klient = new Klient(1, bank);
        konto = new RachunekBankowy(klient, "1237129371", bank);
        konto.setSrodki(100);
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        konto.setMechanizmOdsetkowy(mechanizmOdsetkowyLiniowy);
    }

    @Test
    public void testNaliczOdsetkiRachunek() throws Exception {
        konto.naliczOdsetki();
        assertEquals("Wynik odsetek: ", 110, konto.getSrodki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiKredyt() throws Exception {
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        Kredyt kredyt = new Kredyt(klient, "123", konto, mechanizmOdsetkowyLiniowy);
        kredyt.zaciagnijKredyt(50);
        kredyt.setMechanizmOdsetkowy(mechanizmOdsetkowyLiniowy);
        kredyt.naliczOdsetki();
        kredyt.naliczOdsetki();
        assertEquals("Wynik odsetek: ", 10, kredyt.getOdsetki(), 0.001);
    }

    @Test
    public void testNaliczOdsetkiLokata() throws Exception {
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        Lokata lokata = new Lokata(klient, "123", konto, mechanizmOdsetkowyLiniowy);
        lokata.otworzLokate(50, new GregorianCalendar(2017, 10, 10));
        lokata.setMechanizmOdsetkowy(mechanizmOdsetkowyLiniowy);
        lokata.naliczOdsetki();
        lokata.naliczOdsetki();
        assertEquals("Wynik odsetek: ", 10, lokata.getOdsetki(), 0.001);
    }
}