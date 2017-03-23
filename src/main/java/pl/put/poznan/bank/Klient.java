package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

import java.util.HashMap;

public class Klient {
	int id;
	private HashMap<String, ProduktBankowy> listaProduktow;
	Bank bank;

	public Klient(int id, Bank bank){
		this.id = id;
		this.listaProduktow = new HashMap<>();
		this.bank = bank;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void otworzRachunek(String numerRachunku) {
		RachunekBankowy rachunekBankowy = new RachunekBankowy(this, numerRachunku, this.bank);
        this.dodajProduktBankowy(rachunekBankowy);
	}

    public void otworzRachunek(String numerRachunku, Debet debet) {
        RachunekBankowy rachunekBankowy = new RachunekBankowy(this, numerRachunku, debet, this.bank);
        this.dodajProduktBankowy(rachunekBankowy);
    }

    public void dodajDebetDoRachunku(String numerRachunku, Debet debet)  throws InvalidInputException {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) this.listaProduktow.get(numerRachunku);
        if (rachunekBankowy != null) {
            try {
                rachunekBankowy.setDebet(debet);
            } catch (Exception e) {
                throw new InvalidInputException("Rachunek nie istnieje.");
            }
        } else {
            throw new InvalidInputException("Rachunek nie istnieje.");
        }
    }

    public void dodajProduktBankowy(ProduktBankowy produktBankowy) {
        this.listaProduktow.put(produktBankowy.getNumerRachunku(), produktBankowy);
    }
}
