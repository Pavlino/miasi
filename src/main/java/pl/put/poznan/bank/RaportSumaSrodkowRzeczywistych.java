package pl.put.poznan.bank;

import java.util.HashMap;

public class RaportSumaSrodkowRzeczywistych implements IRaport {

    private double sumaSrodkow;
    private Bank bank;

    public RaportSumaSrodkowRzeczywistych(Bank bank) {
        this.bank = bank;
        sumaSrodkow = 0;
    }

    public void odwiedz(RachunekBankowy rachunekBankowy) {
        sumaSrodkow += rachunekBankowy.getSrodki();
    }

    public void odwiedz(RachunekBankowyDebetowy rachunekBankowyDebetowy) {
        double pozostalyDebet = rachunekBankowyDebetowy.getMaxKwotaDebetu() - rachunekBankowyDebetowy.getKwotaDebetu();
        sumaSrodkow += rachunekBankowyDebetowy.getSrodki() - pozostalyDebet;

    }

    public void odwiedz(Lokata lokata) {
        sumaSrodkow += lokata.getSrodki();
    }

    public void odwiedz(Kredyt kredyt) {
        sumaSrodkow -= kredyt.getSrodki();
    }

    public Double generuj() {
        HashMap<String, IProduktBankowy> listaProduktowBankowych = bank.getListaRachunkow();
        for (String numerRachunku : listaProduktowBankowych.keySet()) {
            listaProduktowBankowych.get(numerRachunku).przyjmijWizytatora(this);
        }
        return sumaSrodkow;

    }

}
