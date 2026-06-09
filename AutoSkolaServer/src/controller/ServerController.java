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
import java.util.ArrayList;
import niti.PokreniServer;
import so.evidencija_voznje.SOAddEvidencijaVoznje;
import so.evidencija_voznje.SODeleteEvidencijaVoznje;
import so.evidencija_voznje.SOGetAllEvidencijaVoznje;
import so.evidencija_voznje.SOUpdateEvidencijaVoznje;
import so.login.SOLogin;
import so.kategorija.SOGetAllKategorijaVozackeDozvole;
import so.plan_casa.SOAddPlanCasa;
import so.plan_casa.SODeletePlanCasa;
import so.plan_casa.SOGetAllPlanCasa;
import so.plan_casa.SOUpdatePlanCasa;
import so.stavka_evidencije.SOGetAllStavkaEvidencije;
import so.ucenik.SOAddUcenik;
import so.ucenik.SODeleteUcenik;
import so.ucenik.SOGetAllUcenik;
import so.ucenik.SOUpdateUcenik;

/**
 *
 * @author Goca
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Instruktor> ulogovaniInstruktori = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Instruktor> getUlogovaniInstruktori() {
        return ulogovaniInstruktori;
    }

    public void setUlogovaniInstruktori(ArrayList<Instruktor> ulogovaniInstruktori) {
        this.ulogovaniInstruktori = ulogovaniInstruktori;
    }

    public Instruktor login(Instruktor instruktor) throws Exception {
        SOLogin so = new SOLogin();
        so.izvrsavanjeSO(instruktor);
        return so.getUlogovani();
    }

    public void addUcenik(Ucenik ucenik) throws Exception {
        (new SOAddUcenik()).izvrsavanjeSO(ucenik);
    }

    public void addEvidencijaVoznje(EvidencijaVoznje evidencijaVoznje) throws Exception {
        (new SOAddEvidencijaVoznje()).izvrsavanjeSO(evidencijaVoznje);
    }

    public void addPlanCasa(PlanCasa planCasa) throws Exception {
        (new SOAddPlanCasa()).izvrsavanjeSO(planCasa);
    }

    public void deleteUcenik(Ucenik ucenik) throws Exception {
        (new SODeleteUcenik()).izvrsavanjeSO(ucenik);
    }

    public void deleteEvidencijaVoznje(EvidencijaVoznje evidencijaVoznje) throws Exception {
        (new SODeleteEvidencijaVoznje()).izvrsavanjeSO(evidencijaVoznje);
    }

    public void deletePlanCasa(PlanCasa planCasa) throws Exception {
        (new SODeletePlanCasa()).izvrsavanjeSO(planCasa);
    }

    public void updateUcenik(Ucenik ucenik) throws Exception {
        (new SOUpdateUcenik()).izvrsavanjeSO(ucenik);
    }

    public void updatePlanCasa(PlanCasa planCasa) throws Exception {
        (new SOUpdatePlanCasa()).izvrsavanjeSO(planCasa);
    }

    public void updateEvidencijaVoznje(EvidencijaVoznje evidencijaVoznje) throws Exception {
        (new SOUpdateEvidencijaVoznje()).izvrsavanjeSO(evidencijaVoznje);
    }

    public ArrayList<Ucenik> getAllUcenik() throws Exception {
        SOGetAllUcenik so = new SOGetAllUcenik();
        so.izvrsavanjeSO(new Ucenik());
        return so.getLista();
    }

    public ArrayList<PlanCasa> getAllPlanCasa() throws Exception {
        SOGetAllPlanCasa so = new SOGetAllPlanCasa();
        so.izvrsavanjeSO(new PlanCasa());
        return so.getPlanovi();
    }

    public ArrayList<EvidencijaVoznje> getAllEvidencijaVoznje(Ucenik u) throws Exception {
        SOGetAllEvidencijaVoznje so = new SOGetAllEvidencijaVoznje();

        EvidencijaVoznje ev = new EvidencijaVoznje();
        ev.setUcenik(u);

        so.izvrsavanjeSO(ev);
        return so.getLista();
    }

    public ArrayList<KategorijaVozackeDozvole> getAllKategorija() throws Exception {
        SOGetAllKategorijaVozackeDozvole so = new SOGetAllKategorijaVozackeDozvole();
        so.izvrsavanjeSO(new KategorijaVozackeDozvole());
        return so.getLista();
    }

    public ArrayList<StavkaEvidencije> getAllStavkaEvidencije(EvidencijaVoznje ev) throws Exception {
        SOGetAllStavkaEvidencije so = new SOGetAllStavkaEvidencije();

        StavkaEvidencije se = new StavkaEvidencije();
        se.setEvidencijaVoznje(ev);
       
        so.izvrsavanjeSO(se);
        return so.getLista();
    }

    public void logout(Instruktor ulogovani) {
        ulogovaniInstruktori.remove(ulogovani);
    }

    public ArrayList<StavkaEvidencije> getAllStavkaPlana(PlanCasa planCasa) throws Exception {
        SOGetAllStavkaEvidencije so = new SOGetAllStavkaEvidencije();

        StavkaEvidencije se = new StavkaEvidencije();
        se.setPlanCasa(planCasa);

        so.izvrsavanjeSO(se);
        return so.getLista();
    }
    
 

}
