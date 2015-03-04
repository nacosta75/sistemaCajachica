/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Tools.AppConstants;
import Tools.JSFUtil;
import Tools.ServiceLocator;
import Tools.ServiceLocatorException;
import beans.DetGastosBeanLocal;
import beans.LiquidacionBeanLocal;
import control.AbstractController;
import dto.DetGastosDTO;
import dto.LiquidacionDTO;
import entidades.CchLiquidacion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

/**
 *
 * @author trompudo
 */
public class Liquidaciones extends AbstractController {
    
    private String prueba;
    private List<LiquidacionDTO> listatodo;
    private List<DetGastosDTO> listaGastos;
    private LiquidacionDTO buscarObj;
    private int selected;
    private String selectedit;
    private String selectdelete;
    private long valorEdit;
    private boolean popupadd;
    private boolean popupedit;
    private boolean popupdelete;
    private boolean sussesfuladd;
    
    private LiquidacionBeanLocal ejbLiquidacion;
    private LiquidacionDTO liquidAdd;
    private LiquidacionDTO liquidEdit;
    private BigDecimal caja,efectivo,faltante;
    private Integer cdocs,idliq ; 
    private String observaciones;
    @EJB
    private DetGastosBeanLocal ejbDetGasto;
   
    //@EJB
    //private DetGastosBeanLocal detGastos;
    
    
    private static final Logger logger = Logger.getLogger(LoginBean.class);

    
     public Liquidaciones() {
         String user = (String)JSFUtil.getRequestParameter("usuario");
         JSFUtil.setSessionAttribute("usuario", user);    
    }

    public List<DetGastosDTO> getListaGastos() {
        return listaGastos;
    }

    public void setListaGastos(List<DetGastosDTO> listaGastos) {
        this.listaGastos = listaGastos;
    }
    
    public BigDecimal getCaja() {
        return caja;
    }

