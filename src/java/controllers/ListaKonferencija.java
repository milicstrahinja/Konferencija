package controllers;

import beans.Konferencija;
import beans.Oblast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ListaKonferencija {

    private List<Konferencija> konferencije;

    public List<Konferencija> getKonferencije() {
        return konferencije;
    }

    public void setKonferencije(List<Konferencija> konferencije) {
        this.konferencije = konferencije;
    }

    public List<Konferencija> listaKonferencija() {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm1 = con.createStatement();
            Statement stm2 = con.createStatement();
            konferencije = new ArrayList<>();
            ResultSet rs1 = stm1.executeQuery("select * from konferencija");
            while (rs1.next()) {
                Konferencija konferencija = new Konferencija();
                String idKonferencija = rs1.getString("idkonferencija");
                konferencija.setIdKonferencija(Integer.parseInt(idKonferencija));
                konferencija.setIdKorisnik(Integer.parseInt(rs1.getString("idkorisnik")));
                konferencija.setKrajPrijava(rs1.getString("krajprijava"));
                konferencija.setMaxBroj(Integer.parseInt(rs1.getString("maxbroj")));
                konferencija.setMesto(rs1.getString("mesto"));
                konferencija.setNaziv(rs1.getString("naziv"));
                ResultSet rs2 = stm2.executeQuery("select * from oblast o, oblastikonferecnije k where k.idkonferencija = " + idKonferencija + " and k.idoblast = o.idoblast");
                List<Oblast> oblasti = new ArrayList<>();
                while (rs2.next()) {
                    Oblast oblast = new Oblast();
                    oblast.setIdoblast(Integer.parseInt(rs2.getString("idoblast")));
                    oblast.setOblast(rs2.getString("oblast"));
                    oblasti.add(oblast);
                }
                konferencija.setOblasti(oblasti);
                konferencija.setPocetakPrijave(rs1.getString("pocetakprijava"));
                konferencija.setVremeOdrzavanja(rs1.getString("vremeodrzavanja"));
                konferencije.add(konferencija);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaOblasti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return konferencije;
    }

}
