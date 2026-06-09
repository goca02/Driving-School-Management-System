/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.evidencija_voznje;

import db.DBBroker;
import klase.AbstractDomainObject;
import klase.EvidencijaVoznje;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SODeleteEvidencijaVoznje extends AbstractSO {

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof EvidencijaVoznje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaVoznje!");
        }
    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
