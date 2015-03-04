/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Tools.JSFUtil;
import dto.DetGastosDTO;
import entidades.Bodegas;
import entidades.CchCanal;
import entidades.CchCatalogo;
import entidades.CchDetGastos;
import entidades.Cuentas;
import entidades.Tipodoc;
import entidades.Usuarios;
import facade.CuentasFacade;
import facade.TipodocFacade;
import facade.UsuarioFacade;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import utils.Constans;

/**
 * @author Noe Acosta
 */
@Stateless(mappedName = "ejb/DetGastosBean")
public class DetGastosBean implements DetGastosBeanLocal{
    
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;
    
    private static final Logger logger = Logger.getLogger(UsuarioFacade.class);
    
    @EJB
    private TipodocFacade tipoDocFacade;
    
    @EJB
    private CuentasFacade cuentasFacade;
    
   
    
    @Override
    public List<DetGastosDTO> findAll() {                   
        
        List<DetGastosDTO> retorno = new ArrayList<DetGastosDTO>();
        try {
            List<CchDetGastos> temp = new ArrayList<CchDetGastos>();
            
            //BigDecimal Saldo= new BigDecimal("0");
            
            Query query = em.createNamedQuery("CchDetGastos.findAll").setHint("eclipselink.refresh", "true");
            temp = query.getResultList();
            //System.out.println(temp.size());
            int plusuc= 0;
            if (!(JSFUtil.getSessionValue("sucursal").toString().equals(null)))
            plusuc = Integer.parseInt(JSFUtil.getSessionValue("sucursal").toString());
        
            
            for (CchDetGastos nuevo : temp) {                
                if ((nuevo.getIdsucursal().getPlubodega()==plusuc) && (nuevo.getProcesado().equals("N")))
                {
                   
                    DetGastosDTO agregar = ConvertToDTO(nuevo);
     
                    retorno.add(agregar);
                    
                }                      
            }
        } catch (NoResultException nrex) {           
            logger.info("No se Encontraron Gastos:");
            retorno = null;
        }
        return retorno;
    }
    
    public List<DetGastosDTO> partidabyLiq(Integer Liq,String detalle) {                   
        
        List<DetGastosDTO> retorno = new ArrayList<DetGastosDTO>();
        try {
            List<CchDetGastos> temp = new ArrayList<CchDetGastos>();
            
            BigDecimal Saldo= new BigDecimal("0");
            
            //filtro todos los gastos de esta liquidacion
            //Query query = em.createNamedQuery("CchDetGastos.findAll").setHint("eclipselink.refresh", "true");
            //Query query = em.createQuery("select c from CchDetGastos c where c.procesado='N' and c.idliq=:idliq");//createNamedQuery("CchDetGastos.findAll").setHint("eclipselink.refresh", "true");
            //System.out.println("liq:"+Liq);
            Query query = em.createQuery("SELECT c FROM CchDetGastos c WHERE c.idliq = :idliq").setHint("eclipselink.refresh", "true");
            query.setParameter("idliq", Liq);
            
            temp = query.getResultList();
                                 
            int plusuc= 0;
            if (!(JSFUtil.getSessionValue("sucursal").toString().equals(null)))
            plusuc = Integer.parseInt(JSFUtil.getSessionValue("sucursal").toString());
        
            if (!(JSFUtil.getSessionValue("fondoCaja").toString().equals(null)))
            {
                //Saldo.add(JSFUtil.normalizeBigDecimal(JSFUtil.getSessionValue("fondoCaja").toString(),new BigDecimal("0")));
                Saldo = Saldo.add(new BigDecimal(JSFUtil.getSessionValue("fondoCaja").toString()));
                 
            }
            
            BigDecimal total= new BigDecimal("0");
            CchDetGastos temp2 = new CchDetGastos(); 
            DetGastosDTO agregar2 = null;
            
            for (CchDetGastos nuevo : temp) {   
                
                if (nuevo.getIdsucursal().getPlubodega()==plusuc) 
                {
                    
                    DetGastosDTO agregar = ConvertToDTO(nuevo);
                    Saldo = Saldo.subtract(agregar.getTotal());
                    total = total.add(agregar.getTotal());
                    agregar.setSaldo(Saldo);// prueba para calcular saldo pendiente
                    retorno.add(agregar);
                    if (nuevo.getIdtipodoc().getIva().equals("S"))
                    {
                        agregar2 = ConvertToDTOIVA(nuevo);
                        retorno.add(agregar2);
                    }
                    //Saldo = Saldo.subtract(agregar.getTotal());
                   // agregar.setSaldo(Saldo);// prueba para calcular saldo pendiente
                    
                    temp2 = nuevo;
                    
                    
                }                      
            }
            
            agregar2 = ConvertToDTOTOTAL(temp2,total);
            agregar2.setConcepto(detalle);
            retorno.add(agregar2);
            
            
        } catch (NoResultException nrex) {           
            logger.info("No se Encontraron Gastos:");
            retorno = null;
        }
        return retorno;
    }
    
