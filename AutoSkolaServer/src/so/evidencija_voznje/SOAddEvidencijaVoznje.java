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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SOAddEvidencijaVoznje extends AbstractSO {

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof EvidencijaVoznje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaVoznje!");
        }

        EvidencijaVoznje ev = (EvidencijaVoznje) ado;

        if (ev.getBrojCasova() < 20 || ev.getBrojCasova() > 40) {
            throw new Exception("Broj casova mora biti izmedju 20 i 40!");
        }

        if (ev.getStavkeEvidencije().isEmpty() || ev.getStavkeEvidencije().size() > ev.getBrojCasova()) {
            throw new Exception("Morate uneti barem jednu stavku, a manje od " + ev.getBrojCasova() + "!");
        }

    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        int evID = tableKeys.getInt(1);
        
        
        EvidencijaVoznje ev = (EvidencijaVoznje) ado;
        ev.setEvidencijaVoznjeID(evID);
        
        
        for (StavkaEvidencije stavkaEvidencije : ev.getStavkeEvidencije()) {
            stavkaEvidencije.setEvidencijaVoznje(ev); 
            DBBroker.getInstance().insert(stavkaEvidencije);
        }
        
    }

}
