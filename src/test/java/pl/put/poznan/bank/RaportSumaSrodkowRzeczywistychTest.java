package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RaportSumaSrodkowRzeczywistychTest {

    private Bank bank;
    private Klient klient;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("testowy", 1, new KIR());
        klient = bank.stworzKlienta();
        RachunekBankowy konto = bank.stworzRachunek(klient);
        konto.setSrodki(1000);
        konto = bank.stworzRachunek(klient);
        konto.setSrodki(100);
        bank.stworzRachunekDebetowy(klient, new Debet(100), 200);
        bank.stworzRachunekDebetowy(klient, new Debet(100), 0);
        bank.stworzKredyt(klient, konto, 1000, new MechanizmOdsetkowyLiniowy(0.1));
    }

    @Test
    public void testWygenerujRaport() throws Exception {
        double sumaSrodkow = (Double) bank.stworzRaport(new RaportSumaSrodkowRzeczywistych(bank));
        assertEquals("Suma srodkow rzeczywistych:", 1300, sumaSrodkow, 0.001);
    }
}
