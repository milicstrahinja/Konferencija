/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.KljucnaRec;
import beans.Konferencija;
import beans.Oblast;
import beans.Rad;
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
public class ListaRadovaAutora {

    private List<Rad> radovi;

    public List<Rad> getRadovi() {
        return radovi;
    }

    public void setRadovi(List<Rad> radovi) {
        this.radovi = radovi;
    }

    public void listaRadovaAutora(int idKorisnik) {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm1 = con.createStatement();
            Statement stm2 = con.createStatement();
            radovi = new ArrayList<>();
            ResultSet rs1 = stm1.executeQuery("select * from rad r, oblast o, konferencija k, status s where r.idkorisnik = " + idKorisnik + " and o.idoblast = r.idoblast and k.idkonferencija = r.idkonferencija and s.idstatus = r.idstatus");
            while (rs1.next()) {
                Rad rad = new Rad();
                rad.setAbstrakt(rs1.getString("abstrakt"));
                ListaKonferencija lko = new ListaKonferencija();
                Konferencija konferencija = lko.listaKonferencija().get(Integer.parseInt(rs1.getString("idkonferencija")));
                rad.setKonferencija(konferencija);
                Oblast oblast = new Oblast();
                oblast.setIdoblast(Integer.parseInt(rs1.getString("idoblast")));
                oblast.setOblast(rs1.getString("oblast"));
                rad.setOblast(oblast);
                String idRad = rs1.getString("idrad");
                rad.setIdRad(Integer.parseInt(idRad));
                rad.setStatus(rs1.getString("statusrada"));
                rad.setIdKorisnik(Integer.parseInt(rs1.getString("idkorisnik")));
                rad.setNaslov(rs1.getString("naslov"));
                rad.setLinkRada(rs1.getString("linkrada"));
                ResultSet rs2 = stm2.executeQuery("select * from vezaajax v, kljucnarec k where v.idrad = " + idRad + " and v.idkljucnarec = k.idkljucnarec");
                List<KljucnaRec> kljucneReci = new ArrayList<>();
                while (rs2.next()) {
                    KljucnaRec kljucnaRec = new KljucnaRec();
                    kljucnaRec.setIdKljucnaRec(Integer.parseInt(rs2.getString("idkljucnarec")));
                    kljucnaRec.setRec(rs2.getString("rec"));
                    kljucneReci.add(kljucnaRec);
                }
                rad.setKljucneReci(kljucneReci);
                rad.setKoautori(rs1.getString("koautori"));
                radovi.add(rad);

            }
            System.out.println(radovi);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRadovaAutora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
