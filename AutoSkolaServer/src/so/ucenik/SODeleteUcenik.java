/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ucenik;

import db.DBBroker;
import klase.AbstractDomainObject;
import klase.Ucenik;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SODeleteUcenik extends AbstractSO {

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Ucenik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
        }
    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
