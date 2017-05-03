package pl.put.poznan.bank;

import java.util.ArrayList;
import java.util.HashMap;

public class KIR {
		
	public ArrayList<PrzelewMiedzybankowyOdrzucony> rozliczPrzelewy(ArrayList<IOperacjaBankowa> przelewyMiedzybankowe)
	{
		HashMap<Bank, ArrayList<PrzelewMiedzybankowy>> przelewy = new HashMap<>();
		for (int i=0; i < przelewyMiedzybankowe.size(); i++)
		{
			PrzelewMiedzybankowy aktualnyPrzelewMiedzybankowy = (PrzelewMiedzybankowy) przelewyMiedzybankowe.get(i);
			if (!przelewy.containsKey(aktualnyPrzelewMiedzybankowy.getBankDocelowy())) {
                przelewy.put(aktualnyPrzelewMiedzybankowy.getBankDocelowy(), new ArrayList<>());
            }
			ArrayList<PrzelewMiedzybankowy> przelewyDoBanku = przelewy.get(aktualnyPrzelewMiedzybankowy.getBankDocelowy());
			przelewyDoBanku.add(aktualnyPrzelewMiedzybankowy);
		}
        ArrayList<PrzelewMiedzybankowyOdrzucony> przelewyOdrzucone = new ArrayList<>();
        for (Bank bankDocelowy : przelewy.keySet()) {
            ArrayList<PrzelewMiedzybankowyOdrzucony> odrzucone =  bankDocelowy.odbierzPaczkeOdKIR(przelewy.get(bankDocelowy));
            for (PrzelewMiedzybankowyOdrzucony odrzucony : odrzucone) {
                przelewyOdrzucone.add(odrzucony);
            }
        }
        return przelewyOdrzucone;
	}

}
