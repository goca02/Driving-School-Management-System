/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import klase.PlanCasa;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import klase.EvidencijaVoznje;
import klase.StavkaEvidencije;
import klase.Ucenik;

/**
 *
 * @author Goca
 */
public class TableModelPlanoviCasa extends AbstractTableModel implements Runnable {
     private ArrayList<PlanCasa> lista;
    private String[] kolone = {"ID", "Opis", "Trajanje", "Lokacija"};
    private String parametar = "";
    
    public TableModelPlanoviCasa(){
        try {
            lista = ClientController.getInstance().getAllPlanCasa();
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
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PlanCasa plan=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return plan.getPlanCasaID();
            case 1:
                return plan.getOpis();
            case 2:
                return plan.getTrajanjeUSatima();
            case 3:
                return plan.getLokacija();
            default:
                return null;
        }
    }
    
    public PlanCasa getSelectedPlanCasa(int row) {
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
            lista = ClientController.getInstance().getAllPlanCasa();
            if (!parametar.equals("")) {
                ArrayList<PlanCasa> novaLista = new ArrayList<>();
                for (PlanCasa plan : lista) {
                    if (plan.getOpis().toLowerCase().contains(parametar.toLowerCase())
                            || plan.getLokacija().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(plan);
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
    

