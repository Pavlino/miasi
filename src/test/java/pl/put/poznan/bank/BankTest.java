package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.InvalidInputException;

import static org.junit.Assert.*;


public class BankTest {

    Bank bank;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("test bank", 1);
    }

    @Test
    public void testStworzKlienta() throws Exception {
        Klient klient = bank.stworzKlienta();
        Klient drugiKlient = bank.stworzKlienta();
        assertEquals("Id klienta: ", klient.getId()+1, drugiKlient.getId());
    }

    @Test(expected = InvalidInputException.class)
    public void testStworzRachunek() throws Exception {
        Klient klient = bank.stworzKlienta();
        klient.setId(10);
        bank.stworzRachunek(klient);
    }

}