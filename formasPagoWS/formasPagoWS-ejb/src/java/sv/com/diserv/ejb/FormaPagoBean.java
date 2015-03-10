/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.diserv.ejb;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import sv.com.diserv.dto.FormaPagoDTO;
import sv.com.diserv.dto.respuestaDTO;
import sv.com.diserv.entity.Remesas;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author abraham.acosta
 */
@Stateless
public class FormaPagoBean implements FormaPagoBeanLocal {

    static final Logger logger = Logger.getLogger(FormaPagoBean.class.getName());
    @PersistenceContext(unitName = "formasPagoWS-ejbPU")
    
    private EntityManager em;
    
    @Override
    public respuestaDTO insertFormaPago(FormaPagoDTO params) {
        
        respuestaDTO respuesta= new respuestaDTO();
        
        if (!params.getCodigo().isEmpty())            
        {
                 if (params.getIdliq()!=0)            
                {
                    if (params.getFechaLiq()!=null)            
                    {

                           Remesas remesa= new Remesas();
                           remesa.setCodliq(params.getCodliq());
                           remesa.setCodpago(params.getCodigo());
                           remesa.setDescripcion(params.getDescripcion());
                           remesa.setFecha(params.getFecha());
                           remesa.setFechaliq(params.getFechaLiq());
                           remesa.setIdliq(params.getIdliq());
                           remesa.setIdsucursal(params.getIdsucursal());
                           remesa.setIdvendedor(params.getIdvendedor());
                           remesa.setMonto(new BigDecimal(params.getMonto()));
                           remesa.setObservacion(params.getObservacion());
                           remesa.setSucursal(params.getSucursal());
                           remesa.setTipo(params.getTipo());
                           remesa.setUsuario(params.getUsuario());
                           remesa.setVendedor(params.getVendedor());
                           
                           remesa = guardarFormaPago(remesa);
                           if (remesa==null)
                           {
                            respuesta.setCodigo(99);
                            respuesta.setMensaje("error al guardar registro");
                           }
                           else
                           {
                              respuesta.setCodigo(100);
                              respuesta.setMensaje("registro guardado con exito");
                           }

                    }
                    else
                    {
                        respuesta.setCodigo(99);
                        respuesta.setMensaje("fecha nula");

                    }               

                }
                else
                {
                    respuesta.setCodigo(99);
                    respuesta.setMensaje("id liquidacion nulo");

                }                                           
        }
        else
        {
            respuesta.setCodigo(99);
            respuesta.setMensaje("codigo de pago nulo");
           
        }
        
        return respuesta;
    }

    public Remesas guardarFormaPago(Remesas remesa){
        Remesas response= null;
        try {
            
            response= em.merge(remesa);
            
        } catch (Exception e) {
            e.printStackTrace();
          
        }
        return response;
    }
}
