package pl.put.poznan.bank;

public class Debet {

    private double maxKwotaDebetu;
    private double kwotaDebetu;

    public Debet(double maxKwotaDebetu) {
        this.maxKwotaDebetu = maxKwotaDebetu;
        this.kwotaDebetu = 0;
    }

    public Debet(double maxKwotaDebetu, double kwotaDebetu) {
        this.maxKwotaDebetu = maxKwotaDebetu;
        this.kwotaDebetu = kwotaDebetu;
    }

    public double getMaxKwotaDebetu() {
        return this.maxKwotaDebetu;
    }

    public void setMaxKwotaDebetu(double maxKwotaDebetu) {
        this.maxKwotaDebetu = maxKwotaDebetu;
    }

    public double getKwotaDebetu() {
        return this.kwotaDebetu;
    }

    public void setKwotaDebetu(double kwotaDebetu) {
        this.kwotaDebetu = kwotaDebetu;
    }

}
