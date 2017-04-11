package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.InvalidBankOperationException;

import static org.junit.Assert.*;

public class PrzelewTest {

    RachunekBankowy kontoZrodlowe;
    ProduktBankowy kontoDocelowe;
    Bank bank;
    Klient klient;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("Bank testowy", 1);
        klient = new Klient(1, bank);
        kontoZrodlowe = new RachunekBankowy(klient, "1237129371", bank);
        kontoDocelowe = new RachunekBankowy(klient, "123", bank);
        kontoZrodlowe.setSrodki(100);
        kontoDocelowe.setSrodki(100);
    }

    @Test
    public void testWykonajKwotaDodatnia() throws Exception {
        Przelew przelew = new Przelew(kontoZrodlowe, kontoDocelowe, 100, "test przelew");
        kontoZrodlowe.wykonajOperacje(przelew);
        assertEquals("Wynik przelewu nadawca: ", 0, kontoZrodlowe.getSrodki(), 0.001);
        assertEquals("Wynik przelewu odbiorca: ", 200, kontoDocelowe.getSrodki(), 0.001);
    }

    @Test(expected = InvalidBankOperationException.class)
    public void testWykonajKwotaUjemna() throws Exception {
        Przelew przelew = new Przelew(kontoZrodlowe, kontoDocelowe, -100, "test przelew");
        kontoZrodlowe.wykonajOperacje(przelew);
    }

    @Test(expected = InvalidBankOperationException.class)
    public void testWykonajKwotaZero() throws Exception {
        Przelew przelew = new Przelew(kontoZrodlowe, kontoDocelowe, 0, "test przelew");
        kontoZrodlowe.wykonajOperacje(przelew);
    }

    @Test(expected = InvalidBankOperationException.class)
    public void testWykonajKwotaZbytDuza() throws Exception {
        Przelew przelew = new Przelew(kontoZrodlowe, kontoDocelowe, 3000, "test przelew");
        kontoZrodlowe.wykonajOperacje(przelew);
    }
    @Test(expected = InvalidBankOperationException.class)
    public void testWykonajToSamoKonto() throws Exception {
        Przelew przelew = new Przelew(kontoZrodlowe, kontoZrodlowe, 3000, "test przelew");
        kontoZrodlowe.wykonajOperacje(przelew);
    }


}