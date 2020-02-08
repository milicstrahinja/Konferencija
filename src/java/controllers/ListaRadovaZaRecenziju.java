package controllers;

import beans.Oblast;
import beans.Rad;
import beans.Recenzija;
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
public class ListaRadovaZaRecenziju {

    private List<Recenzija> radovi;

    public List<Recenzija> getRadovi() {
        return radovi;
    }

    public void setRadovi(List<Recenzija> radovi) {
        this.radovi = radovi;
    }

    public void listaRadovaZaRecenziju(int idKorisnik) {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm1 = con.createStatement();
            ResultSet rs = stm1.executeQuery("select * from recenzija r, rad a where r.idkorisnik = " + idKorisnik + " and r.idrad = a.idrad");
            radovi = new ArrayList<>();
            while (rs.next()) {
                Recenzija recenzija = new Recenzija();
                Rad rad = new Rad();
                rad.setAbstrakt(rs.getString("abstrakt"));
                Oblast oblast = new Oblast();
                oblast.setIdoblast(Integer.parseInt(rs.getString("idoblast")));
                oblast.setOblast(rs.getString("oblast"));
                rad.setOblast(oblast);
                rad.setIdRad(Integer.parseInt(rs.getString("idrad")));
                rad.setNaslov(rs.getString("naslov"));
                rad.setLinkRada(rs.getString("linkrada"));
                recenzija.setRad(rad);
                recenzija.setOcena(Integer.parseInt(rs.getString("ocena")));
//                if (

            }
            System.out.println(radovi);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRadovaAutora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
