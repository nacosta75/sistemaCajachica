/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.InputStreamReader;
import java.io.Reader;
import org.xml.sax.InputSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import Tools.AppConstants;
import Tools.JSFUtil;
import Tools.ServiceLocator;
import Tools.ServiceLocatorException;
import control.AbstractController;
import dto.DetGastosDTO;
import entidades.CchCatalogo;
import facade.CatalogoFacade;
import facade.DetCatalogoFacade;
import beans.DetGastosBeanLocal;
import entidades.Tipodoc;
import facade.TipodocFacade;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.apache.log4j.Logger;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;  


/**
 *
 * @author trompudo
 */
public class DetalleGastos extends AbstractController {
    
    private String prueba;
    private List<DetGastosDTO> listatodo;
    private List<DetGastosDTO> listatodoLiq;
    private DetGastosDTO buscarObj;
    private int selected;
    private String selectedit;
    private String selectdelete;
    private long valorEdit;
    private boolean popupadd;
    private boolean popupedit;
    private boolean popupdelete;
    private boolean sussesfuladd;
    private boolean fovial;
    private boolean popupliquid;
    private BigDecimal TGastos;
    private boolean verIva;

    public boolean isVerIva() {
        return verIva;
    }

    public List<DetGastosDTO> getListatodoLiq() {
        return listatodoLiq;
    }

    public void setListatodoLiq(List<DetGastosDTO> listatodoLiq) {
        this.listatodoLiq = listatodoLiq;
    }
    
    

    public void setVerIva(boolean verIva) {
        this.verIva = verIva;
    }
    
    
    

    public BigDecimal getTGastos() {
        return TGastos;
    }

    public void setTGastos(BigDecimal TGastos) {
        this.TGastos = TGastos;
    }
    

   // private static org.apache.log4j.Logger logger = LoggerUtil.getAppLogger(DetalleGastos.class).getLog4jLogger();
    //@EJB     
    private DetGastosBeanLocal ejbDetGasto;
    private DetGastosDTO detGastoadd;
    private DetGastosDTO detGastoedit;
    @EJB
    private DetCatalogoFacade detCatalogo;
    @EJB
    private CatalogoFacade catalogo;
    @EJB 
    private TipodocFacade tipodocFacade;

    private static final Logger logger = Logger.getLogger(LoginBean.class);

    public boolean isPopupliquid() {
        return popupliquid;
    }

    public void setPopupliquid(boolean popupliquid) {
        this.popupliquid = popupliquid;
    }
        
    public void esFovialEdt(ActionEvent event)
    {
        // int plu = Integer.parseInt(event.getComponent().getAttributes().get("id").toString());
      
         int plu = detGastoedit.getIdGasto();
           
         try
         {
                 
         System.out.println("ID:"+plu);
         setFovial(false);
         
         CchCatalogo obj= new CchCatalogo();
         obj.setIdgcatalogo(plu);
         obj = catalogo.BuscaCatalogo(obj);
         if (obj!=null){
         //System.out.println(obj.getFovial());
         if (obj.getFovial().equals("S"))
            { setFovial(true);
            
            }
         else
         {
           setFovial(false);
           detGastoedit.setFovial(new BigDecimal("0"));
         }
           
         
         }
         
         }
         catch (Exception e)
        {
            System.out.println("Det Error al buscar Catalogo:"+e);
            logger.error("Det Error al buscar Catalogo",e); 
        }   
         
         
    }

    public void verIVAadd()
    {
       Tipodoc obj= new Tipodoc();
       try
       {
                obj.setIdtipodoc(detGastoadd.getIdtipoDoc());
                obj = tipodocFacade.BuscaTipodoc(obj);
                if (obj!=null)
                {
                    
                    System.out.print("es iva add:"+obj.getIva());
                       if (obj.getIva().equals("S"))
                          { 
                              setVerIva(true);
                          }
                         else
                         {
                              setVerIva(false);
                              detGastoadd.setIva(new BigDecimal("0"));
                          }
                 }
         }
         catch (Exception e)
        {
            System.out.println("Det Error al buscar TipoDoc:"+e);
            logger.error("Error al buscar TipoDoc"); 
        } 
    }
    
