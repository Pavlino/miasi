package pl.put.poznan.bank;

public class Bank {
	private int id;
	public String nazwa;
	private Raport raport;
	private Historia historia;
	public Bank(String nazwa, int id){
		this.nazwa = nazwa;
		this.id = id;
		this.raport = new Raport();
		this.historia = new Historia();
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
