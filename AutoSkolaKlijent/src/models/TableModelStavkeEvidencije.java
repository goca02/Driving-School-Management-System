
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import klase.EvidencijaVoznje;
import klase.StavkaEvidencije;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Goca
 */
public class TableModelStavkeEvidencije extends AbstractTableModel {

    private ArrayList<StavkaEvidencije> lista;
    private String[] kolone = {"Rb", "Opis", "Tip voznje", "Datum", "Trajanje u satima", "Cena casa"};
    private int rb;

    public TableModelStavkeEvidencije() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeEvidencije(EvidencijaVoznje ev) {
        try {
            lista = ClientController.getInstance().getAllStavkaEvidencije(ev);
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkeEvidencije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaEvidencije se = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        switch (column) {
            case 0:
                return se.getRb();
            case 1:
                return se.getPlanCasa().getOpis();
            case 2:
                return se.getTipVoznje();
            case 3:
                return sdf.format(se.getDatumCasa());
            case 4:
                return se.getTrajanjeUSatima() + "h";
            case 5:
                return se.getCenaCasa() + "din";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaEvidencije se) {
        rb = lista.size();
        se.setRb(++rb);
        lista.add(se);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaEvidencije stavkaEvidencije : lista) {
            stavkaEvidencije.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;
        
        for (StavkaEvidencije stavkaEvidencije : lista) {
            ukupanIznos += stavkaEvidencije.getCenaCasa();
        }
        
        return ukupanIznos;
    }

    public ArrayList<StavkaEvidencije> getLista() {
        return lista;
    }

}
