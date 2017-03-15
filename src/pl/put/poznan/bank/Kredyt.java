package pl.put.poznan.bank;

public class Kredyt extends ProduktBankowy {
	int id;
	int idRachunku;
	double kwota; //pozosta³a do sp³acenia
	boolean splacony;//
	
	public Kredyt(int id, int rachunek){
		this.id = id;
		this.idRachunku = rachunek;
	}
	
	public Kredyt(int id, int rachunek, double kwota){
		this.id = id;
		this.idRachunku = rachunek;
		this.kwota = kwota;
		splacony = false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdRachunku() {
		return idRachunku;
	}
	public void setIdRachunku(int idRachunku) {
		this.idRachunku = idRachunku;
	}
}
