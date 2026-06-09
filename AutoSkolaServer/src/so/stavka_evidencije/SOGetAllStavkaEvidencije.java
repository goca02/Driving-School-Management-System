/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavka_evidencije;

import db.DBBroker;
import klase.AbstractDomainObject;
import klase.StavkaEvidencije;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SOGetAllStavkaEvidencije extends AbstractSO {

    private ArrayList<StavkaEvidencije> lista;

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaEvidencije)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaEvidencije!");
        }
    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavke = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaEvidencije>) (ArrayList<?>) stavke;
    }

    public ArrayList<StavkaEvidencije> getLista() {
        return lista;
    }

}
