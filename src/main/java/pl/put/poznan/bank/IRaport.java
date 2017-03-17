package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotDebetException;

import java.util.ArrayList;
import java.util.HashMap;

public interface IRaport {
    Object generujRaport(HashMap<Long, ProduktBankowy> listaProduktow) throws InvalidInputException, NotDebetException;
}
