/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import klase.AbstractDomainObject;
import klase.Instruktor;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SOLogin extends AbstractSO {

    Instruktor ulogovani;

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Instruktor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Instruktor!");
        }

        Instruktor i = (Instruktor) ado;

        for (Instruktor inst : ServerController.getInstance().getUlogovaniInstruktori()) {
            if (inst.getKorisnickoIme().equals(i.getKorisnickoIme())) {
                throw new Exception("Ovaj instruktor je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {

        Instruktor i = (Instruktor) ado;

        ArrayList<Instruktor> instruktori
                = (ArrayList<Instruktor>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Instruktor instruktor : instruktori) {
            if (instruktor.getKorisnickoIme().equals(i.getKorisnickoIme())
                    && instruktor.getSifra().equals(i.getSifra())) {
                ulogovani = instruktor;
                ServerController.getInstance().getUlogovaniInstruktori().add(instruktor);
                return;
            }
        }

        throw new Exception("Ne postoji instruktor sa tim kredencijalima.");

    }

    public Instruktor getUlogovani() {
        return ulogovani;
    }

}
