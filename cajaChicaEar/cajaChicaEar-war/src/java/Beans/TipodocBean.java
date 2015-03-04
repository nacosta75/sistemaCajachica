/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import entidades.CchCatalogo;
import entidades.Empresas;
import entidades.Tipodoc;
import facade.EmpresasFacade;
import facade.TipodocFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author trompudo
 */
public class TipodocBean implements Serializable {
      private static final long serialVersionUID = 1L;
    
    // private LoggerUtil logger = new LoggerUtil(LoginBean.class);
     
     @EJB
     private TipodocFacade tipodocFacade;
     
     @EJB 
     private EmpresasFacade empresaFacade;
     
     private Tipodoc currectTipoDoc;
     private List<Tipodoc> tipodoc = null;
     private int idtipodoc,idempresa,estado;
     private String descripcion;
     private ArrayList<SelectItem> tipodocLista;

    public ArrayList<SelectItem> getTipodocLista() {
        
        tipodocLista= new ArrayList<SelectItem>();
        
        for (Tipodoc obj:tipodocFacade.BuscaTipoDocTodos() )
        {
          tipodocLista.add(new SelectItem(obj.getIdtipodoc(),obj.getDescripcion()));
        }
        return tipodocLista;
    }

    public void setTipodocLista(ArrayList<SelectItem> tipodocLista) {
        this.tipodocLista = tipodocLista;
    }
     
     
     

    public Tipodoc getCurrectTipoDoc() {
        
        if (currectTipoDoc == null)
        {
           currectTipoDoc = new Tipodoc();
   
        }
        return currectTipoDoc;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Tipodoc> getTipodoc() {
        
         if (tipodoc == null) {
            tipodoc = tipodocFacade.BuscaTipoDocTodos();
        }
        
        
        return tipodoc;
    }

    
    

    public void setCurrectTipoDoc(Tipodoc currectTipoDoc) {
        this.currectTipoDoc = currectTipoDoc;
    }

    public int getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(int idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    
    
    public TipodocBean() {
    }
    

    
    public void buscarTipodoc(ActionEvent e)
    {
        Tipodoc obj = new Tipodoc();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        obj.setIdtipodoc(Integer.parseInt(plu));
        obj = tipodocFacade.BuscaTipodoc(obj);
        //logger.warn("buscando tipodoc:"+plu); 
        
        System.out.println("Buscando tipodoc");
        
        if (obj!=null)
        {
        setEstado(1);
        setDescripcion(obj.getDescripcion());
        setIdempresa(1);
        }
    
    }
    
     public void grabar()
    {
        
       
        Tipodoc obj = new Tipodoc();
        obj.setDescripcion(getDescripcion());
        
        Empresas objemp= new Empresas();
        objemp.setPluempresa(1); 
        objemp = empresaFacade.BuscarEmpresa(objemp);
        
        obj.setIdempresa(objemp);
        
        System.out.println(getEstado());
                
    
        if (!(obj.getIdtipodoc()==null))
        {
           //obj.setPlucliente(getPlucliente()); 
           tipodocFacade.ModificarTipoDoc(obj);
           System.out.println("modificando el tipodoc");
        }
         else
        {
          tipodocFacade.GuardarTipoDoc(obj);
          System.out.println("Inserto Nuevo Tipodoc");
        }
        
        tipodoc = tipodocFacade.BuscaTipoDocTodos();
                       
    }
   
     public void eliminarTipodoc(ActionEvent e)
    {
        Tipodoc obj = new Tipodoc();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        obj.setIdtipodoc(Integer.parseInt(plu));
        tipodocFacade.EliminarTipodoc(obj);
        tipodoc = tipodocFacade.BuscaTipoDocTodos();
    }
}
