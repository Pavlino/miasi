package pl.put.poznan.bank;

import pl.put.poznan.utils.InvalidInputException;

public class MechanizmOdsetkowyProgresywny implements IMechanizmOdsetkowy {
	private double procent;
	
	public MechanizmOdsetkowyProgresywny() {
		procent=0;
	}
	
	public double naliczOdsetki(ProduktBankowy konto) throws InvalidInputException {
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
            return konto.getSrodki() * this.procent;
        } else {
            throw new InvalidInputException("Podane konto nie istnieje.");
        }
	}
	
	 public double getProcent() {
	        return this.procent;
	    }

	    public void setProcent(double procent) {
	        this.procent = procent;
	    }

}
