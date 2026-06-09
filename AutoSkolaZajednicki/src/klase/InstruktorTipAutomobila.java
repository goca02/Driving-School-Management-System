/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Goca
 */
public class InstruktorTipAutomobila extends AbstractDomainObject {

    private TipAutomobila tipAutomobila;
    private Instruktor instruktor;
    private Date datumIzdavanja;

    public InstruktorTipAutomobila(TipAutomobila tipAutomobila, Instruktor instruktor, Date datumIzdavanja) {
        this.tipAutomobila = tipAutomobila;
        this.instruktor = instruktor;
        this.datumIzdavanja = datumIzdavanja;
    }

    public InstruktorTipAutomobila() {
    }

    @Override
    public String nazivTabele() {
        return " InstruktorTipAutomobila ";
    }

    @Override
    public String alijas() {
        return " ita ";
    }

    @Override
    public String join() {
        return " JOIN TIPAUTOMOBILA TA ON (TA.TIPAUTOMOBILAID = ITA.TIPAUTOMOBILAID)\n"
                + "JOIN INSTRUKTOR I ON (I.INSTRUKTORID = ITA.INSTRUKTORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Instruktor i = new Instruktor(rs.getInt("InstruktorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Sifra"));

            TipAutomobila ta = new TipAutomobila(rs.getInt("TipAutomobilaID"),
                    rs.getString("Marka"), rs.getString("Model"),
                    rs.getString("Gorivo"), rs.getString("Menjac"));

            InstruktorTipAutomobila ita = new InstruktorTipAutomobila(ta, i,
                    rs.getDate("datumIzdavanja"));

            lista.add(ita);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " instruktorID = " + instruktor.getInstruktorID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public TipAutomobila getTipAutomobila() {
        return tipAutomobila;
    }

    public void setTipAutomobila(TipAutomobila tipAutomobila) {
        this.tipAutomobila = tipAutomobila;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

}
