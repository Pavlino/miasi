package pl.put.poznan.bank;

import java.util.HashMap;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.NotDebetException;

public class RaportDebetowy implements IRaport {
    public HashMap<String, ProduktBankowy> getRaportDebetowy() {
        return raportDebetowy;
    }

    public void setRaportDebetowy(HashMap<String, ProduktBankowy> raportDebetowy) {
        this.raportDebetowy = raportDebetowy;
    }

    private HashMap<String, ProduktBankowy> raportDebetowy;

    public RaportDebetowy() {
        this.raportDebetowy = new HashMap<String, ProduktBankowy>();
    }

    public HashMap<String, ProduktBankowy> generujRaport(HashMap<Long, ProduktBankowy> listaProduktow) throws NotDebetException {
        for (ProduktBankowy produktBankowy : listaProduktow.values()) {
            try {
                dodajProdukt((RachunekBankowyDebetowy) produktBankowy);
            } catch (InvalidBankOperationException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return this.raportDebetowy;
    }

    public void dodajProdukt(RachunekBankowyDebetowy produkt) throws InvalidBankOperationException, NotDebetException {
        if (produkt != null) {
            //Double stanSrodkow = produkt.getDebet().getKwotaDebetu();
            if (produkt.getKwotaDebetu() > 0) {
                raportDebetowy.put(produkt.getNumerRachunku(), produkt);
                System.out.println("Dodano do raportu produkt z debetem");
            } else {
                throw new NotDebetException("Na tym produkcie nie ma debetu");
            }
        } else {
            throw new InvalidBankOperationException("Produkt nie istnieje");
        }
    }

    public void usunProdukt(String rachunek) {
        raportDebetowy.remove(rachunek);
    }

}
