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
public class PlanCasa extends AbstractDomainObject {

    private int planCasaID;
    private String opis;
    private double trajanjeUSatima;
    private String lokacija;

    @Override
    public String toString() {
        return lokacija + " (Trajanje: " + trajanjeUSatima + "h)";
    }

    public PlanCasa(int planCasaID, String opis, double trajanjeUSatima, String lokacija) {
        this.planCasaID = planCasaID;
        this.opis = opis;
        this.trajanjeUSatima = trajanjeUSatima;
        this.lokacija = lokacija;
    }

    public PlanCasa() {
    }

    @Override
    public String nazivTabele() {
        return " PlanCasa ";
    }

    @Override
    public String alijas() {
        return " pc ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            PlanCasa pc = new PlanCasa(rs.getInt("PlanCasaID"),
                    rs.getString("Opis"), rs.getDouble("trajanjeUSatima"),
                    rs.getString("Lokacija"));

            lista.add(pc);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Opis, trajanjeUSatima, Lokacija) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + opis + "', " + trajanjeUSatima + ", "
                + "'" + lokacija + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " opis = '" + opis + "', trajanjeUSatima = " + trajanjeUSatima + ", "
                + "lokacija = '" + lokacija + "' ";
    }

    @Override
    public String uslov() {
        return " planCasaID = " + planCasaID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getPlanCasaID() {
        return planCasaID;
    }

    public void setPlanCasaID(int planCasaID) {
        this.planCasaID = planCasaID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getTrajanjeUSatima() {
        return trajanjeUSatima;
    }

    public void setTrajanjeUSatima(double trajanjeUSatima) {
        this.trajanjeUSatima = trajanjeUSatima;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

}
