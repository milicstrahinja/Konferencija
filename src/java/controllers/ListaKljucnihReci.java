package controllers;

import beans.KljucnaRec;
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
public class ListaKljucnihReci {

    private List<KljucnaRec> kljucneReci;

    public List<KljucnaRec> getKljucneReci() {
        return kljucneReci;
    }

    public void setKljucneReci(List<KljucnaRec> kljucneReci) {
        this.kljucneReci = kljucneReci;
    }

    public List<KljucnaRec> listaKljucnihReci() {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm = con.createStatement();
            kljucneReci = new ArrayList<>();
            ResultSet rs = stm.executeQuery("select * from kljucnarec");
            while (rs.next()) {
                KljucnaRec kljucnaRec = new KljucnaRec();
                kljucnaRec.setIdKljucnaRec(Integer.parseInt(rs.getString("idkljucnarec")));
                kljucnaRec.setRec(rs.getString("rec"));
                kljucneReci.add(kljucnaRec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaKljucnihReci.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kljucneReci;
    }

    public void novaKljucnaRec(String rec) {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm = con.createStatement();
            stm.executeUpdate("insert into kljucnarec (rec) value ('" + rec + "')");
        } catch (SQLException ex) {
            Logger.getLogger(ListaKljucnihReci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
