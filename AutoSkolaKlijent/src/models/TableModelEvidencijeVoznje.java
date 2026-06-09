/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import klase.EvidencijaVoznje;
import klase.PlanCasa;
import klase.StavkaEvidencije;
import klase.Ucenik;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Goca
 */
public class TableModelEvidencijeVoznje extends AbstractTableModel implements Runnable {

    private ArrayList<EvidencijaVoznje> lista;
    private String[] kolone = {"ID", "Ucenik", "Broj casova", "Ukupan iznos", "Zavrseno", "Polozeno"};
    private String parametar = "";

    public TableModelEvidencijeVoznje() {
        try {
            lista = ClientController.getInstance().getAllEvidencijaVoznje();
        } catch (Exception ex) {
            Logger.getLogger(TableModelEvidencijeVoznje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    public TableModelEvidencijeVoznje(PlanCasa pc) {
        try {
            lista = new ArrayList<>();

            ArrayList<StavkaEvidencije> stavkePlana
                    = ClientController.getInstance().getAllStavkaPlana(pc);

            for (StavkaEvidencije stavkaEvidencije : stavkePlana) {
                lista.add(stavkaEvidencije.getEvidencijaVoznje());
            }

        } catch (Exception ex) {
            Logger.getLogger(TableModelEvidencijeVoznje.class.getName()).log(Level.SEVERE, null, ex);
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
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4 || columnIndex == 5) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex); 
    }

    @Override
    public Object getValueAt(int row, int column) {
        EvidencijaVoznje ev = lista.get(row);

        switch (column) {
            case 0:
                return ev.getEvidencijaVoznjeID();
            case 1:
                return ev.getUcenik();
            case 2:
                return ev.getBrojCasova();
            case 3:
                return ev.getUkupanIznos() + "din";
            case 4:
                return ev.isZavrseno();
            case 5:
                return ev.isPolozeno();

            default:
                return null;
        }
    }

    public EvidencijaVoznje getSelectedEvidencijaVoznje(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelEvidencijeVoznje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllEvidencijaVoznje();
            if (!parametar.equals("")) {
                ArrayList<EvidencijaVoznje> novaLista = new ArrayList<>();
                for (EvidencijaVoznje ev : lista) {
                    if (ev.getUcenik().getIme().toLowerCase().contains(parametar.toLowerCase())
                            || ev.getUcenik().getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(ev);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
