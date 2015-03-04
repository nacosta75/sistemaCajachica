/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Empresas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author trompudo
 */
@Stateless
public class EmpresasFacade extends AbstractFacade<Empresas> {
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresasFacade() {
        super(Empresas.class);
    }
    
     public Empresas BuscarEmpresa(Empresas empObj){
    
        // find efectua busqueda por llave primaria : plucliente 
       Empresas obj=null;
        try
        {
            obj = em.find(Empresas.class, empObj.getPluempresa());
           
        }
        catch (Exception e)
        {
          System.out.println("Error al buscar empresa:"+e); 
         //logger.error("Error al buscar tipoDoc",e); 
        }
        
        return obj;
           
    }
    
}
