/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Tipodoc;
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
public class TipodocFacade extends AbstractFacade<Tipodoc> {
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;
    
   // private LoggerUtil logger = new LoggerUtil(TipodocFacade.class); 
     private static final Logger logger = Logger.getLogger(CchCanalFacade.class);


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipodocFacade() {
        super(Tipodoc.class);
    }
    
    public List<Tipodoc> BuscaTipoDocTodos()
    {
        Query q=null;   
        try
        {
           q = em.createQuery("SELECT c FROM Tipodoc c order by c.iva ");  
        }
        catch (Exception e)
        {
          logger.error("Error al Listar Tipo Docs ",e);  
        }
               
        return q.getResultList(); 
    }
    
     public List<Tipodoc> BuscaTipoDocDescripcion(String descripcion){
    
       Query q=null;
        
        try
        {
           q = em.createQuery("SELECT c FROM Tipodoc c where c.descripcion like :descripcion+'%' order by c.descripcion");  
           q.setParameter("nombre", descripcion);
           
        }
        catch (Exception e)
        {
          logger.error("Error al Listar TipoDocs ",e);  
        }
        return q.getResultList();       
    }
     
     public Tipodoc BuscaTipodoc(Tipodoc tipoD){
            // find efectua busqueda por llave primaria : plucliente 
       Tipodoc obj=null;
        try
        {
            obj = em.find(Tipodoc.class, tipoD.getIdtipodoc());  
        }
        catch (Exception e)
        {
         logger.error("Error al buscar tipoDoc",e); 
        }    
        return obj;  
    }
     
     
     
     public String GuardarTipoDoc(Tipodoc tipoD)
     {  
         String msg="";
        try
        {
         // em.getTransaction().begin();
          em.persist(tipoD);
          em.flush();
          msg = "Se inserto el TipoDoc";
          logger.info("Se inserto el TipoDoc "+tipoD.getIdtipodoc()); 
        }
        catch (Exception e)
            {
               // em.getTransaction().rollback();
                logger.error("Error al Guardar Tipo Doc ",e); 
            }             
       return msg;
     }
     
      public String ModificarTipoDoc(Tipodoc tipoD)
     {  
         String msg="";
        try
        {
          //em.getTransaction().begin();
          em.merge(tipoD);
          em.flush();
          msg="Se modifico el TipoDoc";
          logger.info("Se modifico el Tipodoc :"+tipoD.getIdtipodoc()); 
        }
        catch (Exception e)
            {
               // em.getTransaction().rollback();
                logger.error("Error al Modificar TipoDoc ",e); 
            }             
       return msg;
     }
      
       public String EliminarTipodoc(Tipodoc tipoD)
     {  
         String msg="";
        try
        {
          //em.getTransaction().begin();
          Tipodoc obj= null;
          obj = em.find(Tipodoc.class, tipoD.getIdtipodoc());
          if (obj !=null) 
          {
            em.remove(obj);
            em.flush();
            //Delete the object  
            //em.remove(entity); 
            // em.getTransaction().commit();
            msg="se elimino el Tipodoc";
            logger.info("Error al Borrar el Tipodoc :"+obj.getIdtipodoc()); 
          }
        }
        catch (Exception e)
            {
             //   em.getTransaction().rollback();
                logger.error("Error al Borrar el Tipodoc ",e); 
            }             
       return msg;
     }
    
    
}
