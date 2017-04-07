package pl.put.poznan.bank;

import java.util.*;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotDebetException;
import pl.put.poznan.utils.NotEnoughFundsException;

public class Bank {
	private int id;
	public String nazwa;
	private Historia historia;
	private HashMap<String, ProduktBankowy> listaRachunkow;
	private HashMap<Integer, Klient> listaKlientow;

	public Bank(String nazwa, int id){
		this.nazwa = nazwa;
		this.id = id;
		historia = new Historia();
		listaRachunkow = new HashMap<>();
		listaKlientow = new HashMap<>();
	}
	
	public Klient stworzKlienta(){
        int id = 1;
		if(listaKlientow.size() > 0){
            id = listaKlientow.size() + 1;
        }
        listaKlientow.put(id, new Klient(id, this));
        System.out.println("Stworzono klienta o id " + id);
        return new Klient(id, this);
	}
	
	public RachunekBankowy stworzRachunek(Klient klient) throws InvalidInputException {
		if (listaKlientow.containsKey(klient.getId())) {
            int numer = 1;
			if (listaRachunkow.size() > 0){
                numer = new Integer(Collections.max(listaRachunkow.keySet())) + 1;
            }
            RachunekBankowy rachunekBankowy = new RachunekBankowy(klient, Integer.toString(numer), this);
            listaRachunkow.put(Integer.toString(numer), rachunekBankowy);  // zmienic rachunek na String?
            System.out.println("Stworzono rachunek o numerze " + Integer.toString(numer));
            return rachunekBankowy;
		} else {
			throw new InvalidInputException("Klient o id " + klient.getId() + " nie istnieje");
		}
	}

    public Lokata stworzLokate(Klient klient, RachunekBankowy rachunekBankowy, double kwota, Calendar data, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidInputException, NotEnoughFundsException {
        if (listaKlientow.containsKey(klient.getId())) {
            int numer = 1;
            if (listaRachunkow.size() > 0){
                numer = new Integer(Collections.max(listaRachunkow.keySet())) + 1;
            }
            Lokata lokata = new Lokata(klient, Integer.toString(numer), rachunekBankowy, mechanizmOdsetkowy);
            listaRachunkow.put(Integer.toString(numer), lokata);  // zmienic rachunek na String?
            System.out.println("Stworzono lokate o numerze " + Integer.toString(numer));
            lokata.otworzLokate(kwota, data);
            return lokata;
        } else {
            throw new InvalidInputException("Klient o id " + klient.getId() + " nie istnieje");
        }
    }

    public Kredyt stworzKredyt(Klient klient, RachunekBankowy rachunekBankowy, double kwota, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidInputException, NotEnoughFundsException {
        if (listaKlientow.containsKey(klient.getId())) {
            int numer = 1;
            if (listaRachunkow.size() > 0){
                numer = new Integer(Collections.max(listaRachunkow.keySet())) + 1;
            }
            Kredyt kredyt = new Kredyt(klient, Integer.toString(numer), rachunekBankowy, mechanizmOdsetkowy);
            listaRachunkow.put(Integer.toString(numer), kredyt);  // zmienic rachunek na String?
            System.out.println("Stworzono lokate o numerze " + Integer.toString(numer));
            kredyt.zaciagnijKredyt(kwota);
            return kredyt;
        } else {
            throw new InvalidInputException("Klient o id " + klient.getId() + " nie istnieje");
        }
    }



    public Object stworzRaport(IRaport raport, HashMap<Long, ProduktBankowy> listaProduktow) throws InvalidInputException, NotDebetException {
        return raport.generujRaport(listaProduktow);
    }

    public void dodajOperacjeDoHistorii(IOperacjaBankowa operacjaBankowa) throws InvalidInputException {
        historia.dodajOperacje(operacjaBankowa);
    }

	public HashMap<String, ProduktBankowy> getListaRachunkow() {
		return listaRachunkow;
	}
	public void setListaProduktow(HashMap<String, ProduktBankowy> listaRachunkow) {
		this.listaRachunkow = listaRachunkow;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public Historia getHistoria() {
		return historia;
	}
	public void setHistoria(Historia historia) {
		this.historia = historia;
	}
}
