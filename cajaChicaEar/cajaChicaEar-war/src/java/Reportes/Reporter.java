/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reportes;
import Beans.DetalleGastos;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import dto.DetGastosDTO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Reporter {
  
    private DetalleGastos bean;
    
    @SuppressWarnings("unchecked")
public void imprimir() throws Exception {
InputStream inputStream = new FileInputStream ("reports/rptGastos.jrxml");
 
//DetgastosBean dataBeanMaker = new DetgastosBean();
ArrayList<DetGastosDTO> dataBeanList = (ArrayList<DetGastosDTO>) bean.getListatodo();
 
JRBeanCollectionDataSource beanColDataSource = new
JRBeanCollectionDataSource(dataBeanList);
 
Map parameters = new HashMap();
 
JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//JasperExportManager.exportReportToPdfFile(jasperPrint, "c:/reports/test_jasper.pdf");
JasperExportManager.exportReportToPdfFile(jasperPrint, "//home//trompudo//Documentos/test_jasper.pdf");
}

}
