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
public class StavkaEvidencije extends AbstractDomainObject {

    private EvidencijaVoznje evidencijaVoznje;
    private int rb;
    private Date datumCasa;
    private double trajanjeUSatima;
    private String tipVoznje;
    private double cenaCasa;
    private double cenaPoSatu;
    private PlanCasa planCasa;

    public StavkaEvidencije(EvidencijaVoznje evidencijaVoznje, int rb, Date datumCasa, double trajanjeUSatima, String tipVoznje, double cenaCasa, double cenaPoSatu, PlanCasa planCasa) {
        this.evidencijaVoznje = evidencijaVoznje;
        this.rb = rb;
        this.datumCasa = datumCasa;
        this.trajanjeUSatima = trajanjeUSatima;
        this.tipVoznje = tipVoznje;
        this.cenaCasa = cenaCasa;
        this.cenaPoSatu = cenaPoSatu;
        this.planCasa = planCasa;
    }

    public StavkaEvidencije() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaEvidencije ";
    }

    @Override
    public String alijas() {
        return " se ";
    }

    @Override
    public String join() {
        return " JOIN EVIDENCIJAVOZNJE EV ON (EV.EVIDENCIJAVOZNJEID = SE.EVIDENCIJAVOZNJEID)\n"
                + "JOIN UCENIK U ON (U.UCENIKID = EV.UCENIKID)\n"
                + "JOIN kategorijaVozackeDozvole KVD ON (KVD.kategorijaVozackeDozvoleID = U.kategorijaVozackeDozvoleID)\n"
                + "JOIN INSTRUKTOR I ON (I.INSTRUKTORID = EV.INSTRUKTORID)\n"
                + "JOIN PLANCASA PC ON (PC.PLANCASAID = SE.PLANCASAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Instruktor i = new Instruktor(rs.getInt("InstruktorID"),
                    rs.getString("i.Ime"), rs.getString("i.Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Sifra"));

            KategorijaVozackeDozvole kvd = new KategorijaVozackeDozvole(rs.getInt("KategorijaVozackeDozvoleID"),
                    rs.getString("Naziv"), rs.getString("TipVozila"));

            Ucenik u = new Ucenik(rs.getInt("ucenikID"), rs.getString("u.ime"),
                    rs.getString("u.prezime"), rs.getString("email"),
                    rs.getString("telefon"), kvd);

            EvidencijaVoznje ev = new EvidencijaVoznje(rs.getInt("evidencijaVoznjeID"),
                    rs.getDouble("ukupanIznos"), rs.getBoolean("polozeno"),
                    rs.getInt("brojCasova"), rs.getBoolean("zavrseno"), u, i, null);

            PlanCasa pc = new PlanCasa(rs.getInt("PlanCasaID"),
                    rs.getString("Opis"), rs.getDouble("pc.trajanjeUSatima"),
                    rs.getString("Lokacija"));

            StavkaEvidencije se = new StavkaEvidencije(ev, rs.getInt("rb"),
                    rs.getDate("datumCasa"), rs.getDouble("se.trajanjeUSatima"),
                    rs.getString("tipVoznje"), rs.getDouble("cenaCasa"),
                    rs.getDouble("cenaPoSatu"), pc);

            lista.add(se);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (evidencijaVoznjeID, rb, datumCasa, trajanjeUSatima, tipVoznje, cenaCasa, cenaPoSatu, planCasaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + evidencijaVoznje.getEvidencijaVoznjeID() + ", " + rb + ", "
                + "'" + new java.sql.Date(datumCasa.getTime()) + "', " + trajanjeUSatima + ", "
                + "'" + tipVoznje + "', " + cenaCasa + ", " + cenaPoSatu + ", "
                + planCasa.getPlanCasaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " evidencijaVoznjeID = " + evidencijaVoznje.getEvidencijaVoznjeID();
    }

    @Override
    public String uslovZaSelect() {
        if(evidencijaVoznje != null){
            return " WHERE EV.EVIDENCIJAVOZNJEID = " + evidencijaVoznje.getEvidencijaVoznjeID();
        }
        return " WHERE PC.PLANCASAID = " + planCasa.getPlanCasaID();
    }

    public EvidencijaVoznje getEvidencijaVoznje() {
        return evidencijaVoznje;
    }

    public void setEvidencijaVoznje(EvidencijaVoznje evidencijaVoznje) {
        this.evidencijaVoznje = evidencijaVoznje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatumCasa() {
        return datumCasa;
    }

    public void setDatumCasa(Date datumCasa) {
        this.datumCasa = datumCasa;
    }

    public double getTrajanjeUSatima() {
        return trajanjeUSatima;
    }

    public void setTrajanjeUSatima(double trajanjeUSatima) {
        this.trajanjeUSatima = trajanjeUSatima;
    }

    public String getTipVoznje() {
        return tipVoznje;
    }

    public void setTipVoznje(String tipVoznje) {
        this.tipVoznje = tipVoznje;
    }

    public double getCenaCasa() {
        return cenaCasa;
    }

    public void setCenaCasa(double cenaCasa) {
        this.cenaCasa = cenaCasa;
    }

    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setCenaPoSatu(double cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    public PlanCasa getPlanCasa() {
        return planCasa;
    }

    public void setPlanCasa(PlanCasa planCasa) {
        this.planCasa = planCasa;
    }

}
