package controllers;

import beans.Oblast;
import beans.Rad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NapraviKonferenciju {

    private int idKonferencija;
    private String pocetakPrijave;
    private String krajPrijava;
    private int maxBroj;
    private String naziv;
    private String mesto;
    private String vremeOdrzavanja;
    private String oblastiKonferencije;//izabrane oblasti
    private List<Rad> radovi;
    private static int dodeljeniIdKonferencije;

    public static int getDodeljeniIdKonferencije() {
        return dodeljeniIdKonferencije;
    }

    public static void setDodeljeniIdKonferencije(int dodeljeniIdKonferencije) {
        NapraviKonferenciju.dodeljeniIdKonferencije = dodeljeniIdKonferencije;
    }

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

    public String getOblastiKonferencije() {
        return oblastiKonferencije;
    }

    public void setOblastiKonferencije(String oblastiKonferencije) {
        this.oblastiKonferencije = oblastiKonferencije;
    }

    public List<Rad> getRadovi() {
        return radovi;
    }

    public void setRadovi(List<Rad> radovi) {
        this.radovi = radovi;
    }

    public void napraviKonferenciju(int idKorisnik, List<Oblast> oblastiUBazi) {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            List<String> oblastKonferencije = Arrays.asList(oblastiKonferencije.split(","));

            dodeljeniIdKonferencije++;
            String pojedinacniUpit;
            String upit = "";
            ListaOblasti lob = new ListaOblasti();

            for (int i = 0; i < oblastiUBazi.size(); i++) {
                if (oblastiUBazi.get(i).getOblast().equals(oblastKonferencije.get(i).trim())) {
                    pojedinacniUpit = "insert into oblastikonferencije (idkonferencija,idoblast) value (" + dodeljeniIdKonferencije + ", " + oblastiUBazi.get(i).getIdoblast() + ");";
                    upit = upit.concat(pojedinacniUpit + " ");
                } else {
                    lob.novaOblast(oblastKonferencije.get(i).trim());
                    pojedinacniUpit = "insert into oblastkonferencija (idkonferencija, idoblast) value (" + dodeljeniIdKonferencije + ", " + (oblastiUBazi.size() + 1) + ");";
                    upit = upit.concat(pojedinacniUpit + " ");
                }
            }

            PreparedStatement ps = con.prepareStatement("begin; insert into konferencija (idkorisnik, pocetakprijava, krajprijava, naziv, mesto, vremeodrzavanja, maxbroj) values (?, ?, ?, ?, ?, ?, ?); " + upit + "commit;");
            ps.setInt(1, idKorisnik);
            ps.setString(2, pocetakPrijave);
            ps.setString(3, krajPrijava);
            ps.setString(4, naziv);
            ps.setString(5, mesto);
            ps.setString(6, vremeOdrzavanja);
            ps.setInt(7, maxBroj);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistracijaRada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
