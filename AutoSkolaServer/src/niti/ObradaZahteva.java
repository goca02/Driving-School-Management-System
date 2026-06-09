/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import controller.ServerController;
import klase.EvidencijaVoznje;
import klase.Instruktor;
import klase.PlanCasa;
import klase.Ucenik;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.util.StatusOdgovora;
import transfer.util.Operacije;

/**
 *
 * @author Goca
 */
public class ObradaZahteva extends Thread {

    private Socket socket;

    ObradaZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev request = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor handleRequest(KlijentskiZahtev request) {
        ServerskiOdgovor response = new ServerskiOdgovor(null, null, StatusOdgovora.Success);
        try {
            switch (request.getOperation()) {
                case Operacije.ADD_EVIDENCIJA_VOZNJE:
                    ServerController.getInstance().addEvidencijaVoznje((EvidencijaVoznje) request.getData());
                    break;
                case Operacije.ADD_UCENIK:
                    ServerController.getInstance().addUcenik((Ucenik) request.getData());
                    break;
                case Operacije.ADD_PLAN_CASA:
                    ServerController.getInstance().addPlanCasa((PlanCasa) request.getData());
                    break;
                case Operacije.DELETE_EVIDENCIJA_VOZNJE:
                    ServerController.getInstance().deleteEvidencijaVoznje((EvidencijaVoznje) request.getData());
                    break;
                case Operacije.DELETE_UCENIK:
                    ServerController.getInstance().deleteUcenik((Ucenik) request.getData());
                    break;
                case Operacije.DELETE_PLAN_CASA:
                    ServerController.getInstance().deletePlanCasa((PlanCasa) request.getData());
                    break;
                case Operacije.UPDATE_EVIDENCIJA_VOZNJE:
                    ServerController.getInstance().updateEvidencijaVoznje((EvidencijaVoznje) request.getData());
                    break;
                case Operacije.UPDATE_UCENIK:
                    ServerController.getInstance().updateUcenik((Ucenik) request.getData());
                    break;
                case Operacije.UPDATE_PLAN_CASA:
                    ServerController.getInstance().updatePlanCasa((PlanCasa) request.getData());
                    break;
                case Operacije.GET_ALL_EVIDENCIJA_VOZNJE:
                    response.setData(ServerController.getInstance().getAllEvidencijaVoznje((Ucenik) request.getData()));
                    break;
                case Operacije.GET_ALL_KATEGORIJA:
                    response.setData(ServerController.getInstance().getAllKategorija());
                    break;
                case Operacije.GET_ALL_STAVKA_EVIDENCIJE:
                    if (request.getData() instanceof EvidencijaVoznje) {
                        response.setData(ServerController.getInstance().getAllStavkaEvidencije((EvidencijaVoznje) request.getData()));
                    }else{
                        response.setData(ServerController.getInstance().getAllStavkaPlana((PlanCasa) request.getData()));
                    }
                    break;
                case Operacije.GET_ALL_UCENIK:
                    response.setData(ServerController.getInstance().getAllUcenik());
                    break;
                case Operacije.GET_ALL_PLAN_CASA:
                    response.setData(ServerController.getInstance().getAllPlanCasa());
                    break;
                case Operacije.LOGIN:
                    Instruktor instruktor = (Instruktor) request.getData();
                    Instruktor i = ServerController.getInstance().login(instruktor);
                    response.setData(i);
                    break;
                case Operacije.LOGOUT:
                    Instruktor ulogovani = (Instruktor) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setStatusOdgovora(StatusOdgovora.Error);
            response.setException(ex);
        }
        return response;
    }

}
