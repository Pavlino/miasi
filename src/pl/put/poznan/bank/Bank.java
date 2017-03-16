package pl.put.poznan.bank;

import java.util.ArrayList;
import java.util.HashMap;

import pl.put.poznan.utils.InvalidInputException;

public class Bank {
	private int id;
	public String nazwa;
	private Raport raport;
	private Historia historia;
	private ArrayList<RachunekBankowy> listaRachunkow;
	private HashMap<Integer, Klient> listaKlientow;

	public Bank(String nazwa, int id){
		this.nazwa = nazwa;
		this.id = id;
		this.raport = new Raport();
		this.historia = new Historia();
		this.listaRachunkow = new ArrayList<>();
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
				listaRachunkow.add(new RachunekBankowy(idKlienta, 1));  // zmienic rachunek na String?
				System.out.println("Stworzono rachunek o numerze 1");
			} else {
				long numer = listaRachunkow.get(listaRachunkow.size()-1).getNumerRachunku();
				listaRachunkow.add(new RachunekBankowy(idKlienta, numer+1)); //zmienic rachunek na String?
				System.out.println("Stworzono rachunek o numerze " + numer);
			}
		} else {
			throw new InvalidInputException("Klient o id " + idKlienta + " nie istnieje");
		}
	}

	public ArrayList<RachunekBankowy> getListaRachunkow() {
		return listaRachunkow;
	}
	public void setListaProduktow(ArrayList<RachunekBankowy> listaRachunkow) {
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
	public Raport getRaport() {
		return raport;
	}
	public void setRaport(Raport raport) {
		this.raport = raport;
	}
	public Historia getHistoria() {
		return historia;
	}
	public void setHistoria(Historia historia) {
		this.historia = historia;
	}
}
