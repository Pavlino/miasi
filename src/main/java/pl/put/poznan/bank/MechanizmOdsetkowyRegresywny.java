package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

public class MechanizmOdsetkowyRegresywny implements IMechanizmOdsetkowy {

    private double procent;

    public MechanizmOdsetkowyRegresywny(/*double procent*/) {
        /*if (procent > 1) {
            procent /= 100;
        }
        this.procent = procent;*/
    }

    public void naliczOdsetki(ProduktBankowy konto) throws InvalidBankOperationException {
        if (konto != null) {
        	//oblicz procent
        	double srodki = konto.getSrodki();
            double odsetki = 0;
        	if (srodki > 0 && srodki < 1000)
        		odsetki = srodki * 0.2;
        	else if (srodki >= 1000 && srodki < 10000)
        		odsetki =  srodki * 0.1;
        	else
        		odsetki = srodki * 0.05;
            Wplata wplata = new Wplata(odsetki, "Wplata odsetek", TypyOperacjiBankowych.ODSETKI);
            konto.wykonajOperacje(wplata);
        } else {
            throw new InvalidBankOperationException("Podane konto nie istnieje.");
        }
    }

    public double getProcent() {
        return procent;
    }

    public void setProcent(double procent) {
        this.procent = procent;
    }
}

