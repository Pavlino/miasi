package pl.put.poznan.bank;

import org.junit.Test;
import org.junit.Before;
import pl.put.poznan.utils.TypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidBankOperationException;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by pawel on 2017-03-23.
 */
public class KredytTest {
    Bank bank;
    Klient k;
    RachunekBankowy rb;
    Kredyt kr;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("TEST", 1);
        k = new Klient(1,bank);
        rb = new RachunekBankowy(k, "00 11 22",bank);
        kr = new Kredyt(k,"02369418554145545",rb,new MechanizmOdsetkowyLiniowy(0.1));
    }

    @Test
    public void zaciagnijKredyt() throws Exception {
        kr.zaciagnijKredyt(500);
        assertEquals(500,kr.getSrodki(), 0.001);
        assertEquals(500, rb.getSrodki(), 0.001);
    }

    @Test(expected = InvalidBankOperationException.class)
    public void zaciagnijKredytInvalidInput() throws Exception {
        kr.zaciagnijKredyt(-500);
    }


    @Test(expected = InvalidBankOperationException.class)
    public void splacKredyt() throws Exception {
        kr.zaciagnijKredyt(500);
        Wyplata wyplata = new Wyplata(rb, 400, "test wyplata", TypyOperacjiBankowych.WYPLATA);
        rb.wykonajOperacje(wyplata);
        kr.splacKredyt();
    }

}