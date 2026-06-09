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
public class SOAddPlanCasa extends AbstractSO {

    @Override
    protected void validacija(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PlanCasa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase PlanCasa!");
        }
        PlanCasa pc = (PlanCasa) ado;

        if (pc.getTrajanjeUSatima() < 0.75 || pc.getTrajanjeUSatima() > 2) {
            throw new Exception("Trajanje casa mora biti izmedju 0.75h (45min) i 2h!");
        }

        ArrayList<PlanCasa> plan_casa = (ArrayList<PlanCasa>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (PlanCasa plan : plan_casa) {
            if (plan.getOpis().equals(pc.getOpis())) {
                throw new Exception("Opis vec postoji!");
            }

        }
    }

    @Override
    protected void izvrsavanje(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
