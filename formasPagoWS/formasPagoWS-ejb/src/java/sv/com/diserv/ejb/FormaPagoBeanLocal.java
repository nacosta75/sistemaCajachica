/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.diserv.ejb;

import javax.ejb.Local;
import sv.com.diserv.dto.FormaPagoDTO;
import sv.com.diserv.dto.respuestaDTO;


/**
 *
 * @author usuario01
 */
@Local
public interface FormaPagoBeanLocal {

    public respuestaDTO insertFormaPago(FormaPagoDTO params);
    
}
