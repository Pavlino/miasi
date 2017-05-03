package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;

public interface IProduktBankowy {
    public void przyjmijWizytatora(IRaport wizytator);
    public void naliczOdsetki() throws InvalidBankOperationException;
    public void wykonajOperacje(IOperacjaBankowa operacjaBankowa) throws InvalidBankOperationException;
}
