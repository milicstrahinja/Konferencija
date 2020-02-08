package beans;

import java.util.List;

public class Rad {

    private int idRad;
    private String naslov;
    private Oblast oblast; //iz klase oblasti
    private String abstrakt;
    private int idKorisnik;
    private String status;
    private Konferencija Konferencija; //iz konferencije
    private List<KljucnaRec> kljucneReci;
    private String linkRada;
    private String koautori;
    private String rokZaIspravku;

    public String getRokZaIspravku() {
        return rokZaIspravku;
    }

    public void setRokZaIspravku(String rokZaIspravku) {
        this.rokZaIspravku = rokZaIspravku;
    }

    public String getKoautori() {
        return koautori;
    }

    public void setKoautori(String koautori) {
        this.koautori = koautori;
    }

    public String getLinkRada() {
        return linkRada;
    }

    public void setLinkRada(String linkRada) {
        this.linkRada = linkRada;
    }

    public List<KljucnaRec> getKljucneReci() {
        return kljucneReci;
    }

    public void setKljucneReci(List<KljucnaRec> kljucneReci) {
        this.kljucneReci = kljucneReci;
    }

    public int getIdRad() {
        return idRad;
    }

    public void setIdRad(int idRad) {
        this.idRad = idRad;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getAbstrakt() {
        return abstrakt;
    }

    public void setAbstrakt(String abstrakt) {
        this.abstrakt = abstrakt;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Oblast getOblast() {
        return oblast;
    }

    public void setOblast(Oblast oblast) {
        this.oblast = oblast;
    }

    public Konferencija getKonferencija() {
        return Konferencija;
    }

    public void setKonferencija(Konferencija Konferencija) {
        this.Konferencija = Konferencija;
    }

}
