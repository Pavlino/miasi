package pl.put.poznan.bank;

import java.util.Date;

public class RachunekBankowy extends ProduktBankowy {
	int id;
	String numer;
	Date dataZalozenia;
	int idHistorii;
	
	public RachunekBankowy(int id, String nr){
		this.id = id;
		this.numer = nr;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumer() {
		return numer;
	}
	public void setNumer(String nrRachunku) {
		this.numer = nrRachunku;
	}
	public Date getDataZalozenia() {
		return dataZalozenia;
	}
	public void setDataZalozenia(Date dataZalozenia) {
		this.dataZalozenia = dataZalozenia;
	}
	public int getIdHistorii() {
		return idHistorii;
	}
	public void setIdHistorii(int idHistorii) {
		this.idHistorii = idHistorii;
	}
}
