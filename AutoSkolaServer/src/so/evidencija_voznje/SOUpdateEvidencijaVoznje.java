/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.evidencija_voznje;

import db.DBBroker;
import klase.AbstractDomainObject;
import klase.EvidencijaVoznje;
import klase.StavkaEvidencije;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SOUpdateEvidencijaVoznje extends AbstractSO {

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof EvidencijaVoznje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaVoznje!");
        }

        EvidencijaVoznje ev = (EvidencijaVoznje) ado;

        if (ev.isPolozeno() && !ev.isZavrseno()) {
            throw new Exception("Casovi moraju da budu zavrseni da bi se polozilo!");
        }

        if (ev.getStavkeEvidencije().isEmpty() 
                || ev.getStavkeEvidencije().size() > ev.getBrojCasova()) {
            throw new Exception("Morate uneti barem jednu stavku, a manje od " + ev.getBrojCasova() + "!");
        }

    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {

        
        DBBroker.getInstance().update(ado);

        EvidencijaVoznje ev = (EvidencijaVoznje) ado;
        
        DBBroker.getInstance().delete(ev.getStavkeEvidencije().get(0));
       

        
        for (StavkaEvidencije stavkaEvidencije : ev.getStavkeEvidencije()) {
            DBBroker.getInstance().insert(stavkaEvidencije);
        }

        
    }

}
