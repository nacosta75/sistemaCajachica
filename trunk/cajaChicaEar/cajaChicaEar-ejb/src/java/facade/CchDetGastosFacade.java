/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.CchDetGastos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author trompudo
 */
@Stateless
public class CchDetGastosFacade extends AbstractFacade<CchDetGastos> {
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;
    
     private static final Logger logger = Logger.getLogger(CchCanalFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CchDetGastosFacade() {
        super(CchDetGastos.class);
    }
    
    
    
}
