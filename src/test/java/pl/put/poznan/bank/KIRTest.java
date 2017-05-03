package pl.put.poznan.bank;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class KIRTest {
	
	KIR testowy;
	Bank docelowy0, docelowy1;
	
	
	@Before
    public void setUp() throws Exception {
        testowy = new KIR();
        docelowy0 = new Bank("test docelowy", 0, testowy);
        docelowy1 = new Bank("test docelowy2", 1, testowy);
    }

//	@Test
//	public void testRozliczPrzelewy() {
//		ArrayList<PrzelewMiedzybankowy> testowe = new ArrayList<PrzelewMiedzybankowy>();
//		PrzelewMiedzybankowy pmb1 = new PrzelewMiedzybankowy(null,docelowy0,100.0,"test1");
//		PrzelewMiedzybankowy pmb2 = new PrzelewMiedzybankowy(null,docelowy1,50.0,"test2");
//		testowe.add(pmb1);
//		testowe.add(pmb2);
//		testowy.rozliczPrzelewy(testowe);
//		//testowy.rozliczPrzelewy(testowe);
//	}

}
