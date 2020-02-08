package controllers;

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
public class ListaOblasti {

    private List<Oblast> oblasti;

    public List<Oblast> getOblasti() {
        return oblasti;
    }

    public void setOblasti(List<Oblast> oblasti) {
        this.oblasti = oblasti;
    }

    public List<Oblast> listaOblasti() {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm = con.createStatement();
            oblasti = new ArrayList<>();
            ResultSet rs = stm.executeQuery("select * from oblasti");
            while (rs.next()) {
                Oblast oblast = new Oblast();
                oblast.setIdoblast(Integer.parseInt(rs.getString("idoblast")));
                oblast.setOblast(rs.getString("oblast"));
                oblasti.add(oblast);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaOblasti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oblasti;
    }

//    public List<Oblast> listaOblastiKonferencije(int idKonferencije) {
//        try {
//            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
//            Statement stm = con.createStatement();
//            oblasti = new ArrayList<>();
//            ResultSet rs = stm.executeQuery("select * from oblastikoferencije k, oblast o where k.idoblast = o.idoblast");
//            while (rs.next()) {
//                Oblast oblast = new Oblast();
//                oblast.setIdoblast(Integer.parseInt(rs.getString("idoblast")));
//                oblast.setOblast(rs.getString("oblast"));
//                oblasti.add(oblast);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ListaOblasti.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return oblasti;
//    }

    public void novaOblast(String oblast) {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm = con.createStatement();
            stm.executeUpdate("insert into oblast (oblast) value ('" + oblast + "')");
        } catch (SQLException ex) {
            Logger.getLogger(ListaOblasti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
