package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class RaportSumaOdsetekTest {

    private Bank bank;
    private Klient klient;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("testowy", 1, new KIR());
        klient = bank.stworzKlienta();
        RachunekBankowy konto = bank.stworzRachunek(klient);
        konto.setSrodki(1000);
        RachunekBankowy konto2 = bank.stworzRachunek(klient);
        konto.setSrodki(1000);
        Lokata lokata = bank.stworzLokate(klient, konto, 500, new GregorianCalendar(2017, Calendar.DECEMBER, 31), new MechanizmOdsetkowyLiniowy(0.1));
        lokata.naliczOdsetki();
        Kredyt kredyt = bank.stworzKredyt(klient, konto2, 1000, new MechanizmOdsetkowyLiniowy(0.1));
        kredyt.naliczOdsetki();

    }

    @Test
    public void testWygenerujRaport() throws Exception {
        double sumaOdsetek = (Double) bank.stworzRaport(new RaportSumaOdsetek(bank));
        assertEquals("Suma odsetek:", 50, sumaOdsetek, 0.001);
    }
}
