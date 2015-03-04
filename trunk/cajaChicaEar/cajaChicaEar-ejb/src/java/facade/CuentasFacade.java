/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Cuentas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author trompudo
 */
@Stateless
public class CuentasFacade extends AbstractFacade<Cuentas> {
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(DetCatalogoFacade.class);
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentasFacade() {
        super(Cuentas.class);
    }
    
    public Cuentas BuscarCuenta(Cuentas ctaObj){
    
       Cuentas obj=null;
        try
        {
            obj = em.find(Cuentas.class, ctaObj.getPlucuenta());
           
        }
        catch (Exception e)
        {
          System.out.println("Error al buscar Cuenta:"+e); 
         
        }
        
        return obj;
           
    }
    
 
      public Cuentas BuscaCtaContable(String objCta)
    {
        Cuentas obj= null;
        
        Query q=null;   
        try
        {
            
           q = em.createQuery("SELECT c FROM Cuentas c where c.codigo=:codigo "); 
           q.setParameter("codigo", objCta);
           
           obj = (Cuentas) q.getSingleResult();
    
        }
        catch (Exception e)
        {
          logger.error("Error al Buscar Cuenta Contable ",e);
          System.out.println("ERROR CONSULTADO CUENTA:"+e);
        }
               
        return obj; 
    }
    
}
