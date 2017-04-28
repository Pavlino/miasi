package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;

public class RachunekBankowyDebetowy extends ProduktBankowy {

    private RachunekBankowy rachunekBankowy;
    private Debet debet;

    public RachunekBankowyDebetowy(RachunekBankowy rachunekBankowy, Debet debet) {
        this.rachunekBankowy = rachunekBankowy;
        this.debet = debet;
        this.srodki = rachunekBankowy.getSrodki();
    }

    @Override
    public double getSrodki() {
        double pozostalyDebet = debet.getMaxKwotaDebetu() - debet.getKwotaDebetu();
        return srodki + pozostalyDebet;
    }

    @Override
    public void setSrodki(double sumaSrodkow) {
        double dostepneSrodki = getSrodki();
        double saldo = sumaSrodkow - dostepneSrodki;
        if (saldo < 0) {
            if ((srodki + saldo) >= 0) {
                srodki += saldo;
            } else {
                double dodatkowyDebet = (srodki + saldo) * -1;
                srodki = 0;
                debet.setKwotaDebetu(debet.getKwotaDebetu() + dodatkowyDebet);
            }

        } else {
            double pozostaleSrodki = debet.getKwotaDebetu() - saldo;
            if (pozostaleSrodki >= 0) {
                debet.setKwotaDebetu(pozostaleSrodki);
            } else {
                debet.setKwotaDebetu(0);
                srodki -= pozostaleSrodki;
            }
        }
    }

    public double getKwotaDebetu() {
        return debet.getKwotaDebetu();
    }

    public double getMaxKwotaDebetu() {
        return debet.getMaxKwotaDebetu();
    }

    public void dodajOperacjeDoHistorii(IOperacjaBankowa operacjaBankowa) throws InvalidBankOperationException {
        rachunekBankowy.getHistoria().dodajOperacje(operacjaBankowa);
    }

    public void dodajOperacjeDoHistoriiBanku(IOperacjaBankowa operacjaBankowa) throws InvalidBankOperationException {
        rachunekBankowy.getBank().dodajOperacjeDoHistorii(operacjaBankowa);
    }

    public IOperacjaBankowa pobierzOperacjeBankowaZHistorii(int indeks) {
        return rachunekBankowy.getHistoria().getOperacje().get(indeks);
    }

    public IOperacjaBankowa pobierzOperacjeBankowaZHistoriiBanku(int indeks) {
        return rachunekBankowy.getBank().pobierzOperacjeZHistorii(indeks);
    }

    public void naliczOdsetki() throws InvalidBankOperationException {
        rachunekBankowy.getMechanizmOdsetkowy().naliczOdsetki(this);
    }

    public Klient getKlient() {
        return rachunekBankowy.getKlient();
    }

    public void setKlient(Klient klient) {
        rachunekBankowy.setKlient(klient);
    }

    public String getNumerRachunku() {
        return rachunekBankowy.getNumerRachunku();
    }

    public IMechanizmOdsetkowy getMechanizmOdsetkowy() {
        return rachunekBankowy.getMechanizmOdsetkowy();
    }

    public void setMechanizmOdsetkowy(IMechanizmOdsetkowy mechanizmOdsetkowy) {
        rachunekBankowy.setMechanizmOdsetkowy(mechanizmOdsetkowy);
    }

    public Historia getHistoria() {
        return rachunekBankowy.getHistoria();
    }

    public Bank getBank() {
        return rachunekBankowy.getBank();
    }

    public void setBank(Bank bank) {
        rachunekBankowy.setBank(bank);
    }

    public void setOdsetki(double odsetki) {
        rachunekBankowy.setSrodki(rachunekBankowy.getSrodki() + odsetki);
    }

}
