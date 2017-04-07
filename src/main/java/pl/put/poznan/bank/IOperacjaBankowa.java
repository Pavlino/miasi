package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.Calendar;

public interface IOperacjaBankowa {
    void wykonaj() throws InvalidInputException;
    Calendar getData();
}
