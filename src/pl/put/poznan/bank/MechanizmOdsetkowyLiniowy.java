package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

public class MechanizmOdsetkowyLiniowy implements IMechanizmOdsetkowy {

    private double procent;

    public MechanizmOdsetkowyLiniowy(double procent) {
        this.procent = procent;
    }

    public double naliczOdsetki(ProduktBankowy konto) throws InvalidInputException {
        if (konto != null) {
            return konto.getStanSrodkow() * this.procent;
        } else {
            throw new InvalidInputException("Podane konto nie istnieje.");
        }
    }

    public double getProcent() {
        return this.procent;
    }

    public void setProcent(double procent) {
        this.procent = procent;
    }
}
