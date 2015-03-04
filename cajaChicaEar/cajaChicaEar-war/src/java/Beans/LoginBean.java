/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Tools.JSFUtil;
import entidades.Bodegas;
import entidades.Usuarios;
import facade.BodegasFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


/**
 *
 * @author trompudo
 */
//@ManagedBean(name = "catalogoController")
//@SessionScoped
public class LoginBean implements Serializable {
      private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(LoginBean.class);
     
         
     @EJB
    private BodegasFacade bodegasFacade;
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
     private boolean login=false;
     private boolean admin=false;
     private boolean general; // todas las sucursales true o false
 
    private Usuarios CurrentUser;
    private int idsucursal,idusuario,nivel;
    private String sucursal,usuario,nombre,clave;
    private BigDecimal montoCaja;

    public boolean isGeneral() {
        return general;
    }

    public void setGeneral(boolean general) {
        this.general = general;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
 
    
    

 
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }


    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    
    private List<Usuarios> listUsuarios;

    public List<Usuarios> getListUsuarios() {
       
        listUsuarios = usuarioFacade.BuscaUsuariosTodos();
        
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuarios> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }
    
    

    public BigDecimal getMontoCaja() {
        return montoCaja;
    }

    public void setMontoCaja(BigDecimal montoCaja) {
        this.montoCaja = montoCaja;
    }
    
    
    

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
    
    

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }
    

    public Usuarios getCurrentUser() {
       if (CurrentUser==null)
        {
           CurrentUser= new Usuarios();    
        }       
        return CurrentUser;
    }

    public void setCurrentUser(Usuarios CurrentUser) {
        this.CurrentUser = CurrentUser;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
 
    public LoginBean() {
              JSFUtil.setSessionValue("usuario","");
              JSFUtil.setSessionValue("sucursal","");
    }
    
    public String ValidarUsuario()
    {
        String outcome= "index";
        String clave =getCurrentUser().getClave();
            
        try
        {
            //Crypt32 cripto= new Crypt32();
               
            //clave = cripto.Encrypt(getCurrentUser().getClave(), 981, 12674, 35891);
           //  System.out.println(login);
             clave = getCurrentUser().getClave();
            
            Usuarios l= usuarioFacade.ValidarUsuarios(getCurrentUser().getUsuario().toUpperCase(), clave);
            
            if (l!=null)
            {
             
                        if ((l.getIdbodega()== getIdsucursal()) || (l.getGeneral().equals("S")))
                        {

                         Bodegas objSuc= new Bodegas();
                         objSuc.setPlubodega(getIdsucursal());
                         objSuc = bodegasFacade.BuscaBodega(objSuc);

                          setLogin(true);
                          setSucursal(objSuc.getNombre());
                          setMontoCaja(objSuc.getCajaChica());
                          if (l.getNivel()==1)
                          {    
                            setAdmin(true);
                          }
                          
                          logger.info("Login :  true :"+clave);
                          JSFUtil.setSessionValue("Login",true);
                          JSFUtil.setSessionValue("usuario",l.getIdusuario());
                          JSFUtil.setSessionValue("sucursal",getIdsucursal());
                          JSFUtil.setSessionValue("fondoCaja",objSuc.getCajaChica());

                          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", l.getIdusuario());
                          outcome= "principal?faces-redirect=true";
                        }
                        else
                        {
                           setLogin(false);                
                           FacesContext.getCurrentInstance().addMessage("Error al loguearse!", new FacesMessage("Sucursal no Autorizada!!"));
                        }
            }
            else
            {
               setLogin(false);                
               FacesContext.getCurrentInstance().addMessage("Error al loguearse!", new FacesMessage("Clave Invalida!!"));
               FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            }
            
        }
        catch (Exception e)
        {
            logger.error("Error al validar el usuario "+clave+":",e);
            System.out.println("Error al validar el usuario "+clave+":"+e);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        
        
        return outcome;
      
    }
    
    
       public void buscarUsuario(ActionEvent e)
    {
        Usuarios obj = new Usuarios();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        
        obj = usuarioFacade.BuscaUsuario(Integer.parseInt(plu));
  
        System.out.println("Buscando usuario:"+plu);
        logger.info("Buscanco usuario:"+plu);
        
        if (obj!=null)
        {
            setIdusuario(obj.getIdusuario());
            setNombre(obj.getNombre());
            setUsuario(obj.getUsuario());
            setClave(obj.getClave());
            
            if (obj.getGeneral().equals("S"))
            {        
              setGeneral(true);
            }
            else
            {
              setGeneral(false);  
            }
            
            if (obj.getNivel()!=null)
            {
                setNivel(obj.getNivel());
            }
            else
            {
                setNivel(2);
            }
            
            if (obj.getIdbodega()!=null)
            {
                 setIdsucursal(obj.getIdbodega());
            }
            else
            {
                setIdsucursal(0);
            }
           
         
            System.out.println("Encontro CODIGO:"+plu);
            
        }
        else
        {
            setIdusuario(0);
            setNombre("");
            setUsuario("");
            
            System.out.println("No existe Usuario:"+plu);
            logger.info("No existe Usuario:"+plu);
         }
    
    }
   
    
       public void logout() {
        
        setLogin(false);
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        //logger.log(Level.INFO, "User ({0}) loging out #" + DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
        if (session != null) {
            session.invalidate();
        }
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml?faces-redirect=true");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
  
    public void grabar()
    {    
               
       
        Usuarios obj = new Usuarios();
        obj.setActivo("S");
        obj.setNombre(getNombre());
        obj.setUsuario(getUsuario());
        obj.setClave(getClave());
        obj.setNivel(getNivel());
        obj.setIdbodega(getIdsucursal());
        obj.setIdusuario(getIdusuario());
        
        if (isGeneral())
        {
         obj.setGeneral("S");
        }
        else
        {
          obj.setGeneral("N");
        }
        
        
                
        if (!(obj.getIdusuario()==null))
        {
           //modificar 
            usuarioFacade.ModificarUsuario(obj);
            System.out.println("modificando el Usuario:"+getNombre());
        }
         else
        {
          usuarioFacade.GuardarUsuario(obj);
          System.out.println("Inserto Nuevo USuario:"+getNombre());
        }   
        
        listUsuarios = usuarioFacade.BuscaUsuariosTodos();
   
         
    }
   
    
}
