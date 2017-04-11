package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidBankOperationException;
import pl.put.poznan.utils.TypyOperacjiBankowych;

public class MechanizmOdsetkowyProgresywny implements IMechanizmOdsetkowy {
	private double procent;
	
	public MechanizmOdsetkowyProgresywny() {
		procent=0;
	}
	
	public void naliczOdsetki(ProduktBankowy konto) throws InvalidBankOperationException {
		if (konto != null) {
			if(konto.getSrodki()>=0 && konto.getSrodki()<=10000){
				procent = 0.01;
			}
			else if(konto.getSrodki()<=20000){
				procent = 0.011;
			}
			else{
				procent = 0.012;
			}
            double odsetki = konto.getSrodki() * this.procent;
			Wplata wplata = new Wplata(konto, odsetki, "Wplata odsetek", TypyOperacjiBankowych.ODSETKI);
			konto.wykonajOperacje(wplata);
        } else {
            throw new InvalidBankOperationException("Podane konto nie istnieje.");
        }
	}
	
	 public double getProcent() {
	        return this.procent;
	    }

	    public void setProcent(double procent) {
	        this.procent = procent;
	    }

}