    public List<DetGastosDTO> findbyLiq(Integer Liq) {                   
        
        List<DetGastosDTO> retorno = new ArrayList<DetGastosDTO>();
        try {
            List<CchDetGastos> temp = new ArrayList<CchDetGastos>();
            
            BigDecimal Saldo= new BigDecimal("0");
            
            //filtro todos los gastos de esta liquidacion
            //Query query = em.createNamedQuery("CchDetGastos.findAll").setHint("eclipselink.refresh", "true");
            //Query query = em.createQuery("select c from CchDetGastos c where c.procesado='N' and c.idliq=:idliq");//createNamedQuery("CchDetGastos.findAll").setHint("eclipselink.refresh", "true");
            //System.out.println("liq:"+Liq);
            Query query = em.createQuery("SELECT c FROM CchDetGastos c WHERE c.idliq = :idliq").setHint("eclipselink.refresh", "true");
            query.setParameter("idliq", Liq);
            
            temp = query.getResultList();
                                 
            int plusuc= 0;
            if (!(JSFUtil.getSessionValue("sucursal").toString().equals(null)))
            plusuc = Integer.parseInt(JSFUtil.getSessionValue("sucursal").toString());
        
            if (!(JSFUtil.getSessionValue("fondoCaja").toString().equals(null)))
            {
                //Saldo.add(JSFUtil.normalizeBigDecimal(JSFUtil.getSessionValue("fondoCaja").toString(),new BigDecimal("0")));
                Saldo = Saldo.add(new BigDecimal(JSFUtil.getSessionValue("fondoCaja").toString()));
                 
            }
            
            for (CchDetGastos nuevo : temp) {   
                
                if (nuevo.getIdsucursal().getPlubodega()==plusuc) 
                {
                    DetGastosDTO agregar = ConvertToDTO(nuevo);
                    Saldo = Saldo.subtract(agregar.getTotal());
                    agregar.setSaldo(Saldo);// prueba para calcular saldo pendiente
                    retorno.add(agregar);
                    
                }                      
            }
        } catch (NoResultException nrex) {           
            logger.info("No se Encontraron Gastos:");
            retorno = null;
        }
        return retorno;
    }
    
   

    @Override
    public DetGastosDTO create(DetGastosDTO inputs) {
        CchDetGastos agregar = ConvertToEntity(inputs);
        agregar = em.merge(agregar);
        em.flush();
        inputs = ConvertToDTO(agregar);
           
        return inputs;
    }

    @Override
    public DetGastosDTO edit(DetGastosDTO temp) {
        CchDetGastos edit = ConvertToEntity(temp);
        
        edit = em.merge(edit);
        em.flush();
        temp = ConvertToDTO(edit);
        return temp;
    }

    @Override
    public boolean delete(DetGastosDTO temp) {
        CchDetGastos delete = ConvertToEntity(temp);
        em.remove(em.merge(delete));
        em.flush();
        return true;
    }
    
