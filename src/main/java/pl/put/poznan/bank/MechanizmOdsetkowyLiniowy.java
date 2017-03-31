package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

public class MechanizmOdsetkowyLiniowy implements IMechanizmOdsetkowy {

    private double procent;

    public MechanizmOdsetkowyLiniowy(double procent) {
        if (procent > 1) {
            procent /= 100;
        }
        this.procent = procent;
    }

    public double naliczOdsetki(ProduktBankowy konto) throws InvalidInputException {
        if (konto != null) {
            return konto.getSrodki() * procent;
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
