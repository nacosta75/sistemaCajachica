/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peticion;

import Tools.JSFUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author trompudo
 */
public class CanalesController {
    
    
    private String critSrch;
    private List<SelectItem> critSrchLst = null;

    public String getCritSrch() {
        return critSrch;
    }

    public void setCritSrch(String critSrch) {
        this.critSrch = critSrch;
    }

    public List<SelectItem> getCritSrchLst() {
        return critSrchLst;
    }

    public void setCritSrchLst(List<SelectItem> critSrchLst) {
        this.critSrchLst = critSrchLst;
    }

    public CanalesController() {
        
        JSFUtil util = new JSFUtil();
        String usuarioLogeado = util.getRequestParameter("usuario").toUpperCase();
        if (usuarioLogeado != null) {
            usuarioLogeado = usuarioLogeado.toUpperCase();
        }
        critSrch = usuarioLogeado;
        critSrchLst = new ArrayList<SelectItem>();
        System.out.println("* usuario:[" + usuarioLogeado + "]");
        util.setSessionAttribute("usuario", usuarioLogeado);
        try {           
            Beans.CanalBean mb = (Beans.CanalBean)JSFUtil.getSessionAttributeValue("Canales");
            if(mb!=null){
                mb.reiniciarFomulario();
                JSFUtil.setSessionAttribute("columnsBean", mb);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("/app/ListCanales.xhtml");
        } catch (IOException ex) {
//            log.error( ex.getMessage(), ex);
        }
        
        
    }
    
    
    
    
}
