package pl.put.poznan.bank;


import java.util.HashMap;

public class RaportDebetowy implements IRaport {

    private int liczbaKontDebetowych = 0;
    private Bank bank;

    public RaportDebetowy(Bank bank) {
        this.bank = bank;
    }

    public void odwiedz(RachunekBankowy rachunekBankowy) {}
    public void odwiedz(RachunekBankowyDebetowy rachunekBankowyDebetowy) {
        liczbaKontDebetowych++;
    }
    public void odwiedz(Lokata lokata) {}
    public void odwiedz(Kredyt kredyt) {}

    public Integer generuj() {
        HashMap<String, IProduktBankowy> listaProduktowBankowych = bank.getListaRachunkow();
        for (String numerRachunku : listaProduktowBankowych.keySet()) {
            listaProduktowBankowych.get(numerRachunku).przyjmijWizytatora(this);
        }
        return liczbaKontDebetowych;
    }

}
