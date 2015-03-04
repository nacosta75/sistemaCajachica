package Beans;

import entidades.CchCanal;
import entidades.Empresas;
import facade.CchCanalFacade;
import facade.EmpresasFacade;
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
public class CanalBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EJB 
     private EmpresasFacade empresaFacade;
    @EJB
    private CchCanalFacade canalFacade;
    private CchCanal currentCanal;
    private List<CchCanal> listCanal;
    private int estado,idcanal,idempresa;
    private String nombre;
    private ArrayList<SelectItem> canalLista;

    public ArrayList<SelectItem> getCanalLista() {
        
        canalLista = new ArrayList<SelectItem>();
        
        for (CchCanal obj:canalFacade.BuscaCanalTodos() )
        {
          canalLista.add(new SelectItem(obj.getIdcanal(),obj.getNombreCanal()));
        }
        
        return canalLista;
    }

    public void setCanalLista(ArrayList<SelectItem> canalLista) {
        this.canalLista = canalLista;
    }

    
    
    

    public List<CchCanal> getListCanal() {
        
        if (listCanal==null)
        {
            listCanal = canalFacade.BuscaCanalTodos();
        }
        
        return listCanal;
    }

    public void setListCanal(List<CchCanal> listCanal) {
        this.listCanal = listCanal;
    }
    

    public CchCanal getCurrentCanal() {
        
        if (currentCanal == null)
        {
            currentCanal = new CchCanal();
        }
        return currentCanal;
    }

    public void setCurrentCanal(CchCanal currentCanal) {
        this.currentCanal = currentCanal;
    }
   
    public CanalBean() {
    }
    
    public void buscarCanal(ActionEvent e)
    {
        CchCanal obj = new CchCanal();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        obj.setIdcanal(Integer.parseInt(plu));
        obj = canalFacade.BuscaCanal(obj);
  
        System.out.println("Buscando Canal:"+plu);
        
        if (obj!=null)
        {
        setIdcanal(Integer.parseInt(plu));
        setNombre(obj.getNombreCanal());
        setIdempresa(1);
        
         System.out.println("Encontro idCanal:"+getIdcanal());
        }
        else
        {
          setIdcanal(0);
          setNombre("");
         
         }
    
    }

     public void buscarCanal2(ActionEvent e)
    {
        CchCanal obj = new CchCanal();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        obj.setIdcanal(Integer.parseInt(plu));
        currentCanal = canalFacade.BuscaCanal(obj);
  
        System.out.println("Buscando Canal:"+plu);
        
        if (obj!=null)
        {
        /*setIdcanal(Integer.parseInt(plu));
        setNombre(obj.getNombreCanal());
        setIdempresa(1);*/
        
         System.out.println("Encontro idCanal:"+getIdcanal());
        }
        else
        {
          setIdcanal(0);
          setNombre("");
         
         }
    
    }

     public void grabar()
    {    
               
       
        CchCanal obj = new CchCanal();
        obj.setNombreCanal(getNombre());
        
        Empresas objemp= new Empresas();
        objemp.setPluempresa(1); 
        objemp = empresaFacade.BuscarEmpresa(objemp);
        
        
        obj.setIdempresa(objemp);
        obj.setIdcanal(getIdcanal());
        
        //System.out.println("ID:"+obj.getIdcanal());
                
        if (!(obj.getIdcanal()==null))
        {
           //obj.setPlucliente(getPlucliente()); 
           canalFacade.ModificarCanal(obj);
           System.out.println("modificando el canal:"+getNombre());
        }
         else
        {
          canalFacade.GuardarCanal(obj);
          System.out.println("Inserto Nuevo canal:"+getNombre());
        }   
        
     
        listCanal = canalFacade.BuscaCanalTodos();
   
         
    }
     
      public void eliminarCanal(ActionEvent e)
    {
        CchCanal obj = new CchCanal();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        obj.setIdcanal(Integer.parseInt(plu));
        canalFacade.EliminarCanal(obj);
        listCanal = canalFacade.BuscaCanalTodos();
    }
      
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdcanal() {
        return idcanal;
    }

    public void setIdcanal(int idcanal) {
        this.idcanal = idcanal;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void reiniciarFomulario() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
    
}