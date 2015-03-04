/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.CchCatalogo;
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
public class CatalogoFacade extends AbstractFacade<CchCatalogo> {
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;
    
     private static final Logger logger = Logger.getLogger(CatalogoFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public CatalogoFacade()
    {
      super(CchCatalogo.class);
    }

     public List<CchCatalogo> BuscaCatalogoTodos()
    {
        Query q=null;   
        try
        {
           q = em.createQuery("SELECT c FROM CchCatalogo c "); 
           
           logger.info("Listando Catalogo :");
        }
        catch (Exception e)
        {
          logger.error("Error al Listar Catalogo ",e);  
        }
               
        return q.getResultList(); 
    }
    
    public CchCatalogo BuscaCatalogo(CchCatalogo catalogo){
       // find efectua busqueda por llave primaria : idcanal
        System.out.println("buscando:"+catalogo.getIdgcatalogo());
       CchCatalogo obj=null;
        try
        {
            System.out.println("buscando:"+catalogo.getIdgcatalogo());
            obj = em.find(CchCatalogo.class, catalogo.getIdgcatalogo());  
            System.out.println("fovial:"+obj.getFovial());
            
            
        }
        catch (Exception e)
        {
         System.out.println("Error al buscar Catalogo:"+e);
         logger.error("Error al buscar Catalogo",e); 
        }    
        return obj;  
    }
    
     public String GuardarCatalogo(CchCatalogo catalogo)
     {  
         String msg="";
        try
        {
         
          em.persist(catalogo);
          em.flush();
          msg = "Se inserto el Catalogo";
          logger.info("Se inserto el Catalogo:"+catalogo.getNombreGasto());
        }
        catch (Exception e)
            {
               // em.getTransaction().rollback();
                logger.error("Error al Guardar el Catalogo ",e); 
            }             
       return msg;
     }
    
      public String ModificarCatalogo(CchCatalogo catalogo)
     {  
         String msg="";
        try
        {
          em.merge(catalogo);
          em.flush();
          msg="Se modifico el Catalogo";
          logger.info("Se modifico el Catalogo:"+catalogo.getNombreGasto());
        }
        catch (Exception e)
            {
                logger.error("Error al Modificar Catalogo ",e); 
            }             
       return msg;
     }
      
       public String EliminarCatalogo(CchCatalogo catalogo)
     {  
         String msg="";
        try
        {
          //em.getTransaction().begin();
          CchCatalogo obj= null;
          obj = em.find(CchCatalogo.class, catalogo.getIdgcatalogo());
          if (obj !=null) 
          {
            em.remove(obj);
            em.flush();
            msg="se elimino el Catalogo";
            logger.info("Se elimino el Catalogo:"+catalogo.getNombreGasto());
          }
        }
        catch (Exception e)
            {
                logger.error("Error al Borrar el Catalogo ",e); 
            }             
       return msg;
     }
    
    
}
