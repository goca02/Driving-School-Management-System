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
public class TipAutomobila extends AbstractDomainObject {

    private int tipAutomobilaID;
    private String marka;
    private String model;
    private String gorivo;
    private String menjac;

    public TipAutomobila(int tipAutomobilaID, String marka, String model, String gorivo, String menjac) {
        this.tipAutomobilaID = tipAutomobilaID;
        this.marka = marka;
        this.model = model;
        this.gorivo = gorivo;
        this.menjac = menjac;
    }

    public TipAutomobila() {
    }

    @Override
    public String nazivTabele() {
        return " TipAutomobila ";
    }

    @Override
    public String alijas() {
        return " ta ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            TipAutomobila ta = new TipAutomobila(rs.getInt("TipAutomobilaID"),
                    rs.getString("Marka"), rs.getString("Model"),
                    rs.getString("Gorivo"), rs.getString("Menjac"));

            lista.add(ta);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Marka, Model, Gorivo, Menjac) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + marka + "', '" + model + "', "
                + "'" + gorivo + "', '" + menjac + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " marka = '" + marka + "', model = '" + model + "', "
                + "gorivo = '" + gorivo + "', menjac = '" + menjac + "' ";
    }

    @Override
    public String uslov() {
        return " tipAutomobilaID = " + tipAutomobilaID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getTipAutomobilaID() {
        return tipAutomobilaID;
    }

    public void setTipAutomobilaID(int tipAutomobilaID) {
        this.tipAutomobilaID = tipAutomobilaID;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGorivo() {
        return gorivo;
    }

    public void setGorivo(String gorivo) {
        this.gorivo = gorivo;
    }

    public String getMenjac() {
        return menjac;
    }

    public void setMenjac(String menjac) {
        this.menjac = menjac;
    }

}
