package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

public class MechanizmOdsetkowyLiniowyLokata implements IMechanizmOdsetkowy {

    private double procent;

    public MechanizmOdsetkowyLiniowyLokata(double procent) {
        this.procent = procent;
    }

    public double naliczOdsetki(ProduktBankowy konto) throws InvalidInputException {
        if (konto != null) {
            return konto.getStanSrodkow() * this.procent;
        } else {
            throw new InvalidInputException("Podane konto nie istnieje.");
        }
    }
}
