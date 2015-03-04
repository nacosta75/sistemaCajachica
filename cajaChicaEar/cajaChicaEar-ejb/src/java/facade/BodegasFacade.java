/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.BodegaResumenDTO;
import entidades.Bodegas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
public class BodegasFacade extends AbstractFacade<Bodegas> {
   
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(CatalogoFacade.class);
   

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BodegasFacade() {
        super(Bodegas.class);
    }
    
    public List<Bodegas> BuscaSucursalTodos()
    {
        Query q=null;   
        try
        {
           q = em.createQuery("SELECT b FROM Bodegas b"); 
           
           logger.info("Listando Bodegas :");
        }
        catch (Exception e)
        {
          logger.error("Error al Listar Sucursales ",e);  
        }
               
        return q.getResultList(); 
    }
    
    public Bodegas BuscaBodega(Bodegas bodega){
       // find efectua busqueda por llave primaria : idcanal
       Bodegas obj=null;
        try
        {
            obj = em.find(Bodegas.class, bodega.getPlubodega());  
        }
        catch (Exception e)
        {
         logger.error("Error al buscar Bodega",e); 
        }    
        return obj;  
    }
    
       
      public String ModificarBodega(Bodegas bodega)
     {  
         String msg="";
        try
        {
          em.merge(bodega);
          em.flush();
          msg="Se modifico la Bodega";
          logger.info("Se modifico bodega:"+bodega.getCodigo());
        }
        catch (Exception e)
            {
                System.out.println("Error al Modificar  "+e);
               logger.error("Error al Modificar  ",e); 
            }             
       return msg;
     }
    
     public List<BodegaResumenDTO> ReporteCaja() {
         
        List<BodegaResumenDTO> retorno = new ArrayList<BodegaResumenDTO>();
        try {
            //List<CchLiquidacion> temp = new ArrayList<CchLiquidacion>();
            //Query query = em.createNamedQuery("CchLiquidacion.findAll").setHint("eclipselink.refresh", "true");
            
            Query query = em.createNativeQuery(" SELECT B.PLUBODEGA,B.NOMBRE,coalesce(CAJA_CHICA,0) CAJA_CHICA, "+
            " COALESCE((SELECT SUM(TOTAL) FROM CCH_DET_GASTOS  WHERE IDSUCURSAL=B.PLUBODEGA "+
            " AND (IDLIQ=NULL OR (IDLIQ=0))),0) GASTOS FROM BODEGAS B ");
            
            List<Object[]> temp = query.getResultList();
            
          
            
             if (temp != null && temp.size()>0) {
                 
                 int i=0;
                 
              while (temp.size()>i+1)
                 {
                     
                   BodegaResumenDTO ret = new BodegaResumenDTO(); 
                   ret.setIdbodega(Integer.parseInt(temp.get(i)[0].toString()));// idbodega
                   ret.setBodega(temp.get(i)[1].toString());// bodega
                   ret.setTotCaja(new BigDecimal(temp.get(i)[2].toString()));// caja chica
                   ret.setGastos(new BigDecimal(temp.get(i)[3].toString()));
                   ret.setSaldo(ret.getTotCaja().subtract(ret.getGastos()));
                   ret.setTitulo("RESUMEN DE GASTOS POR BODEGA AL :"+new Date());
                   retorno.add(ret);
                   System.out.println(ret);
                  i += 1;
                  
                 }
               // response.setId(retention.get(0)[0].toString());
               // response.setName(retention.get(0)[1].toString());
     
            }
             
   
           /* for (List<Object> nuevo : temp.get(0)>0) {
                //logger.info("algo:"+nuevo);
                if (nuevo.getIdsucursal().getPlubodega()==plusuc)
                {
                    BodegaResumenDTO agregar = ConvertToDTO(nuevo);
                    retorno.add(agregar);
                }
                
                
            }*/
            
            
        } catch (Exception e)  {
            logger.info("No se encontraron liquidaciones!:"+e.getMessage());
            retorno = null;
        }
        return retorno;
    }


     

}

