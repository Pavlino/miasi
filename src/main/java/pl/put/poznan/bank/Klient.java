package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotEnoughFundsException;

import java.util.GregorianCalendar;
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

	public void otworzRachunek() throws InvalidInputException {
		RachunekBankowy rachunekBankowy = this.bank.stworzRachunek(this);
        this.listaProduktow.put(rachunekBankowy.getNumerRachunku(), rachunekBankowy);
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

    public void otworzLokate(String numerRachunku, double kwota, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidInputException, NotEnoughFundsException {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) this.listaProduktow.get(numerRachunku);
        if (rachunekBankowy != null) {
            Lokata lokata = this.bank.stworzLokate(this, rachunekBankowy, kwota, new GregorianCalendar(2017, 10, 10), mechanizmOdsetkowy);
            this.listaProduktow.put(rachunekBankowy.getNumerRachunku(), rachunekBankowy);
        } else {
            throw new InvalidInputException("Rachunek nie istnieje.");
        }
    }
    public void zaciagnijKredyt(String numerRachunku, double kwota, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidInputException, NotEnoughFundsException {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) this.listaProduktow.get(numerRachunku);
        if (rachunekBankowy != null) {
            Kredyt kredyt = this.bank.stworzKredyt(this, rachunekBankowy, kwota, mechanizmOdsetkowy);
            this.listaProduktow.put(rachunekBankowy.getNumerRachunku(), rachunekBankowy);
        } else {
            throw new InvalidInputException("Rachunek nie istnieje.");
        }
    }



    public void dodajProduktBankowy(ProduktBankowy produktBankowy) {
        this.listaProduktow.put(produktBankowy.getNumerRachunku(), produktBankowy);
    }
}
