/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import klase.Ucenik;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import klase.EvidencijaVoznje;
import klase.PlanCasa;
import klase.StavkaEvidencije;

/**
 *
 * @author Goca
 */
public class TableModelUcenici extends AbstractTableModel implements Runnable {

    private ArrayList<Ucenik> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon", "Kategorija"};
    private String parametar = "";

    public TableModelUcenici() {
        try {
            lista = ClientController.getInstance().getAllUcenik();
        } catch (Exception ex) {
            Logger.getLogger(TableModelUcenici.class.getName()).log(Level.SEVERE, null, ex);
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
        Ucenik u = lista.get(row);

        switch (column) {
            case 0:
                return u.getUcenikID();
            case 1:
                return u.getIme();
            case 2:
                return u.getPrezime();
            case 3:
                return u.getEmail();
            case 4:
                return u.getTelefon();
            case 5:
                return u.getKategorijaVozackeDozvole();

            default:
                return null;
        }
    }

    public Ucenik getSelectedUcenik(int row) {
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
            Logger.getLogger(TableModelUcenici.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllUcenik();
            if (!parametar.equals("")) {
                ArrayList<Ucenik> novaLista = new ArrayList<>();
                for (Ucenik u : lista) {
                    if (u.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || u.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(u);
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