    public void verIVAedt()
    {
       Tipodoc obj= new Tipodoc();
       try
       {
               //System.out.print(detGastoedit.getIdtipoDoc());
               
                obj.setIdtipodoc(detGastoedit.getIdtipoDoc());
                obj = tipodocFacade.BuscaTipodoc(obj);
                if (obj!=null)
                {
                    
                    System.out.print("es iva edt:"+obj.getIva());
                    
                       if (obj.getIva().equals("S"))
                          { 
                              setVerIva(true);
                          }
                         else
                         {
                              setVerIva(false);
                              detGastoedit.setIva(new BigDecimal("0"));
                          }
                 }
         }
         catch (Exception e)
        {
            System.out.println("Det Error al buscar TipoDoc Edit:"+e);
            logger.error("Error al buscar TipoDoc Edit"); 
        } 
    }
    
    
    public void esFovialadd()
    {
         int plu = detGastoadd.getIdGasto();
         
         try
         {
                 
         //System.out.println("ID:"+plu);
         setFovial(false);
         
         CchCatalogo obj= new CchCatalogo();
         obj.setIdgcatalogo(plu);
         obj = catalogo.BuscaCatalogo(obj);
         if (obj!=null)
          {
              if (obj.getFovial().equals("S"))
                   { 
                       setFovial(true);
                   }          
                else
                {
                  setFovial(false);
                  detGastoadd.setFovial(new BigDecimal("0"));
                }
           }
         }
         catch (Exception e)
        {
            System.out.println("Det Error al buscar Catalogo:"+e);
            logger.error("Error al buscar Catalogo"); 
        }   
    }
    
    
    JasperPrint jasperPrint;
    public void initDS() throws JRException, FileNotFoundException, UnsupportedEncodingException{
       
        listatodo = ejbDetGasto.findAll();
        
        InputStream inputStream = new FileInputStream ("../CajaChica/listaDocs.jrxml");
        Reader reader = new InputStreamReader(inputStream,"UTF-8");
 
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
 
       //  saxParser.parse(is, handler);
 
        
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(listatodo);
        
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        //jasperPrint=JasperFillManager.fillReport("../CajaChica/gastos2.jasper", new HashMap(),beanCollectionDataSource);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), beanCollectionDataSource);        
       
    }



  public void aExcel() throws JRException, IOException{
        
      initDS();

       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx"); 
       ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
       
       
   }
  
  public void pruebaExcel()
  {
  
    try
    {
            String filename="data.xls" ;
            HSSFWorkbook hwb=new HSSFWorkbook();
            HSSFSheet sheet =  hwb.createSheet("new sheet");

            HSSFRow rowhead=   sheet.createRow((short)0);
            rowhead.createCell((short) 0).setCellValue("id");
            rowhead.createCell((short) 1).setCellValue("cuenta");
            rowhead.createCell((short) 2).setCellValue("concepto");
            rowhead.createCell((short) 3).setCellValue("debe");
            rowhead.createCell((short) 4).setCellValue("haber");


            int i=1;

            for (DetGastosDTO temp : listatodo) 
            {

                    HSSFRow row=   sheet.createRow((short)i);
                    row.createCell((short) 0).setCellValue(Integer.toString(i));
                    row.createCell((short) 1).setCellValue(temp.getCtaContable());
                    row.createCell((short) 2).setCellValue(temp.getConcepto()+"-documento No."+temp.getNumDoc()+", Proveedor:"+temp.getProvNombre());
                    row.createCell((short) 3).setCellValue(temp.getDebe().doubleValue());
                    row.createCell((short) 4).setCellValue(temp.getHaber().doubleValue());
                    i++;

            }

            FileOutputStream fileOut =  new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

    } catch ( Exception ex ) {
        System.out.println(ex);

    }
  
}
  
  
  
  public void initDS2() throws IOException, JRException {
    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listatodo);
    //String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/web/ireport/monthlyReport.jasper");
    jasperPrint = JasperFillManager.fillReport("../CajaChica/listaDocs.jasper", new HashMap(), beanCollectionDataSource);
    
}


