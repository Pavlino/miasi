package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import static org.junit.Assert.*;

public class WplataTest {

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

    @Test
    public void testWykonajKwotaDodatnia() throws Exception {
        Wplata wplata = new Wplata(100, "test wplata");
        konto.wykonajOperacje(wplata);
        assertEquals("Wynik wplaty: ", 200, konto.getSrodki(), 0.001);
    }

    @Test(expected = InvalidBankOperationException.class)
    public void testWykonajKwotaUjemna() throws Exception {
        Wplata wplata = new Wplata(-100, "test wplata");
        konto.wykonajOperacje(wplata);
    }

    @Test(expected = InvalidBankOperationException.class)
    public void testWykonajKwotaZero() throws Exception {
        Wplata wplata = new Wplata(0, "test wplata");
        konto.wykonajOperacje(wplata);
    }

    @Test
    public void testWykonajDebet() throws Exception {
        konto.setSrodki(0);
        Debet debet = new Debet(100, 20);
        RachunekBankowyDebetowy rachunekBankowyDebetowy = konto.setDebet(debet);
        Wplata wplata = new Wplata(100, "test wplata");
        rachunekBankowyDebetowy.wykonajOperacje(wplata);
        assertEquals("Wynik wplaty: ", 180, rachunekBankowyDebetowy.getSrodki(), 0.001);
    }

    @Test
    public void testDodajDoHistorii() throws Exception {
        Wplata wplata = new Wplata(100, "test wplata");
        konto.wykonajOperacje(wplata);
        assertEquals("Rozmiar historii: ", 1, konto.getHistoria().getOperacje().size());
        assertEquals("Typ wpisu: ", TypyOperacjiBankowych.WPLATA, konto.pobierzOperacjeBankowaZHistorii(0).getTyp());
        assertEquals("Rozmiar historii banku: ", 1, konto.getBank().getHistoria().getOperacje().size());
        assertEquals("Typ wpisu w historii banku: ", TypyOperacjiBankowych.WPLATA, konto.pobierzOperacjeBankowaZHistoriiBanku(0).getTyp());
    }

}