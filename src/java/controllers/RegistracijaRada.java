package controllers;

import beans.KljucnaRec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegistracijaRada {

    private static int dodeljeniIdRada;
    private static int dodeljeniIdSpisakautora;
    private String naslov;
    private int idOblasti;
    private String abstrakt;
    private int idStatus;
    private int idKonferencija;
    private String linkRada;
    private String koautori;

    public String getKoautori() {
        return koautori;
    }

    public void setKoautori(String koautori) {
        this.koautori = koautori;
    }

    private String kljucneReci;

    public static int getDodeljeniIdRada() {
        return dodeljeniIdRada;
    }

    public static void setDodeljeniIdRada(int dodeljeniIdRada) {
        RegistracijaRada.dodeljeniIdRada = dodeljeniIdRada;
    }

    public static int getDodeljeniIdSpisakautora() {
        return dodeljeniIdSpisakautora;
    }

    public static void setDodeljeniIdSpisakautora(int dodeljeniIdSpisakautora) {
        RegistracijaRada.dodeljeniIdSpisakautora = dodeljeniIdSpisakautora;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public int getIdOblasti() {
        return idOblasti;
    }

    public void setIdOblasti(int idOblasti) {
        this.idOblasti = idOblasti;
    }

    public String getAbstrakt() {
        return abstrakt;
    }

    public void setAbstrakt(String abstrakt) {
        this.abstrakt = abstrakt;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdKonferencija() {
        return idKonferencija;
    }

    public void setIdKonferencija(int idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

    public String getLinkRada() {
        return linkRada;
    }

    public void setLinkRada(String linkRada) {
        this.linkRada = linkRada;
    }

    public String getKljucneReci() {
        return kljucneReci;
    }

    public void setKljucneReci(String kljucneReci) {
        this.kljucneReci = kljucneReci;
    }

    public void registracijaRada(int idKorisnik, String ime, String prezime, List<KljucnaRec> kljucneReciUBazi) {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            List<String> kljucnaRec = Arrays.asList(kljucneReci.split(","));

            dodeljeniIdRada++;
            String pojedinacniUpit;
            String upit = "";
            ListaKljucnihReci lkr = new ListaKljucnihReci();
            for (int i = 0; i < kljucneReciUBazi.size(); i++) {
                for (int j = 0; j < kljucnaRec.size(); j++) {
                    if (kljucneReciUBazi.get(i).getRec().equals(kljucnaRec.get(j).trim())) {
                        pojedinacniUpit = "insert into vezaajax (idrad, idkljucnarec) value (" + dodeljeniIdRada + ", " + kljucneReciUBazi.get(i).getIdKljucnaRec() + ");";
                        upit = upit.concat(pojedinacniUpit + " ");
                    } else {
                        lkr.novaKljucnaRec(kljucnaRec.get(i).trim());
                        pojedinacniUpit = "insert into vezaajax (idrad, idkljucnarec) value (" + dodeljeniIdRada + ", " + (kljucneReciUBazi.size() + 1) + ");";
                        upit = upit.concat(pojedinacniUpit + " ");
                    }
                }

            }

            PreparedStatement ps = con.prepareStatement("begin; insert into rad (naslov, idoblast, abstrakt, korespondent, idstatus, idkonferencija, linkrada, koautori) values (?, ?, ?, ?, ?, ?, ?, ?); " + upit + "commit;");
            ps.setString(1, naslov);
            ps.setInt(2, idOblasti);
            ps.setString(3, abstrakt);
            ps.setInt(4, idKorisnik);
            ps.setInt(5, idStatus);
            ps.setInt(6, idKonferencija);
            ps.setString(7, linkRada);
            ps.setString(8, koautori);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistracijaRada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
