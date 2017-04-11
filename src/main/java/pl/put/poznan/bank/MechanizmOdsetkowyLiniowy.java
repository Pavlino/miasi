package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

public class MechanizmOdsetkowyLiniowy implements IMechanizmOdsetkowy {

    private double procent;

    public MechanizmOdsetkowyLiniowy(double procent) {
        if (procent > 1) {
            procent /= 100;
        }
        this.procent = procent;
    }

    public void naliczOdsetki(ProduktBankowy konto) throws InvalidBankOperationException {
        if (konto != null) {
            double odsetki = konto.getSrodki() * procent;
            Wplata wplata = new Wplata(konto, odsetki, "Wplata odsetek", TypyOperacjiBankowych.ODSETKI);
            konto.wykonajOperacje(wplata);
        } else {
            throw new InvalidBankOperationException("Podane konto nie istnieje.");
        }
    }

    public double getProcent() {
        return procent;
    }

    public void setProcent(double procent) {
        this.procent = procent;
    }
}
