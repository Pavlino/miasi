package pl.put.poznan.bank;

import java.util.Collections;
import java.util.HashMap;

import pl.put.poznan.utils.InvalidInputException;
import pl.put.poznan.utils.NotDebetException;

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
	
	public void stworzRachunek(Klient klient) throws InvalidInputException {
		if (listaKlientow.containsKey(klient.getId())) {
			if (listaRachunkow.size()==0){
                String numer = "0001";
				listaRachunkow.put(numer, new RachunekBankowy(klient, numer, this));  // zmienic rachunek na String?
				System.out.println("Stworzono rachunek o numerze 1");
			} else {
                String numer = Collections.max(listaRachunkow.keySet());
				listaRachunkow.put("00001", new RachunekBankowy(klient, "0001", this)); //zmienic rachunek na String?
				System.out.println("Stworzono rachunek o numerze " + numer);
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
