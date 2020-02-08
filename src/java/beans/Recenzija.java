package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Straja
 */
public class Recenzija {

    private Rad rad;
    private int idKorisnik;
    private int ocena;
    private String rokRecenzije;
    private int idRecenzije;

    public Rad getRad() {
        return rad;
    }

    public void setRad(Rad rad) {
        this.rad = rad;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public int getOcena() {
        return ocena;
    }

    public String getRokRecenzije() {
        return rokRecenzije;
    }

    public int getIdRecenzije() {
        return idRecenzije;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public void setRokRecenzije(String rokRecenzije) {
        this.rokRecenzije = rokRecenzije;
    }

    public void setIdRecenzije(int idRecenzije) {
        this.idRecenzije = idRecenzije;
    }

}
