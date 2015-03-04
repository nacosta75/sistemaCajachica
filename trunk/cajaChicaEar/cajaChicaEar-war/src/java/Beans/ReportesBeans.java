/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import beans.DetGastosBeanLocal;
import dto.DetGastosDTO;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author trompudo
 */
@ManagedBean(name="reportes")
@SessionScoped
public class ReportesBeans{
 
    @EJB
    private DetGastosBeanLocal ejbDetGasto;
  
   private List<DetGastosDTO> listGastos;

    public List<DetGastosDTO> getListGastos() {
        
        if (listGastos==null)
        {
           listGastos = ejbDetGasto.findAll();
        }
        
        return listGastos;
    }

    public void setListGastos(List<DetGastosDTO> listGastos) {
        this.listGastos = listGastos;
    }
   

    JasperPrint jasperPrint;
    public void init() throws JRException{
        
        listGastos = ejbDetGasto.findAll();
        
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(listGastos);
        jasperPrint=JasperFillManager.fillReport("../CajaChica/listaDocs.jasper", new HashMap(),beanCollectionDataSource);
        
    }
    
   public void PDF(ActionEvent actionEvent) throws JRException, IOException{
       init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
       
       
   }
    public void DOCX(ActionEvent actionEvent) throws JRException, IOException{
        init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.docx");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JRDocxExporter docxExporter=new JRDocxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }
     public void XLSX() throws JRException, IOException{
        init();
       
        HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       
       
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }
    
}
