package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

public interface IOdsetki {
    void setMechanizmOdsetkowy(IMechanizmOdsetkowy mechanizmOdsetkowy);
    double naliczOdsetki(ProduktBankowy konto) throws InvalidInputException;
}
