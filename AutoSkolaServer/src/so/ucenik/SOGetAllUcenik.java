/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ucenik;

import db.DBBroker;
import klase.AbstractDomainObject;
import klase.Ucenik;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SOGetAllUcenik extends AbstractSO {

    private ArrayList<Ucenik> lista;

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Ucenik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
        }
    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> ucenici = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Ucenik>) (ArrayList<?>) ucenici;
    }

    public ArrayList<Ucenik> getLista() {
        return lista;
    }

}
