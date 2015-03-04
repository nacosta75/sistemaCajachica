/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import entidades.CchCatalogo;
import entidades.Empresas;
import facade.CatalogoFacade;
import facade.EmpresasFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import Tools.JSFUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.xml.sax.InputSource;

/**
 *
 * @author trompudo
 */

public class CatalogoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EJB 
     private EmpresasFacade empresaFacade;
    @EJB
    private CatalogoFacade catalogoFacade;
    private CchCatalogo currentCatalogo;
    private List<CchCatalogo> listCatalogo;
    private int estado,idcatalogo,idempresa;
    private String nombre;
    private boolean fovial;
    private ArrayList<SelectItem> catalogoLista;

    public ArrayList<SelectItem> getCatalogoLista() {
        
        catalogoLista = new ArrayList<SelectItem>();
        
        for (CchCatalogo obj:catalogoFacade.BuscaCatalogoTodos() )
        {
          catalogoLista.add(new SelectItem(obj.getIdgcatalogo(),obj.getNombreGasto()));
        }
        
        return catalogoLista;
    }

    public void setCatalogoLista(ArrayList<SelectItem> catalogoLista) {
        this.catalogoLista = catalogoLista;
    }
    
    

    public List<CchCatalogo> getListCatalogo() {
        
        if (listCatalogo == null)
        {
         listCatalogo = catalogoFacade.BuscaCatalogoTodos();
        }
        
        return listCatalogo;
    }

    public void setListCatalogo(List<CchCatalogo> listCatalogo) {
        this.listCatalogo = listCatalogo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdcatalogo() {
        return idcatalogo;
    }

    public void setIdcatalogo(int idcatalogo) {
        this.idcatalogo = idcatalogo;
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

    public boolean getFovial() {
        return fovial;
    }

    public void setFovial(boolean fovial) {
        this.fovial = fovial;
    }

    public CchCatalogo getCurrentCatalogo() {
        
        if (currentCatalogo == null)
        {
            currentCatalogo = new CchCatalogo();
        }
        return currentCatalogo;
        
    }

    public void setCurrentCaralogo(CchCatalogo currentCatalogo) {
        this.currentCatalogo = currentCatalogo;
    }
    
    
 
    public CatalogoBean() {
        
    }
    
      public void setSessionValue(String name,Object value)
    {
        HttpServletRequest  request = (HttpServletRequest)  FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        HttpSession session= request.getSession();
        
        session.setAttribute(name, value);
        
       
    }
    
     public void buscarCatalogo(ActionEvent e)
    {
        CchCatalogo obj = new CchCatalogo();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        Tools.JSFUtil.setSessionAttribute("catalogo",plu);
             
        
        obj.setIdgcatalogo(Integer.parseInt(plu));
        obj = catalogoFacade.BuscaCatalogo(obj);
               
        System.out.println("Buscando Catalogo:"+plu);
        setEstado(0);
        
        if (obj!=null)
        {
            setSessionValue("catalogo",plu);
            setIdcatalogo(Integer.parseInt(plu));    
            //setEstado(1);
            setNombre(obj.getNombreGasto());
            setIdempresa(1);

            if (obj.getFovial().equals("S"))
            //if (obj.getFovial() == "S")
            { setFovial(true);}
            else
              {setFovial(false);}
        
        }
    
    }

     public void grabar()
    {    
        
        Empresas objemp= new Empresas();
        objemp.setPluempresa(1); 
        objemp = empresaFacade.BuscarEmpresa(objemp);
        
        CchCatalogo obj = new CchCatalogo();
        
        obj.setIdempresa(objemp);
        obj.setIdgcatalogo(getIdcatalogo());
        
        if (getFovial()==true)
        {
            obj.setFovial("S");
        }
        else
        {
           obj.setFovial("N");
        }
        obj.setNombreGasto(getNombre());
        
        
                
        if (!(obj.getIdgcatalogo()==null))
        {
           //obj.setPlucliente(getPlucliente()); 
           catalogoFacade.ModificarCatalogo(obj);
           System.out.println("modificando el catalogo:"+getNombre());
        }
         else
        {
          catalogoFacade.GuardarCatalogo(obj);
          System.out.println("Inserto Nuevo catalogo:"+getNombre());
        }
        listCatalogo = catalogoFacade.BuscaCatalogoTodos();
                       
    }
     
      public void eliminarCatalogo(ActionEvent e)
    {
        CchCatalogo obj = new CchCatalogo();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        obj.setIdgcatalogo(Integer.parseInt(plu));
        catalogoFacade.EliminarCatalogo(obj);
        
    
    }
      
       JasperPrint jasperPrint;
    public void initDS() throws JRException, FileNotFoundException, UnsupportedEncodingException{
       
        listCatalogo = catalogoFacade.BuscaCatalogoTodos();
               
        InputStream inputStream = new FileInputStream ("../CajaChica/listCatalogo.jrxml");
        Reader reader = new InputStreamReader(inputStream,"UTF-8");
 
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
 
    
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(listCatalogo);
        
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        //jasperPrint=JasperFillManager.fillReport("../CajaChica/gastos2.jasper", new HashMap(),beanCollectionDataSource);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), beanCollectionDataSource);        
       
    }



  public void aExcel() throws JRException, IOException{
        
       initDS();

       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=catalogo.xlsx"); 
       ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
       
       
   }
  
    
    
}
