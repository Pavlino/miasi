package pl.put.poznan.bank;

import java.util.Date;

public class Lokata extends RachunekBankowy {
	int id;
	Date dataKonca;
	int idRachunku;
	
	public Lokata(int id, String nr){
		super(id, nr);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataKonca() {
		return dataKonca;
	}
	public void setDataKonca(Date dataKonca) {
		this.dataKonca = dataKonca;
	}
	public int getIdRachunku() {
		return idRachunku;
	}
	public void setIdRachunku(int idRachunku) {
		this.idRachunku = idRachunku;
	}
}
