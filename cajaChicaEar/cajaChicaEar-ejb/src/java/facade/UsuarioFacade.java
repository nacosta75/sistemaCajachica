/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Usuarios;
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
public class UsuarioFacade extends AbstractFacade<Usuarios> {
    @PersistenceContext(unitName = "cajaChicaEar-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     private static final Logger logger = Logger.getLogger(UsuarioFacade.class);
      


    public UsuarioFacade() {
        super(Usuarios.class);
    }
    
    public Usuarios ValidarUsuarios(String login,String clave)
    {
        Usuarios user = null;
        
        try
        {
           // System.out.println(clave);
          Query query = getEntityManager().createQuery("SELECT c FROM Usuarios c WHERE c.usuario = :usuario and c.clave= :clave ");
          query.setParameter("usuario",login);
          query.setParameter("clave",clave);
          
          user = (Usuarios) query.getSingleResult();
          
          System.out.println(user.getNombre());
          
          if (user != null)
          {
            logger.info("Usuario encontrado:"+login);
             System.out.println("Usuario encontrado-:"+login);
          }
          
        }
        catch (Exception e)
        {
          logger.error("Error al validar el usuario "+login+":",e);
            System.out.println("Error al validar el usuario "+login+":"+e);
        }
                
                
         return user;
    }

    public List<Usuarios> BuscaUsuariosTodos()
    {
        Query q=null;   
        try
        {
           q = em.createQuery("SELECT b FROM Usuarios b"); 
           
           logger.info("Listando Usuarios :");
        }
        catch (Exception e)
        {
          logger.error("Error al Listar Usuarios ",e);  
        }
               
        return q.getResultList(); 
    }
    
    
    public Usuarios BuscaUsuario(Integer usuario){
       // find efectua busqueda por llave primaria : idcanal
       // System.out.print("idusuario:"+usuario.getIdusuario());
       Usuarios obj=null;
        try
        {
            
            obj = em.find(Usuarios.class, usuario); 
            System.out.print(obj.getNombre());
        }
        catch (Exception e)
        {
         logger.error("Error al buscar Usuario",e); 
        }
        return obj;  
    }

    public String ModificarUsuario(Usuarios obj) {
        
         String msg="";
        try
        {
          em.merge(obj);
          em.flush();
          msg="Se modifico el Usuario";
          logger.info("Se modifico el Usuario:"+obj.getUsuario());
        }
        catch (Exception e)
            {
                logger.error("Error al Modificar Usuario ",e); 
            }             
       return msg;
       
    }

    public String GuardarUsuario(Usuarios obj) {
        String msg="";
        try
        {
          em.persist(obj);
          em.flush();
          msg="Se Inserto el Usuario"+obj.getUsuario();
          logger.info("Se Inserto el Usuario:"+obj.getUsuario());
        }
        catch (Exception e)
            {
                logger.error("Error al Insertar Usuario ",e); 
            }             
       return msg;
    }
}
