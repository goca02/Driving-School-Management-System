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
public class KategorijaVozackeDozvole extends AbstractDomainObject {

    private int kategorijaVozackeDozvoleID;
    private String naziv;
    private String tipVozila;

    @Override
    public String toString() {
        return naziv + " (Tip vozila: " + tipVozila + ")";
    }

    public KategorijaVozackeDozvole(int kategorijaVozackeDozvoleID, String naziv, String tipVozila) {
        this.kategorijaVozackeDozvoleID = kategorijaVozackeDozvoleID;
        this.naziv = naziv;
        this.tipVozila = tipVozila;
    }

    public KategorijaVozackeDozvole() {
    }

    @Override
    public String nazivTabele() {
        return " KategorijaVozackeDozvole ";
    }

    @Override
    public String alijas() {
        return " kvd ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            KategorijaVozackeDozvole kvd = new KategorijaVozackeDozvole(rs.getInt("KategorijaVozackeDozvoleID"),
                    rs.getString("Naziv"), rs.getString("TipVozila"));

            lista.add(kvd);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv, TipVozila) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + tipVozila + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', tipVozila = '" + tipVozila + "' ";
    }

    @Override
    public String uslov() {
        return " kategorijaVozackeDozvoleID = " + kategorijaVozackeDozvoleID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getKategorijaVozackeDozvoleID() {
        return kategorijaVozackeDozvoleID;
    }

    public void setKategorijaVozackeDozvoleID(int kategorijaVozackeDozvoleID) {
        this.kategorijaVozackeDozvoleID = kategorijaVozackeDozvoleID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipVozila() {
        return tipVozila;
    }

    public void setTipVozila(String tipVozila) {
        this.tipVozila = tipVozila;
    }

}
