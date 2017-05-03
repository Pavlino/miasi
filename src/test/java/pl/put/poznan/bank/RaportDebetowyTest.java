package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RaportDebetowyTest {

    private Bank bank;
    private Klient klient;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("testowy", 1, new KIR());
        klient = bank.stworzKlienta();
        RachunekBankowy konto = bank.stworzRachunek(klient);
        bank.stworzRachunek(klient);
        bank.stworzRachunekDebetowy(klient, new Debet(100), 100);
        bank.stworzRachunekDebetowy(klient, new Debet(100), 100);
        bank.stworzKredyt(klient, konto, 1000, new MechanizmOdsetkowyLiniowy(0.1));
    }

    @Test
    public void testWygenerujRaport() throws Exception {
        int liczbaKontDebetowych = (Integer) bank.stworzRaport(new RaportDebetowy(bank));
        assertEquals("Liczba kont debetowych:", 2, liczbaKontDebetowych);
    }
}