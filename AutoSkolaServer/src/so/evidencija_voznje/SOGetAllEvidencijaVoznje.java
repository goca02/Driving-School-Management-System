/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.evidencija_voznje;

import db.DBBroker;
import klase.AbstractDomainObject;
import klase.EvidencijaVoznje;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SOGetAllEvidencijaVoznje extends AbstractSO {

    private ArrayList<EvidencijaVoznje> lista;

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof EvidencijaVoznje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaVoznje!");
        }
    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaEvidencije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<EvidencijaVoznje>) (ArrayList<?>) listaEvidencije;
    }

    public ArrayList<EvidencijaVoznje> getLista() {
        return lista;
    }

}
