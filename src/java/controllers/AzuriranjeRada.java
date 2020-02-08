package controllers;

import beans.KljucnaRec;
import beans.Konferencija;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AzuriranjeRada {

    private int idRad;
    private String naslov;
    private Oblast oblast; //iz klase oblasti
    private String abstrakt;
    private int korespondent;
    private Konferencija konferencija; //iz konferencije
    private List<KljucnaRec> kljucneReci;
    private String koautori;
    private String linkRada;
    private int idKorisnik;

    public String getKoautori() {
        return koautori;
    }

    public void setKoautori(String koautori) {
        this.koautori = koautori;
    }

    public int getIdRad() {
        return idRad;
    }

    public void setIdRad(int idRad) {
        this.idRad = idRad;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public Oblast getOblast() {
        return oblast;
    }

    public void setOblast(Oblast oblast) {
        this.oblast = oblast;
    }

   

    public String getAbstrakt() {
        return abstrakt;
    }

    public void setAbstrakt(String abstrakt) {
        this.abstrakt = abstrakt;
    }

    public int getKorespondent() {
        return korespondent;
    }

    public void setKorespondent(int korespondent) {
        this.korespondent = korespondent;
    }

    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }

    

    public List<KljucnaRec> getKljucneReci() {
        return kljucneReci;
    }

    public void setKljucneReci(List<KljucnaRec> kljucneReci) {
        this.kljucneReci = kljucneReci;
    }

    public String getLinkRada() {
        return linkRada;
    }

    public void setLinkRada(String linkRada) {
        this.linkRada = linkRada;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public void sacuvajIdRad(Rad rad) {
        idRad = rad.getIdRad();
        naslov = rad.getNaslov();
        oblast.setIdoblast(rad.getOblast().getIdoblast());
        abstrakt = rad.getAbstrakt();
        korespondent = rad.getIdKorisnik();
        konferencija.setIdKonferencija(rad.getKonferencija().getIdKonferencija()); 
        kljucneReci = rad.getKljucneReci();
        koautori = rad.getKoautori();
        linkRada = rad.getLinkRada();
        idKorisnik = rad.getIdKorisnik();
        
    }

    public void azuriranjeRada(String ime, String prezime, List<KljucnaRec> kljucneReciUBazi) {
        
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            
            String pojedinacniUpit;
            String upit = "";
            ListaKljucnihReci lkr = new ListaKljucnihReci();
                for (int i = 0; i < kljucneReciUBazi.size(); i++) {
                if (kljucneReciUBazi.get(i).getRec().equals(kljucnaRec.get(i))) {                    
                    pojedinacniUpit = "insert into vezaajax (idrad, idkljucnarec) value (" + idRad + ", " + kljucneReciUBazi.get(i).getIdKljucnaRec() + ");";
                    upit = upit.concat(pojedinacniUpit + " ");
                } else {
                    lkr.novaKljucnaRec(kljucnaRec.get(i).toString().trim());
                    pojedinacniUpit = "insert into vezaajax (idrad, idkljucnarec) value (" + idRad + ", " + (kljucneReciUBazi.size() + 1) + ");";
                    upit = upit.concat(pojedinacniUpit + " ");
                }
            }

//            if (koautori == null) {
//                upit2 = "insert into autor (ime, prezime) value ('" + ime + "', '" + prezime + "'); insert into spisakautora (idautor, idrad) values (" + idAutor.get(0) + ", " + idRad + "); ";
//            } else {
//                dodeljeniIdAutora++;
//                upit2 = "insert into autor (ime, prezime) value ('" + ime + "', '" + prezime + "'); insert into spisakautora (idautor, idrad) values (" + dodeljeniIdAutora + ", " + dodeljeniIdRada + "); ";
//                for (int i = 0; i < koautori.size(); i++) {
//                    dodeljeniIdAutora++;
//                    pojedinacniUpit = "insert into autor (ime, prezime) value ('" + koautori.get(i).split(" ")[0] + "', '" + koautori.get(i).split(" ")[1] + "'); insert into spisakautora (idautor, idrad) values (" + dodeljeniIdAutora + ", " + dodeljeniIdRada + ");";
//                    upit2 = upit2.concat(pojedinacniUpit + " ");
//                }
//            }
            PreparedStatement ps = con.prepareStatement("begin; insert into rad (naslov, idoblast, abstrakt, korespondent, idstatus, idkonferencija, linkrada) values (?, ?, ?, ?, ?, ?, ?); " + upit + upit2 + "commit;");
            ps.setString(1, naslov);
            ps.setInt(2, idOblasti);
            ps.setString(3, abstrakt);
            ps.setInt(4, idKorisnik);
            ps.setInt(5, idStatus);
            ps.setInt(6, idKonferencija);
            ps.setString(7, linkRada);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AzuriranjeRada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
