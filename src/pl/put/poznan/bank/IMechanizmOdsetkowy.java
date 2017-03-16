package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

public interface IMechanizmOdsetkowy {
    double naliczOdsetki(ProduktBankowy konto) throws InvalidInputException;
}
