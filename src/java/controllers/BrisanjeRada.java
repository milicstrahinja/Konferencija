package controllers;

import beans.Rad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrisanjeRada {

    private int idRad;

    public int getIdRad() {
        return idRad;
    }

    public void setIdRad(int idRad) {
        this.idRad = idRad;
    }

    public void sacuvajIdRad(Rad rad) {
        idRad = rad.getIdRad();
    }

    public void brisanjeRada() {
        try {
            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
            Statement stm = con.createStatement();
            stm.executeUpdate("delete from rad where idrad = " + idRad + "");
        } catch (SQLException ex) {
            Logger.getLogger(BrisanjeRada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
