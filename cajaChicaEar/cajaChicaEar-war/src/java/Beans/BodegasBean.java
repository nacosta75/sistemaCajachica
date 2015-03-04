/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import dto.BodegaResumenDTO;
import entidades.Bodegas;
import facade.BodegasFacade;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

/**
 *
 * @author trompudo
 */
public class BodegasBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(LoginBean.class);
    
 
    
    @EJB
    private BodegasFacade bodegasFacade;
    
    private List<BodegaResumenDTO> listaGastos;
    
    private List<Bodegas> listSucursal;
    private ArrayList<SelectItem> bodegaLista;
    private int idBodega,idEmpresa;
    private BigDecimal cajachica;
    private String nombre,codigo,activa,local,ip,
            dir,ciudad,telefono,cuenta;
    
    

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    
    
    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public BigDecimal getCajachica() {
        return cajachica;
    }

    public void setCajachica(BigDecimal cajachica) {
        this.cajachica = cajachica;
    }
    
    
     JasperPrint jasperPrint;
    public void initDS() throws JRException, FileNotFoundException, UnsupportedEncodingException{
       
        listaGastos = bodegasFacade.ReporteCaja();
        
        InputStream inputStream = new FileInputStream ("../CajaChica/ResumenCajasBodega.jrxml");
        Reader reader = new InputStreamReader(inputStream,"UTF-8");
 
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
         
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(listaGastos);
        
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), beanCollectionDataSource);        
       
    }

  public void aExcel(ActionEvent e) throws JRException, IOException{
      
      initDS();

       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=ResumenGastosBodega.xlsx"); 
       ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
       
       
   }
    
        

    public ArrayList<SelectItem> getBodegaLista() {
        
        bodegaLista = new ArrayList<SelectItem>();
        
        for (Bodegas obj:bodegasFacade.BuscaSucursalTodos() )
        {
          bodegaLista.add(new SelectItem(obj.getPlubodega(),obj.getNombre()));
        }
        
        return bodegaLista;
    }

    public void setBodegaLista(ArrayList<SelectItem> bodegaLista) {
        this.bodegaLista = bodegaLista;
    }
    
    
   
    public BodegasBean() {
    }

    public List<Bodegas> getListSucursal() {
        
        if (listSucursal == null)
        {
         listSucursal = bodegasFacade.BuscaSucursalTodos();
        }
        
        return listSucursal;
    }

    public void setListSucursal(List<Bodegas> listSucursal) {
        this.listSucursal = listSucursal;
    }
    
      public void grabar()
    {    
        
        
        Bodegas obj = new Bodegas();
      
        obj.setPlubodega(getIdBodega());
        
      /*  
        
        obj.setNombre(getNombre());
        obj.setPluempresas(1);
        obj.setCiudad(getCiudad());
        obj.setActiva(getActiva());
        obj.setLocal(getLocal());
        obj.setDireccion1(getDir());
        obj.setTelefono(getTelefono());
        obj.setCodigo(getCodigo());
        obj.setDirIp(getIp());
        */
        
        obj = bodegasFacade.BuscaBodega(obj);  
       
        
        System.out.println("codigo:"+obj.getCodigo());
        
        
        if (!(obj.getPlubodega()==null))
        {
           obj.setCajaChica(getCajachica()); 
           obj.setCtacontable(getCuenta());
           
           System.out.println("modificando bodega:"+getIdBodega());
           
           bodegasFacade.ModificarBodega(obj);
           logger.info("modificando Bodega:"+getIdBodega());
        }
         else
        {
         
          System.out.println("sin plubodega ");
          logger.info("sin Bodega:");
        }
        
        listSucursal = bodegasFacade.BuscaSucursalTodos();
                       
    }
    
       public void buscarBodega(ActionEvent e)
    {
        Bodegas obj = new Bodegas();
        String plu= null;
        plu = e.getComponent().getAttributes().get("plu").toString();
        
        obj.setPlubodega(Integer.parseInt(plu));
        obj = bodegasFacade.BuscaBodega(obj);
  
        System.out.println("Buscando bodega:"+plu);
        logger.info("Buscanco Bodega:"+plu);
        
        if (obj!=null)
        {
        
            setIdBodega(Integer.parseInt(plu));
            setNombre(obj.getNombre());
            setCajachica(obj.getCajaChica());
            setIdEmpresa(obj.getPluempresas());
            setCodigo(obj.getCodigo());
            setActiva(obj.getActiva());
            setLocal(obj.getLocal());
            setIp(obj.getDirIp());
            setDir(obj.getDireccion1());
            setCiudad(obj.getCiudad());
            setTelefono(obj.getTelefono());
            setCuenta(obj.getCtacontable()); 
         
            System.out.println("Encontro CODIGO:"+getCodigo());
            
        }
        else
        {
            setIdBodega(0);
            setNombre("");
            setCajachica(new BigDecimal("0"));
         
            
            System.out.println("No existe Bodega:"+plu);
            logger.info("No existe Bodega:"+plu);
         }
    
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    


       
    
}
