package pl.put.poznan.utils;

public interface TypyOperacjiBankowych {
    int WPLATA = 0;
    int WYPLATA = 1;
    int PRZELEW = 2;
    int ODSETKI = 3;
    int ZERWANIE_LOKATY = 4;
    int ROZWIAZANIE_LOKATY = 5;
    int OTWORZENIE_LOKATY = 6;
    int ZACIAGNIECIE_KREDYTU = 7;
    int SPLATA_KREDYTU = 8;
    int PRZELEW_MIEDZYBANKOWY = 9;
    int PRZELEW_MIEDZYBANKOWY_ODRZUCONY = 10;
}
