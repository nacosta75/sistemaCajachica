/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import entidades.Bodegas;
import entidades.CchCanal;
import entidades.CchCatalogo;
import entidades.CchDetCatalogo;
import facade.BodegasFacade;
import facade.CchCanalFacade;
import facade.DetCatalogoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trompudo
 */
//@Named
//@SessionScoped
public class DetCatalogoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private DetCatalogoFacade detCatalogoFacade;
    
    @EJB
    private BodegasFacade bodegasFacade;
     
    @EJB
    private CchCanalFacade canalFacade;

  
     
    private CchDetCatalogo currentDetCatalogo;
    private List<CchDetCatalogo> listDetCatalogo;
    private int id,estado,idCatalogo,idcanal,idsucursal;
    private String cuentacontab;

    public CchDetCatalogo getCurrentDetCatalogo() {
        return currentDetCatalogo;
    }

    public void setCurrentDetCatalogo(CchDetCatalogo currentDetCatalogo) {
        this.currentDetCatalogo = currentDetCatalogo;
    }
   
   
    
    public List<CchDetCatalogo> getListDetCatalogo() {
        
        int plusuc= 0;
        int idprod=0;
      
        //System.out.println(e.getComponent().getAttributes().get("plu").toString());
        HttpServletRequest  request = (HttpServletRequest)  FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
      
        if (session.getAttribute("sucursal").toString()!=null)
        plusuc = Integer.parseInt(session.getAttribute("sucursal").toString());
       
        if (session.getAttribute("catalogo").toString()!=null)
        idprod = Integer.parseInt(session.getAttribute("catalogo").toString());
       

   
        
       
       /* if (listDetCatalogo==null)
        {*/
            listDetCatalogo = detCatalogoFacade.BuscaDetCatalogoTodos(idprod,plusuc);
           // listDetCatalogo = detCatalogoFacade.BuscaDetCatalogoTodos(0,0);
        //}
        
        return listDetCatalogo;
    }
   /* 
    public void LlistDetCatalogo(ActionEvent e)
    {
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();   
        
       listDetCatalogo = detCatalogoFacade.BuscaDetCatalogoTodos();
    }*/

    public void setListDetCatalogo(List<CchDetCatalogo> listDetCatalogo) {
        this.listDetCatalogo = listDetCatalogo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public int getIdcanal() {
        return idcanal;
    }

    public void setIdcanal(int idcanal) {
        this.idcanal = idcanal;
    }

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getCuentacontab() {
        return cuentacontab;
    }

    public void setCuentacontab(String cuentacontab) {
        this.cuentacontab = cuentacontab;
    }
   
    
    public DetCatalogoBean() {
        
        
    }
    
    public List<CchDetCatalogo> verDetCatalogo(ActionEvent e) {
        
        String plu= null;
        int plusuc= 0;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        HttpServletRequest  request = (HttpServletRequest)  FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
      
        plusuc = Integer.parseInt(session.getAttribute("sucursal").toString());
       

        listDetCatalogo = detCatalogoFacade.BuscaDetCatalogoTodos(Integer.parseInt(plu),plusuc);
        
        return listDetCatalogo;
    }
    
    
    
    public void buscarCatalogo(ActionEvent e)
    {
        CchDetCatalogo obj = new CchDetCatalogo();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
       
        //this.currentDetCatalogo.setId(Integer.parseInt(plu));
         
        obj.setId(Integer.parseInt(plu));
        
        obj = detCatalogoFacade.BuscaCatalogoDet(obj);
       
        //this.currentDetCatalogo = obj;
         
         
        System.out.println("Buscando Catalogo Det:"+plu);
        if (obj!=null)
        //if (this.currentDetCatalogo!=null)
        {
            setId(Integer.parseInt(plu));    
            setEstado(1);
            setCuentacontab(obj.getCtaContab());
            setIdCatalogo(obj.getIdgcatalogo().getIdgcatalogo());
            setIdsucursal(obj.getIdsucursal().getPlubodega());
            setIdcanal(obj.getIdcanal().getIdcanal());
              
             System.out.println("cuandoBusca:"+getIdsucursal()); 
          
        }
    }

     public void grabar()
    {    
        
 
        CchDetCatalogo obj = new CchDetCatalogo();
 
        /* System.out.println("id:"+getId()); 
        System.out.println("idsuc:"+getIdsucursal()); 
        System.out.println("idcat:"+getIdCatalogo()); 
        */
        //catalogo
        CchCatalogo objCat= new CchCatalogo();
        objCat.setIdgcatalogo(getIdCatalogo());
        obj.setIdgcatalogo(objCat);
       
        // bodega
        Bodegas objBod = new Bodegas();
        objBod.setPlubodega(getIdsucursal());
        obj.setIdsucursal(objBod);
        
        //canal
        CchCanal objCan= new CchCanal();
        objCan.setIdcanal(idcanal);
        obj.setIdcanal(objCan);
        
        obj.setCtaContab(getCuentacontab());
        obj.setId(getId());
        
        System.out.println("ESTADO:"+getEstado());
                
        if (!(obj.getId()==null))
        {
           detCatalogoFacade.ModificarDetCatalogo(obj);
           System.out.println("modificando el catalogo:"+getCuentacontab());
        }
         else
        {
          detCatalogoFacade.GuardarDetCatalogo(obj);
          System.out.println("Inserto Nuevo catalogo:"+getCuentacontab());
        }
        
    
                       
    }
     
      public void eliminarCatalogo(ActionEvent e)
    {
        CchDetCatalogo obj = new CchDetCatalogo();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();   
        obj.setId(Integer.parseInt(plu));
        
        detCatalogoFacade.EliminarDetCatalogo(obj);
        
    
    }
      
    public void cargarDetalle()
    {
     
        HttpServletRequest  request = (HttpServletRequest)  FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        
        int plu= 0;
        //plu = Integer.parseInt(e.getComponent().getAttributes().get("plu").toString());   
        plu = Integer.parseInt(session.getAttribute("catalogo").toString());   
        int plusuc= 0;
        plusuc = Integer.parseInt(session.getAttribute("sucursal").toString());

        
        if (plu>0)
        {
                setIdCatalogo(plu);
                //System.out.println("antes del for producto:"+plu);
                //CchCanal objC= canalFacade.BuscaCanalTodos();
                
                for (CchCanal objC:canalFacade.BuscaCanalTodos())
                {   
                      setIdcanal(objC.getIdcanal());              
                      setIdsucursal(plusuc);
                      if (!(detCatalogoFacade.BuscaDetCatalogoDetalle(objC.getIdcanal(),plusuc,plu))) //canal,sucursal,catg
                      {        
                           grabar();
                           System.out.println("Insertando: cat"+getIdCatalogo()+", Canal:"+getIdcanal()+", Bodega:"+getIdsucursal());
                      }
                      else
                      {
                           System.out.println("no se Insertando: cat"+getIdCatalogo()+", Canal:"+getIdcanal()+", Bodega:"+getIdsucursal());
                      }
                              
                      
                  
                }
                //System.out.println("al salir:"+plu);
                
         }
    
     }
    
}
