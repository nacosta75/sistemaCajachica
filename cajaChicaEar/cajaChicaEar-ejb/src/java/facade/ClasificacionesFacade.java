/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Clasificaciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author trompudo
 */
@Stateless
public class ClasificacionesFacade extends AbstractFacade<Clasificaciones> {
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasificacionesFacade() {
        super(Clasificaciones.class);
    }
    
}
