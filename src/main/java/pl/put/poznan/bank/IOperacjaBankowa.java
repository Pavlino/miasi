package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;

import java.util.Calendar;

public interface IOperacjaBankowa {
    void wykonaj() throws InvalidBankOperationException;
    Calendar getData();
    int getTyp();
}
