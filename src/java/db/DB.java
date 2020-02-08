/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Korisnik
 */
public class DB {

    public static String user = "root";
    public static String password = "";
    public static String protocol = "jdbc:mysql:";
    public static String ip = "localhost";
    public static String port = "3306";

    public static String dbName = "konferencijav3";
    public static String dbDriver = "com.mysql.jdbc.Driver";

    public static String connectionString = protocol + "//" + ip + ":" + port + "/" + dbName;

    static {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }
}
