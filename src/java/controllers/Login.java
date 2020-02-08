package controllers;

import beans.Korisnik;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Login {

    private String korisnickoIme;
    private String lozinka;

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    private Korisnik prijavljeniKorisnik;

    public Korisnik getPrijavljeniKorisnik() {
        return prijavljeniKorisnik;
    }

    public void setPrijavljeniKorisnik(Korisnik prijavljeniKorisnik) {
        this.prijavljeniKorisnik = prijavljeniKorisnik;
    }

    public String login() {
        try {
            Connection conn = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from korisnik");
            while (rs.next()) {
                if (rs.getString("korime").equals(korisnickoIme) && rs.getString("lozinka").equals(lozinka)) {
                    prijavljeniKorisnik = new Korisnik();
                    prijavljeniKorisnik.setIme(rs.getString("ime"));
                    DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
                    try {
                        prijavljeniKorisnik.setDatumRodjenja(format.parse(rs.getString("datrodj")));
                    } catch (ParseException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    prijavljeniKorisnik.setEmail(rs.getString("email"));
                    prijavljeniKorisnik.setIdKorisnik(Integer.parseInt(rs.getString("idkorisnik")));
                    prijavljeniKorisnik.setKorisnickoIme(korisnickoIme);
                    prijavljeniKorisnik.setLozinka(lozinka);
                    prijavljeniKorisnik.setPrezime(rs.getString("prezime"));
                    prijavljeniKorisnik.setSlika(rs.getString("slika"));
                    prijavljeniKorisnik.setTelefon(rs.getString("kontel"));
                    prijavljeniKorisnik.setIdOrganizacija(Integer.parseInt(rs.getString("idorganizacija")));
                    return "index";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
