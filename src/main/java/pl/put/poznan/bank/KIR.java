package pl.put.poznan.bank;

import java.util.ArrayList;
import java.util.HashMap;

public class KIR {
		
	public void RozliczPrzelewy(ArrayList<PrzelewMiedzybankowy> wejscie)
	{
		HashMap<Bank, ArrayList<PrzelewMiedzybankowy>> przelewy = new HashMap<Bank, ArrayList<PrzelewMiedzybankowy>>();
		for (int i=0; i < wejscie.size(); i++)
		{
			PrzelewMiedzybankowy aktualny = wejscie.get(i);
			if (!przelewy.containsKey(aktualny.getBankDocelowy()))
				przelewy.put(aktualny.getBankDocelowy(), new ArrayList<PrzelewMiedzybankowy>());
			
			ArrayList<PrzelewMiedzybankowy> przelewyDoBanku = przelewy.get(aktualny.getBankDocelowy());
			przelewyDoBanku.add(aktualny);
		}
		for (int i=0; i < przelewy.keySet().size(); i++)
		{
			ArrayList<PrzelewMiedzybankowy> przelewyDoBanku = przelewy.get(przelewy.keySet().toArray()[i]);
			// wyslij przelewy
		}		
	}

}
