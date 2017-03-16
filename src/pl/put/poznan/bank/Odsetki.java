package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

public class Odsetki implements IOdsetki {

    private IMechanizmOdsetkowy mechanizmOdsetkowy;

    public Odsetki(IMechanizmOdsetkowy mechanizmOdsetkowy) {
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
    }

    public double naliczOdsetki(ProduktBankowy konto) throws InvalidInputException {
        return this.mechanizmOdsetkowy.naliczOdsetki(konto);
    }

    public void setMechanizmOdsetkowy(IMechanizmOdsetkowy mechanizmOdsetkowy) {
        this.mechanizmOdsetkowy = mechanizmOdsetkowy;
    }

}
