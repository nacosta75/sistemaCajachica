/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Bodegas;
import entidades.CchCanal;
import entidades.CchCatalogo;
import entidades.CchDetCatalogo;
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
public class DetCatalogoFacade extends AbstractFacade<CchDetCatalogo> {
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;
    
     private static final Logger logger = Logger.getLogger(DetCatalogoFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public DetCatalogoFacade()
    {
        super(CchDetCatalogo.class);
    }
    
    public List<CchDetCatalogo> BuscaDetCatalogoTodos(int idCatG,int idSuc)
    {
        
        //System.out.println("Buscando idgcatalogo:"+idCatG);
        Query q=null;   
        try
        {
           //catalogo
        CchCatalogo objCat= new CchCatalogo();
        objCat.setIdgcatalogo(idCatG);
      
        // bodega
        Bodegas objBod = new Bodegas();
        objBod.setPlubodega(idSuc);
    
          
           q = em.createQuery("SELECT c FROM CchDetCatalogo c where c.idgcatalogo=:id and c.idsucursal=:idsucursal "); 
           q.setParameter("id", objCat);
           q.setParameter("idsucursal", objBod);
        
           logger.info("Listando detCatalogo :"+idCatG);
        }
        catch (Exception e)
        {
          logger.error("Error al Listar detCatalogo ",e);  
        }
               
        return q.getResultList(); 
    }
    
     public String BuscaCuentaContable(int idCanal,int idSuc,int idCatG)
    {
        String resultado = "0";
        
        Query q=null;   
        try
        {
        
        //catalogo
        CchCatalogo objCat= new CchCatalogo();
        objCat.setIdgcatalogo(idCatG);
      
        // bodega
        Bodegas objBod = new Bodegas();
        objBod.setPlubodega(idSuc);
        
        //canal
        CchCanal objCanal= new CchCanal();
        objCanal.setIdcanal(idCanal);
           
          
           q = em.createQuery("SELECT c.ctaContab FROM CchDetCatalogo c where c.idgcatalogo=:id and c.idsucursal=:idsucursal and c.idcanal=:idcanal "); 
           q.setParameter("id", objCat);
           q.setParameter("idsucursal", objBod);
           q.setParameter("idcanal", objCanal);
           
            List<Object> lstResult = (List<Object>) q.getResultList();
            if (lstResult != null && !lstResult.isEmpty()) {
           
                resultado = lstResult.get(0).toString();
               
            }
           
           
        }
        catch (Exception e)
        {
          logger.error("Error al Buscar Cuenta Contable ",e);
          System.out.println("ERROR CONSULTADO CUENTA:"+e);
        }
               
        return resultado; 
    }
    
    public CchDetCatalogo BuscaCatalogoDet(CchDetCatalogo detcatalogo){
       // find efectua busqueda por llave primaria : idcanal
        CchDetCatalogo obj=null;
        try
        {
            System.out.println("buscando:"+detcatalogo.getId());
            obj = em.find(CchDetCatalogo.class, detcatalogo.getId());  
        }
        catch (Exception e)
        {
         logger.error("Error al buscar DetCatalogo",e); 
        }    
        return obj;  
    }
    
     public String GuardarDetCatalogo(CchDetCatalogo detcatalogo)
     {  
         String msg="";
        try
        {
         // em.getTransaction().begin();
          em.persist(detcatalogo);
          em.flush();
          msg = "Se inserto el DetCatalogo";
          logger.info("Se inserto el DetCatalogo:"+detcatalogo.getId());
        }
        catch (Exception e)
            {
               // em.getTransaction().rollback();
                logger.error("Error al Guardar el detCatalogo ",e); 
            }             
       return msg;
     }
    
      public String ModificarDetCatalogo(CchDetCatalogo detcatalogo)
     {  
         String msg="";
        try
        {
          em.merge(detcatalogo);
          em.flush();
          msg="Se modifico el detCatalogo";
          logger.info("Se modifico el detCatalogo:"+detcatalogo.getId());
        }
        catch (Exception e)
            {
                logger.error("Error al Modificar detCatalogo ",e); 
            }             
       return msg;
     }
      
       public String EliminarDetCatalogo(CchDetCatalogo detcatalogo)
     {  
         String msg="";
        try
        {
          //em.getTransaction().begin();
          CchDetCatalogo obj= null;
          obj = em.find(CchDetCatalogo.class, detcatalogo.getId());
          if (obj !=null) 
          {
            em.remove(obj);
            em.flush();
            msg="se elimino el DetCatalogo";
            logger.info("Se elimino el detCatalogo:"+detcatalogo.getId());
          }
        }
        catch (Exception e)
            {
                logger.error("Error al Borrar el detCatalogo ",e); 
            }             
       return msg;
     }   
       
    public boolean BuscaDetCatalogoDetalle(int idCanal,int idSuc,int idCatG)
    {
        boolean resultado = false;
        
        Query q=null;   
        try
        {
        
        //catalogo
        CchCatalogo objCat= new CchCatalogo();
        objCat.setIdgcatalogo(idCatG);
      
        // bodega
        Bodegas objBod = new Bodegas();
        objBod.setPlubodega(idSuc);
        
        //canal
        CchCanal objCanal= new CchCanal();
        objCanal.setIdcanal(idCanal);
        
    
          
           q = em.createQuery("SELECT c FROM CchDetCatalogo c where c.idgcatalogo=:id and c.idsucursal=:idsucursal and c.idcanal=:idcanal "); 
           q.setParameter("id", objCat);
           q.setParameter("idsucursal", objBod);
           q.setParameter("idcanal", objCanal);
           
            List<Object> lstResult = (List<Object>) q.getResultList();
            if (lstResult != null && !lstResult.isEmpty()) {
                resultado = true;
            } else {
                resultado = false;
            }
        
           
        }
        catch (Exception e)
        {
          logger.error("Error al Buscar detCatalogo ",e);
          resultado = true; // no inserto nada
        }
               
        return resultado; 
    }
}
