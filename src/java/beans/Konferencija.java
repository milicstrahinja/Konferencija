package beans;

import java.util.List;

public class Konferencija {

    private int idKonferencija;
    private String pocetakPrijave;
    private String krajPrijava;
    private int maxBroj;
    private String naziv;
    private String mesto;
    private String vremeOdrzavanja;
    private List<Oblast> oblasti;
    private List<Rad> radovi;
    private int idKorisnik;

    public int getIdKonferencija() {
        return idKonferencija;
    }

    public void setIdKonferencija(int idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

    public String getPocetakPrijave() {
        return pocetakPrijave;
    }

    public void setPocetakPrijave(String pocetakPrijave) {
        this.pocetakPrijave = pocetakPrijave;
    }

    public String getKrajPrijava() {
        return krajPrijava;
    }

    public void setKrajPrijava(String krajPrijava) {
        this.krajPrijava = krajPrijava;
    }

    public int getMaxBroj() {
        return maxBroj;
    }

    public void setMaxBroj(int maxBroj) {
        this.maxBroj = maxBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getVremeOdrzavanja() {
        return vremeOdrzavanja;
    }

    public void setVremeOdrzavanja(String vremeOdrzavanja) {
        this.vremeOdrzavanja = vremeOdrzavanja;
    }

    public List<Oblast> getOblasti() {
        return oblasti;
    }

    public void setOblasti(List<Oblast> oblasti) {
        this.oblasti = oblasti;
    }

    public List<Rad> getRadovi() {
        return radovi;
    }

    public void setRadovi(List<Rad> radovi) {
        this.radovi = radovi;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

}
