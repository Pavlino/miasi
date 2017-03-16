package pl.put.poznan.bank;

import java.util.ArrayList;

public class Klient {
	int id;
	private ArrayList<ProduktBankowy> listaProduktow;

	public Klient(int id){
		this.id=id;
		this.listaProduktow = new ArrayList<ProduktBankowy>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public void dodajProduktBankowy(ProduktBankowy produktBankowy) {
        this.listaProduktow.add(produktBankowy);
    }
}