public void pdf() throws IOException, JRException {
    initDS2();
    HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
    httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
    ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
    FacesContext.getCurrentInstance().responseComplete();
}
    
  
    public DetGastosDTO getDetGastoadd() {
        return detGastoadd;
    }

    public void setDetGastoadd(DetGastosDTO detGastoadd) {
        this.detGastoadd = detGastoadd;
        
    }

    public DetGastosDTO getDetGastoedit() {
        
        
        return detGastoedit;
        
        
    }

    public void setDetGastoedit(DetGastosDTO detGastoedit) {
        
        this.detGastoedit = detGastoedit;
    }

    public boolean isFovial() {
        return fovial;
    }

    public void setFovial(boolean fovial) {
        this.fovial = fovial;
    }

        
    
    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public List<DetGastosDTO > getListatodo() {
          
        return listatodo;
    }

    public void setListatodo(List<DetGastosDTO > listatodo) {
        this.listatodo = listatodo;
    }

    public DetGastosDTO  getBuscarObj() {
        return buscarObj;
    }

    public void setBuscarObj(DetGastosDTO buscarObj) {
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

   
    
    /////////// GET & SET /////////

 /*   public void rowSelectionListener(RowSelectorEvent event) {
        System.out.println("el seleccionado es  " + selected);
    }
*/
    public void popupaddon(ActionEvent event) {
        popupadd = true;
        detGastoadd = new DetGastosDTO();
        
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
        detGastoedit =  null;
    }
    
    
     public void popupliquidoff(ActionEvent event) {
        popupliquid = false;
        detGastoedit =  null;
    }
     
    public void popupliquidon(ActionEvent event) {
        popupliquid = true;
    }

    public void agregar(ActionEvent event) {
        
        int plu= 0;
        if (!(JSFUtil.getSessionValue("usuario").toString().equals(null)))
        plu = Integer.parseInt(JSFUtil.getSessionValue("usuario").toString());   
        
        int plusuc= 0;
        if (!(JSFUtil.getSessionValue("sucursal").toString().equals(null)))
        plusuc = Integer.parseInt(JSFUtil.getSessionValue("sucursal").toString());
        
        if (!detGastoadd.getNumDoc().equals("") && detGastoadd.getNumDoc() != null) {
            
            detGastoadd.setFechaIng(new Date());  
            detGastoadd.setIdsucursal(plusuc);
            detGastoadd.setIdusuario(plu);
            detGastoadd.setProcesado("N");
            detGastoadd.setCtaContable(detCatalogo.BuscaCuentaContable(detGastoadd.getIdCanal(), detGastoadd.getIdsucursal(), detGastoadd.getIdGasto()));
            detGastoadd = ejbDetGasto.create(detGastoadd);
            
            if (detGastoadd!= null) {
                listatodo.add(detGastoadd);
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setDetail("Creado Satisfactoriamente");
                context.addMessage("Ok", message);
                sussesfuladd = true;
            }
        }
        detGastoadd = new DetGastosDTO();
        Rgastos();
        popupadd = !popupadd;
    }

    public void editar(ActionEvent event) {
        
       // String user = (String)JSFUtil.getSessionAttributeValue("usuario");
        System.out.println("iddetgasto"+detGastoedit.getIddetgasto());
        if (detGastoedit != null && detGastoedit.getIddetgasto()!=0) {
            
            System.out.println("TOTAL:"+detGastoedit.getTotal());
            System.out.println("proveedor:"+detGastoedit.getProvNombre());
            ejbDetGasto.edit(detGastoedit);
            if (detGastoedit != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setDetail("Actualizado Satisfactoriamente");
                context.addMessage("Ok", message);
                sussesfuladd = true;
            }
        }  
        listatodo = ejbDetGasto.findAll();
        Rgastos();
      // NO ES NECESARIO
       /* HttpServletResponse r = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            r.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/app/ListGastos.jsf");            
        } catch (IOException ex) {
           
           logger.error(DetalleGastos.class.getName() ,  ex); 
        }
        
        */
        detGastoedit=new DetGastosDTO();        
        popupedit = !popupedit;
        
        //RequestContext context = RequestContext.getCurrentInstance();
        //context.update(":form:dataGastos");
     
    }
    
    public void procesar() {
          
         for (DetGastosDTO objC:ejbDetGasto.findAll())
         {  
               if ((objC != null) && (objC.getIddetgasto()!=0) && (objC.getProcesado().equals("N"))) 
                {
            
                    objC.setProcesado("S");
                    ejbDetGasto.edit(objC); 
                  
                }
        }
    }
    
    public void liquidar(ActionEvent event) {
        
        String user = (String)JSFUtil.getSessionValue("usuario");
        String plusuc = (String)JSFUtil.getSessionValue("sucursal");
       
  
        if (user != null && plusuc !=null) {
            
            
            String liq=ejbDetGasto.createLiq(user,plusuc);
            
           
            if (detGastoedit != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setDetail("Liquidacion Creadaa");
                context.addMessage("Ok", message);
                
            }
        }
        
         
        listatodo = ejbDetGasto.findAll();
       /* 
        HttpServletResponse r = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            r.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/app/ListGastos.jsf");
        } catch (IOException ex) {
           
           logger.error(DetalleGastos.class.getName() ,  ex); 
        }
        */
    }
    
    public void totales()
    {
        try
        {
            
        //java.math.BigDecimal suma=unCentavo.add(unCentavo).add(unCentavo).add(unCentavo).add(unCentavo).add(unCentavo);
        BigDecimal total=new BigDecimal("0");
        BigDecimal iva= new BigDecimal("0.13");
        BigDecimal tiva = new BigDecimal("0");
        BigDecimal tfovial = new BigDecimal("0");
        MathContext mc = new MathContext(2); // 4 precision
        
         if (detGastoedit != null) 
         {
             
             System.out.print(isVerIva());
             
             if (isVerIva())
             {
                 tiva = detGastoedit.getGravado().multiply(iva);
             }
             else
             {
                 tiva = new BigDecimal("0");
             }
             
             if (detGastoedit.getFovial()!=null)
             {
                 tfovial = tfovial.add(detGastoedit.getFovial());
             
             }   
             
             total = total.add(detGastoedit.getGravado()).add(tiva).add(tfovial);
             detGastoedit.setIva(tiva);
             detGastoedit.setTotal(total);
             detGastoedit.setFovial(tfovial);
             
         }
         
        }
        catch (Exception e)
        {
            logger.error("Error:"+e);
            System.err.println("Error:"+e);
            
        }
         
    }

    
     public void totalesadd()
    {
        try
        {
            
        //java.math.BigDecimal suma=unCentavo.add(unCentavo).add(unCentavo).add(unCentavo).add(unCentavo).add(unCentavo);
        BigDecimal total=new BigDecimal("0");
        BigDecimal iva= new BigDecimal("0.13");
        BigDecimal tiva = new BigDecimal("0");
        BigDecimal tfovial = new BigDecimal("0");
        MathContext mc = new MathContext(2); // 4 precision
        
         if (detGastoadd != null) 
         {
             
             System.out.print(isVerIva());
             
             if (isVerIva())
             {
                 tiva = detGastoadd.getGravado().multiply(iva);
             }
             else
             {
                 tiva = new BigDecimal("0");
             }
             
             
             if (detGastoadd.getFovial()!=null)
             {
                 tfovial = tfovial.add(detGastoadd.getFovial());
             
             }   
             
             total = total.add(detGastoadd.getGravado()).add(tiva).add(tfovial);
             detGastoadd.setIva(tiva);
             detGastoadd.setTotal(total);
             detGastoadd.setFovial(tfovial);
             //detGastoedit.setTotal(detGastoedit.getGravado()+detGastoedit.getIva()+detGastoedit.getFovial());
         }
         
        }
        catch (Exception e)
        {
            logger.error("Error:"+e);
            System.err.println("Error:"+e);
            
        }
         
    }
    

     
    public void borrar(ActionEvent event) {
        //System.out.println("borrar:"+selectdelete);
        if (!selectdelete.equals("") && selectdelete != null) {
            for (DetGastosDTO temp : listatodo) {
                //if (temp.getId()==equals(selectdelete)) {
                if (temp.getIddetgasto()==Integer.parseInt(selectdelete))
                 {
                    boolean status = ejbDetGasto.delete(temp);
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
        Rgastos();
        popupdelete = false;
    }

    public void find(ActionEvent event) {
        /*if (buscarObj.getCodPlan() != 0 || buscarObj.getCodProdFFE() != 0) {
            
         } else {
         FacesContext context = FacesContext.getCurrentInstance();
         FacesMessage message = new FacesMessage();
         message.setSeverity(FacesMessage.SEVERITY_ERROR);
         message.setDetail("No hay parametros cargados");
         context.addMessage("Ok", message);
         }*/
    }

    @PostConstruct
    public void init() {
        try {
            prueba = "ESTA ES MI PRUEBA";
            popupadd = false;
            popupedit = false;
            popupdelete = false;
            System.out.println("\n\n\n paso init \n\n");
            //Logger.getLogger(DetalleGastos.class.getName()).log(Level.INFO,">>>>>> paso init");
            listatodo = new ArrayList<DetGastosDTO>();
            ejbDetGasto = ServiceLocator.getInstance().getService(AppConstants.JNDI_INTERNAL_DETALLE_GASTOS);
            selected = 0;
            detGastoadd = new DetGastosDTO();
            detGastoedit = new DetGastosDTO();
            buscarObj = new DetGastosDTO();
            sussesfuladd = false;
            listatodo = ejbDetGasto.findAll();
            
            
            Rgastos();
            
        } catch (ServiceLocatorException ex) {
           // Logger.getLogger(DetalleGastos.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Error init:"+ex);
        }
      
   }
 
     public void reiniciarFomulario() {
    }

   public void Rgastos()
    {
      try
        {
          
       // BigDecimal total=new BigDecimal("0");
        BigDecimal gastos=new BigDecimal("0");
        //BigDecimal saldo=new BigDecimal("0");
        
        
       // lsaldo = new ArrayList<DetSaldoDTO>();
        
              //List<DetGastosDTO> retorno = new ArrayList<DetGastosDTO>();
              
             for (DetGastosDTO objC:ejbDetGasto.findAll())
                {                         
                  
                      if (objC.getProcesado().equals("N")) 
                      {  
                         
                         gastos = gastos.add(objC.getTotal());
                         
                      }                                                                    
                }
             
               this.setTGastos(gastos);
               
               
        }
        catch (Exception e)
        {
            logger.error("Error cal calcular Gastos:"+e);
            System.err.println("Error al calcular Gastos:"+e);            
        }      
    }     
    
}
