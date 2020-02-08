package beans;

public class Recenzent extends Korisnik {

    private int idRecenzent;
    private int idKonferencija;
    private String[] kompetentnost;

    public String[] getKompetentnost() {
        return kompetentnost;
    }

    public void setKompetentnost(String[] kompetentnost) {
        this.kompetentnost = kompetentnost;
    }

    public int getIdRecenzent() {
        return idRecenzent;
    }

    public void setIdRecenzent(int idRecenzent) {
        this.idRecenzent = idRecenzent;
    }

    public int getIdKonferencija() {
        return idKonferencija;
    }

    public void setIdKonferencija(int idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

}
