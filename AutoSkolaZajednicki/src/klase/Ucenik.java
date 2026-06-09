/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Goca
 */
public class Ucenik extends AbstractDomainObject {

    private int ucenikID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private KategorijaVozackeDozvole kategorijaVozackeDozvole;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Email: " + email + ")";
    }

    public Ucenik(int ucenikID, String ime, String prezime, String email, String telefon, KategorijaVozackeDozvole kategorijaVozackeDozvole) {
        this.ucenikID = ucenikID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.kategorijaVozackeDozvole = kategorijaVozackeDozvole;
    }

    public Ucenik() {
    }

    @Override
    public String nazivTabele() {
        return " Ucenik ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return " JOIN kategorijaVozackeDozvole KVD ON (KVD.kategorijaVozackeDozvoleID = U.kategorijaVozackeDozvoleID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            KategorijaVozackeDozvole kvd = new KategorijaVozackeDozvole(rs.getInt("KategorijaVozackeDozvoleID"),
                    rs.getString("Naziv"), rs.getString("TipVozila"));

            Ucenik u = new Ucenik(rs.getInt("ucenikID"), rs.getString("ime"),
                    rs.getString("prezime"), rs.getString("email"),
                    rs.getString("telefon"), kvd);

            lista.add(u);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, email, telefon, KategorijaVozackeDozvoleID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + telefon + "', " + kategorijaVozackeDozvole.getKategorijaVozackeDozvoleID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', telefon = '" + telefon + "', "
                + "kategorijaVozackeDozvoleID = "
                + kategorijaVozackeDozvole.getKategorijaVozackeDozvoleID() + " ";
    }

    @Override
    public String uslov() {
        return " ucenikID = " + ucenikID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getUcenikID() {
        return ucenikID;
    }

    public void setUcenikID(int ucenikID) {
        this.ucenikID = ucenikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public KategorijaVozackeDozvole getKategorijaVozackeDozvole() {
        return kategorijaVozackeDozvole;
    }

    public void setKategorijaVozackeDozvole(KategorijaVozackeDozvole kategorijaVozackeDozvole) {
        this.kategorijaVozackeDozvole = kategorijaVozackeDozvole;
    }

}
