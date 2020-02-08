/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Registracija {

    private String korime;
    private String ime;
    private String prezime;
    private String kontel;
    private String email;
    private String datrodj;
    private String slika;
    private String lozinka;
    private String organizacija;

    public String getKorime() {
        return korime;
    }

    public void setKorime(String korime) {
        this.korime = korime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKontel() {
        return kontel;
    }

    public void setKontel(String kontel) {
        this.kontel = kontel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatrodj() {
        return datrodj;
    }

    public void setDatrodj(String datrodj) {
        this.datrodj = datrodj;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getOrganizacija() {
        return organizacija;
    }

    public void setOrganizacija(String organizacija) {
        this.organizacija = organizacija;
    }

    public String registracija() {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery("select * from organizacija, korisnik;");

            boolean nePostoji = true;
            int id = 0;

            if (rs.getString("korime").equals(korime)) {
                return "korime";
            }

            if (rs.getString("email").equals(email)) {
                return "email";
            }
            while (rs.next()) {
                if (rs.getString("naziv").equals(organizacija)) {
                    id = rs.getInt("idorganizacija");
                    nePostoji = false;
                }
            }

            if (nePostoji == true) {
                rs.last();
                id = (rs.getRow() + 1);
                stm.executeUpdate("insert into organizacija (idorganizacija, naziv) values(" + id + ", '" + organizacija + "')");
            }
            PreparedStatement ps = con.prepareStatement("insert into korisnik (korime, ime, prezime, kontel, email, datrodj, slika, lozinka, idorganizacija) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, korime);
            ps.setString(2, ime);
            ps.setString(3, prezime);
            ps.setString(4, kontel);
            ps.setString(5, email);
            ps.setString(6, datrodj);
            ps.setString(7, slika);
            ps.setString(8, lozinka);
            ps.setInt(9, id);
            ps.executeUpdate();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(Registracija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