    public void setCaja(BigDecimal caja) {
        this.caja = caja;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public BigDecimal getFaltante() {
        return faltante;
    }

    public void setFaltante(BigDecimal faltante) {
        this.faltante = faltante;
    }

    public Integer getCdocs() {
        return cdocs;
    }

    public void setCdocs(Integer cdocs) {
        this.cdocs = cdocs;
    }

    public Integer getIdliq() {
        return idliq;
    }

    public void setIdliq(Integer idliq) {
        this.idliq = idliq;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public LiquidacionDTO getLiquidAdd() {
        return liquidAdd;
    }

    public void setLiquidAdd(LiquidacionDTO liquidAdd) {
        this.liquidAdd = liquidAdd;
    }

    public LiquidacionDTO getLiquidEdit() {
        return liquidEdit;
    }

    public void setLiquidEdit(LiquidacionDTO liquidEdit) {
        this.liquidEdit = liquidEdit;
    }
    
    
    
    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public List<LiquidacionDTO> getListatodo() {
        return listatodo;
    }

    public void setListatodo(List<LiquidacionDTO> listatodo) {
 
        this.listatodo = listatodo;
    }

    public LiquidacionDTO getBuscarObj() {
        return buscarObj;
    }

    public void setBuscarObj(LiquidacionDTO buscarObj) {
        this.buscarObj = buscarObj;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getSelectedit() {
        return selectedit;
    }

    public void setSelectedit(String selectedit) {
        this.selectedit = selectedit;
    }

    public String getSelectdelete() {
        return selectdelete;
    }

    public void setSelectdelete(String selectdelete) {
        this.selectdelete = selectdelete;
    }

    public long getValorEdit() {
        return valorEdit;
    }

    public void setValorEdit(long valorEdit) {
        this.valorEdit = valorEdit;
    }

    public boolean isPopupadd() {
        return popupadd;
    }

    public void setPopupadd(boolean popupadd) {
        this.popupadd = popupadd;
    }

    public boolean isPopupedit() {
        return popupedit;
    }

    public void setPopupedit(boolean popupedit) {
        this.popupedit = popupedit;
    }

    public boolean isPopupdelete() {
        return popupdelete;
    }

    public void setPopupdelete(boolean popupdelete) {
        this.popupdelete = popupdelete;
    }

    public boolean isSussesfuladd() {
        return sussesfuladd;
    }

    public void setSussesfuladd(boolean sussesfuladd) {
        this.sussesfuladd = sussesfuladd;
    }
    
    JasperPrint jasperPrint;
    public void initDS(Integer idliq) throws JRException, FileNotFoundException, UnsupportedEncodingException{
       
        listaGastos = ejbDetGasto.findbyLiq(idliq);
        
        InputStream inputStream = new FileInputStream ("../CajaChica/libroCajaChica.jrxml");
        Reader reader = new InputStreamReader(inputStream,"UTF-8");
 
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
         
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(listaGastos);
        
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), beanCollectionDataSource);        
       
    }

  public void aExcel(ActionEvent e) throws JRException, IOException{
        
      String plu = e.getComponent().getAttributes().get("plu").toString();
      
      initDS(Integer.parseInt(plu));

       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=repLiquid.xlsx"); 
       ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
       
       
   }
  
  
    public void initDSGastos(Integer idliq) throws JRException, FileNotFoundException, UnsupportedEncodingException{
       
        listaGastos = ejbDetGasto.findbyLiq(idliq);
        
        InputStream inputStream = new FileInputStream ("../CajaChica/listaDocs.jrxml");
        Reader reader = new InputStreamReader(inputStream,"UTF-8");
 
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
         
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(listaGastos);
        
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), beanCollectionDataSource);        
       
    }

  public void aExcelGastos(ActionEvent e) throws JRException, IOException{
        
      String plu = e.getComponent().getAttributes().get("plu").toString();
      
      initDS(Integer.parseInt(plu));

       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=repGastos.xlsx"); 
       ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
       
       
   }
  
  public void initDSPartida(Integer idliq, String observacion) throws JRException, FileNotFoundException, UnsupportedEncodingException{
       
   
        listaGastos = ejbDetGasto.partidabyLiq(idliq,observacion);
        
        InputStream inputStream = new FileInputStream ("../CajaChica/docPartida.jrxml");
        Reader reader = new InputStreamReader(inputStream,"UTF-8");
 
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
         
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(listaGastos);
        
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), beanCollectionDataSource);        
       
    }



  public void aExcelPartida(ActionEvent e) throws JRException, IOException{
        
      String plu = e.getComponent().getAttributes().get("plu").toString();
      
     CchLiquidacion obj= new CchLiquidacion();
     obj = ejbLiquidacion.BuscarLiquid(Integer.parseInt(plu));
      
      initDSPartida(obj.getIdliq(),obj.getObservaciones());

       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=repPartida.xlsx"); 
       ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);

       docxExporter.exportReport();
       
       
   }
    
  
    public void calcularEdt()
    {
      try
        {
          
        BigDecimal total=new BigDecimal("0");
        int tdocs = 0;
        BigDecimal tfaltante = new BigDecimal("0");
        //MathContext mc = new MathContext(2); // 4 precision
        
         if (liquidEdit != null) 
         {
                    
             for (DetGastosDTO objC:ejbDetGasto.findbyLiq(liquidEdit.getIdliq()))
                {   
                      
                      if (objC.getProcesado().equals("N")) 
                      {        
                         System.out.println(total);
                         total = total.add(objC.getTotal());
                         tdocs++;
                      }
                                                                    
                }
                         
              liquidEdit.setCantDocs(tdocs);
              liquidEdit.setTotalDocs(total);
              liquidEdit.setFaltante(total);
             
          }
         
        }
        catch (Exception e)
        {
            logger.error("Error cal calcular Liquid:"+e);
            System.err.println("Error al calcular Liquid:"+e);
            
        } 
     
    }
    
 
    public void Guardar(ActionEvent event) {
              
        int plu= 0;
        //plu = Integer.parseInt(e.getComponent().getAttributes().get("plu").toString());   
        //System.out.println(JSFUtil.getSessionValue("usuario").toString());
        if (!(JSFUtil.getSessionValue("usuario").toString().equals(null)))
        plu = Integer.parseInt(JSFUtil.getSessionValue("usuario").toString());   
        
        //System.out.println("PLUSUARIO:"+plu);
        
        int plusuc= 0;
        if (!(JSFUtil.getSessionValue("sucursal").toString().equals(null)))
        plusuc = Integer.parseInt(JSFUtil.getSessionValue("sucursal").toString());

               
         if (liquidAdd != null) 
         {    
                       
            
            liquidAdd.setIdSucursal(plusuc);
            liquidAdd.setIdusuario(plu);
            liquidAdd.setPost("N");
           
            liquidAdd = ejbLiquidacion.create(liquidAdd);

            if (liquidAdd!= null) {
                //System.out.println("entro a guardar");
                listatodo.add(liquidAdd);
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setDetail("Creado Satisfactoriamente");
                context.addMessage("Ok", message);
                sussesfuladd = true;
            }
        }
        System.out.println("nuevo");
        //gastos();
        liquidAdd = new LiquidacionDTO();
        popupadd = !popupadd;
    }
    
    
    public void editar(ActionEvent event) {
        
        String user = (String)JSFUtil.getSessionAttributeValue("usuario");
        
        if (liquidEdit!= null && liquidEdit.getIdliq()!= 0) {
            //detGastoedit.setFechaModificacion(new Date()); 
            //detGastoedit.setUsuarioModifica(user != null ? user.toUpperCase() : user);
            
            ejbLiquidacion.edit(liquidEdit);
            
            if (liquidEdit != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setDetail("Actualizado Satisfactoriamente");
                context.addMessage("Ok", message);
                sussesfuladd = true;
            }
        }
        listatodo = ejbLiquidacion.findAll();
        HttpServletResponse r = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            r.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/app/ListLiquidacion.jsf" + user);
        } catch (IOException ex) {
           
           logger.error(DetalleGastos.class.getName() ,  ex); 
        }
        
        System.out.println("editar");
        //gastos();
        liquidEdit = new LiquidacionDTO();
        popupedit = !popupedit;
    }
    
    
    public void procesar(ActionEvent event) {
        
        String user = (String)JSFUtil.getSessionAttributeValue("usuario");
        
        System.out.println("idliq:"+liquidEdit.getIdliq());
        if (liquidEdit!= null && liquidEdit.getIdliq()!= 0) {
          
            liquidEdit.setPost("S");
            
            ejbLiquidacion.edit(liquidEdit);
            
            if (liquidEdit != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setDetail("Procesado Satisfactoriamente");
                context.addMessage("Ok", message);
                sussesfuladd = true;
            }
        }
        listatodo = ejbLiquidacion.findAll();
        HttpServletResponse r = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            r.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/app/ListLiquidacion.jsf" + user);
        } catch (IOException ex) {
           
           logger.error(DetalleGastos.class.getName() ,  ex); 
        }
        
        System.out.println("Procesado");
        //gastos();
       liquidEdit = new LiquidacionDTO();
        popupedit = !popupedit;
    }
    
     public void borrar(ActionEvent event) {
        //System.out.println("borrar:"+selectdelete);
        if (!selectdelete.equals("") && selectdelete != null) {
            for (LiquidacionDTO temp : listatodo) {
                //if (temp.getId()==equals(selectdelete)) {
                if (temp.getIdliq()==Integer.parseInt(selectdelete))
                 {
                    boolean status = ejbLiquidacion.delete(temp);
                    if (status) {
                        listatodo.remove(temp);
                        FacesContext context = FacesContext.getCurrentInstance();
                        FacesMessage message = new FacesMessage();
                        message.setSeverity(FacesMessage.SEVERITY_INFO);
                        message.setDetail("Eliminado Satisfactoriamente");
                        context.addMessage("Ok", message);
                        break;
                    }
                }
            }
        }
        //gastos();
        popupdelete = false;
    }
     
     public void calcularAdd(ActionEvent e)
    {
      try
        {
          
        BigDecimal total=new BigDecimal("0");
        int tdocs = 0;
        BigDecimal tfaltante = new BigDecimal("0");
        //MathContext mc = new MathContext(2); // 4 precision
        BigDecimal efectivo = new BigDecimal("0");
        
        
        String m= null;
        m = e.getComponent().getAttributes().get("monto").toString();
        BigDecimal tcaja = new BigDecimal(m);
        setCaja(tcaja);
        
         if (liquidAdd != null) 
         {                    
             for (DetGastosDTO objC:ejbDetGasto.findbyLiq(liquidEdit.getIdliq()))
                {                         
                      if (objC.getProcesado().equals("N")) 
                      {        
                         total = total.add(objC.getTotal());
                         tdocs++;
                      }
                                                                    
                }
                         
              liquidAdd.setCantDocs(tdocs);
              liquidAdd.setTotalDocs(total);
              //System.out.println(liquidAdd.getTotalEfec());
              //System.out.println(getCaja());
              liquidAdd.setTotalEfec(getCaja().subtract(total));
              /*
              if (liquidAdd.getTotalEfec()!=null )
              {
                   System.out.println(total);
                  System.out.println(getCaja());
                  
                liquidAdd.setTotalEfec(getCaja().subtract(total));
                 
                 
              }
              */
              //tfaltante = tcaja - total - liquidAdd.getTotalEfec();
              liquidAdd.setFaltante(tfaltante);
             
          }
         
        }
        catch (Exception ex)
        {
            logger.error("Error al Calcular Liquid:"+ex);
            System.err.println("Error al Calcular Liquid:"+ex);
            
        } 
     
    }
    
     
     public void popupaddon(ActionEvent event) {
        popupadd = true;
    }

    public void popupaddoff(ActionEvent event) {
        popupadd = false;
    }

    public void popupediton(ActionEvent event) {
        popupedit = true;
    }

    public void popupeditoff(ActionEvent event) {
        popupedit = false;
    }

    public void popupdeleteon(ActionEvent event) {
        popupdelete = true;
    }

    public void popupdeleteoff(ActionEvent event) {
        popupdelete = false;
    }
    
 
    
    @PostConstruct
    public void init() {
        try {
            prueba = "ESTA ES MI PRUEBA";
            popupadd = false;
            popupedit = false;
            popupdelete = false;
            System.out.println("\n\n\n paso init \n\n");
            Logger.getLogger(DetalleGastos.class.getName()).log(Level.INFO,">>>>>> paso init");
            listatodo = new ArrayList<LiquidacionDTO>();
            ejbLiquidacion = ServiceLocator.getInstance().getService(AppConstants.JNDI_INTERNAL_DETALLE_LIQ);
            selected = 0;
            liquidAdd= new LiquidacionDTO();
            liquidEdit = new LiquidacionDTO();
            buscarObj = new LiquidacionDTO();
            sussesfuladd = false;
            listatodo = ejbLiquidacion.findAll();
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(DetalleGastos.class.getName()).log(Level.INFO, null, ex);
        }
      
   }
 
     public void reiniciarFomulario() {
         

    }
    
    
    
    
}
