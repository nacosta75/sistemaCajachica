/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**/
 
public abstract class AbstractController {

   // private static org.apache.log4j.Logger logger = LoggerUtil.getAppLogger(AbstractController.class).getLog4jLogger();

    /**
     * @return FacesContext para la petición actual
     * @author José David Martínez Rico - Informática & Tecnología
     */
    protected FacesContext context() {
        return (FacesContext.getCurrentInstance());
    }

    /**
     * Adiciona un mensaje localizado a el FacesContext para la petición actual
     * @param clientId Identificador del componente que genera el mensaje o null para mensajes globales
     * @param key Clave del mensaje a adiccionar
     */
    protected void message(String clientId, String key) {
        // Look up the requested message text
        String text = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("sv.com.mensajes", context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        // Construct and add a FacesMessage containing it
        context().addMessage(clientId, new FacesMessage(text));
    }

    protected void message(FacesMessage.Severity severity, String clientId, String key) {
        // Look up the requested message text
        String text = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("sv.com.mensajes", context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = key;
        }

        // Construct and add a FacesMessage containing it
        context().addMessage(clientId, new FacesMessage(severity, text, text));
    }

    /**
     * Adiciona un mensaje localizado a el FacesContext para la petición actual
     * @param clientId Identificador del componente que genera el mensaje o null para mensajes globales
     * @param key Clave del mensaje a adiccionar
     * @param params parametros de sustitución para usar en el texto del mensaje
     */
    protected void message(String clientId, String key, Object[] params) {

        // Look up the requested message text
        String text = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("sv.com.millicom.gestorordenes.web.mensajes.mensajes", context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        // Perform the requested substitutions
        if ((params != null) && (params.length > 0)) {
            text = MessageFormat.format(text, params);
        }

        // Construct and add a FacesMessage containing it
        context().addMessage(clientId, new FacesMessage(text));
    }

    /**
     * Obtiene un mensaje del resourceBundle
     * @param key Clave del mensaje
     * @param params parametros de sustitución para usar en el texto del mensaje
     * @return resultado
     */
    protected String getMessage(String key, Object[] params) {

        // Look up the requested message text
        String text = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("sv.com.millicom.gestorordenes.web.mensajes.mensajes", context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        // Perform the requested substitutions
        if ((params != null) && (params.length > 0)) {
            text = MessageFormat.format(text, params);
        }

        // Construct and add a FacesMessage containing it
        return text;
    }

    /**
     * Obtiene un mensaje del resourceBundle
     * @param key Clave del mensaje
     * @param params parametros de sustitución para usar en el texto del mensaje
     * @return resultado
     */
    protected String getMessage(String key) {

        // Look up the requested message text
        String text = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("sv.com.millicom.gestorordenes.web.mensajes.mensajes", context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
           // logger.error(e.getMessage(), e);
            text = "???" + key + "???";
        }


        // Construct and add a FacesMessage containing it
        return text;
    }

    protected String getMessage(String key, String valor) {

        // Look up the requested message text
        String text = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("sv.com.millicom.gestorordenes.web.mensajes.mensajes", context().getViewRoot().getLocale());
            text = bundle.getString(key) + valor;
        } catch (Exception e) {
        //    logger.error(e.getMessage(), e);
            text = "???" + key + "???";
        }


        // Construct and add a FacesMessage containing it
        return text;
    }

    /**
     * Método para la obtención del usuario que realiza las operaciones
     * @return
     */
    public String getUser() {
        String usuario = "n/e";
        try {
            usuario = ((HttpServletRequest) this.context().getExternalContext().getRequest()).getUserPrincipal().getName();
        } catch (Exception e) {
           // logger.warn("No se econtro el usuario");
        }

        return usuario;
    }

    protected void message(String key) {
        // Look up the requested message text
        String text = null;

        try {
            text = key;
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        // Construct and add a FacesMessage containing it
        context().addMessage(null, new FacesMessage(text));
    }
    
    protected void message(FacesMessage key) {
        // Look up the requested message text
        String text = null;

        try {
            text = key.getSummary();
        } catch (Exception e) {
            text = "???" + key + "???";
            key.setSummary(text);
        }

        // Construct and add a FacesMessage containing it
        context().addMessage(null, key);
    }        
}
