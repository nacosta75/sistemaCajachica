/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Tools.JSFUtil;
import dto.DetGastosDTO;
import dto.LiquidacionDTO;
import entidades.Bodegas;
import entidades.CchLiquidacion;
import entidades.Usuarios;
import facade.BodegasFacade;
import facade.UsuarioFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author trompudo
 */
@Stateless(mappedName = "ejb/LiquidacionBean")
public class LiquidacionBean implements LiquidacionBeanLocal{
    
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;
    
    private static final Logger logger = Logger.getLogger(UsuarioFacade.class);
    
    @EJB
    private BodegasFacade bodegasFacade;
    
    @EJB
    private UsuarioFacade usuarioFacade;

    @Override
    public List<LiquidacionDTO> findAll() {
        
        //em.getEntityManagerFactory().getCache().evictAll();
        
        List<LiquidacionDTO> retorno = new ArrayList<LiquidacionDTO>();
        try {
            List<CchLiquidacion> temp = new ArrayList<CchLiquidacion>();
            Query query = em.createNamedQuery("CchLiquidacion.findAll").setHint("eclipselink.refresh", "true");
            temp = query.getResultList();
            //System.out.println("Llega:"+temp);
            int plusuc= 0;
            if (!(JSFUtil.getSessionValue("sucursal").toString().equals(null)))
            plusuc = Integer.parseInt(JSFUtil.getSessionValue("sucursal").toString());
        
            for (CchLiquidacion nuevo : temp) {
                //logger.info("algo:"+nuevo);
                if (nuevo.getIdsucursal().getPlubodega()==plusuc)
                {
                    LiquidacionDTO agregar = ConvertToDTO(nuevo);
                    retorno.add(agregar);
                }
                
                
            }
        } catch (NoResultException nrex) {
            logger.info("No se encontraron liquidaciones!");
            retorno = null;
        }
        return retorno;
    }

    @Override
    public LiquidacionDTO create(LiquidacionDTO inputs) {
        
        CchLiquidacion agregar = ConvertToEntity(inputs);
        agregar = em.merge(agregar);
        inputs = ConvertToDTO(agregar);
        
        return inputs;
    }

    @Override
    public LiquidacionDTO edit(LiquidacionDTO temp) {
        
        CchLiquidacion  edit = ConvertToEntity(temp);
        edit = em.merge(edit);
        em.flush();
        temp = ConvertToDTO(edit);
        return temp;
        
    }

    @Override
    public boolean delete(LiquidacionDTO temp) {
        CchLiquidacion delete = ConvertToEntity(temp);
        em.remove(em.merge(delete));
        em.flush();
        return true;
    }

    private LiquidacionDTO ConvertToDTO(CchLiquidacion nuevo) {
        
         LiquidacionDTO  ret = new LiquidacionDTO();          
        
        ret.setObservaciones(nuevo.getObservaciones());
        ret.setCantDocs(nuevo.getCantDocs());
        ret.setFaltante(nuevo.getFaltante());
        ret.setFecha(nuevo.getFecha());
        ret.setIdSucursal(nuevo.getIdsucursal().getPlubodega());
        ret.setIdliq(nuevo.getIdliq());
        ret.setIdusuario(nuevo.getIdusuario().getIdusuario());
        ret.setPost(nuevo.getPost());
        ret.setTotalCaja(nuevo.getTotalCaja());
        ret.setTotalDocs(nuevo.getTotalDocs());
        ret.setTotalEfec(nuevo.getTotalEfec());
        
        if (nuevo.getPost().equals("S"))
        {
           ret.setProcesado(true);
        }
        else
        {
           ret.setProcesado(false);
        }
        
        return ret;
    }

    private CchLiquidacion ConvertToEntity(LiquidacionDTO inputs) {
        
         
        CchLiquidacion ret = new CchLiquidacion();
       
        ret.setObservaciones(inputs.getObservaciones());
        ret.setCantDocs(inputs.getCantDocs());
        ret.setFaltante(inputs.getFaltante());
        ret.setFecha(inputs.getFecha());
        ret.setIdliq(inputs.getIdliq());
        ret.setPost(inputs.getPost());
        ret.setTotalCaja(inputs.getTotalCaja());
        ret.setTotalDocs(inputs.getTotalDocs());
        ret.setTotalEfec(inputs.getTotalEfec());
               
        //sucursales
        Bodegas objBod = new Bodegas();
        objBod.setPlubodega(inputs.getIdSucursal());
        
        ret.setIdsucursal(objBod);
       
        //usuarios
        Usuarios objUser= new Usuarios();
        objUser.setIdusuario(inputs.getIdusuario());
        
        ret.setIdusuario(objUser);
        
        return ret;
        
    }
    
    public CchLiquidacion BuscarLiquid(Integer liq)
    {
      CchLiquidacion obj=null;
        try
        {
            obj = em.find(CchLiquidacion.class, liq);  
        }
        catch (Exception e)
        {
         logger.error("Error al buscar Liquidacion",e); 
        }    
        return obj;  
    
    }
  
}
