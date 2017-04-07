package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

public class MechanizmOdsetkowyRegresywny implements IMechanizmOdsetkowy {

    private double procent;

    public MechanizmOdsetkowyRegresywny(/*double procent*/) {
        /*if (procent > 1) {
            procent /= 100;
        }
        this.procent = procent;*/
    }

    public double naliczOdsetki(ProduktBankowy konto) throws InvalidInputException {
        if (konto != null) {
        	//oblicz procent
        	double srodki = konto.getSrodki();
        	if (srodki > 0 && srodki < 1000)
        		return srodki * 0.2;
        	else if (srodki >= 1000 && srodki < 10000)
        		return srodki * 0.1;
        	else if (srodki >= 10000)
        		return srodki * 0.05;
        	else
        		return srodki;
        } else {
            throw new InvalidInputException("Podane konto nie istnieje.");
        }
    }

    public double getProcent() {
        return procent;
    }

    public void setProcent(double procent) {
        this.procent = procent;
    }
}

