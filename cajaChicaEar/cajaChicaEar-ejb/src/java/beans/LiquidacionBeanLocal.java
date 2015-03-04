/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dto.LiquidacionDTO;
import entidades.CchLiquidacion;
import java.util.List;

/**
 *
 * @author trompudo
 */
public interface LiquidacionBeanLocal {
    
    public List<LiquidacionDTO> findAll();

    public LiquidacionDTO create(LiquidacionDTO inputs);

    public LiquidacionDTO edit(LiquidacionDTO temp);

    public boolean delete(LiquidacionDTO temp);
    
    public CchLiquidacion BuscarLiquid(Integer liq);
    
}
