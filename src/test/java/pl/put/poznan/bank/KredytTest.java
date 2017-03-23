package pl.put.poznan.bank;

import org.junit.Test;
import org.junit.Before;
import pl.put.poznan.utils.ITypyOperacjiBankowych;
import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.Date;
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

    @Test(expected = InvalidInputException.class)
    public void zaciagnijKredytInvalidInput() throws Exception {
        kr.zaciagnijKredyt(-500);
    }


    @Test(expected = NotEnoughFundsException.class)
    public void splacKredyt() throws Exception {
        kr.zaciagnijKredyt(500);
        OperacjaBankowa opb = new OperacjaBankowa(new GregorianCalendar(), "splata", ITypyOperacjiBankowych.WYPLATA);
        opb.wyplata(rb,400);
        kr.splacKredyt();
    }

}