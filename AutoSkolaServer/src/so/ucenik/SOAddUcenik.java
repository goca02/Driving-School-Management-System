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
public class SOAddUcenik extends AbstractSO {

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Ucenik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
        }

        Ucenik u = (Ucenik) ado;

        ArrayList<Ucenik> ucenici = (ArrayList<Ucenik>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Ucenik ucenik : ucenici) {
            if (ucenik.getEmail().equals(u.getEmail())) {
                throw new Exception("Email vec postoji!");
            }
            if (ucenik.getTelefon().equals(u.getTelefon())) {
                throw new Exception("Telefon vec postoji!");
            }
        }

    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
