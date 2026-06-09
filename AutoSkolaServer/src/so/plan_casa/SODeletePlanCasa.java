/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.plan_casa;

import db.DBBroker;
import klase.AbstractDomainObject;
import klase.PlanCasa;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SODeletePlanCasa extends AbstractSO {

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof PlanCasa)){
            throw new Exception("Prosledjeni objekat nije instanca klase PlanCasa!");
        }
    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
