package pl.put.poznan.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import pl.put.poznan.utils.InvalidInputException;

public class Bank {
	private int id;
	public String nazwa;
	private Historia historia;
	private HashMap<Long, ProduktBankowy> listaRachunkow;
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
			listaKlientow.put(1, new Klient(1));
			System.out.println("Stworzono klienta o id 1");
		}
		else{
			int id = listaKlientow.get(listaKlientow.size()-1).getId();
			listaKlientow.put(id+1, new Klient(id+1));
			System.out.println("Stworzono klienta o id " + id);
		}
	}
	
	public void stworzRachunek(int idKlienta) throws InvalidInputException {
		if (listaKlientow.containsKey(idKlienta)) {
			if (listaRachunkow.size()==0){
                long numer = 1;
				listaRachunkow.put(numer, new RachunekBankowy(idKlienta, numer, this));  // zmienic rachunek na String?
				System.out.println("Stworzono rachunek o numerze 1");
			} else {
                long numer = Collections.max(listaRachunkow.keySet());
				listaRachunkow.put(numer+1, new RachunekBankowy(idKlienta, numer+1, this)); //zmienic rachunek na String?
				System.out.println("Stworzono rachunek o numerze " + numer);
			}
		} else {
			throw new InvalidInputException("Klient o id " + idKlienta + " nie istnieje");
		}
	}

    public Object stworzRaport(IRaport raport, HashMap<Long, ProduktBankowy> listaProduktow) throws InvalidInputException {
        return raport.generujRaport(listaProduktow);
    }

	public HashMap<Long, ProduktBankowy> getListaRachunkow() {
		return this.listaRachunkow;
	}
	public void setListaProduktow(HashMap<Long, ProduktBankowy> listaRachunkow) {
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
