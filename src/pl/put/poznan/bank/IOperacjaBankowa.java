package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

public interface IOperacjaBankowa {
    void wplata(final ProduktBankowy konto, final double kwota) throws InvalidInputException;
    void wyplata(final ProduktBankowy konto, final double kwota) throws InvalidInputException, NotEnoughFundsException;
    void przelew(final ProduktBankowy kontoZ, final ProduktBankowy kontoDo, final double kwota) throws InvalidInputException, NotEnoughFundsException;
}
