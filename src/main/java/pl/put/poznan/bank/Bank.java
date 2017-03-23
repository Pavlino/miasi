package pl.put.poznan.bank;

import java.util.Collections;
import java.util.HashMap;

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
		this.historia = new Historia();
		this.listaRachunkow = new HashMap<>();
		this.listaKlientow = new HashMap<>();
	}
	
	public void stworzKlienta(){
		if(listaKlientow.size()==0){
			listaKlientow.put(1, new Klient(1, this));
			System.out.println("Stworzono klienta o id 1");
		}
		else{
			int id = listaKlientow.get(listaKlientow.size()-1).getId();
			listaKlientow.put(id+1, new Klient(id+1, this));
			System.out.println("Stworzono klienta o id " + id);
		}
	}
	
	public RachunekBankowy stworzRachunek(Klient klient) throws InvalidInputException {
		if (listaKlientow.containsKey(klient.getId())) {
			if (listaRachunkow.size()==0){
                String numer = "0001";
				RachunekBankowy rachunekBankowy = new RachunekBankowy(klient, numer, this);
				listaRachunkow.put(numer, rachunekBankowy);  // zmienic rachunek na String?
				System.out.println("Stworzono rachunek o numerze 1");
				return rachunekBankowy;
			} else {
                String numer = Collections.max(listaRachunkow.keySet());
				RachunekBankowy rachunekBankowy = new RachunekBankowy(klient, "0002", this);
				listaRachunkow.put("00001", rachunekBankowy); //zmienic rachunek na String?
				System.out.println("Stworzono rachunek o numerze " + numer);
				return rachunekBankowy;
			}
		} else {
			throw new InvalidInputException("Klient o id " + klient.getId() + " nie istnieje");
		}
	}

    public Lokata stworzLokate(Klient klient, RachunekBankowy rachunekBankowy, double kwota, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidInputException, NotEnoughFundsException {
        if (listaKlientow.containsKey(klient.getId())) {
            if (listaRachunkow.size()==0){
                String numer = "0001";
                Lokata lokata = new Lokata(klient, numer, rachunekBankowy, mechanizmOdsetkowy);
                listaRachunkow.put(numer, lokata);  // zmienic rachunek na String?
                System.out.println("Stworzono lokate o numerze 1");
                lokata.otworzLokate(kwota);
                return lokata;
            } else {
                String numer = "0002";
                Lokata lokata = new Lokata(klient, numer, rachunekBankowy, mechanizmOdsetkowy);
                listaRachunkow.put(numer, lokata);  // zmienic rachunek na String?
                System.out.println("Stworzono lokate o numerze 2");
                lokata.otworzLokate(kwota);
                return lokata;
            }
        } else {
            throw new InvalidInputException("Klient o id " + klient.getId() + " nie istnieje");
        }
    }

    public Kredyt stworzKredyt(Klient klient, RachunekBankowy rachunekBankowy, double kwota, IMechanizmOdsetkowy mechanizmOdsetkowy) throws InvalidInputException, NotEnoughFundsException {
        if (listaKlientow.containsKey(klient.getId())) {
            if (listaRachunkow.size()==0){
                String numer = "0001";
                Kredyt kredyt = new Kredyt(klient, numer, rachunekBankowy, mechanizmOdsetkowy);
                listaRachunkow.put(numer, kredyt);  // zmienic rachunek na String?
                System.out.println("Stworzono lokate o numerze 1");
                kredyt.zaciagnijKredyt(kwota);
                return kredyt;
            } else {
                String numer = "0002";
                Kredyt kredyt = new Kredyt(klient, numer, rachunekBankowy, mechanizmOdsetkowy);
                listaRachunkow.put(numer, kredyt);  // zmienic rachunek na String?
                System.out.println("Stworzono lokate o numerze 2");
                kredyt.zaciagnijKredyt(kwota);
                return kredyt;
            }
        } else {
            throw new InvalidInputException("Klient o id " + klient.getId() + " nie istnieje");
        }
    }



    public Object stworzRaport(IRaport raport, HashMap<Long, ProduktBankowy> listaProduktow) throws InvalidInputException, NotDebetException {
        return raport.generujRaport(listaProduktow);
    }

	public HashMap<String, ProduktBankowy> getListaRachunkow() {
		return this.listaRachunkow;
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
