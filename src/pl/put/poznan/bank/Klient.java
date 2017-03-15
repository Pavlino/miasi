package pl.put.poznan.bank;

import java.util.ArrayList;

public class Klient {
	int id;
	private ArrayList<RachunekBankowy> listaRachunkow;

	public Klient(int id){
		this.id=id;
		this.listaRachunkow = new ArrayList<RachunekBankowy>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