    private CchDetGastos  ConvertToEntity(DetGastosDTO temp) {
        
        CchDetGastos  ret = new CchDetGastos();
       
        ret.setConcepto(temp.getConcepto());
        ret.setCuentacontable(temp.getCtaContable());
        ret.setFechaDoc(temp.getFechaDoc());
        ret.setFechaIng(temp.getFechaIng());
        ret.setFovial(temp.getFovial());
        ret.setGalSegDoc(temp.getGalonesSD());
        ret.setGravado(temp.getGravado());
        
        //canal
        CchCanal objCan= new CchCanal();
        objCan.setIdcanal(temp.getIdCanal());
        ret.setIdcanal(objCan);
        //idcanal
        ret.setIddetgasto(temp.getIddetgasto());
         // bodega
        Bodegas objBod = new Bodegas();
        objBod.setPlubodega(temp.getIdsucursal());
        ret.setIdsucursal(objBod);
        //ret.setIdgasto(null); idgasto
        CchCatalogo objGasto= new CchCatalogo();
        objGasto.setIdgcatalogo(temp.getIdGasto());
        ret.setIdgasto(objGasto);
        //ret.setIdtipodoc(null); tipodoc
        Tipodoc objTipo= new Tipodoc();
        objTipo.setIdtipodoc(temp.getIdtipoDoc());
        ret.setIdtipodoc(objTipo);
        //usuarios
        Usuarios objUser= new Usuarios();
        objUser.setIdusuario(temp.getIdusuario());
        
        ret.setIdusuario(objUser);
        
        //ret.setCuentacontable(detCatalogo.BuscaCuentaContable(temp.getIdCanal(), temp.getIdsucursal(), temp.getIdGasto()));
        //ret.setCuentacontable("11072000");
        
        //detCatalogo.BuscaCuentaContable(temp.getIdCanal(), temp.getIdsucursal(), temp.getIdGasto()));
        
        ret.setIva(temp.getIva());
        ret.setKmActual(temp.getKm_actual());
        ret.setNoPlaca(temp.getPlaca());
        ret.setNumDoc(temp.getNumDoc());
        ret.setProcesado(temp.getProcesado());
        ret.setProvNombre(temp.getProvNombre());
        ret.setProvRegistro(temp.getProvRegistro());
        ret.setTotal(temp.getTotal());
        
        return ret;
    }

    
    private DetGastosDTO ConvertToDTO(CchDetGastos temp) {
        
        DetGastosDTO ret = new DetGastosDTO();          
        
        ret.setConcepto(temp.getConcepto());
        ret.setCtaContable(temp.getCuentacontable());
        ret.setFechaDoc(temp.getFechaDoc());
        ret.setFechaIng(temp.getFechaIng());
        ret.setFovial(temp.getFovial());
        ret.setGalonesSD(temp.getGalSegDoc());
        ret.setGravado(temp.getGravado());
 
        ret.setIddetgasto(temp.getIddetgasto());
        //canal
        ret.setIdCanal(temp.getIdcanal().getIdcanal());
        //idcanal
        ret.setIddetgasto(temp.getIddetgasto());
         // bodega        
        ret.setIdsucursal(temp.getIdsucursal().getPlubodega());
        ret.setSucursal(temp.getIdsucursal().getNombre());
        //ret.setIdgasto(null); idgasto        
        ret.setIdGasto(temp.getIdgasto().getIdgcatalogo());
        //ret.setIdtipodoc(null); tipodoc        
        ret.setIdtipoDoc(temp.getIdtipodoc().getIdtipodoc());
        //usuarios
        ret.setIdusuario(temp.getIdusuario().getIdusuario());
        
        //System.out.println("iddetg:"+ret.getIddetgasto());
        ret.setIva(temp.getIva());
        ret.setKm_actual(temp.getKmActual());
        ret.setPlaca(temp.getNoPlaca());
        ret.setNumDoc(temp.getNumDoc());
        ret.setProcesado(temp.getProcesado());
        ret.setProvNombre(temp.getProvNombre());
        ret.setProvRegistro(temp.getProvRegistro());
        ret.setTotal(temp.getTotal());
        ret.setTipodoc(temp.getIdtipodoc().getDescripcion());
        
       
        ret.setFondoCaja(temp.getIdsucursal().getCajaChica());
        
        Cuentas obj= cuentasFacade.BuscaCtaContable(temp.getCuentacontable());
        
        if (obj!=null)
        {
          ret.setTipoSaldo(obj.getTiposaldo());
          
         // System.out.println(obj.getTiposaldo()+"-"+obj.getCodigo());
          
         /* if (obj.getTiposaldo().equals("D"))
          {*/
           // if (temp.getIdtipodoc().getIva().equals("S"))  
            
           
            if (temp.getFovial()!=null)
                ret.setDebe(temp.getGravado().add(temp.getFovial()));
            else
                ret.setDebe(temp.getGravado());
                
                
            
          /*  else
            {
                 ret.setDebe(temp.getTotal());
                if (temp.getGravado()!=new BigDecimal("0")) 
                   ret.setDebe(temp.getGravado().divide(new BigDecimal("1.13"),2,RoundingMode.FLOOR));
                 else      
                   ret.setDebe(new BigDecimal("0"));
            }*/
            ret.setHaber(new BigDecimal("0"));
            
         /* }
          else
          {
                  ret.setDebe(new BigDecimal("0"));
                  
                  if (temp.getIdtipodoc().getIva().equals("S")) 
                  {
                       ret.setHaber(temp.getGravado());
                 
                  }
                  else
                  {
                               
                    if (temp.getGravado()!=new BigDecimal("0")) 
                       ret.setHaber(temp.getGravado().divide(new BigDecimal("1.13"),2,RoundingMode.FLOOR));
                       else      
                       ret.setHaber(new BigDecimal("0"));
                  
                  }
                  
                  
          }*/
          
         
        }
        else
        {
                  ret.setDebe(new BigDecimal("0"));
                  ret.setHaber(new BigDecimal("0"));
        }
        
        
        return ret;
    }


