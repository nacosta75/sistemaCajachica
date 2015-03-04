/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;


import java.math.BigDecimal;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trompudo
 */
public class JSFUtil {
    
   // private static org.apache.log4j.Logger logger = LoggerUtil.getAppLogger(JSFUtil.class).getLog4jLogger();

    protected FacesContext context() {
        return (FacesContext.getCurrentInstance());
    }
    
    
     public static void setSessionValue(String name,Object value)
    {
        HttpServletRequest  request = (HttpServletRequest)  FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        HttpSession session= request.getSession();
        
              
        session.setAttribute(name, value);
        
       
    }
     
     
     public static String getSessionValue(String name)
    {
        HttpServletRequest  request = (HttpServletRequest)  FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        HttpSession session= request.getSession();
        
        String resultado = "1";
        
        if (session.getAttribute(name)!=null)
            resultado=session.getAttribute(name).toString();
        
       return resultado;
       
    }
     
     
   
  
    public static void setSessionAttribute(String attribute, Object value){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute(attribute, value);
    }

    public static Object getSessionAttributeValue(String attribute){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return session.getAttribute(attribute);
    }

    public static Object getBean(String beanReference){
        ValueBinding vb = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{"+ beanReference + "}");
        return vb.getValue(FacesContext.getCurrentInstance());
    }

    public String getUser() {
        String usuario = "n/e";
        try {
            usuario = ((HttpServletRequest) this.context().getExternalContext().getRequest()).getUserPrincipal().getName();
        } catch (Exception e) {
            //logger.warn("No se econtro el usuario");
        }

        return usuario;
    }

    public static String getRequestParameter(String parameterName) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameterName);
    }

    public static String getRemoteUser(){
        //INICIALMENTE OBTIENE DE LA SESION EL ATRIBUTO usuario, CUANDO SE TENGA LA CREDENCIAL EN Principal,
        //SE CAMBIARA LA IMPLEMENTACION DEL METODO
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return (String)session.getAttribute("usuario");
    }
    
    
    public static BigDecimal normalizeBigDecimal(String s, BigDecimal defaultValue) 
    {
        if(s == null || s.trim().length() == 0)
        {
                if (defaultValue != null) {
                    return defaultValue;
                } else 
                {
                    return null;
                }
        }
        s = s.replace(',', '.');
        return new BigDecimal(new Double(s));
     }

}
    

