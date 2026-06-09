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
public class EvidencijaVoznje extends AbstractDomainObject {

    private int evidencijaVoznjeID;
    private double ukupanIznos;
    private boolean polozeno;
    private int brojCasova;
    private boolean zavrseno;
    private Ucenik ucenik;
    private Instruktor instruktor;
    private ArrayList<StavkaEvidencije> stavkeEvidencije;

    public EvidencijaVoznje() {
    }

    public EvidencijaVoznje(int evidencijaVoznjeID, double ukupanIznos, boolean polozeno, int brojCasova, boolean zavrseno, Ucenik ucenik, Instruktor instruktor, ArrayList<StavkaEvidencije> stavkeEvidencije) {
        this.evidencijaVoznjeID = evidencijaVoznjeID;
        this.ukupanIznos = ukupanIznos;
        this.polozeno = polozeno;
        this.brojCasova = brojCasova;
        this.zavrseno = zavrseno;
        this.ucenik = ucenik;
        this.instruktor = instruktor;
        this.stavkeEvidencije = stavkeEvidencije;
    }

    @Override
    public String nazivTabele() {
        return " EvidencijaVoznje ";
    }

    @Override
    public String alijas() {
        return " ev ";
    }

    @Override
    public String join() {
        return " JOIN UCENIK U ON (U.UCENIKID = EV.UCENIKID)\n"
                + "JOIN kategorijaVozackeDozvole KVD ON (KVD.kategorijaVozackeDozvoleID = U.kategorijaVozackeDozvoleID)\n"
                + "JOIN INSTRUKTOR I ON (I.INSTRUKTORID = EV.INSTRUKTORID)";
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

            lista.add(ev);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ukupanIznos, polozeno, brojCasova, zavrseno, ucenikID, instruktorID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + ukupanIznos + ", " + (polozeno ? 1 : 0) + ", "
                + " " + brojCasova + ", " + (zavrseno ? 1 : 0) + ", "
                + ucenik.getUcenikID() + ", " + instruktor.getInstruktorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " ukupanIznos = " + ukupanIznos + ", "
                + "polozeno = " + (polozeno ? 1 : 0) + ", "
                + "zavrseno = " + zavrseno + ", brojCasova = " + brojCasova;
    }

    @Override
    public String uslov() {
        return " evidencijaVoznjeID = " + evidencijaVoznjeID;
    }

    @Override
    public String uslovZaSelect() {
        if(ucenik == null){
            return "";
        }
        return " WHERE U.UCENIKID = " + ucenik.getUcenikID();
    }

    public int getEvidencijaVoznjeID() {
        return evidencijaVoznjeID;
    }

    public void setEvidencijaVoznjeID(int evidencijaVoznjeID) {
        this.evidencijaVoznjeID = evidencijaVoznjeID;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public boolean isPolozeno() {
        return polozeno;
    }

    public void setPolozeno(boolean polozeno) {
        this.polozeno = polozeno;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }

    public boolean isZavrseno() {
        return zavrseno;
    }

    public void setZavrseno(boolean zavrseno) {
        this.zavrseno = zavrseno;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public ArrayList<StavkaEvidencije> getStavkeEvidencije() {
        return stavkeEvidencije;
    }

    public void setStavkeEvidencije(ArrayList<StavkaEvidencije> stavkeEvidencije) {
        this.stavkeEvidencije = stavkeEvidencije;
    }

}
