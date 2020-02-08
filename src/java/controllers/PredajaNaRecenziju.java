package controllers;

import beans.Rad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PredajaNaRecenziju {
    
    int idRad;
    
    public void sacuvajIdRad(Rad rad) {
        idRad = rad.getIdRad();
    }

    public void predajaNaRecenziju() {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE rad SET idstatus = 2 WHERE idrad =" + idRad + "");
        } catch (SQLException ex) {
            Logger.getLogger(PredajaNaRecenziju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
