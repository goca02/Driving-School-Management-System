/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import klase.AbstractDomainObject;
import java.sql.SQLException;

/**
 *
 * @author Goca
 */
public abstract class AbstractSO {
    
    protected abstract void validacija(AbstractDomainObject ado) throws Exception;
    protected abstract void izvrsavanje(AbstractDomainObject ado) throws Exception;

    public void izvrsavanjeSO(AbstractDomainObject ado) throws Exception {
        try {
            validacija(ado);
            izvrsavanje(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
