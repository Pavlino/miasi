package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;

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

	public void otworzRachunek() throws InvalidBankOperationException {
		RachunekBankowy rachunekBankowy = bank.stworzRachunek(this);
        listaProduktow.put(rachunekBankowy.getNumerRachunku(), rachunekBankowy);
	}

    public void dodajDebetDoRachunku(String numerRachunku, Debet debet)  throws InvalidBankOperationException {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) listaProduktow.get(numerRachunku);
        if (rachunekBankowy != null) {
            try {
                rachunekBankowy.setDebet(debet);
            } catch (Exception e) {
                throw new InvalidBankOperationException("Rachunek nie istnieje.");
            }
        } else {
            throw new InvalidBankOperationException("Rachunek nie istnieje.");
        }
    }

    public void otworzLokate(String numerRachunku, double kwota, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidBankOperationException {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) listaProduktow.get(numerRachunku);
        if (rachunekBankowy != null) {
            Lokata lokata = bank.stworzLokate(this, rachunekBankowy, kwota, new GregorianCalendar(2017, 10, 10), mechanizmOdsetkowy);
            this.listaProduktow.put(lokata.getNumerRachunku(), lokata);
        } else {
            throw new InvalidBankOperationException("Rachunek nie istnieje.");
        }
    }
    public void zaciagnijKredyt(String numerRachunku, double kwota, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidBankOperationException {
        RachunekBankowy rachunekBankowy = (RachunekBankowy) listaProduktow.get(numerRachunku);
        if (rachunekBankowy != null) {
            Kredyt kredyt = bank.stworzKredyt(this, rachunekBankowy, kwota, mechanizmOdsetkowy);
            listaProduktow.put(kredyt.getNumerRachunku(), kredyt);
        } else {
            throw new InvalidBankOperationException("Rachunek nie istnieje.");
        }
    }

    public void dodajProduktBankowy(ProduktBankowy produktBankowy) {
        listaProduktow.put(produktBankowy.getNumerRachunku(), produktBankowy);
    }

    public HashMap<String, ProduktBankowy> getListaProduktow() {
        return this.listaProduktow;
    }
}
