package pl.put.poznan.bank;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

import static org.junit.Assert.*;

public class KlientTest {

    Klient klient;
    Bank bank;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("Testowy", 1);
        klient = bank.stworzKlienta();
        klient.otworzRachunek();
        RachunekBankowy rachunekBankowy = (RachunekBankowy) klient.getListaProduktow().get("1");
        Wplata wplata = new Wplata(200, "test wplata", TypyOperacjiBankowych.WPLATA);
        rachunekBankowy.wykonajOperacje(wplata);
    }

    @Test
    public void testOtworzRachunek() throws Exception {
        klient.otworzRachunek();
        assertEquals("Otwarcie rachunku: ", 2, klient.getListaProduktow().size());
    }

    @Test
    public void testDodajDebetDoRachunku() throws Exception {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) klient.getListaProduktow().get("1");
        Debet debet = new Debet(1000);
        klient.dodajDebetDoRachunku(rachunekBankowy.getNumerRachunku(), debet);
        RachunekBankowyDebetowy rachunekBankowyDebetowy = (RachunekBankowyDebetowy) bank.getListaRachunkow().get(rachunekBankowy.getNumerRachunku());
        assertEquals("Stan debetu: ", 1000, rachunekBankowyDebetowy.getMaxKwotaDebetu(), 0.001);
    }

    @Test
    public void testOtworzLokate() throws Exception {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) klient.getListaProduktow().get("1");
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        klient.otworzLokate(rachunekBankowy.getNumerRachunku(), 100, mechanizmOdsetkowyLiniowy);
        Lokata lokata = (Lokata) klient.getListaProduktow().get("2");
        assertEquals("Otwarcie lokaty: ", 2, klient.getListaProduktow().keySet().size());
        assertEquals("Stan konta: ", 100, rachunekBankowy.getSrodki(), 0.001);
        assertEquals("Stan lokaty: ", 100, rachunekBankowy.getSrodki(), 0.001);
    }

    @Test(expected = InvalidBankOperationException.class)
    public void testOtworzLokateNiewystarczajaceFundusze() throws Exception {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) klient.getListaProduktow().get("1");
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        klient.otworzLokate(rachunekBankowy.getNumerRachunku(), 300, mechanizmOdsetkowyLiniowy);
    }

    @Test
    public void testZaciagnijKredyt() throws Exception {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) klient.getListaProduktow().get("1");
        MechanizmOdsetkowyLiniowy mechanizmOdsetkowyLiniowy = new MechanizmOdsetkowyLiniowy(0.1);
        klient.zaciagnijKredyt(rachunekBankowy.getNumerRachunku(), 100, mechanizmOdsetkowyLiniowy);
        assertEquals("Otwarcie kredytu: ", 2, klient.getListaProduktow().keySet().size());
        assertEquals("Stan konta: ", 300, rachunekBankowy.getSrodki(), 0.001);
    }
}