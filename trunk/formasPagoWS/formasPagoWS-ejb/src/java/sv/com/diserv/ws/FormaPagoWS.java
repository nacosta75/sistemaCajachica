/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.diserv.ws;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import sv.com.diserv.dto.FormaPagoDTO;
import sv.com.diserv.dto.respuestaDTO;
import sv.com.diserv.ejb.FormaPagoBeanLocal;

/**
 *
 * @author usuario01
 */
@WebService(serviceName = "formaPagoWS")
@Stateless()
public class FormaPagoWS {
    @EJB
    private FormaPagoBeanLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "insertFormaPago")
    public respuestaDTO insertFormaPago(@WebParam(name = "params") FormaPagoDTO params) {
        return ejbRef.insertFormaPago(params);
    }
    
    
    
}
