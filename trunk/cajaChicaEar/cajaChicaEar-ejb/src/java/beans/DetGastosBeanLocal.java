/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dto.DetGastosDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noe Acosta
 */
public interface DetGastosBeanLocal {
    
    public List<DetGastosDTO> findAll();

    public DetGastosDTO create(DetGastosDTO inputs);

    public DetGastosDTO edit(DetGastosDTO temp);

    public boolean delete(DetGastosDTO temp);
    
    public String createLiq(String user,String suc);

    public List<DetGastosDTO> findbyLiq(Integer Liq);
    
    public List<DetGastosDTO> partidabyLiq(Integer Liq,String detalle);
   
}
