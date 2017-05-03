package pl.put.poznan.bank;

import java.util.HashMap;

public class RaportSumaOdsetek implements IRaport {

    private double sumaOdsetek;
    private Bank bank;

    public RaportSumaOdsetek(Bank bank) {
        this.bank = bank;
    }

    public void odwiedz(RachunekBankowy rachunekBankowy) {}

    public void odwiedz(RachunekBankowyDebetowy rachunekBankowyDebetowy) {}

    public void odwiedz(Lokata lokata) {
        sumaOdsetek -= lokata.getOdsetki();
    }

    public void odwiedz(Kredyt kredyt) {
        sumaOdsetek += kredyt.getOdsetki();
    }

    public Double generuj() {
        HashMap<String, IProduktBankowy> listaProduktowBankowych = bank.getListaRachunkow();
        for (String numerRachunku : listaProduktowBankowych.keySet()) {
            listaProduktowBankowych.get(numerRachunku).przyjmijWizytatora(this);
        }
        return sumaOdsetek;

    }
}
