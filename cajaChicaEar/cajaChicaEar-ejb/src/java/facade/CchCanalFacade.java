/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.CchCanal;
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
public class CchCanalFacade extends AbstractFacade<CchCanal> {
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;
    
    private static final Logger logger = Logger.getLogger(CchCanalFacade.class);

    // private LoadLogger logger = new LoadLogger();
     
     
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CchCanalFacade() {
        super(CchCanal.class);
    }
    
    
    public List<CchCanal> BuscaCanalTodos()
    {
        Query q=null;   
        try
        {
           q = em.createQuery("SELECT c FROM CchCanal c"); 
           //System.out.println("Listando todos los canales :");
           logger.info("Listando todos los canales :");
        }
        catch (Exception e)
        {
          logger.error("Error al Listar Canal ",e);
         System.out.println("Error al Listar Canal :"+e);
        }
               
        return q.getResultList(); 
    }
    
    public CchCanal BuscaCanal(CchCanal canal){
       // find efectua busqueda por llave primaria : idcanal
       CchCanal obj=null;
        try
        {
            obj = em.find(CchCanal.class, canal.getIdcanal());  
        }
        catch (Exception e)
        {
         logger.error("Error al buscar Canal",e); 
        }    
        return obj;  
    }
    
     public String GuardarCanal(CchCanal canal)
     {  
         String msg="";
        try
        {
         // em.getTransaction().begin();
          em.persist(canal);
          em.flush();
          msg = "Se inserto el Canal";
          logger.info("Se inserto el Canal:"+canal.getNombreCanal());
        }
        catch (Exception e)
            {
               // em.getTransaction().rollback();
                logger.error("Error al Guardar el Canal ",e); 
            }             
       return msg;
     }
    
      public String ModificarCanal(CchCanal canal)
     {  
         String msg="";
        try
        {
          em.merge(canal);
          em.flush();
          msg="Se modifico el Canal";
          logger.info("Se modifico el Canal:"+canal.getNombreCanal());
        }
        catch (Exception e)
            {
                logger.error("Error al Modificar Canal ",e); 
            }             
       return msg;
     }
      
       public String EliminarCanal(CchCanal canal)
     {  
         String msg="";
        try
        {
          
          CchCanal obj= null;
          obj = em.find(CchCanal.class, canal.getIdcanal());
          if (obj !=null) 
          {
            em.remove(obj);
            em.flush();
            msg="se elimino el Canal";
            logger.info("Se elimino el Canal:"+canal.getNombreCanal());
          }
        }
        catch (Exception e)
            {
                logger.error("Error al Borrar el Canal ",e); 
            }             
       return msg;
     }
    
}
