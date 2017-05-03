package pl.put.poznan.bank;

public interface IRaport {
    void odwiedz(RachunekBankowy rachunekBankowy);
    void odwiedz(RachunekBankowyDebetowy rachunekBankowyDebetowy);
    void odwiedz(Lokata lokata);
    void odwiedz(Kredyt kredyt);
    Object generuj();
}