     private DetGastosDTO ConvertToDTOIVA(CchDetGastos temp) {
        
        DetGastosDTO ret = new DetGastosDTO();          
        
        ret.setConcepto("");
        ret.setCtaContable(Constans.CUENTA_CREDITO_FISCAL);
        ret.setFechaDoc(temp.getFechaDoc());
        ret.setFechaIng(temp.getFechaIng());
        ret.setFovial(temp.getFovial());
        ret.setGalonesSD(temp.getGalSegDoc());
        ret.setGravado(temp.getGravado());
 
        ret.setIddetgasto(temp.getIddetgasto());
        //canal
        ret.setIdCanal(temp.getIdcanal().getIdcanal());
        //idcanal
        ret.setIddetgasto(temp.getIddetgasto());
         // bodega        
        ret.setIdsucursal(temp.getIdsucursal().getPlubodega());
        ret.setSucursal(temp.getIdsucursal().getNombre());
        //ret.setIdgasto(null); idgasto        
        ret.setIdGasto(temp.getIdgasto().getIdgcatalogo());
        //ret.setIdtipodoc(null); tipodoc        
        ret.setIdtipoDoc(temp.getIdtipodoc().getIdtipodoc());
        //usuarios
        ret.setIdusuario(temp.getIdusuario().getIdusuario());
        
        //System.out.println("iddetg:"+ret.getIddetgasto());
        ret.setIva(temp.getIva());
        ret.setKm_actual(temp.getKmActual());
        ret.setPlaca(temp.getNoPlaca());
        ret.setNumDoc(temp.getNumDoc());
        ret.setProcesado(temp.getProcesado());
        ret.setProvNombre("");
        ret.setProvRegistro(temp.getProvRegistro());
        ret.setTotal(temp.getTotal());
        ret.setTipodoc(temp.getIdtipodoc().getDescripcion());
        
       
        ret.setFondoCaja(temp.getIdsucursal().getCajaChica());
        
        Cuentas obj= cuentasFacade.BuscaCtaContable(Constans.CUENTA_CREDITO_FISCAL);
        
        if (obj!=null)
        {
            ret.setTipoSaldo(obj.getTiposaldo());

         /* if (obj.getTiposaldo().equals("D"))
          {*/
            ret.setHaber(new BigDecimal("0"));
            if (temp.getIdtipodoc().getIva().equals("S"))  
            ret.setDebe(temp.getIva());
            else
            {
                
                if (temp.getGravado()!=new BigDecimal("0")) 
                {
                   ret.setDebe(temp.getGravado().divide(new BigDecimal("1.13"),2,RoundingMode.FLOOR));
                   ret.setDebe(ret.getDebe().multiply(new BigDecimal("0.13")));
                }
                 else      
                   ret.setDebe(new BigDecimal("0"));
            
            }
            //ret.setHaber(new BigDecimal("0"));
            
        /*  }
          else
          {
                  ret.setDebe(new BigDecimal("0"));
                  
                  if (temp.getIdtipodoc().getIva().equals("S")) 
                  {
                       ret.setHaber(temp.getIva());
                 
                  }
                  else
                  {
                               
                    if (temp.getGravado()!=new BigDecimal("0"))
                    {
                       ret.setHaber(temp.getGravado().divide(new BigDecimal("1.13"),2,RoundingMode.FLOOR));
                       ret.setHaber(ret.getHaber().multiply(new BigDecimal("0.13")));
                    }
                       else      
                       ret.setHaber(new BigDecimal("0"));
                  
                  }
                  
                  
          }
          
         */
        }
        else
        {
                  ret.setDebe(new BigDecimal("0"));
                  ret.setHaber(new BigDecimal("0"));
        }
        
        
        return ret;
    }

     
     private DetGastosDTO ConvertToDTOTOTAL(CchDetGastos temp, BigDecimal tot) {
        
        DetGastosDTO ret = new DetGastosDTO();          
        
        ret.setConcepto("");
        ret.setCtaContable(temp.getIdsucursal().getCtacontable());
        ret.setFechaDoc(temp.getFechaDoc());
        ret.setFechaIng(temp.getFechaIng());
        ret.setFovial(temp.getFovial());
        ret.setGalonesSD(temp.getGalSegDoc());
        ret.setGravado(temp.getGravado());
 
        ret.setIddetgasto(temp.getIddetgasto());
        //canal
        ret.setIdCanal(temp.getIdcanal().getIdcanal());
        //idcanal
        ret.setIddetgasto(temp.getIddetgasto());
         // bodega        
        ret.setIdsucursal(temp.getIdsucursal().getPlubodega());
        ret.setSucursal(temp.getIdsucursal().getNombre());
        //ret.setIdgasto(null); idgasto        
        ret.setIdGasto(temp.getIdgasto().getIdgcatalogo());
        //ret.setIdtipodoc(null); tipodoc        
   
        //usuarios
        ret.setIdusuario(temp.getIdusuario().getIdusuario());
        
        //System.out.println("iddetg:"+ret.getIddetgasto());
        ret.setIva(temp.getIva());
        ret.setKm_actual(temp.getKmActual());
        ret.setPlaca(temp.getNoPlaca());
        ret.setNumDoc("");
        ret.setProcesado(temp.getProcesado());
        ret.setProvNombre("");
        ret.setProvRegistro(temp.getProvRegistro());
        ret.setTotal(temp.getTotal());
        ret.setTipodoc("");
        
       
        ret.setFondoCaja(temp.getIdsucursal().getCajaChica());
        
        Cuentas obj= cuentasFacade.BuscaCtaContable(temp.getIdsucursal().getCtacontable());
        
        if (obj!=null)
        {
            ret.setDebe(new BigDecimal("0"));
            ret.setHaber(new BigDecimal("0"));
                  
          ret.setTipoSaldo(obj.getTiposaldo());

          /*if (obj.getTiposaldo().equals("D")) 
            ret.setDebe(tot);
          else*/
            ret.setHaber(tot);
        }
        
        
        return ret;
    }
     
     
     public String createLiq(String user,String suc)
    {
    
        String retorno=null;
        try
        {
                      
            
           Query  query = em.createNativeQuery(" EXECUTE PROCEDURE INS_LIQ_CAJA_CHICA('"+suc+"','"+user+"' ) ");  
        
            List<Object> lstResult = (List<Object>) query.getResultList();
            if (lstResult != null && !lstResult.isEmpty()) {
                retorno = lstResult.get(0).toString();
                logger.info("Liquidacion :"+retorno);
            } else {
               retorno = null;                
               logger.info("no se genero ninguna liq");
            }
                   
            em.flush();
            
        
        
            } catch (NoResultException nrex) {
           
            logger.info("Error Al general Liquidacion:");
            retorno = null;
        }   
        return retorno;

    }


}
