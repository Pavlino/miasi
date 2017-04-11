package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;

public interface IMechanizmOdsetkowy {
    void naliczOdsetki(ProduktBankowy konto) throws InvalidBankOperationException;
}
