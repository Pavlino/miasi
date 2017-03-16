package pl.put.poznan.bank;

import java.util.Date;

public class Lokata extends RachunekBankowy {
	int id;
	Date dataKonca;
	int idRachunku;
    double procent;
	
	public Lokata(int id, String nr){
		super(id, nr);
	}

    @Override
    public void setStanSrodkow(double stanSrodkow) {
        //TODO: podzial na odsetki i wartosc lokaty
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
    public double getProcent() {
        return procent;
    }
    public void setProcent(double procent) {
        this.procent = procent;
    }
}
