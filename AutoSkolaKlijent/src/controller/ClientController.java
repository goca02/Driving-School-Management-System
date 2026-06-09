/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import klase.EvidencijaVoznje;
import klase.Instruktor;
import klase.KategorijaVozackeDozvole;
import klase.PlanCasa;
import klase.StavkaEvidencije;
import klase.Ucenik;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.util.StatusOdgovora;
import transfer.util.Operacije;

/**
 *
 * @author Goca
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Instruktor login(Instruktor instruktor) throws Exception {
        return (Instruktor) sendRequest(Operacije.LOGIN, instruktor);
    }

    public void logout(Instruktor ulogovani) throws Exception {
        sendRequest(Operacije.LOGOUT, ulogovani);
    }

    public void addUcenik(Ucenik ucenik) throws Exception {
        sendRequest(Operacije.ADD_UCENIK, ucenik);
    }

    public void addEvidencijaVoznje(EvidencijaVoznje evidencijaVoznje) throws Exception {
        sendRequest(Operacije.ADD_EVIDENCIJA_VOZNJE, evidencijaVoznje);
    }

    public void addPlanCasa(PlanCasa plan_casa) throws Exception {
        sendRequest(Operacije.ADD_PLAN_CASA, plan_casa);
    }

    public void deleteUcenik(Ucenik ucenik) throws Exception {
        sendRequest(Operacije.DELETE_UCENIK, ucenik);
    }

    public void deleteEvidencijaVoznje(EvidencijaVoznje evidencijaVoznje) throws Exception {
        sendRequest(Operacije.DELETE_EVIDENCIJA_VOZNJE, evidencijaVoznje);
    }

    public void deletePlanCasa(PlanCasa plan) throws Exception {
        sendRequest(Operacije.DELETE_PLAN_CASA, plan);
    }

    public void updateUcenik(Ucenik ucenik) throws Exception {
        sendRequest(Operacije.UPDATE_UCENIK, ucenik);
    }

    public void updateEvidencijaVoznje(EvidencijaVoznje evidencijaVoznje) throws Exception {
        sendRequest(Operacije.UPDATE_EVIDENCIJA_VOZNJE, evidencijaVoznje);
    }

    public void updatePlanCasa(PlanCasa plan) throws Exception {
        sendRequest(Operacije.UPDATE_PLAN_CASA, plan);
    }

    public ArrayList<Ucenik> getAllUcenik() throws Exception {
        return (ArrayList<Ucenik>) sendRequest(Operacije.GET_ALL_UCENIK, null);
    }

    public ArrayList<PlanCasa> getAllPlanCasa() throws Exception {
        return (ArrayList<PlanCasa>) sendRequest(Operacije.GET_ALL_PLAN_CASA, null);
    }

    public ArrayList<EvidencijaVoznje> getAllEvidencijaVoznje() throws Exception {
        return (ArrayList<EvidencijaVoznje>) sendRequest(Operacije.GET_ALL_EVIDENCIJA_VOZNJE, null);
    }

    public ArrayList<KategorijaVozackeDozvole> getAllKategorija() throws Exception {
        return (ArrayList<KategorijaVozackeDozvole>) sendRequest(Operacije.GET_ALL_KATEGORIJA, null);
    }

    public ArrayList<StavkaEvidencije> getAllStavkaEvidencije(EvidencijaVoznje ev) throws Exception {
        return (ArrayList<StavkaEvidencije>) sendRequest(Operacije.GET_ALL_STAVKA_EVIDENCIJE, ev);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        KlijentskiZahtev request = new KlijentskiZahtev(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        ServerskiOdgovor response = (ServerskiOdgovor) in.readObject();

        if (response.getStatusOdgovora().equals(StatusOdgovora.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

    public ArrayList<StavkaEvidencije> getAllStavkaPlana(PlanCasa pc) throws Exception {
        return (ArrayList<StavkaEvidencije>) sendRequest(Operacije.GET_ALL_STAVKA_EVIDENCIJE, pc);
    }

   
    

}
