/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reportes;


import Beans.DetalleGastos;
import dto.DetGastosDTO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.naming.NamingException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 *
 * @author trompudo
 */
public class ReportesCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    private InputStream inputStream;
    private final Date lastModified;
    private String resourceName;
    /* LOGGER */
   // static final Logger logger = Logger.getLogger(ReportesCaja.class.getName());
    private static final Logger logger = Logger.getLogger(ReportesCaja.class);
    
    private JasperReport reporte;
    private JasperPrint jasperPrint;
    private DetalleGastos bean;

    public ReportesCaja(DetalleGastos controller) {
        logger.info(Level.INFO+" [ReportesCaja][Constructor]");
        lastModified = new Date();
        System.out.println("Controler: " + controller);
        bean = controller;
    }

    /**
     *
     * @return @throws IOException
     */
    public InputStream open() throws IOException {
        try {
            System.out.print("InputStream ReportesCaja " + bean);
            inputStream = new ByteArrayInputStream(generarReporteGastos(bean.getListatodo()));
        } catch (JRException e) {
            logger.info(Level.SEVERE + " JRException{0}", e);
        } catch (IOException ioe) {
            logger.info(Level.SEVERE + " IOException{0}", ioe);
        } catch (NamingException sx) {
            logger.info(Level.SEVERE + " NamingException{0}", sx);
        }

        return inputStream;
    }

    public String calculateDigest() {
        return resourceName;
    }

    public Date lastModified() {
        return lastModified;
    }

    public void withOptions() throws IOException {
          jasperPrint.setName("generarReporteGastos" + new Date().getTime() + ".pdf");   
        //setFileName("generarReporteGastos" + new Date().getTime() + ".pdf");
    }

    public byte[] generarReporteGastos(List<DetGastosDTO> lista) throws JRException, IOException, NamingException {

      /*  Date hoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Map<String, String> map = new HashMap<String, String>();
        map.put("FECHA_REPORTE", formato.format(hoy));
        */
        
         Map<String, Object> map = new HashMap<String, Object>();
        map.put("FECHA", lista);
        
        //REPORTE
        reporte = (JasperReport) JRLoader.loadObject("../CajaChica/rptGastos.jasper");
        //jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(lista));
        //jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
         jasperPrint = JasperFillManager.fillReport(reporte, map, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
        
        
        

    }

    

}