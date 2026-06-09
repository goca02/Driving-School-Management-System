/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.plan_casa;

import db.DBBroker;
import klase.AbstractDomainObject;
import klase.PlanCasa;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Goca
 */
public class SOGetAllPlanCasa extends AbstractSO {
    
    private ArrayList<PlanCasa> planovi;

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof PlanCasa)){
            throw new Exception("Prosledjeni objekat nije instanca klase PlanCasa!");
        }
    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> lista=DBBroker.getInstance().select(ado);
        planovi=(ArrayList<PlanCasa>) (ArrayList<?>)lista;
    }

    public ArrayList<PlanCasa> getPlanovi() {
        return planovi;
    }
    
    
    
}
