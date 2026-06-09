/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kategorija;

import db.DBBroker;
import klase.AbstractDomainObject;
import klase.KategorijaVozackeDozvole;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SOGetAllKategorijaVozackeDozvole extends AbstractSO {

    private ArrayList<KategorijaVozackeDozvole> lista;

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof KategorijaVozackeDozvole)) {
            throw new Exception("Prosledjeni objekat nije instanca klase KategorijaVozackeDozvole!");
        }
    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> kategorije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<KategorijaVozackeDozvole>) (ArrayList<?>) kategorije;
    }

    public ArrayList<KategorijaVozackeDozvole> getLista() {
        return lista;
    }

}
