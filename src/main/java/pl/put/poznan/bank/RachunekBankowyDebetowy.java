package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;

public class RachunekBankowyDebetowy extends ProduktBankowy {

    private RachunekBankowy rachunekBankowy;
    private Debet debet;

    public RachunekBankowyDebetowy(RachunekBankowy rachunekBankowy, Debet debet) {
        this.rachunekBankowy = rachunekBankowy;
        this.debet = debet;
    }

    @Override
    public double getSrodki() {
        double pozostalyDebet = debet.getMaxKwotaDebetu() - debet.getKwotaDebetu();
        return srodki + pozostalyDebet;
    }

    public double getKwotaDebetu() {
        return debet.getKwotaDebetu();
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
    public void setSrodki(double srodki) {
        rachunekBankowy.setSrodki(srodki);
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
